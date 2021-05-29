package find_ui.service.matching;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;

import find_ui.controller.matching.request.MatchingForm;
import find_ui.controller.matching.response.MatchingResult;
import find_ui.controller.matching.response.MatchingResult.CommonInfo;
import find_ui.entity.matching.Matching;
import find_ui.entity.matching.QMatching;
import find_ui.entity.user.User;
import find_ui.enums.MatchingStatus;
import find_ui.enums.response.ReturnCode;
import find_ui.exception.CustomException;
import find_ui.repository.MatchingRepository;
import find_ui.repository.UserImageRepository;
import find_ui.repository.UserRepository;
import find_ui.utils.AgeUtil;
import find_ui.utils.BasicInfoUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MatchingService {

    private final JPAQueryFactory jpaQueryFactory;
    private final UserRepository userRepository;
    private final UserImageRepository userImageRepository;
    private final MatchingRepository matchingRepository;

    public MatchingResult getMatchingInfo(Long userSequence) {

        Optional<User> userOptional = userRepository.findById(userSequence);
        if (!userOptional.isPresent()) {
            throw new CustomException(ReturnCode.UNKNOWN_ERROR);
        }

        User user = userOptional.get();
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(QMatching.matching.toUser.eq(user));
        List<Matching> allReceivedList = jpaQueryFactory.selectFrom(QMatching.matching)
                                                        .where(builder)
                                                        .fetch();
        List<CommonInfo> receivedFeeling = getFilteredCommonInfoList(MatchingStatus.FEELING,
                                                                     allReceivedList, false);
        List<CommonInfo> receivedDibsList = getFilteredCommonInfoList(MatchingStatus.DIBS,
                                                                      allReceivedList, false);

        builder = new BooleanBuilder();
        builder.and(QMatching.matching.fromUser.eq(user));
        List<Matching> allSendList = jpaQueryFactory.selectFrom(QMatching.matching)
                                                    .where(builder)
                                                    .fetch();

        List<CommonInfo> sendFeeling = getFilteredCommonInfoList(MatchingStatus.FEELING,
                                                                 allSendList, true);
        List<CommonInfo> sendDibsList = getFilteredCommonInfoList(MatchingStatus.DIBS,
                                                                  allSendList, true);

        builder = new BooleanBuilder();
        builder.and(QMatching.matching.fromUser.eq(user));
        builder.and(QMatching.matching.matchingStatus.eq(MatchingStatus.MATCHING));
        List<Matching> matchingList = jpaQueryFactory.selectFrom(QMatching.matching)
                                              .where(builder)
                                              .fetch();

        List<CommonInfo> connectedList = getConnectedList(matchingList);

        return MatchingResult.builder()
                             .connected(connectedList)
                             .receivedFeeling(receivedFeeling)
                             .sendFeeling(sendFeeling)
                             .receivedDibs(receivedDibsList)
                             .sendDibs(sendDibsList)
                             .build();

    }

    private List<CommonInfo> getConnectedList(List<Matching> matchingList) {
        return matchingList.stream()
                           .map(i -> {
                               User user = i.getToUser();
                               return CommonInfo.of(user,
                                             getImageUrl(user),
                                             AgeUtil.getAge(user.getBirthDayYmdt()),
                                             BasicInfoUtil.getBasicInfo(user.getUserDetailInfo().getBasicInfo()).getJob(),
                                             "DM DM DM");
                           })
                           .collect(Collectors.toList());
    }

    private List<CommonInfo> getListWithoutIntersection(List<CommonInfo> targetList,
                                                        List<CommonInfo> connectedList) {
        return targetList.stream()
                          .filter(i -> connectedList.stream()
                                                    .anyMatch(j -> i.getUserSequence() != j.getUserSequence()))
                          .collect(Collectors.toList());
    }

    private List<CommonInfo> getFilteredCommonInfoList(MatchingStatus matchingStatus,
                                                       List<Matching> matchingList,
                                                       boolean isSendRequest) {
        return matchingList.stream()
                           .filter(i -> i.getMatchingStatus() == matchingStatus)
                           .map(i -> getUser(i, isSendRequest))
                           .map(i -> CommonInfo.of(i,
                                                   getImageUrl(i),
                                                   AgeUtil.getAge(i.getBirthDayYmdt()),
                                                   BasicInfoUtil
                                                           .getBasicInfo(i.getUserDetailInfo().getBasicInfo())
                                                           .getJob(),
                                                   "DM DM DM"))
                           .collect(Collectors.toList());
    }

    private String getImageUrl(User i) {
        return userImageRepository.findById(i.getUserProfileImageSequence()).get().getImageUrl();
    }

    private User getUser(Matching matching, boolean isSendRequest) {
        if (isSendRequest) {
            return matching.getToUser();
        }
        return matching.getFromUser();
    }

    @Transactional
    public void requestMatching(MatchingForm matchingForm) {

        Long fromUserSequence = matchingForm.getFromUserSequence();
        Long toUserSequence = matchingForm.getToUserSequence();
        MatchingStatus matchingStatus = matchingForm.getMatchingStatus();

        User fromUser = userRepository.findById(fromUserSequence).get();
        User toUser = userRepository.findById(toUserSequence).get();

        switch (matchingStatus) {
            case MATCHING:
                disconnectedMatchingData(MatchingStatus.FEELING, MatchingStatus.DISCONNECTED_RECEIVED_FEELING,
                                         fromUser, toUser);
                disconnectedMatchingData(MatchingStatus.FEELING, MatchingStatus.DISCONNECTED_RECEIVED_FEELING,
                                         toUser, fromUser);
                addNewMatchingData(MatchingStatus.MATCHING_COMPLETE, fromUser, toUser);
                addNewMatchingData(MatchingStatus.MATCHING_COMPLETE, toUser, fromUser);
                break;
            case FEELING:
                boolean isMatchingPossible = checkMatchingPossible(MatchingStatus.FEELING, fromUser, toUser);
                if (isMatchingPossible) {
                    addNewMatchingData(MatchingStatus.MATCHING, fromUser, toUser);
                } else {
                    addNewMatchingData(MatchingStatus.FEELING, fromUser, toUser);
                }
                break;
            case DIBS:
                addNewMatchingData(MatchingStatus.DIBS, fromUser, toUser);
                break;
            case DISCONNECTED_MATCHING:
                disconnectedMatchingData(MatchingStatus.MATCHING, MatchingStatus.DISCONNECTED_MATCHING,
                                         fromUser, toUser);
                disconnectedMatchingData(MatchingStatus.MATCHING, MatchingStatus.DISCONNECTED_MATCHING,
                                         toUser, fromUser);
                break;
            case DISCONNECTED_RECEIVED_FEELING:
                disconnectedMatchingData(MatchingStatus.FEELING, MatchingStatus.DISCONNECTED_RECEIVED_FEELING,
                                         fromUser, toUser);
                disconnectedMatchingData(MatchingStatus.FEELING, MatchingStatus.DISCONNECTED_RECEIVED_FEELING,
                                         toUser, fromUser);
                break;
            case DISCONNECTED_SEND_FEELING:
                disconnectedMatchingData(MatchingStatus.FEELING, MatchingStatus.DISCONNECTED_SEND_FEELING,
                                         fromUser, toUser);
                break;
            case DISCONNECTED_DIBS:
                disconnectedMatchingData(MatchingStatus.DIBS, MatchingStatus.DISCONNECTED_DIBS,
                                         fromUser, toUser);
                disconnectedMatchingData(MatchingStatus.DIBS, MatchingStatus.DISCONNECTED_DIBS,
                                         toUser, fromUser);
                break;
            default:
                log.info("Requested matchingStatus(={}) is not appropriate.", matchingStatus);
                break;
        }
    }

    private boolean checkMatchingPossible(MatchingStatus matchingStatus, User fromUser, User toUser) {
        BooleanBuilder builder = getBooleanBuilder(matchingStatus, toUser, fromUser);
        long executeCount = executeQueryFactory(MatchingStatus.MATCHING, builder);
        return executeCount != 0 ? true : false;
    }

    private void disconnectedMatchingData(MatchingStatus fromMatchingStatus, MatchingStatus toMatchingStatus,
                                          User fromUser, User toUser) {
        BooleanBuilder builder = getBooleanBuilder(fromMatchingStatus, fromUser, toUser);
        executeQueryFactory(toMatchingStatus, builder);
    }

    public long executeQueryFactory(MatchingStatus toMatchingStatus, BooleanBuilder builder) {
        return jpaQueryFactory.update(QMatching.matching)
                              .where(builder)
                              .set(QMatching.matching.matchingStatus, toMatchingStatus)
                              .execute();
    }

    private BooleanBuilder getBooleanBuilder(MatchingStatus fromMatchingStatus, User fromUser, User toUser) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(QMatching.matching.fromUser.eq(fromUser));
        builder.and(QMatching.matching.toUser.eq(toUser));
        builder.and(QMatching.matching.matchingStatus.eq(fromMatchingStatus));
        return builder;
    }

    private void addNewMatchingData(MatchingStatus matchingStatus, User fromUser, User toUser) {
        Matching matching = Matching.builder()
                                 .fromUser(fromUser)
                                 .toUser(toUser)
                                 .matchingStatus(matchingStatus)
                                 .build();
        matchingRepository.save(matching);
    }
}

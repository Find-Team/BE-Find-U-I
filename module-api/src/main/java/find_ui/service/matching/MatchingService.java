package find_ui.service.matching;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;

import find_ui.controller.matching.response.MatchingResult;
import find_ui.controller.matching.response.MatchingResult.CommonInfo;
import find_ui.entity.matching.Matching;
import find_ui.entity.matching.QMatching;
import find_ui.entity.user.User;
import find_ui.enums.MatchingStatus;
import find_ui.enums.response.ReturnCode;
import find_ui.exception.CustomException;
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

        List<CommonInfo> connectedList = getConnectedList(receivedFeeling, sendFeeling);
        receivedFeeling = getListWithoutIntersection(receivedFeeling, connectedList);
        sendFeeling = getListWithoutIntersection(sendFeeling, connectedList);

        return MatchingResult.builder()
                             .connected(connectedList)
                             .receivedFeeling(receivedFeeling)
                             .sendFeeling(sendFeeling)
                             .receivedDibs(receivedDibsList)
                             .sendDibs(sendDibsList)
                             .build();

    }

    private List<CommonInfo> getConnectedList(List<CommonInfo> receivedFeeling, List<CommonInfo> sendFeeling) {
        return receivedFeeling.stream()
                              .filter(i -> sendFeeling.stream()
                                                           .anyMatch(j -> i.getUserSequence() == j.getUserSequence()))
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
}

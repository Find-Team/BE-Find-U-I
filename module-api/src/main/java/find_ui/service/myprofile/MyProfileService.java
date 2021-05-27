package find_ui.service.myprofile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import find_ui.entity.user.UserDetailInfo;
import find_ui.entity.user.UserImage;
import find_ui.enums.ImageType;
import find_ui.repository.UserDetailInfoRepository;
import find_ui.repository.UserImageRepository;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import find_ui.controller.myprofile.response.AccountInfo;
import find_ui.utils.BasicInfo;
import find_ui.controller.myprofile.response.MyProfileResult;
import find_ui.entity.user.User;
import find_ui.enums.response.ReturnCode;
import find_ui.exception.CustomException;
import find_ui.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MyProfileService {
    private final int MAX_UPLOADABLE_IMAGE_COUNT = 6;

    private final UserRepository userRepository;
    private final UserDetailInfoRepository userDetailInfoRepository;
    private final UserImageRepository userImageRepository;
    private ObjectMapper objectMapper = new ObjectMapper();

    public MyProfileResult getMyProfile(Long userSequence) {
        log.info("userSequence : ", userSequence);

        Optional<User> userOptional = userRepository.findById(userSequence);

        if (!userOptional.isPresent()) {
            throw new CustomException(ReturnCode.UNKNOWN_ERROR);
        }

        User user = userOptional.get();
        List<String> userImageUrlList = user.getUserImage().stream()
                                            .map(e -> e.getImageUrl())
                                            .collect(Collectors.toList());

        AccountInfo accountInfo = getAccountInfo(user);
        BasicInfo basicInfo = null;
        try {
            basicInfo = objectMapper.readValue(user.getUserDetailInfo().getBasicInfo(), BasicInfo.class);
        } catch (JsonProcessingException e) {
            log.info("Parsing Error", e);
        }

        return MyProfileResult.builder()
                              .userSequence(userSequence)
                              .isIdentityVerification(user.getIdentityVerificationYmdt() != null)
                              .introduction(user.getUserDetailInfo().getIntroduction())
                              .imageUrlList(userImageUrlList)
                              .accountInfo(accountInfo)
                              .basicInfo(basicInfo)
                              .build();
    }

    private AccountInfo getAccountInfo(User user) {
        return AccountInfo.builder()
                          .nickName(user.getNickName())
                          .birthDay(user.getBirthDayYmdt())
                          .sexType(user.getSexType())
                          .location(user.getLocation())
                          .build();
    }


    public User createMyProfile(List<String> imageUrlList, String introduction, AccountInfo accountInfo, String basicInfo, boolean isIdentityVerification) {
        if (!isIdentityVerification) {
            throw new CustomException(ReturnCode.NOT_IDENTITY_VERIFIED_USER);
        }

        if (imageUrlList.size() > MAX_UPLOADABLE_IMAGE_COUNT) {
            throw new CustomException(ReturnCode.EXCEED_UPLOAD_IMAGE_COUNT);
        }

        User user = User.builder()
                .birthDayYmdt(accountInfo.getBirthDay())
                .location(accountInfo.getLocation())
                .nickName(accountInfo.getNickName())
                .sexType(accountInfo.getSexType())
                .identityVerificationYmdt(LocalDateTime.now())
                .build();
        userRepository.save(user);

        List<UserImage> userImageList = new ArrayList<>();

        for (int i = 0; i < imageUrlList.size(); i++) {
            UserImage userImage = UserImage.builder()
                    .user(user)
                    .imageUrl(imageUrlList.get(i))
                    .imageType(i == 0 ? ImageType.USER_MAIN_PROFILE : ImageType.USER_PROFILE)
                    .build();
            userImageRepository.save(userImage);
            userImageList.add(userImage);
        }

        UserDetailInfo userDetailInfo = UserDetailInfo.builder()
                .user(user)
                .introduction(introduction)
                .mannerScore(0L)
                .basicInfo(basicInfo)
                .build();
        userDetailInfoRepository.save(userDetailInfo);

        return user;
    }
}


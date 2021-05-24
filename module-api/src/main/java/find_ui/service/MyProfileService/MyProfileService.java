package find_ui.service.MyProfileService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import find_ui.controller.myprofile.response.AccountInfo;
import find_ui.controller.myprofile.response.BasicInfo;
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

    private final UserRepository userRepository;
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
                          .birthDay(user.getBirthDayYmdt().toLocalDate())
                          .sexType(user.getSexType())
                          .location(user.getLocation())
                          .build();
    }
}


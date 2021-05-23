package find_ui.service.MyProfileService;

import static find_ui.entity.user.QUser.user;
import static find_ui.entity.user.QUserDetailInfo.userDetailInfo;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import find_ui.controller.myprofile.request.CreateUserProfileRequest;
import find_ui.entity.user.UserDetailInfo;
import find_ui.entity.user.UserImage;
import find_ui.external.s3.S3Client;
import org.springframework.stereotype.Service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;

import find_ui.controller.myprofile.response.MyProfileResult;
import find_ui.entity.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
@RequiredArgsConstructor
public class MyProfileService {

    private final JPAQueryFactory jpaQueryFactory;
    private final S3Client s3Client;

    public MyProfileResult getMyProfile(Long userSequence) {
        log.info("userSequence : ", userSequence);
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(user.eq(userDetailInfo.user));

        // use QueryDSL
        final List<User> myProfile = jpaQueryFactory.selectFrom(user)
                                                    .where(builder)
                                                    .fetch();

        return null;
    }

    public MyProfileResult createMyProfile(CreateUserProfileRequest createUserProfileRequest) {
        User user = new User();
        user.setBirthDayYmdt(createUserProfileRequest.getBirthdayYmdt());
        user.setIdentityVerificationYmdt(LocalDateTime.now());
        user.setLocation(createUserProfileRequest.getLocation());
        user.setNickName(createUserProfileRequest.getNickName());
        user.setSex(createUserProfileRequest.getSex());

        UserDetailInfo userDetailInfo = new UserDetailInfo();
        userDetailInfo.setIntroduction(createUserProfileRequest.getIntroduction());
        userDetailInfo.setMannerScore(0L);
        userDetailInfo.setJob(createUserProfileRequest.getJob());
        userDetailInfo.setCompany(createUserProfileRequest.getCompany());
        userDetailInfo.setEducation(createUserProfileRequest.getEducation());
        userDetailInfo.setMbti(createUserProfileRequest.getMbti());
        userDetailInfo.setHeight(createUserProfileRequest.getHeight());
        userDetailInfo.setBodyType(createUserProfileRequest.getHeight());
        userDetailInfo.setSmoking(createUserProfileRequest.getSmoking());
        userDetailInfo.setReligion(createUserProfileRequest.getReligion());
        userDetailInfo.setMarriage(createUserProfileRequest.getMarriage());
        userDetailInfo.setDrinking(createUserProfileRequest.getDrinking());
        userDetailInfo.setUser(user);

        UserImage userImage = new UserImage();
        // 이미지 업로드

        // create

        return null;
    }

    public String uploadProfileImages(MultipartFile multipartFile) throws IOException {
        return s3Client.upload(multipartFile, "profile");
    }
}


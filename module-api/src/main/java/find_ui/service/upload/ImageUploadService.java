package find_ui.service.upload;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;

import find_ui.entity.user.QUserImage;
import find_ui.entity.user.User;
import find_ui.entity.user.UserImage;
import find_ui.enums.ImageType;
import find_ui.enums.response.ReturnCode;
import find_ui.exception.CustomException;
import find_ui.repository.UserImageRepository;
import find_ui.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImageUploadService {
    private final int MAX_UPLOADABLE_IMAGE_COUNT = 6;

    private final UserRepository userRepository;
    private final UserImageRepository userImageRepository;
    private final JPAQueryFactory jpaQueryFactory;

    public UserImage uploadImage(Long userSequence, String imageUrl, ImageType imageType) {

        Optional<User> userOptional = userRepository.findById(userSequence);
        if (!userOptional.isPresent()) {
            throw new CustomException(ReturnCode.UNKNOWN_ERROR);
        }

        User user = userOptional.get();
        validateUserImageListCondition(user);

        UserImage userImage = UserImage.builder()
                                       .imageUrl(imageUrl)
                                       .imageType(imageType)
                                       .user(user)
                                       .build();
        UserImage savedUserImage = userImageRepository.save(userImage);
        return savedUserImage;
    }

    private void validateUserImageListCondition(User user) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(QUserImage.userImage.user.eq(user));
        List<UserImage> userImageList = jpaQueryFactory.selectFrom(QUserImage.userImage)
                                                       .where(builder)
                                                       .fetch();
        if (userImageList.size() > MAX_UPLOADABLE_IMAGE_COUNT) {
            throw new CustomException(ReturnCode.EXCEED_UPLOAD_IMAGE_COUNT);
        }
    }

}

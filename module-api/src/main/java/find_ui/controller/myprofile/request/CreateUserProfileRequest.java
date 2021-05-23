package find_ui.controller.myprofile.request;

import find_ui.enums.Sex;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateUserProfileRequest {
    private MultipartFile mainProfileImage;
    private MultipartFile[] profileImages;

    private String introduction;

    private String nickName;
    private LocalDateTime birthdayYmdt;
    private Sex sex;
    private String location;

    // Todo: enum
    private String job;
    private String company;
    private String education;
    private String mbti;
    private String height;
    private String bodyType;
    private String smoking;
    private String religion;
    private String marriage;
    private String drinking;
}

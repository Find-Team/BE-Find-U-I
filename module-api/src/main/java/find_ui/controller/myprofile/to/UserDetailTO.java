package find_ui.controller.myprofile.to;

import find_ui.enums.Sex;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDetailTO {
    private String introduction;

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

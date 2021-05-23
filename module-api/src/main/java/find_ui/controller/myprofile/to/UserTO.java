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
public class UserTO {
    private String nickName;
    private LocalDateTime birthdayYmdt;
    private Sex sex;
    private String location;
}

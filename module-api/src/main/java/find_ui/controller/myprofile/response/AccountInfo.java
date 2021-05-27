package find_ui.controller.myprofile.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import find_ui.enums.SexType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@JsonInclude(Include.NON_NULL)
public class AccountInfo {
    private String nickName;
    private LocalDateTime birthDay;
    private SexType sexType;
    private String location;
}

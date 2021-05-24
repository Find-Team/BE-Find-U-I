
package find_ui.controller.myprofile.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import find_ui.enums.YesOrNoType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonInclude(Include.NON_NULL)
public class MyProfileResult {
    private Long userSequence;
    private boolean isIdentityVerification;
    private String introduction;
    private List<String> imageUrlList;
    private AccountInfo accountInfo;
    private BasicInfo basicInfo;
}

package find_ui.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import find_ui.enums.response.ReturnCode;
import find_ui.enums.response.ReturnEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@JsonInclude(Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class CommonResponse<T> {
    private String returnCode;
    private String returnMessage;
    private T info;

    public CommonResponse(ReturnEnum returnEnum) {
        this(returnEnum, null);
    }

    public CommonResponse(T info) {
        this(ReturnCode.SUCCESS, info);
    }

    public CommonResponse(ReturnEnum returnEnum, T info) {
        setReturnEnum(returnEnum);
        setInfo(info);
    }

    public void setReturnEnum(ReturnEnum returnEnum) {
        setReturnCode(returnEnum.getReturnCode());
        setReturnMessage(returnEnum.getReturnMessage());
    }
}

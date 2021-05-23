package find_ui.response;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import find_ui.enums.response.ReturnCode;
import find_ui.enums.response.ReturnEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(Include.NON_NULL)
@NoArgsConstructor
public class CommonResponse<T> {
    private String returnCode;
    private String returnMessage;
    private Map<String, String> errorDetailMap;
    private Map<String, Object> errorInfo;
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

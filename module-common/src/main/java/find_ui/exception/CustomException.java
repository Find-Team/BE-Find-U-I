
package find_ui.exception;

import find_ui.enums.response.ReturnEnum;
import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {
    private String returnCode;
    private String returnMessage;

    public CustomException() {
        super();
    }

    public CustomException(String returnCode, String returnMessage) {
        super(returnMessage);
        setReturnCode(returnCode);
        setReturnMessage(returnMessage);
    }

    public CustomException(String returnCode, String returnMessage, Throwable cause) {
        super(returnMessage, cause);
        setReturnCode(returnCode);
        setReturnMessage(returnMessage);
    }

    public CustomException(ReturnEnum returnEnum) {
        super(returnEnum.getReturnMessage());
        setReturnCodeEnum(returnEnum);
    }

    public CustomException(Throwable cause) {
        super(cause);
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public void setReturnCodeEnum(ReturnEnum returnCodeEnum) {
        setReturnCode(returnCodeEnum.getReturnCode());
        setReturnMessage(returnCodeEnum.getReturnMessage());
    }

    public String getReturnMessage() {
        return returnMessage;
    }

    public void setReturnMessage(String returnMessage) {
        this.returnMessage = returnMessage;
    }

}

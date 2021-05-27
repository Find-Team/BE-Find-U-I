package find_ui.enums.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ReturnCode implements CodeEnum, TextEnum, ReturnEnum {

    SUCCESS("0000", "Success."),

    NOT_IDENTITY_VERIFIED_USER("2000", "The user's identity has not been verified."),

    EXCEED_UPLOAD_IMAGE_COUNT("4000", "The number of images that can be uploaded has been exceeded."),

    UNKNOWN_ERROR("9999", "Unable to process your request. Please try again later.");


    private String code;
    private String text;

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public String getReturnCode() {
        return getCode();
    }

    @Override
    public String getReturnMessage() {
        return getText();
    }
}

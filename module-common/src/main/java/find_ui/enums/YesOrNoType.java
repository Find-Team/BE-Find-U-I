package find_ui.enums;

import find_ui.enums.response.CodeEnum;
import find_ui.enums.response.TextEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum YesOrNoType implements CodeEnum, TextEnum {
    Y("Y", "Yes"),
    N("N", "No");

    private String code;
    private String text;

    public static YesOrNoType of(boolean value) {
        return value ? Y : N;
    }

	@Override
	public String getCode() {
		return code;
	}

	@Override
	public String getText() {
		return text;
	}

}

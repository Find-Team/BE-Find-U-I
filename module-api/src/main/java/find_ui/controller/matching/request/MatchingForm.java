package find_ui.controller.matching.request;

import find_ui.enums.MatchingStatus;
import lombok.Getter;

@Getter
public class MatchingForm {
    private Long fromUserSequence;
    private MatchingStatus matchingStatus;
    private Long toUserSequence;
}

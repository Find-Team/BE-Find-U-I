package find_ui.controller.values.request;

import java.util.List;

import find_ui.enums.PriorityAnswerType;
import lombok.Getter;

@Getter
public class SavePriorityAnswerForm {
    private Long userSequence;
    private List<Long> questionSequenceList;
    private PriorityAnswerType priorityAnswerType;
}

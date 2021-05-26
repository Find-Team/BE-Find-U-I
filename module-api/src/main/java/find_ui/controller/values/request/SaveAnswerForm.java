package find_ui.controller.values.request;

import java.util.List;
import lombok.Getter;

@Getter
public class SaveAnswerForm {
    private Long userSequence;
    private List<QuestionAndSelectableAnswerVo> questionAndSelectableAnswerList;

    @Getter
    public static class QuestionAndSelectableAnswerVo {
        private Long questionSequence;
        private Long selectableAnswerSequence;
    }
}

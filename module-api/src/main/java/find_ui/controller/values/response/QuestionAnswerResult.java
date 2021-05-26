package find_ui.controller.values.response;

import java.util.ArrayList;
import java.util.List;

import find_ui.enums.ValuesCategoryType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class QuestionAnswerResult {
    private List<QuestionAnswerVo> questionAndOptions;

    @Getter
    @Builder(toBuilder = true)
    public static class QuestionAnswerVo {
        private ValuesCategoryType categoryType;
        private String question;
        private List<String> answerOptions;

        public static QuestionAnswerVo of() {
            return builder()
                    .answerOptions(new ArrayList<>())
                    .build();
        }
    }
}

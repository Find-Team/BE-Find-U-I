package find_ui.controller.values.response;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import find_ui.enums.ValuesCategoryType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class QuestionAnswerResult {
    private List<QuestionAndAnswer> questionAndSelectableAnswerList;

    @Getter
    @Builder(toBuilder = true)
    public static class QuestionAndAnswer {
        private ValuesCategoryType categoryType;
        private List<QuestionAndAnswerVo> questionAndAnswerInfoList;
    }

    @Getter
    @Builder(toBuilder = true)
    @JsonInclude(Include.NON_NULL)
    public static class QuestionAndAnswerVo {
        private Long questionSequence;
        private String question;
        private List<AnswerVo> selectableAnswerList;
        @JsonIgnore
        private ValuesCategoryType categoryType;

        public static QuestionAndAnswerVo of() {
            return builder()
                    .selectableAnswerList(new ArrayList<>())
                    .build();
        }
    }

    @Getter
    @Builder(toBuilder = true)
    @JsonInclude(Include.NON_NULL)
    public static class AnswerVo {
        private Long selectableAnswerSequence;
        private String answerText;
        private boolean isChoiced;
    }
}

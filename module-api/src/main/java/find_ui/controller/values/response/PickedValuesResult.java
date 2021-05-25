package find_ui.controller.values.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@JsonInclude(Include.NON_NULL)
@AllArgsConstructor(staticName = "of")
public class PickedValuesResult {
    private List<ValuesDto> valuesList;

    @Getter
    @Builder
    public static class ValuesDto {
        private Long valuesSequence;
        private String question;
    }
}

package find_ui.controller.values;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import find_ui.controller.values.request.SaveAnswerForm;
import find_ui.controller.values.response.PickedValuesResult;
import find_ui.controller.values.response.QuestionAnswerResult;
import find_ui.enums.ValuesViewType;
import find_ui.response.CommonResponse;
import find_ui.service.values.ValuesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/values")
public class ValuesController {

    private final ValuesService valuesService;

    @Operation(summary = "Get User Values-View Type")
    @ApiResponses({
            @ApiResponse(responseCode = "0000", description = "Success Request",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ValuesViewType.class)))
    })
    @GetMapping("/{userSequence}/view-type")
    public CommonResponse getUserValuesViewType(@PathVariable Long userSequence) {
        ValuesViewType userValuesViewType = valuesService.getUserValuesViewType(userSequence);
        return new CommonResponse(userValuesViewType);
    }

    @Operation(summary = "Get Picked Values")
    @ApiResponses({
            @ApiResponse(responseCode = "0000", description = "Success Request",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PickedValuesResult.class)))
    })
    @GetMapping("/{userSequence}/picked/values")
    public CommonResponse getUserValuesPickedValues(@PathVariable Long userSequence) {
        PickedValuesResult pickedValuesResult = valuesService.getUserPickedValues(userSequence);
        return new CommonResponse(pickedValuesResult);
    }


    @Operation(summary = "Get Values Question")
    @ApiResponses({
            @ApiResponse(responseCode = "0000", description = "Success Request",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = QuestionAnswerResult.class)))
    })
    @GetMapping("/{userSequence}/question")
    public CommonResponse getValuesQuestion(@PathVariable Long userSequence) {
        QuestionAnswerResult questionAnswerResult = valuesService.getValuesQuestion(userSequence);
        return new CommonResponse(questionAnswerResult);
    }


    @Operation(summary = "Save Question Answer")
    @ApiResponses({
            @ApiResponse(responseCode = "0000", description = "Success Request",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CommonResponse.class)))
    })
    @PostMapping("/save/answer")
    public CommonResponse saveAnswer(@RequestBody SaveAnswerForm saveAnswerForm) {
        valuesService.saveAnswer(saveAnswerForm);
        return CommonResponse.success();
    }


}

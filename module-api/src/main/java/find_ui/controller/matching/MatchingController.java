package find_ui.controller.matching;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import find_ui.controller.matching.response.MatchingResult;
import find_ui.response.CommonResponse;
import find_ui.service.matching.MatchingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/match")
public class MatchingController {

    private final MatchingService matchingService;

    @Operation(summary = "Get My Matching Info")
    @ApiResponses({
            @ApiResponse(responseCode = "0000", description = "Success Request",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = MatchingResult.class))),
            @ApiResponse(responseCode = "9999", description = "Fail Request",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CommonResponse.class))),
    })
    @GetMapping("/{userSequence}")
    public CommonResponse<?> getMyMatchingInfo(@PathVariable Long userSequence) {
        MatchingResult matchingResult = matchingService.getMatchingInfo(userSequence);
        return new CommonResponse<>(matchingResult);
    }
}
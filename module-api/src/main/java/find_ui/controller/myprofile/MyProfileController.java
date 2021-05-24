package find_ui.controller.myprofile;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import find_ui.controller.myprofile.response.MyProfileResult;
import find_ui.response.CommonResponse;
import find_ui.service.MyProfileService.MyProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/my-profile")
public class MyProfileController {

    private final MyProfileService myProfileService;

    @Operation(summary = "Get MyProfile")
    @ApiResponses({
            @ApiResponse(responseCode = "0000", description = "Success Request",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = MyProfileResult.class))),
            @ApiResponse(responseCode = "9999", description = "Fail Request",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CommonResponse.class))),
    })
    @GetMapping("/{userSequence}")
    public CommonResponse<?> getUserProfile(@PathVariable Long userSequence) {
        MyProfileResult myProfileResult = myProfileService.getMyProfile(userSequence);
        return new CommonResponse<>(myProfileResult);
    }
}

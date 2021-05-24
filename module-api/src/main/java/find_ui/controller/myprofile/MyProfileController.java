package find_ui.controller.myprofile;

import find_ui.controller.myprofile.response.AccountInfo;
import find_ui.controller.myprofile.response.BasicInfo;
import find_ui.entity.user.User;
import org.springframework.web.bind.annotation.*;

import find_ui.controller.myprofile.response.MyProfileResult;
import find_ui.response.CommonResponse;
import find_ui.service.myprofile.MyProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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

    @PostMapping
    public CommonResponse<?> createUserProfile(@RequestParam("imageUrlList") List<String> imageUrlList,
                                               @RequestParam("introduction") String introduction,
                                               @RequestParam("accountInfo") AccountInfo accountInfo,
                                               @RequestParam("basicInfo") String basicInfo,
                                               @RequestParam(value = "isIdentityVerification", defaultValue = "false") boolean isIdentityVerification) {

        User user = myProfileService.createMyProfile(imageUrlList, introduction, accountInfo, basicInfo, isIdentityVerification);

        return new CommonResponse<>(user);
    }
}

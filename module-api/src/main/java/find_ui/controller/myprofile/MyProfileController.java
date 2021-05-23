package find_ui.controller.myprofile;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import find_ui.controller.myprofile.response.MyProfileResult;
import find_ui.response.CommonResponse;
import find_ui.service.MyProfileService.MyProfileService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/my-profile")
public class MyProfileController {

    private final MyProfileService myProfileService;

    @GetMapping("/{userSequence}")
    public CommonResponse<?> getUserProfile(@PathVariable Long userSequence) {

        myProfileService.getMyProfile(userSequence);

        MyProfileResult myProfileResult = MyProfileResult.builder()
                                                         .name("goodgid")
                                                         .build();
        return new CommonResponse<>(myProfileResult);
    }
}

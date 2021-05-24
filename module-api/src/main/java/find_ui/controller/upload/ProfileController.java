package find_ui.controller.upload;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import find_ui.external.s3.S3Client;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class ProfileController {

    private final S3Client s3Client;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/upload")
    @ResponseBody
    public String upload(@RequestParam("data") MultipartFile multipartFile) throws IOException {
        return s3Client.upload(multipartFile, "profile");
    }
}

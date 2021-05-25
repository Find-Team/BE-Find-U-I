package find_ui.controller.upload;

import java.io.IOException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import find_ui.enums.ImageType;
import find_ui.external.s3.S3Client;
import find_ui.response.CommonResponse;
import find_ui.service.upload.ImageUploadService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ImageUploadController {

    private final S3Client s3Client;
    private final ImageUploadService imageUploadService;

    @ResponseBody
    @PostMapping("/upload")
    public CommonResponse upload(@RequestParam("image") MultipartFile multipartFile) throws IOException {
        String imageUrl = s3Client.upload(multipartFile, "profile");
        return new CommonResponse(imageUrl);
    }

    // remove @RestController
    @Deprecated
    @GetMapping("/")
    public String index() {
        return "index";
    }
}

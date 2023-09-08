package princessandknights.princesshoppang.community.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import princessandknights.princesshoppang.community.config.S3Uploader;
import princessandknights.princesshoppang.community.dto.PostDto;

@RequiredArgsConstructor
@RestController
public class S3Controller {
    private final S3Uploader s3Uploader;

    // 현재 쓰지않음
    @PostMapping("")
    public ResponseEntity<PostDto> updateUserImage(@RequestParam("images") MultipartFile multipartFile) {
        try {
            s3Uploader.uploadFiles(multipartFile, "static");
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}

package princessandknights.princesshoppang.community.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import princessandknights.princesshoppang.community.dto.EmotionDto;
import princessandknights.princesshoppang.community.service.EmotionService;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/community")
public class EmotionController {
    private final EmotionService emotionService;

    @PostMapping("/post/{postId}/emotion")
    public ResponseEntity<?> updateEmotion(@PathVariable Long postId, @RequestBody EmotionDto emotionDto) {
        try {
            emotionService.updateEmotion(postId, emotionDto);
            Map<String, Integer> emotionCounts = emotionService.getEmotionCounts(postId);
            return ResponseEntity.ok(emotionCounts);
        }  catch (Exception e) {
            // 예외 발생 시 클라이언트에게 에러 응답 반환
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("emotion update 중 오류 발생: " + e.getMessage());
        }
    }
}

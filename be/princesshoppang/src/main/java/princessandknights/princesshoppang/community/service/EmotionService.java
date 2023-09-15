package princessandknights.princesshoppang.community.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import princessandknights.princesshoppang.community.dto.EmotionDto;
import princessandknights.princesshoppang.community.entity.Emotion;
import princessandknights.princesshoppang.community.entity.Post;
import princessandknights.princesshoppang.community.repository.EmotionRepository;
import princessandknights.princesshoppang.community.repository.PostRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmotionService {
    private final EmotionRepository emotionRepository;
    private final PostRepository postRepository;

    @Transactional
    public void updateEmotion(Long postId, EmotionDto emotionDto) {
//        Optional<Post> optionalPost = postRepository.findById(postId);
//
//        if (optionalPost.isEmpty()) {
//            throw new RuntimeException("해당 포스트를 찾을 수 없습니다. ID: " + postId);
//        }

        try {
            Long userId = emotionDto.getUserId();
            System.out.println(userId);
            System.out.println(postId);
            Emotion existingEmotion = emotionRepository.findByPost_PostIdAndUser_UserId(postId, emotionDto.getUserId());
            System.out.println(existingEmotion);
            // 감정을 남긴적이 없/있
            if(existingEmotion == null) {
                System.out.println("없음");
                Emotion newEmotion = Emotion.toSaveEntity(postId, emotionDto);
                System.out.println("없음2");
                emotionRepository.save(newEmotion);
                System.out.println("없음3");
            } else {

                Emotion updatedEmotion = Emotion.updateExistingEmotion(existingEmotion, emotionDto);
                emotionRepository.save(updatedEmotion);
            }

        } catch (Exception e) {
            throw new RuntimeException("emotion update 중 오류가 발생하였습니다" + e.getMessage());
        }

    }

    public Map<String, Integer> getEmotionCounts(Long postId) {

        Map<String, Integer> emotionCounts = new HashMap<>();

        emotionCounts.put("good", emotionRepository.countByGoodAndPost_PostId(1, postId));
        emotionCounts.put("agree", emotionRepository.countByAgreeAndPost_PostId(1, postId));
        emotionCounts.put("eh", emotionRepository.countByEhAndPost_PostId(1, postId));
        emotionCounts.put("aww", emotionRepository.countByAwwAndPost_PostId(1, postId));
        emotionCounts.put("annoy", emotionRepository.countByAnnoyAndPost_PostId(1, postId));
        emotionCounts.put("sad", emotionRepository.countBySadAndPost_PostId(1, postId));
        emotionCounts.put("idtlt", emotionRepository.countByIdtltAndPost_PostId(1, postId));

        return emotionCounts;
    }
}

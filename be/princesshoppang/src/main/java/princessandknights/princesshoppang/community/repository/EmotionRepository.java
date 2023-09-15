package princessandknights.princesshoppang.community.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import princessandknights.princesshoppang.community.entity.Emotion;
import princessandknights.princesshoppang.community.entity.Post;

public interface EmotionRepository extends JpaRepository<Emotion, Long> {

    @Query(value = "SELECT COUNT(e) FROM Emotion e WHERE e.post = :post")
    Long countEmotionsByPost(@Param("post") Post post);

    Emotion findByPost_PostIdAndUser_UserId(Long postId, Long userId);


    int countByGoodAndPost_PostId(int good, Long postId);
    int countByAgreeAndPost_PostId(int agree, Long postId);
    int countByEhAndPost_PostId(int eh, Long postId);
    int countByAwwAndPost_PostId(int aww, Long postId);
    int countByAnnoyAndPost_PostId(int annoy, Long postId);
    int countBySadAndPost_PostId(int sad, Long postId);
    int countByIdtltAndPost_PostId(int idtlt, Long postId);



}

package princessandknights.princesshoppang.community.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import princessandknights.princesshoppang.community.entity.Emotion;
import princessandknights.princesshoppang.community.entity.Post;

public interface EmotionRepository extends JpaRepository<Emotion, Long> {


    @Query(value = "SELECT COUNT(e) FROM Emotion e WHERE e.post = :post")
    Long countEmotionsByPost(@Param("post") Post post);

}

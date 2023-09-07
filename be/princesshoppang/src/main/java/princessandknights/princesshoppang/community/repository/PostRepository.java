package princessandknights.princesshoppang.community.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import princessandknights.princesshoppang.community.entity.Post;


public interface PostRepository extends JpaRepository<Post,Long> {

    // update post_table set viewCounts=viewCounts + 1 where id=?
    @Modifying
    @Query(value = "update Post p set p.viewCount=p.viewCount + 1 where p.postId=:id")
    void updateViewCounts(@Param("id") Long id);
}

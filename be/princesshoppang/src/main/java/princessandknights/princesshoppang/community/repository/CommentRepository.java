package princessandknights.princesshoppang.community.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import princessandknights.princesshoppang.community.entity.Comment;


import java.util.List;


public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByPost_PostId(Long postId);
//    findAllByPost_PostIdOrderByCreatedAtDesc
}

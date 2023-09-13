package princessandknights.princesshoppang.community.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import princessandknights.princesshoppang.community.entity.Reply;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

    List<Reply> findAllByComment_CommentId(Long commentId);
}

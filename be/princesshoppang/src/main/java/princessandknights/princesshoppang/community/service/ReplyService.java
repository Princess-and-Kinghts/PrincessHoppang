package princessandknights.princesshoppang.community.service;

import com.amazonaws.services.kms.model.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import princessandknights.princesshoppang.community.dto.CommentDto;
import princessandknights.princesshoppang.community.dto.ReplyDto;
import princessandknights.princesshoppang.community.entity.Comment;
import princessandknights.princesshoppang.community.entity.Post;
import princessandknights.princesshoppang.community.entity.Reply;
import princessandknights.princesshoppang.community.repository.CommentRepository;
import princessandknights.princesshoppang.community.repository.PostRepository;
import princessandknights.princesshoppang.community.repository.ReplyRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;


    public Long createComment(Long postId, Long commentId, ReplyDto replyDto) {
        Optional<Post> optionalPost = postRepository.findById(postId);
        Optional<Comment> optionalComment = commentRepository.findById(commentId);

        if (optionalPost.isEmpty()) {
            throw new RuntimeException("해당 포스트를 찾을 수 없습니다. ID: " + postId);
        }

        if (optionalComment.isEmpty()) {
            throw new RuntimeException("해당 댓글을 찾을 수 없습니다. ID: " + commentId);
        }

        Post post = optionalPost.get();
        Comment comment = optionalComment.get();

        try {
            Reply reply = Reply.toSaveEntity(replyDto, comment, post);
            return replyRepository.save(reply).getReplyId();
        } catch (Exception e) {
            // 예외 발생 시 로깅
            log.error("댓글 생성 중 오류 발생: " + e.getMessage(), e);
            throw new RuntimeException("댓글 생성 중 오류가 발생했습니다.");
        }

    }

    public List<ReplyDto> getAllReplysByCommentId(Long commentId) {
        Optional<Comment> optionalComment = commentRepository.findById(commentId);

        if (optionalComment.isEmpty()) {
            throw new RuntimeException("해당 댓글을 찾을 수 없습니다. ID: " + commentId);
        }

        List<Reply> replyList = replyRepository.findAllByComment_CommentId(commentId);
        List<ReplyDto> replyDtoList = new ArrayList<>();

        for (Reply reply: replyList) {
            ReplyDto replyDto = ReplyDto.toReplyDto(reply);
            replyDtoList.add(replyDto);

        }
        return replyDtoList;
    }

    public ReplyDto updateComment(Long id, ReplyDto replyDto) {

        Optional<Reply> optionalReply = replyRepository.findById(id);

        if (optionalReply.isEmpty()) {
            throw new RuntimeException("해당 답글을 찾을 수 없습니다. ID: " + id);
        }

        Reply reply = optionalReply.get();

        try {
            Reply.toUpdateEntity(reply, replyDto);
            replyRepository.save(reply);

            ReplyDto updatedReplyDto = ReplyDto.toReplyDto(reply);
            return updatedReplyDto;

        } catch (Exception e) {
            throw new RuntimeException("답글 업데이트 중 오류가 발생했습니다.");
        }

    }


    public void deleteComment(Long id) {
        try {
            replyRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            // 해당 ID에 해당하는 댓글을 찾을 수 없는 경우
            throw new NotFoundException("댓글을 찾을 수 없습니다. ID: " + id);
        } catch (DataAccessException e) {
            // 데이터베이스 작업 중 오류가 발생한 경우
            throw new RuntimeException("댓글 삭제 중 오류 발생: " + e.getMessage());
        }
    }


}

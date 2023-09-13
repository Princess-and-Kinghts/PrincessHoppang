package princessandknights.princesshoppang.community.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import princessandknights.princesshoppang.community.dto.CommentDto;
import princessandknights.princesshoppang.community.dto.PostDto;
import princessandknights.princesshoppang.community.dto.ReplyDto;
import princessandknights.princesshoppang.community.entity.Reply;
import princessandknights.princesshoppang.community.service.ReplyService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/community")
public class ReplyController {

    private final ReplyService replyService;

    @PostMapping("/post/{postId}/comment/{commentId}/reply")
    public ResponseEntity<Reply> createReply(@PathVariable Long postId, @PathVariable Long commentId, @RequestBody ReplyDto replyDto) {
        Long createResult = replyService.createComment(postId, commentId, replyDto);
        if(createResult != null) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/post/{postId}/comment/{commentId}/reply")
    public List<ReplyDto> findbyId(@PathVariable Long commentId) {
        List<ReplyDto> replyDtoList = replyService.getAllReplysByCommentId(commentId);
        return replyDtoList;
    }

    @PutMapping("/post/{postId}/comment/{commentId}/reply/{id}")
    public ResponseEntity<?> updateReply(@PathVariable Long postId, @PathVariable Long commentId, @PathVariable Long id, @RequestBody ReplyDto replyDto) {
        try {
            replyService.updateComment(id, replyDto);
            return ResponseEntity.ok(id);
        } catch (Exception e) {
            // 예외 발생 시 클라이언트에게 에러 응답 반환
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("댓글 수정 중 오류 발생: " + e.getMessage());
        }
    }


    @DeleteMapping ("/post/{postId}/comment/{commentId}/reply/{id}")
    public ResponseEntity<?> deleteReply(@PathVariable Long postId, @PathVariable Long commentId, @PathVariable Long id) {
        try {
            replyService.deleteComment(id);
            return ResponseEntity.ok(id);
        } catch (Exception e) {
            // 예외 발생 시 클라이언트에게 에러 응답 반환
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("댓글 삭제 중 오류 발생: " + e.getMessage());
        }
    }

}

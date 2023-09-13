package princessandknights.princesshoppang.community.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import princessandknights.princesshoppang.community.dto.CommentDto;
import princessandknights.princesshoppang.community.entity.Comment;
import princessandknights.princesshoppang.community.service.CommentService;




@RestController
@RequiredArgsConstructor
@RequestMapping("/community")
public class CommentController {
    private final CommentService commentService;


    @PostMapping("/post/{postId}/comment")
    public ResponseEntity<Comment> createComment(@PathVariable Long postId,  @RequestBody CommentDto commentDto) {
        Long createResult = commentService.createComment(postId, commentDto);
        if(createResult != null) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/post/{postId}/comment/{id}")
    public ResponseEntity<Long> update(@PathVariable Long postId, @PathVariable Long id, @RequestBody CommentDto commentDto) {
        commentService.updateComment( id, commentDto);
        return ResponseEntity.ok(id);
    }

    /* DELETE */
    @DeleteMapping("/post/{postId}/comment/{id}")
    public ResponseEntity<Long> delete(@PathVariable Long postId, @PathVariable Long id) {
        commentService.deleteComment( id);
        return ResponseEntity.ok(id);
    }

}

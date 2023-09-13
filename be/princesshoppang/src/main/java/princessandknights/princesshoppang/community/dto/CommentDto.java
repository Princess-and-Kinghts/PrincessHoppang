package princessandknights.princesshoppang.community.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import princessandknights.princesshoppang.community.entity.Comment;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
    // commentID, content, userId, mbti, PostId, anonymousNum, createdAt, reply

    private Long commentId;
    private String content;
    private LocalDateTime createdAt;

    private Long userId;
    private int mbtiId;
    private int anonymous_num;

    public static CommentDto toCommentDto(Comment comment) {

        CommentDto commentDto = new CommentDto();
        commentDto.setCommentId(comment.getCommentId());
        commentDto.setContent(comment.getContent());
        commentDto.setCreatedAt(comment.getCreatedAt());

        commentDto.setMbtiId(comment.getUser().getMbti());
        commentDto.setUserId(comment.getUser().getUserId());
        commentDto.setAnonymous_num(comment.getAnonymousNum());

        return commentDto;
    }

}

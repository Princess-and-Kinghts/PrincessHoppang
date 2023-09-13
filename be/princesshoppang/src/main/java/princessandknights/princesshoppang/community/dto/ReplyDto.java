package princessandknights.princesshoppang.community.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import princessandknights.princesshoppang.community.entity.Reply;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReplyDto {
    private Long replyId;
    private String content;
    private LocalDateTime createdAt;

    private Long userId;
    private int mbtiId;
    private int anonymous_num;

    private Long commentId;


    public static ReplyDto toReplyDto(Reply reply) {

        ReplyDto replyDto = new ReplyDto();
        replyDto.setReplyId(reply.getReplyId());
        replyDto.setContent(reply.getContent());
        replyDto.setCreatedAt(reply.getCreatedAt());
        replyDto.setMbtiId(reply.getUser().getMbti());
        replyDto.setUserId(reply.getUser().getUserId());
        replyDto.setAnonymous_num(reply.getAnonymousNum());
        replyDto.setCommentId(reply.getComment().getCommentId());

        return replyDto;

    }
}

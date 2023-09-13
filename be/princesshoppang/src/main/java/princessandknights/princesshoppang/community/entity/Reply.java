package princessandknights.princesshoppang.community.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.CreationTimestamp;
import princessandknights.princesshoppang.community.dto.ReplyDto;
import princessandknights.princesshoppang.user.entity.User;

import java.time.LocalDateTime;
import java.util.Random;

@Slf4j
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long replyId;

    @Column(nullable = false)
    private String content;
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private int anonymousNum;

    // 작성자 아이디
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    // 게시글 아이디
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    // 댓글 아이디
    @ManyToOne
    @JoinColumn(name="comment_id")
    private Comment comment;


    public static Reply toSaveEntity(ReplyDto replyDto, Comment comment, Post post) {

        User user = User.builder()
                .userId(replyDto.getUserId())
                .build();


        Reply reply = Reply.builder()
                .content(replyDto.getContent())
                .user(user)
                .post(post)
                .comment(comment)
                .build();

        reply.randomAnonymousNumForReply();

        return reply;
    }


    @PrePersist
    public void randomAnonymousNumForReply() {
        String seed = getUser().getUserId().toString() + getPost().getPostId().toString();
        long seedHash = seed.hashCode();
        Random random = new Random(seedHash);
        // (10000~99999)
        this.anonymousNum = random.nextInt(90000) + 10000;
    }

    public static Reply toUpdateEntity(Reply reply, ReplyDto replyDto) {
        reply.setContent(replyDto.getContent());
        return reply;
    }

}

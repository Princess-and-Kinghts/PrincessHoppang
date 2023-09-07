package princessandknights.princesshoppang.community.entity;

import jakarta.persistence.*;
import princessandknights.princesshoppang.user.entity.User;

import java.time.LocalDateTime;

@Entity
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long replyId;

    @JoinColumn(name = "content")
    @Column(nullable = false)
    private String content;
    @JoinColumn(name = "create_at")
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @JoinColumn(name = "anonymous_num")
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



}

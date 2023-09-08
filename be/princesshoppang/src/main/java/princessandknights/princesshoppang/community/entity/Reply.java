package princessandknights.princesshoppang.community.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import princessandknights.princesshoppang.user.entity.User;

import java.time.LocalDateTime;

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



}

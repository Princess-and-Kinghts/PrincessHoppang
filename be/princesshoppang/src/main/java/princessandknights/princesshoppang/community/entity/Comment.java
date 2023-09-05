package princessandknights.princesshoppang.community.entity;

import jakarta.persistence.*;
import princessandknights.princesshoppang.user.entity.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long commentId;

    @Column(nullable = false)
    private String content;
    @Column(nullable = false)
    private LocalDateTime createdAt;
    @Column(nullable = false)
    private Long anonymous_num;

    // 게시글 아이디
    @ManyToOne
    @JoinColumn(name="post_id")
    @Column(nullable = false)
    private Post post;

    // 작성자 아이디
    @ManyToOne
    @JoinColumn(name="user_id")
    @Column(nullable = false)
    private User user;

    @OneToMany(mappedBy = "comment" )
    private List<Reply> reply = new ArrayList<>();


}

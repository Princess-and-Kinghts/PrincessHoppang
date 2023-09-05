package princessandknights.princesshoppang.community.entity;

import jakarta.persistence.*;
import princessandknights.princesshoppang.user.entity.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "post_id")
    private Long postId;

    @JoinColumn(name = "title")
    @Column(nullable = false)
    private String title;

    @JoinColumn(name = "content")
    @Column(nullable = false)
    private String content;

    @JoinColumn(name = "created_at")
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @JoinColumn(name = "anonymous_num")
    @Column(nullable = false)
    private Long anonymousNum;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @Column(nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @Column(nullable = false)
    private Category category;

    @OneToMany(mappedBy = "post")
    private List<Comment> comment = new ArrayList<>();
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reply> replies = new ArrayList<>();
    @OneToOne(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private Emotion emotion;

}

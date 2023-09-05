package princessandknights.princesshoppang.user.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.ColumnDefault;
import princessandknights.princesshoppang.community.entity.Comment;
import princessandknights.princesshoppang.community.entity.Post;
import princessandknights.princesshoppang.community.entity.Reply;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private int mbti;

    @Column(nullable = false)
    @ColumnDefault("0")
    private int exp;

    @Column(nullable = false)
    @ColumnDefault("1")
    private int level;

    // Community 관련
    @OneToMany(mappedBy = "user")
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Reply> replies = new ArrayList<>();
}
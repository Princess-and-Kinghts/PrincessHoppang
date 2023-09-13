package princessandknights.princesshoppang.community.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.CreationTimestamp;
import princessandknights.princesshoppang.community.dto.CommentDto;
import princessandknights.princesshoppang.user.entity.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@Slf4j
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @Column(nullable = false)
    private String content;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;


    @Column(nullable = false)
    private int anonymousNum;

    // 게시글 아이디
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="post_id")
    private Post post;

    // 작성자 아이디
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;


    @OneToMany(mappedBy = "comment", fetch = FetchType.LAZY)
    private List<Reply> replyList = new ArrayList<>();


    public static Comment toSaveEntity(CommentDto commentDto, Post post) {


        User user = User.builder()
                .userId(commentDto.getUserId())
                .build();

        Comment comment = Comment.builder()
                .content(commentDto.getContent())
                .user(user)
                .post(post)
                .build();

        comment.randomAnonymousNumForComment();
        return comment;
    }
    @PrePersist
    public void randomAnonymousNumForComment() {
        String seed = getUser().getUserId().toString() + getPost().getPostId().toString();
        long seedHash = seed.hashCode();
        Random random = new Random(seedHash);
        // (10000~99999)
        this.anonymousNum = random.nextInt(90000) + 10000;
    }

    public static Comment toUpdateEntity(Comment comment, CommentDto commentDto) {
        comment.setContent(commentDto.getContent());
        return comment;
    }
}

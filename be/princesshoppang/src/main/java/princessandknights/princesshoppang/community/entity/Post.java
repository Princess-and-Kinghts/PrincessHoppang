package princessandknights.princesshoppang.community.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.CreationTimestamp;
import princessandknights.princesshoppang.community.dto.PostDto;
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

    @CreationTimestamp
    @JoinColumn(name = "created_at" )
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @JoinColumn(name = "anonymous_num")
    @Column(nullable = false)
    private int anonymousNum;

    @JoinColumn(name="view_count")
    @Column(nullable = false)
    private int viewCount = 0;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments = new ArrayList<>();
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reply> replies = new ArrayList<>();
    @OneToOne(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private Emotion emotion;

    // DTO to Entity
    public static Post toSaveEntity(PostDto postDto) {

        User user = User.builder()
                .userId(postDto.getUserId())
                .mbti(postDto.getMbtiId())
                .build();

        Category category = Category.builder()
                .categoryId(postDto.getCategoryId())
                .build();

        Post post = Post.builder()
                .title(postDto.getTitle())
                .content(postDto.getContent())
                .user(user)
                .category(category)
                .viewCount(0)
                .build();

        post.randomAnonymousNum();
        return post;
    }

    @PrePersist
    public void randomAnonymousNum() {
        Random random = new Random();
        // (10000~99999)
        this.anonymousNum = random.nextInt(90000) + 10000;
    }


    public static Post toUpdateEntity(Post post, PostDto postDto) {
        User user = User.builder()
                .userId(postDto.getUserId())
                .mbti(postDto.getMbtiId())
                .build();

        Category category = Category.builder()
                .categoryId(postDto.getCategoryId())
                .build();

        post.setPostId(postDto.getPostId());
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setAnonymousNum(postDto.getAnonymousNum());
        post.setUser(user);
        post.setCategory(category);
        post.setCreatedAt(postDto.getCreatedAt());
        post.setViewCount(postDto.getViewCount());


        return post;
    }




}

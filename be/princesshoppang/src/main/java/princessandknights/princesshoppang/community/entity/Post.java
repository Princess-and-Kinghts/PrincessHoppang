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
    private Long postId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private int anonymousNum;

    @Column(nullable = false)
    private int viewCount = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Comment> commentList = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Reply> replyList = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Emotion> emotionList = new ArrayList<>();

    @Column
    private int fileAttached;

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<PostFile> postFileList = new ArrayList<>();

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
                .fileAttached(0)
                .build();


        return post;
    }


//    @PrePersist
    public void randomAnonymousNum() {
        String seed = getUser().getUserId().toString() + postId.toString();
        long seedHash = seed.hashCode();
        Random random = new Random(seedHash);
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

        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setCategory(category);
        post.setUser(user);
        post.setViewCount(postDto.getViewCount());

        return post;
    }


    public static Post toSaveFileEntity(PostDto postDto) {

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
                .fileAttached(1)
                .build();

//        post.randomAnonymousNum();
        return post;
    }

    public static Post toUpdateFileEntity(Post post, PostDto postDto) {


        User user = User.builder()
                .userId(postDto.getUserId())
                .mbti(postDto.getMbtiId())
                .build();

        Category category = Category.builder()
                .categoryId(postDto.getCategoryId())
                .build();

        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setUser(user);
        post.setCategory(category);
        post.setViewCount(postDto.getViewCount());
        post.setFileAttached(0);


        return post;
    }



}

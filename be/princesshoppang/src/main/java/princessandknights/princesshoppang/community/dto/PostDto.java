package princessandknights.princesshoppang.community.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import princessandknights.princesshoppang.community.entity.Post;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
    // 글 하나 생성시 : 글ID, 카테고리ID, mbti(user테이블 안의 mbti), 날짜, 제목, 뷰수, 반응수(emotion 테이블 총 반응 갯수), 댓글수(Comment테이블 총댓글수)
    private Long postId;
    private String title;
    private String content;
    private Long categoryId;


    private LocalDateTime createdAt;
    private Long userId;
    private int anonymousNum;

    private int viewCount;

    private int mbtiId;

    private int emotionCount;
    private int commentCount;



    public static PostDto toPostDto(Post post, int commentCount, int emotionCount) {
        PostDto postDto = new PostDto();
        postDto.setPostId(post.getPostId());
        postDto.setTitle(post.getTitle());
        postDto.setContent(post.getContent());
        postDto.setCreatedAt(post.getCreatedAt());
        postDto.setAnonymousNum(post.getAnonymousNum());
        postDto.setUserId(post.getUser().getUserId());
        postDto.setCategoryId(post.getCategory().getCategoryId());
        postDto.setViewCount(post.getViewCount());
        postDto.setMbtiId(post.getUser().getMbti());
        postDto.setEmotionCount(emotionCount);
        postDto.setCommentCount(commentCount);
        return postDto;
    }

}

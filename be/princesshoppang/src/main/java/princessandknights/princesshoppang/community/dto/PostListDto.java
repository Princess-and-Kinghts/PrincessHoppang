package princessandknights.princesshoppang.community.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import princessandknights.princesshoppang.community.entity.Comment;
import princessandknights.princesshoppang.community.entity.Post;
import princessandknights.princesshoppang.community.entity.PostFile;

import java.time.LocalDateTime;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostListDto {
    // 글 하나 생성시 : 글ID, 카테고리ID, mbti(user테이블 안의 mbti), 날짜, 제목, 뷰수, 반응수(emotion 테이블 총 반응 갯수), 댓글수(Comment테이블 총댓글수)
    private Long postId;
    private String title;
    private Long categoryId;

    private LocalDateTime createdAt;
    private Long userId;
    private int anonymousNum;

    private int viewCount;

    private int mbtiId;

    private int emotionCount;
    private int commentCount;

//    private List<MultipartFile> postFile;
//    private List<String> originalFileName;
//    private List<String> imageUrl;
    private String originalFileName;
    private String imageUrl;
    // 파일 첨부 여부(1/0)
    private int fileAttached;


    public static PostListDto toPostListDto(Post post, List<Comment> commentList, int emotionCount) {
        PostListDto postListDto = new PostListDto();
        postListDto.setPostId(post.getPostId());
        postListDto.setTitle(post.getTitle());

        postListDto.setCreatedAt(post.getCreatedAt());
        postListDto.setAnonymousNum(post.getAnonymousNum());
        postListDto.setUserId(post.getUser().getUserId());
        postListDto.setCategoryId(post.getCategory().getCategoryId());
        postListDto.setViewCount(post.getViewCount());
        postListDto.setMbtiId(post.getUser().getMbti());
        postListDto.setEmotionCount(emotionCount);
        postListDto.setCommentCount(commentList.size());


        if (post.getFileAttached() == 0 && post.getPostFileList().isEmpty()){
            postListDto.setFileAttached(post.getFileAttached());

        } else {
            postListDto.setFileAttached(post.getFileAttached());
            PostFile firstFile = post.getPostFileList().get(0);
            postListDto.setOriginalFileName(firstFile.getOriginalFileName());
            postListDto.setImageUrl(firstFile.getImageUrl());

        }
        return postListDto;
    }
}

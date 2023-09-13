package princessandknights.princesshoppang.community.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.web.multipart.MultipartFile;
import princessandknights.princesshoppang.community.entity.Post;
import princessandknights.princesshoppang.community.entity.PostFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    private List<MultipartFile> postFile;
    private List<String> originalFileName;
    private List<String> imageUrl;
    // 파일 첨부 여부(1/0)
    private int fileAttached;

    private List<CommentDto> commentList;


    public static PostDto toPostDto(Post post, List<CommentDto> commentList, int emotionCount) {
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
        postDto.setCommentCount(commentList.size());

        postDto.setCommentList(commentList);

        // file 관련 필요한 값은 originalname과 imageUrl, 그리고 실제로 잘쓰이는건 ImageUrl
        if (post.getFileAttached() == 0 && post.getPostFileList().isEmpty()){
            postDto.setFileAttached(post.getFileAttached());

        } else {
            postDto.setFileAttached(post.getFileAttached());
            List<String> originalFileNameList = new ArrayList<>();
            List<String> imageUrlList = new ArrayList<>();

            for (PostFile postFile :post.getPostFileList()) {
                originalFileNameList.add(postFile.getOriginalFileName());
                imageUrlList.add(postFile.getImageUrl());
            }

            postDto.setOriginalFileName(originalFileNameList);
            postDto.setImageUrl(imageUrlList);

        }
        return postDto;
    }

}

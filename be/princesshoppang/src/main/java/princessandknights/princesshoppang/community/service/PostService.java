package princessandknights.princesshoppang.community.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import princessandknights.princesshoppang.community.config.S3Uploader;
import princessandknights.princesshoppang.community.dto.PostDto;
import princessandknights.princesshoppang.community.entity.Post;
import princessandknights.princesshoppang.community.entity.PostFile;
import princessandknights.princesshoppang.community.repository.EmotionRepository;
import princessandknights.princesshoppang.community.repository.PostFileRepository;
import princessandknights.princesshoppang.community.repository.PostRepository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final EmotionRepository emotionRepository;
    private final PostFileRepository postFileRepository;
    private final S3Uploader s3Uploader;

    // Create

    public Post createPost(PostDto postDto) throws IOException {
        if (postDto.getPostFile().isEmpty()) {
            Post post = Post.toSaveEntity(postDto);
            postRepository.save(post);

        } else {

            Post post = Post.toSaveFileEntity(postDto);
            Long savedId = postRepository.save(post).getPostId();
            Post tempPost = postRepository.findById(savedId).get();

            for (MultipartFile postFile:postDto.getPostFile()) {
                // 파일이름 가져오기
                String originalFilename = postFile.getOriginalFilename();
                String imageUrl = s3Uploader.uploadFiles(postFile, "postImages");

                // 저장 경로 설정 및 그 경로에 파일 저장
//                String savePath = "C:/springboot_img/" + storedFileName;
//                postFile.transferTo(new File(savePath));

                PostFile newPostfile = PostFile.toPostFileEntity(tempPost, originalFilename, imageUrl);
                postFileRepository.save(newPostfile);
            }

        }
        return null;
    }

    // List
    @Transactional
    public List<PostDto> getAllPosts() {
        // repository는 기본적으로 엔티티로 넘어옴
        List<Post> postList = postRepository.findAll();
        // Dto로 바꿔야함
        List<PostDto> postDtoList = new ArrayList<>();
        for (Post post: postList) {

            int commentCount = post.getCommentList().size();
            int emotionCount = emotionRepository.countEmotionsByPost(post).intValue();
            postDtoList.add(PostDto.toPostDto(post, commentCount, emotionCount));
        }
        return postDtoList;
    }

    // Detail
    @Transactional
    public void updateViewCounts(Long id) {
        postRepository.updateViewCounts(id);
    }

    @Transactional
    public PostDto findById(Long id) {
        Optional<Post> optionalPost = postRepository.findById(id);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            int commentCount = 0;

            if (post.getCommentList() != null) {
                commentCount = post.getCommentList().size();
            }
            int emotionCount = emotionRepository.countEmotionsByPost(post).intValue();
            PostDto postDto = PostDto.toPostDto(post, commentCount, emotionCount);
            return postDto;

        } else {
            return null;
        }
    }


    // update(PostDto는 새로 들어온 정보),
    @Transactional
    public PostDto updatePost(Long id, PostDto postDto) {
        //id로 해당 post 가져와서
        Optional<Post> optionalPost = postRepository.findById(id);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            Post.toUpdateEntity(post, postDto);
            postRepository.save(post);

            int commentCount = post.getCommentList().size();
            int emotionCount = emotionRepository.countEmotionsByPost(post).intValue();
            return PostDto.toPostDto(post, commentCount, emotionCount);

        }   else {
            return null;
        }

    }


    public void delete(Long id) {
        // 게시물을 먼저 조회
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("게시물이 존재하지 않습니다: " + id));

        // 연관된 S3 파일을 삭제
        List<PostFile> postFiles = post.getPostFileList();

        for (PostFile postFile : postFiles) {
            String imageUrl = postFile.getImageUrl();
            String originalName = postFile.getOriginalFileName();

            if (imageUrl != null) {
                s3Uploader.deleteS3(imageUrl, originalName);
            }
            // db 삭제
            postRepository.deleteById(id);
        }
    }


}

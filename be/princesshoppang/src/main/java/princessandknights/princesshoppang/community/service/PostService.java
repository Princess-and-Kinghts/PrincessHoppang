package princessandknights.princesshoppang.community.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import princessandknights.princesshoppang.community.config.S3Uploader;
import princessandknights.princesshoppang.community.dto.CommentDto;
import princessandknights.princesshoppang.community.dto.PostDto;
import princessandknights.princesshoppang.community.dto.PostListDto;
import princessandknights.princesshoppang.community.entity.Comment;
import princessandknights.princesshoppang.community.entity.Post;
import princessandknights.princesshoppang.community.entity.PostFile;
import princessandknights.princesshoppang.community.repository.CommentRepository;
import princessandknights.princesshoppang.community.repository.EmotionRepository;
import princessandknights.princesshoppang.community.repository.PostFileRepository;
import princessandknights.princesshoppang.community.repository.PostRepository;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final EmotionRepository emotionRepository;
    private final PostFileRepository postFileRepository;
    private final S3Uploader s3Uploader;
    private final CommentRepository commentRepository;

    // Create

    public Post createPost(PostDto postDto) throws IOException {
        MultipartFile file = postDto.getPostFile().get(0);

        if(file.isEmpty() || file.getSize() == 0){
            Post post = Post.toSaveEntity(postDto);
            postRepository.save(post);
            post.randomAnonymousNum();
            postRepository.save(post);

        } else {

            Post post = Post.toSaveFileEntity(postDto);
            Long savedId = postRepository.save(post).getPostId();
            Post tempPost = postRepository.findById(savedId).get();

            for (MultipartFile postFile:postDto.getPostFile()) {
                // 파일이름 가져오기
                String originalFilename = postFile.getOriginalFilename();
                String imageUrl = s3Uploader.uploadFiles(postFile, "postImages");

                PostFile newPostfile = PostFile.toPostFileEntity(tempPost, originalFilename, imageUrl);
                postFileRepository.save(newPostfile);
                post.randomAnonymousNum();
                postFileRepository.save(newPostfile);

            }

        }
        return null;
    }

    // List
    @Transactional
    public List<PostListDto> getAllPosts() {
        // repository는 기본적으로 엔티티로 넘어옴
        List<Post> postList = postRepository.findAll();
        // Dto로 바꿔야함
        List<PostListDto> postDtoList = new ArrayList<>();
        for (Post post: postList) {
            List<Comment> commentList = commentRepository.findAllByPost_PostId(post.getPostId());
            int emotionCount = emotionRepository.countEmotionsByPost(post).intValue();
            postDtoList.add(PostListDto.toPostListDto(post, commentList, emotionCount));
        }
        return postDtoList;
    }

    // Detail
    @Transactional
    public void updateViewCounts(Long id) {
        postRepository.updateViewCounts(id);
    }

    @Transactional
    public PostDto findById(Long id, List<CommentDto> commentDtoList) {
        Optional<Post> optionalPost = postRepository.findById(id);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            int emotionCount = emotionRepository.countEmotionsByPost(post).intValue();
            PostDto postDto = PostDto.toPostDto(post, commentDtoList, emotionCount);
            return postDto;

        } else {
            return null;
        }
    }

    // update(PostDto는 새로 들어온 정보), update 보류
    @Transactional
    public PostDto updatePost(Long id, PostDto postDto, List<CommentDto> commentDtoList) throws IOException {

        Optional<Post> optionalPost = postRepository.findById(id);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();

            // 리스트를 받아와서 originalFileName으로 먼저 구분
            List<MultipartFile> newPostFiles = postDto.getPostFile();
            List<String> newOriginalFileNames = postDto.getOriginalFileName();
            if (newOriginalFileNames == null) {
                newOriginalFileNames = new ArrayList<>();
            }

            MultipartFile checkFile = newPostFiles.get(0);
            if (checkFile.isEmpty() || checkFile.getSize() == 0) {
                // 기존 파일 전부 삭제
                deleteAllFiles(post);
                Post noFilePost = Post.toUpdateFileEntity(post, postDto);
                postRepository.save(noFilePost);


            } else {
                for (MultipartFile file : newPostFiles) {

                    String originalFilename = file.getOriginalFilename();
                    newOriginalFileNames.add(originalFilename);
                }
                // 실제 구분 및 s3 업데이트 로직
                deleteAndAddFiles(post, newPostFiles, newOriginalFileNames);
            }

            Post.toUpdateEntity(post, postDto);
            postRepository.save(post);
            int emotionCount = emotionRepository.countEmotionsByPost(post).intValue();
            PostDto updatedPostDto = PostDto.toPostDto(post, commentDtoList, emotionCount);
            return updatedPostDto;
        } else {
            return null;
        }
    }


    private void deleteAndAddFiles(Post post, List<MultipartFile> newPostFiles, List<String> newOriginalFileNames) throws IOException {

        // 비교할 수 있도록 파일이름 받아옴
        Set<String> existingFileNames = post.getPostFileList().stream()
                .map(PostFile::getOriginalFileName)
                .collect(Collectors.toSet());

        // 삭제할 파일 리스트
        List<PostFile> filesToDelete = new ArrayList<>();


        if (newOriginalFileNames != null) {
            for (PostFile existingFile : post.getPostFileList()) {
                // newOriginalFileNames에 값이 존재하고, existingFile의 originalFileName이 newOriginalFileNames에 포함되지 않으면 삭제할 파일 리스트에 추가
                if (!newOriginalFileNames.contains(existingFile.getOriginalFileName())) {
                    filesToDelete.add(existingFile);
                }
            }
        }

        // 파일 삭제
        for (PostFile fileToDelete : filesToDelete) {
            String oldImageUrl = fileToDelete.getImageUrl();
            String oldOriginalFileName = fileToDelete.getOriginalFileName();
            if (oldImageUrl != null) {
                s3Uploader.deleteS3(oldImageUrl, oldOriginalFileName);
            }

            // 이게 맞나...?
            post.getPostFileList().remove(fileToDelete);
            postFileRepository.delete(fileToDelete);
        }

        // 파일 추가
        if (newOriginalFileNames != null) {
            for (int i = 0; i < newPostFiles.size(); i++) {
                MultipartFile newPostFile = newPostFiles.get(i);
                String newOriginalFileName = newOriginalFileNames.get(i);

                // originalfilename을 비교하여 현존하는 파일리스트에 없다면 추가
                if (!existingFileNames.contains(newOriginalFileName)) {
                    String newFileUrl = s3Uploader.uploadFiles(newPostFile, "postImages");
                    PostFile updatedPostFile = PostFile.toPostFileEntity(post, newOriginalFileName, newFileUrl);
                    postFileRepository.save(updatedPostFile);
                }
            }
        }
    }

    private void deleteAllFiles(Post post) {
        // 모든 파일 삭제
        List<PostFile> filesToDelete = post.getPostFileList();
        for (PostFile fileToDelete : filesToDelete) {
            String oldImageUrl = fileToDelete.getImageUrl();
            String oldOriginalFileName = fileToDelete.getOriginalFileName();
            if (oldImageUrl != null) {
                s3Uploader.deleteS3(oldImageUrl, oldOriginalFileName);
            }
            postFileRepository.delete(fileToDelete);
        }
    }

    @Transactional
    public void deletePost(Long id) {
        // 게시물을 먼저 조회
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("게시물이 존재하지 않습니다: " + id));
//        List<PostFile> postFiles = post.getPostFileList();
//
//        for (PostFile postFile : postFiles) {
//            String imageUrl = postFile.getImageUrl();
//            String originalName = postFile.getOriginalFileName();
//
//            if (imageUrl != null) {
//                s3Uploader.deleteS3(imageUrl, originalName);
//            }
        // 연관된 S3 파일을 삭제
        deleteAllFiles(post);
        // db 삭제
        postRepository.delete(post);
    }

}

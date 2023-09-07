package princessandknights.princesshoppang.community.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import princessandknights.princesshoppang.community.dto.PostDto;
import princessandknights.princesshoppang.community.entity.Emotion;
import princessandknights.princesshoppang.community.entity.Post;
import princessandknights.princesshoppang.community.repository.EmotionRepository;
import princessandknights.princesshoppang.community.repository.PostRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final EmotionRepository emotionRepository;

    // Create
    public Post createPost(PostDto postDto) {
        Post post = Post.toSaveEntity(postDto);
        postRepository.save(post);
        return post;
    }

    // List
    @Transactional
    public List<PostDto> getAllPosts() {
        // repository는 기본적으로 엔티티로 넘어옴
        List<Post> postList = postRepository.findAll();
        // Dto로 바꿔야함
        List<PostDto> postDtoList = new ArrayList<>();
        for (Post post: postList) {

            int commentCount = post.getComments().size();
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

    public PostDto findById(Long id) {
        Optional<Post> optionalPost = postRepository.findById(id);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            int commentCount = 0;

            if (post.getComments() != null) {
                commentCount = post.getComments().size();
            }
            int emotionCount = emotionRepository.countEmotionsByPost(post).intValue();
            PostDto postDto = PostDto.toPostDto(post, commentCount, emotionCount);
            return postDto;

        } else {
            return null;
        }
    }


    // update(PostDto는 새로 들어온 정보)
    @Transactional
    public PostDto updatePost(Long id, PostDto postDto) {
        //id로 해당 post 가져와서
        Optional<Post> optionalPost = postRepository.findById(id);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            Post.toUpdateEntity(post, postDto);
            postRepository.save(post);

            int commentCount = post.getComments().size();
            int emotionCount = emotionRepository.countEmotionsByPost(post).intValue();
            return PostDto.toPostDto(post, commentCount, emotionCount);

        }   else {
        return null;
    }

    }


}

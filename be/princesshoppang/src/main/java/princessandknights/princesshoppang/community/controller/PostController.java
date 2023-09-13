package princessandknights.princesshoppang.community.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import princessandknights.princesshoppang.community.dto.CommentDto;
import princessandknights.princesshoppang.community.dto.PostDto;
import princessandknights.princesshoppang.community.dto.PostListDto;
import princessandknights.princesshoppang.community.entity.Post;
import princessandknights.princesshoppang.community.service.CommentService;
import princessandknights.princesshoppang.community.service.PostService;

import java.io.IOException;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/community")
public class PostController {
    private final PostService postService;
    private final CommentService commentService;


    @GetMapping("/")
    public List<PostListDto> getAllPosts() {
        return postService.getAllPosts();
    }

    @PostMapping("/post/create")
    public ResponseEntity<Post> createPost(@ModelAttribute PostDto postDto) throws IOException {
        postService.createPost(postDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/post/{id}")
    public PostDto findById(@PathVariable Long id) {
        // 조회수를 올리고
        postService.updateViewCounts(id);
        List<CommentDto> commentDtoList = commentService.getAllCommentsByPostId(id);
        PostDto postDto = postService.findById(id, commentDtoList);
        return postDto;
    }

    @GetMapping("/post/update/{id}")
    public PostDto updateForm(@PathVariable Long id) {
        List<CommentDto> commentDtoList = commentService.getAllCommentsByPostId(id);
        PostDto postDto = postService.findById(id, commentDtoList);
        return postDto;
    }

    @PutMapping("/post/update/{id}")
    public ResponseEntity<PostDto> updatePost(@PathVariable Long id, @ModelAttribute PostDto postDto) throws IOException {
        List<CommentDto> commentDtoList = commentService.getAllCommentsByPostId(id);
        PostDto upDatedPostDto= postService.updatePost(id, postDto, commentDtoList);
        return ResponseEntity.ok(upDatedPostDto);
    }
    // delete

    @DeleteMapping("/post/delete/{id}")
    public ResponseEntity<String> deletePost(@PathVariable Long id) {
        try {
            postService.deletePost(id);
            return ResponseEntity.ok("게시물이 성공적으로 삭제되었습니다.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("게시물 삭제에 실패하였습니다.");
        }
    }









    // SSR방식
//    @GetMapping("/")
//    public String findAll(Model model) {
//        List<PostDto> postDtoList = postService.findAll();
//        model.addAttribute("postList", postDtoList);
//        return "list";
//    }
}


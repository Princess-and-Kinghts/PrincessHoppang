package princessandknights.princesshoppang.community.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import princessandknights.princesshoppang.community.dto.PostDto;
import princessandknights.princesshoppang.community.entity.Post;
import princessandknights.princesshoppang.community.service.PostService;

import java.io.IOException;
import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/community")
public class PostController {
    private final PostService postService;


    @GetMapping("/")
    public List<PostDto> getAllPosts() {
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
        PostDto postDto = postService.findById(id);
        return postDto;
    }

    @GetMapping("/post/update/{id}")
    public PostDto updateForm(@PathVariable Long id) {
        PostDto postDto = postService.findById(id);
        return postDto;
    }

    @PutMapping("/post/update/{id}")
    public ResponseEntity<PostDto> updatePost(@PathVariable Long id, @ModelAttribute PostDto postDto) throws IOException {
        PostDto upDatedPostDto= postService.updatePost(id, postDto);
        return ResponseEntity.ok(upDatedPostDto);
    }
    // delete

    @DeleteMapping("/post/delete/{id}")
    public ResponseEntity<String> deletePost(@PathVariable Long id) {
        try {
            postService.delete(id);
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


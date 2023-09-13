package princessandknights.princesshoppang.community.service;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.server.Cookie;
import org.springframework.stereotype.Service;
import princessandknights.princesshoppang.community.dto.CommentDto;
import princessandknights.princesshoppang.community.entity.Comment;
import princessandknights.princesshoppang.community.entity.Post;
import princessandknights.princesshoppang.community.repository.CommentRepository;
import princessandknights.princesshoppang.community.repository.PostRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;


    public Long createComment(Long id, CommentDto commentDto) {
        Optional<Post> optionalPost = postRepository.findById(id);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            Comment comment = Comment.toSaveEntity(commentDto, post);
            return commentRepository.save(comment).getCommentId();
        } else {

            return null;
        }
    }


    public List<CommentDto> getAllCommentsByPostId(Long id) {
        Optional<Post> optionalPost = postRepository.findById(id);
        if (optionalPost.isPresent()) {
            List<Comment> commentList = commentRepository.findAllByPost_PostId(id);
            List<CommentDto> commentDtoList = new ArrayList<>();
            for (Comment comment: commentList) {
                CommentDto commentDto = CommentDto.toCommentDto(comment);
                commentDtoList.add(commentDto);
            }
            return commentDtoList;
        } else {
            return null;
        }

    }

    public CommentDto updateComment(Long id, CommentDto commentDto) {
        // 해당 comment를 불러와서
        Optional<Comment> optionalComment = commentRepository.findById(id);
        if( optionalComment.isPresent()) {
            Comment comment = optionalComment.get();
            Comment.toUpdateEntity(comment, commentDto);
            commentRepository.save(comment);
            CommentDto updatedCommentDto = CommentDto.toCommentDto(comment);
            return updatedCommentDto;
        }
        else {
            throw new RuntimeException("댓글을 찾을 수 없습니다. ID: " + id);
        }
    }
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }


}

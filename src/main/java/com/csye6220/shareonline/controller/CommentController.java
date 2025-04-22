package com.csye6220.shareonline.controller;

import com.csye6220.shareonline.model.Comment;
import com.csye6220.shareonline.model.Post;
import com.csye6220.shareonline.service.CommentService;
import com.csye6220.shareonline.service.PostService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired private CommentService commentService;
    @Autowired private PostService    postService;

    /** create comment */
    @PostMapping
    public Comment createComment(@RequestBody CommentDto dto, HttpSession session) {
        Long uid = (Long) session.getAttribute("uid");
        if (uid == null) throw new RuntimeException("Not login");

        Post post = postService.getPostById(dto.postId());

        Comment parent = dto.parentCommentId() == null ? null :
                commentService.findById(dto.parentCommentId())
                        .orElseThrow(() -> new RuntimeException("Parent comment not found"));

        Comment c = new Comment(dto.content(), uid, post, parent);
        return commentService.createComment(c);
    }

    /** DTO for comment */
    public record CommentDto(Long postId, Long parentCommentId, String content) {}

    /** get nested comment tree */
    @GetMapping("/post/{postId}")
    public List<Comment> getNestedComments(@PathVariable Long postId) {
        return commentService.getNestedCommentsByPost(postId);
    }

    /** delete one comment, will delete all sub comment under it */
    @DeleteMapping("/{commentId}")
    public void deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
    }

    /** delete whole comment */
    @DeleteMapping("/post/{postId}")
    public void deleteAllCommentsForPost(@PathVariable Long postId) {
        commentService.deleteAllCommentsByPost(postId);
    }
}

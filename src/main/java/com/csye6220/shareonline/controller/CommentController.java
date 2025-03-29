package com.csye6220.shareonline.controller;

import com.csye6220.shareonline.model.Comment;
import com.csye6220.shareonline.model.Post;
import com.csye6220.shareonline.service.CommentService;
import com.csye6220.shareonline.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private PostService postService;

    /**
     * create post
     */
    @PostMapping
    public String createComment(@RequestParam Long postId,
                                @RequestParam Long userId,
                                @RequestParam(required = false) Long parentCommentId,
                                @RequestParam String content) {
        Post post = postService.getPostById(postId);

        Comment parent = null;
        if (parentCommentId != null) {
            parent = commentService.getNestedCommentsByPost(postId) // or commentService.getCommentById(...)
                    .stream()
                    .filter(c -> c.getId().equals(parentCommentId))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Parent comment not found."));
        }

        Comment newComment = new Comment(content, userId, post, parent);
        commentService.createComment(newComment);

        return "Comment created successfully.";
    }

    /**
     * get nested comments in this post
     */
    @GetMapping("/post/{postId}")
    public List<Comment> getNestedComments(@PathVariable Long postId) {
        return commentService.getNestedCommentsByPost(postId);
    }

    /**
     * delete one comment(also his children)
     */
    @DeleteMapping("/{commentId}")
    public String deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return "Comment with id " + commentId + " deleted.";
    }

    /**
     * delete one post comments
     */
    @DeleteMapping("/post/{postId}")
    public String deleteAllCommentsForPost(@PathVariable Long postId) {
        commentService.deleteAllCommentsByPost(postId);
        return "All comments for post " + postId + " deleted.";
    }
}

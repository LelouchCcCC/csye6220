package com.csye6220.shareonline.controller;

import com.csye6220.shareonline.model.Post;
import com.csye6220.shareonline.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping
    public Post createPost(@RequestBody Post post,
                           @RequestParam Long userId) {
        post.setUserId(userId);
        return postService.createPost(post);
    }

    @GetMapping
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/{postId}")
    public Post getPostById(@PathVariable Long postId) {
        return postService.getPostById(postId);
    }

    @PutMapping("/{postId}")
    public Post updatePost(
            @PathVariable Long postId,
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam String category,
            @RequestParam(required = false) String imageUrl,
            @RequestParam Long userId
    ) {
        return postService.updatePost(postId, title, description, category, imageUrl, userId);
    }

    @DeleteMapping("/{postId}")
    public String deletePost(@PathVariable Long postId,
                             @RequestParam Long userId) {
        postService.deletePost(postId, userId);
        return "Post with id " + postId + " deleted successfully.";
    }

    @GetMapping("/user/{username}")
    public List<Post> getPostsByUsername(@PathVariable String username) {
        return postService.getPostsByUsername(username);
    }
}

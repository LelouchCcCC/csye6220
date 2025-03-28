package com.csye6220.shareonline.controller;


import com.csye6220.shareonline.dto.PostDto;
import com.csye6220.shareonline.model.Post;
import com.csye6220.shareonline.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用于演示帖子的增删改查
 */
@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    /**
     * 创建帖子
     * 注意：实际项目中，会从 JWT/Session 中获取 userId
     */
    @PostMapping
    public Post createPost(@RequestBody PostDto postDto, @RequestParam Long userId) {

        System.out.println("PostController.createPost");
        // 或者也把 userId 放进 DTO
        return postService.createPost(
                postDto.getTitle(),
                postDto.getDescription(),
                postDto.getCategory(),
                postDto.getImageUrl(),
                userId
        );
    }
    /**
     * 获取所有帖子
     */
    @GetMapping
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    /**
     * 根据ID获取帖子
     */
    @GetMapping("/{postId}")
    public Post getPostById(@PathVariable Long postId) {
        return postService.getPostById(postId);
    }

    /**
     * 更新帖子
     */
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

    /**
     * 删除帖子
     */
    @DeleteMapping("/{postId}")
    public String deletePost(@PathVariable Long postId, @RequestParam Long userId) {
        postService.deletePost(postId, userId);
        return "Post with id " + postId + " deleted successfully.";
    }
}

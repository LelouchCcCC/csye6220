package com.csye6220.shareonline.controller;

import com.csye6220.shareonline.model.Post;
import com.csye6220.shareonline.service.PostService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    /** ========== CRUD ========== */

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody Post post, HttpSession session) {
        Long uid = (Long) session.getAttribute("uid");
        if (uid == null) throw new RuntimeException("Not login");

        // check user
        if (post.getTitle() == null || post.getTitle().isBlank())
            throw new RuntimeException("Title is required");
        if (post.getDescription() == null) post.setDescription("");

        post.setUserId(uid);
        Post saved = postService.createPost(post);

        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }


    @GetMapping("/{postId}")
    public Post getPostById(@PathVariable Long postId) {
        return postService.getPostById(postId);
    }

    @PutMapping("/{postId}")
    public Post updatePost(@PathVariable Long postId,
                           @RequestBody Post body,
                           HttpSession session) {
        Long uid = (Long) session.getAttribute("uid");
        return postService.updatePost(postId,
                body.getTitle(), body.getDescription(),
                body.getCategory(), body.getImageUrl(), uid);
    }

    @DeleteMapping("/{postId}")
    public void deletePost(@PathVariable Long postId, HttpSession session) {
        Long uid = (Long) session.getAttribute("uid");
        postService.deletePost(postId, uid);
    }

    @GetMapping("/my")
    public List<Post> myPosts(HttpSession session) {
        Long uid = (Long) session.getAttribute("uid");
        return postService.getPostsByUserId(uid);
    }

    @GetMapping("/user/{username}")
    public List<Post> getPostsByUsername(@PathVariable String username) {
        return postService.getPostsByUsername(username);
    }

    /**
     * /api/posts/latest?offset=0&limit=10
     */
    @GetMapping("/latest")
    public List<Post> latest(@RequestParam(defaultValue="0") int offset,
                             @RequestParam(defaultValue="10") int limit){
        return postService.getLatestPosts(offset,limit);
    }

    /** search by category */
    @GetMapping("/category/{cat}")
    public List<Post> byCategory(@PathVariable String cat,
                                 @RequestParam(defaultValue="0") int offset,
                                 @RequestParam(defaultValue="10") int limit){
        return postService.getPostsByCategory(cat,offset,limit);
    }
}

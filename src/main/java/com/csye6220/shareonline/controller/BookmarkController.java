//package com.csye6220.shareonline.controller;
//
//import com.csye6220.shareonline.model.Post;
//import com.csye6220.shareonline.model.User;
//import com.csye6220.shareonline.service.PostService;
//import com.csye6220.shareonline.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/bookmarks")
//public class BookmarkController {
//
//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private PostService postService;
//
//    /**
//     * Save a post to bookmarks
//     */
//    @PostMapping("/{userId}/{postId}")
//    public String bookmarkPost(@PathVariable Long userId, @PathVariable Long postId) {
//
//        // TODO
//    }
//
//    /**
//     * Remove a post from bookmarks
//     */
//    @DeleteMapping("/{userId}/{postId}")
//    public String unbookmarkPost(@PathVariable Long userId, @PathVariable Long postId) {
//        // TODO
//    }
//
//    /**
//     * Get all bookmarked posts for a user
//     */
//    @GetMapping("/{userId}")
//    public List<Post> getBookmarkedPosts(@PathVariable Long userId) {
//        // TODO
//    }
//}

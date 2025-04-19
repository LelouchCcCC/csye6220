package com.csye6220.shareonline.controller;

import com.csye6220.shareonline.model.Comment;
import com.csye6220.shareonline.model.Post;
import com.csye6220.shareonline.service.CommentService;
import com.csye6220.shareonline.service.PostService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ViewController {

    @Autowired
    private PostService postService;

    @Autowired
    private CommentService commentService;

    /** 分页列表 */
    @GetMapping("/feed")
    public String feed(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            Model model) {

        List<Post> posts = postService.getPostsPaginated(page, size);
        model.addAttribute("posts", posts);
        model.addAttribute("page", page);
        model.addAttribute("size", size);
        return "feed";   // -> /WEB-INF/views/feed.jsp
    }

    /** 详情 + comments 嵌套 */
    @GetMapping("/posts/{postId}")
    public String postDetail(
            @PathVariable Long postId,
            Model model) {

        Post post = postService.getPostById(postId);
        List<Comment> comments = commentService.getNestedCommentsByPost(postId);

        model.addAttribute("post", post);
        model.addAttribute("comments", comments);
        return "post-detail";  // -> /WEB-INF/views/post-detail.jsp
    }

    /** GET Logout */
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}

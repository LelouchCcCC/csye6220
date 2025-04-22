package com.csye6220.shareonline.service;

import com.csye6220.shareonline.dao.PostDAO;
import com.csye6220.shareonline.model.Post;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private final PostDAO postDAO = new PostDAO();

    public Post createPost(Post post) {
        postDAO.savePost(post);
        return post;
    }

    public List<Post> getAllPosts() {
        return postDAO.getAllPosts();
    }

    public List<Post> getLatestPosts(int offset, int limit) {
        return postDAO.findLatest(offset, limit);
    }

    public Post getPostById(Long postId) {
        Post post = postDAO.getPostById(postId);
        if (post == null) {
            throw new RuntimeException("Post not found with id: " + postId);
        }
        return post;
    }

    public Post updatePost(Long postId,
                           String title,
                           String description,
                           String category,
                           String imageUrl,
                           Long userId) {
        Post existing = getPostById(postId);
        if (!existing.getUserId().equals(userId)) {
            throw new RuntimeException("No permission to update this post");
        }
        existing.setTitle(title);
        existing.setDescription(description);
        existing.setCategory(category);
        existing.setImageUrl(imageUrl);
        postDAO.savePost(existing);
        return existing;
    }

    public void deletePost(Long postId, Long userId) {
        Post existing = getPostById(postId);
        if (!existing.getUserId().equals(userId)) {
            throw new RuntimeException("No permission to delete this post");
        }
        postDAO.deletePost(postId);
    }

    public List<Post> getPostsByUsername(String username) {
        return postDAO.getPostsByUsername(username);
    }

    public List<Post> getPostsByUserId(Long uid) {
        return postDAO.getPostsByUserId(uid);
    }

    public List<Post> getPostsByCategory(String c,int off,int lim){
        return postDAO.findByCategory(c,off,lim);
    }

}

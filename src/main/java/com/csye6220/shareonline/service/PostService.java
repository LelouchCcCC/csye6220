package com.csye6220.shareonline.service;

import com.csye6220.shareonline.dao.PostDao;
import com.csye6220.shareonline.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostDao postDao;

    /**
     * create or update post
     */
    public Post createPost(String title, String description, String category, String imageUrl, Long userId) {
        Post post = new Post(title, description, category, imageUrl, userId);
        System.out.println("post: " + post.toString());
        postDao.savePost(post);
        return post;
    }

    /**
     * get post by id
     */
    public Post getPostById(Long postId) {
        Post post = postDao.getPostById(postId);
        if (post == null) {
            throw new RuntimeException("Post not found with id: " + postId);
        }
        return post;
    }

    /**
     * get all post
     */
    public List<Post> getAllPosts() {
        return postDao.getAllPosts();
    }

    /**
     * update post
     */
    public Post updatePost(Long postId, String title, String description, String category, String imageUrl, Long userId) {
        Post existing = getPostById(postId);
        // check whether is the same user
        if (!existing.getUserId().equals(userId)) {
            throw new RuntimeException("No permission to update this post");
        }
        existing.setTitle(title);
        existing.setDescription(description);
        existing.setCategory(category);
        existing.setImageUrl(imageUrl);

        postDao.savePost(existing); // saveOrUpdate
        return existing;
    }

    /**
     * delete post
     */
    public void deletePost(Long postId, Long userId) {
        Post existing = getPostById(postId);
        if (!existing.getUserId().equals(userId)) {
            throw new RuntimeException("No permission to delete this post");
        }
        postDao.deletePost(postId);
    }
}
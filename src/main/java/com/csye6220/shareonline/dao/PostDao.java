package com.csye6220.shareonline.dao;

import com.csye6220.shareonline.model.Post;

import java.util.List;

public interface PostDao {
    // create or update post
    void savePost(Post post);

    // get post by id
    Post getPostById(Long id);

    // get all post
    List<Post> getAllPosts();

    // delete one post by id
    void deletePost(Long id);
}
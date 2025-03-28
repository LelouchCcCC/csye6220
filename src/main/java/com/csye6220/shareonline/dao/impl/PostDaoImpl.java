package com.csye6220.shareonline.dao.impl;

import com.csye6220.shareonline.dao.PostDao;
import com.csye6220.shareonline.model.Post;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class PostDaoImpl implements PostDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void savePost(Post post) {
        getSession().saveOrUpdate(post);
    }

    @Override
    public Post getPostById(Long id) {
        return getSession().get(Post.class, id);
    }

    @Override
    public List<Post> getAllPosts() {
        return getSession().createQuery("FROM Post", Post.class).list();
    }

    @Override
    public void deletePost(Long id) {
        Post post = getPostById(id);
        if (post != null) {
            getSession().delete(post);
        }
    }
}
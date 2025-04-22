package com.csye6220.shareonline.dao;

import com.csye6220.shareonline.model.Post;
import org.hibernate.query.Query;
import java.util.List;

public class PostDAO extends DAO {

    public void savePost(Post post) {
        try {
            begin();
            getSession().saveOrUpdate(post);
            commit();
        } catch (Exception e) {
            rollback();
            throw e;
        } finally {
            close();
        }
    }

    public List<Post> findLatest(int offset, int limit) {
        try {
            begin();
            Query<Post> q = getSession().createQuery(
                    "FROM Post p ORDER BY p.createdAt DESC", Post.class);
            q.setFirstResult(offset).setMaxResults(limit);
            List<Post> res = q.list();
            commit();
            return res;
        } catch (Exception e) {
            rollback(); throw e;
        } finally { close(); }
    }

    public Post getPostById(Long id) {
        try {
            begin();
            Post p = getSession().get(Post.class, id);
            commit();
            return p;
        } catch (Exception e) {
            rollback();
            throw e;
        } finally {
            close();
        }
    }
    public List<Post> getPostsByUserId(Long uid) {
        try {
            begin();
            Query<Post> q = getSession().createQuery(
                    "FROM Post p WHERE p.userId = :uid ORDER BY p.createdAt DESC", Post.class);
            q.setParameter("uid", uid);
            List<Post> list = q.list();
            commit();
            return list;
        } finally { close(); }
    }


    public List<Post> getAllPosts() {
        try {
            begin();
            Query<Post> query = getSession().createQuery("FROM Post", Post.class);
            List<Post> list = query.list();
            commit();
            return list;
        } catch (Exception e) {
            rollback();
            throw e;
        } finally {
            close();
        }
    }

    public List<Post> findByCategory(String cat,int offset,int limit){
        try{
            begin();
            Query<Post> q=getSession().createQuery(
                    "FROM Post p WHERE p.category=:c ORDER BY p.createdAt DESC",Post.class);
            q.setParameter("c",cat);
            q.setFirstResult(offset).setMaxResults(limit);
            List<Post> list=q.list();
            commit();
            return list;
        }finally{ close(); }
    }

    public void deletePost(Long id) {
        try {
            begin();
            Post p = getSession().get(Post.class, id);
            if (p != null) {
                getSession().delete(p);
            }
            commit();
        } catch (Exception e) {
            rollback();
            throw e;
        } finally {
            close();
        }
    }

    public List<Post> getPostsByUsername(String username) {
        try {
            begin();
            Query<Post> query = getSession().createQuery(
                    "select p from Post p, User u where p.userId = u.id and u.username = :username order by p.createdAt desc",
                    Post.class);
            query.setParameter("username", username);
            List<Post> posts = query.list();
            commit();
            return posts;
        } catch (Exception e) {
            rollback();
            throw e;
        } finally {
            close();
        }
    }
}

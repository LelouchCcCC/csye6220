package com.csye6220.shareonline.dao;

import com.csye6220.shareonline.model.Post;
import org.hibernate.query.Query;
import java.util.List;

/**
 * 针对Post实体的CRUD操作，继承DAO基类
 */
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

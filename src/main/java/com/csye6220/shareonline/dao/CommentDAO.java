package com.csye6220.shareonline.dao;

import com.csye6220.shareonline.model.Comment;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class CommentDAO extends DAO {

    /** save/update */
    public Comment saveOrUpdateComment(Comment c) {
        try {
            begin();
            getSession().saveOrUpdate(c);
            commit();
            return c;
        } catch (Exception e) {
            rollback(); throw e;
        } finally { close(); }
    }

    /** search based on id */
    public Optional<Comment> findById(Long id) {
        try {
            begin();
            Comment c = getSession().get(Comment.class, id);
            commit();
            return Optional.ofNullable(c);
        } finally { close(); }
    }

    /** get all comment */
    public List<Comment> findAllByPostId(Long postId) {
        try {
            begin();
            Query<Comment> q = getSession().createQuery(
                    "FROM Comment c WHERE c.post.id = :pid ORDER BY c.createdAt ASC", Comment.class);
            q.setParameter("pid", postId);
            List<Comment> list = q.list();
            list.forEach(this::loadAllReplies);
            commit();
            return list;
        } finally { close(); }
    }

    private void loadAllReplies(Comment c) {
        c.getReplies().size();
        c.getReplies().forEach(this::loadAllReplies);
    }

    /** delete one comment */
    public void deleteComment(Long id) {
        try {
            begin();
            Comment c = getSession().get(Comment.class, id);
            if (c != null) getSession().delete(c);
            commit();
        } finally { close(); }
    }

    public void deleteAllCommentsByPostId(Long postId) {
        try {
            begin();
            Query<?> q = getSession().createQuery(
                    "DELETE FROM Comment c WHERE c.post.id = :pid");
            q.setParameter("pid", postId);
            q.executeUpdate();
            commit();
        } finally { close(); }
    }
}

package com.csye6220.shareonline.dao;

import com.csye6220.shareonline.model.Comment;
import org.hibernate.query.Query;

import java.util.List;

public class CommentDAO extends DAO {

    /**
     * save or update
     */
    public void saveOrUpdateComment(Comment comment) {
        try {
            begin();
            getSession().saveOrUpdate(comment);
            commit();
        } catch (Exception e) {
            rollback();
            throw e;
        } finally {
            close();
        }
    }

    /**
     * get cmt by id
     */
    public Comment findById(Long commentId) {
        try {
            begin();
            Comment c = getSession().get(Comment.class, commentId);
            commit();
            return c;
        } catch (Exception e) {
            rollback();
            throw e;
        } finally {
            close();
        }
    }

    /**
     * get all cmts and date DEC
     */
    public List<Comment> findAllByPostId(Long postId) {
        try {
            begin();
            Query<Comment> query = getSession().createQuery(
                    "FROM Comment c WHERE c.post.id = :pid ORDER BY c.createdAt ASC",
                    Comment.class
            );
            query.setParameter("pid", postId);

            List<Comment> result = query.list();

            for (Comment c : result) {
                loadAllReplies(c);
            }

            commit();
            return result;
        } catch (Exception e) {
            rollback();
            throw e;
        } finally {
            close();
        }
    }

    /**
     * nested loop getting cmts
     */
    private void loadAllReplies(Comment comment) {
        // lazy loading
        comment.getReplies().size();
        for (Comment child : comment.getReplies()) {
            loadAllReplies(child);
        }
    }

    /**
     * delete one cmt and set Cascade=ALL
     */
    public void deleteComment(Long commentId) {
        try {
            begin();
            Comment c = getSession().get(Comment.class, commentId);
            if (c != null) {
                getSession().delete(c);
            }
            commit();
        } catch (Exception e) {
            rollback();
            throw e;
        } finally {
            close();
        }
    }

    /**
     * delete all cmts
     */
    public void deleteAllCommentsByPostId(Long postId) {
        try {
            begin();
            Query<?> query = getSession().createQuery(
                    "DELETE FROM Comment c WHERE c.post.id = :pid"
            );
            query.setParameter("pid", postId);
            query.executeUpdate();
            commit();
        } catch (Exception e) {
            rollback();
            throw e;
        } finally {
            close();
        }
    }
}

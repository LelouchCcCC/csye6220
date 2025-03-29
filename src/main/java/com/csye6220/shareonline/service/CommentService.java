package com.csye6220.shareonline.service;

import com.csye6220.shareonline.dao.CommentDAO;
import com.csye6220.shareonline.model.Comment;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CommentService {

    private final CommentDAO commentDAO = new CommentDAO();

    public void createComment(Comment comment) {
        commentDAO.saveOrUpdateComment(comment);
    }

    /**
     * get nested comments in this post id
     */
    public List<Comment> getNestedCommentsByPost(Long postId) {
        // 1) get all comments within this post
        List<Comment> allComments = commentDAO.findAllByPostId(postId);

        // 2) use map to store id in comments
        Map<Long, Comment> commentMap = new HashMap<>();
        for (Comment c : allComments) {
            commentMap.put(c.getId(), c);
            c.getReplies().clear();
        }

        List<Comment> topLevel = new ArrayList<>();

        for (Comment c : allComments) {
            Comment parent = c.getParent();
            if (parent == null) {
                // top level cmt
                topLevel.add(c);
            } else {
                // other lvl cmt
                Comment parentInMap = commentMap.get(parent.getId());
                if (parentInMap != null) {
                    parentInMap.getReplies().add(c);
                }
            }
        }
        return topLevel;
    }

    /**
     * delete cmt nestedly
     */
    public void deleteComment(Long commentId) {
        commentDAO.deleteComment(commentId);
    }

    /**
     * delete post comments nestedly
     */
    public void deleteAllCommentsByPost(Long postId) {
        commentDAO.deleteAllCommentsByPostId(postId);
    }
}

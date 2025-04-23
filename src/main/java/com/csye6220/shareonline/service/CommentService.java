package com.csye6220.shareonline.service;

import com.csye6220.shareonline.dao.CommentDAO;
import com.csye6220.shareonline.model.Comment;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CommentService {

    private final CommentDAO dao = new CommentDAO();

    public Comment createComment(Comment c) {
        return dao.saveOrUpdateComment(c);
    }

    public Optional<Comment> findById(Long id) {
        return dao.findById(id);
    }

    /** create the tree */
    public List<Comment> getNestedCommentsByPost(Long postId) {
        List<Comment> flat = dao.findAllByPostId(postId);

        Map<Long, Comment> map = new HashMap<>();
        flat.forEach(c -> { map.put(c.getId(), c); c.getReplies().clear(); });

        List<Comment> roots = new ArrayList<>();
        for (Comment c : flat) {
            Comment p = c.getParent();
            if (p == null) roots.add(c);
            else map.get(p.getId()).getReplies().add(c);
        }
        return roots;
    }

    public void deleteComment(Long id)               { dao.deleteComment(id); }
    public void deleteAllCommentsByPost(Long postId) { dao.deleteAllCommentsByPostId(postId); }
}

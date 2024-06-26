package com.project.CommentsManagement.service;


import com.project.CommentsManagement.model.Comments;
import com.project.CommentsManagement.repository.ICommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentService implements ICommentService {
    @Autowired
    private ICommentRepository commentRepository;


    public List<Comments> getAllComments() {
        return commentRepository.findAllComments();
    }

    public List<Comments> getCommentsByUsername(String username) {
        return commentRepository.findCommentByUserName(username);
    }

    public List<Comments> getCommentsByDate(LocalDateTime date) {
        return commentRepository.findCommentDateofcomment(date);
    }

    public Comments saveComment(Comments comments) {
        return commentRepository.save(comments);
    }

    @Override
    public String updateComment(Long id, Comments commentDetails) {
        return commentRepository.updateComment(id, commentDetails);
    }

    @Override
    public String deleteComment(Long id) {
        return commentRepository.deleteCommentById(id);
    }

}


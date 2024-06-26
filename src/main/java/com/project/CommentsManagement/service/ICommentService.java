package com.project.CommentsManagement.service;

import com.project.CommentsManagement.model.Comments;

import java.time.LocalDateTime;
import java.util.List;

public interface ICommentService {
    List<Comments> getAllComments();

    List<Comments> getCommentsByUsername(String username);

    List<Comments> getCommentsByDate(LocalDateTime dateTime);

    Comments saveComment(Comments comment);

    String updateComment(Long id, Comments commentDetails);

    String deleteComment(Long id);
}

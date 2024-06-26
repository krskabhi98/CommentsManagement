package com.project.CommentsManagement.service;

import com.project.CommentsManagement.model.Comments;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ICommentService {
    List<Comments> getAllComments();

    List<Comments> getCommentsByUsername(String username);

    List<Comments> getCommentsByDate(LocalDateTime dateTime);

    Comments saveComment(Comments comment);

}

package com.project.CommentsManagement.repository;


import com.project.CommentsManagement.model.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ICommentRepository {

    Comments save(Comments comments);

    List<Comments> findCommentByUserName(String username);

    List<Comments> findCommentDateofcomment(LocalDateTime date);

    List<Comments> findAllComments();
}

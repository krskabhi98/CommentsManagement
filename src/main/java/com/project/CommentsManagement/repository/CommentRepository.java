package com.project.CommentsManagement.repository;

import com.project.CommentsManagement.model.Comments;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class CommentRepository implements ICommentRepository{

    @Autowired
    private EntityManager entityManager;

    @Override
    @Transactional()
    public Comments save(Comments comments) {
        entityManager.persist(comments);
        return comments;
    }

    @Override
    public List<Comments> findCommentByUserName(String username) {

        TypedQuery<Comments> query=entityManager.createNamedQuery("Comments.findByUsername",Comments.class);
        query.setParameter("username",username);
        return query.getResultList();
    }

    @Override
    public List<Comments> findCommentDateofcomment(LocalDateTime date) {
        TypedQuery<Comments> query=entityManager.createNamedQuery("Comments.findByDate",Comments.class);
        query.setParameter("date",date);
        return query.getResultList();
    }

    @Override
    public List<Comments> findAllComments() {
        Query query=entityManager.createNativeQuery("select * from Comments",Comments.class);
        return query.getResultList();
    }
}

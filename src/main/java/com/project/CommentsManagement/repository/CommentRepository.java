package com.project.CommentsManagement.repository;

import com.project.CommentsManagement.model.Comments;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class CommentRepository implements ICommentRepository {


    private static final String FIND_BY_USER_NAME = "Comments.findByUsername";
    private static final String FIND_BY_DATE = "Comments.findByDate";
    private static final String GET_ALL_COMMENT_QUERY = "select * from Comments";
    private static final String USER_NAME = "username";
    private static final String DATE = "date";
    private static final String UPDATE_COMMENT_MESSAGE = "Data updated for User : {0}";
    private static final String DELETE_COMMENT_MESSAGE = "Data deleted for User : {0}";
    private static final String NO_DATA_FOUND_MESSAGE = "No user found";

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

        TypedQuery<Comments> query = entityManager.createNamedQuery(FIND_BY_USER_NAME, Comments.class);
        query.setParameter(USER_NAME, username);
        return query.getResultList();
    }

    @Override
    public List<Comments> findCommentDateofcomment(LocalDateTime date) {
        TypedQuery<Comments> query = entityManager.createNamedQuery(FIND_BY_DATE, Comments.class);
        query.setParameter(DATE, date);
        return query.getResultList();
    }

    @Override
    public List<Comments> findAllComments() {
        Query query = entityManager.createNativeQuery(GET_ALL_COMMENT_QUERY, Comments.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public String updateComment(Long id, Comments commentDetails) {
        Comments comment = entityManager.find(Comments.class, id);
        if (comment != null) {
            comment.setBy(commentDetails.getBy());
            comment.setText(commentDetails.getText());
            comment.setDateofcomment(commentDetails.getDateofcomment());
            entityManager.merge(comment);
            return MessageFormat.format(UPDATE_COMMENT_MESSAGE, comment.getBy());
        }
        return NO_DATA_FOUND_MESSAGE;
    }

    @Override
    @Transactional
    public String deleteCommentById(Long id) {
        Comments comment = entityManager.find(Comments.class, id);
        if (comment != null) {
            entityManager.remove(comment);
            return MessageFormat.format(DELETE_COMMENT_MESSAGE, comment.getBy());
        }
        return NO_DATA_FOUND_MESSAGE;
    }
}

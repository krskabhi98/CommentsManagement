package com.project.CommentsManagement.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.CommentsManagement.constant.UriConstant;
import com.project.CommentsManagement.model.Comments;
import com.project.CommentsManagement.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v2/comments")
public class CommentController {
    @Autowired
    private ICommentService commentService;

    @GetMapping
    public List<Comments> getAllComments() {

        return commentService.getAllComments();
    }

    @GetMapping(UriConstant.SEARCH)
    public List<Comments> searchComments(
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String date) {
        if (username != null) {
            return commentService.getCommentsByUsername(username);
        } else if (date != null) {
            LocalDateTime dateTime = LocalDateTime.parse(date);
            return commentService.getCommentsByDate(dateTime);
        } else {
            return commentService.getAllComments();
        }
    }


    @PostMapping(UriConstant.ADD_COMMENTS)
    public ResponseEntity<String> createComment(@RequestBody Comments comment) {
        Comments comments=commentService.saveComment(comment);

         return new ResponseEntity<>("Comments created for id : "+comments.getId(), HttpStatus.CREATED);
    }

}


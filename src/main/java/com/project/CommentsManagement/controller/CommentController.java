package com.project.CommentsManagement.controller;


import com.project.CommentsManagement.constant.UriConstant;
import com.project.CommentsManagement.model.Comments;
import com.project.CommentsManagement.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v2/comments")
public class CommentController {
    private static final String COMMNET_ADDED = "Comments added for user : ";
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
        Comments comments = commentService.saveComment(comment);

        return new ResponseEntity<>(COMMNET_ADDED + comments.getBy(), HttpStatus.CREATED);
    }

    @PutMapping(UriConstant.UPDATE_COMMENT)
    public ResponseEntity<String> updateComment(@PathVariable Long id, @RequestBody Comments commentDetails) {
        String response = commentService.updateComment(id, commentDetails);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(UriConstant.DELETE_COMMENT)
    public ResponseEntity<String> deleteComment(@PathVariable Long id) {
        String response = commentService.deleteComment(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}


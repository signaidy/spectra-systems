package SpectraSystems.Nexus.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import SpectraSystems.Nexus.models.Comment;
import SpectraSystems.Nexus.services.CommentService;

import java.util.List;

@RestController
@RequestMapping("/nexus/comments")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    // Endpoint to retrieve all comments
    @GetMapping
    public ResponseEntity<List<Comment>> getAllComments() {
        List<Comment> comments = commentService.getAllComments();
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    // Endpoint to retrieve a comment by ID
    @GetMapping("/{id}")
    public ResponseEntity<Comment> getCommentById(@PathVariable("id") Long id) {
        return commentService.getCommentById(id)
                .map(comment -> new ResponseEntity<>(comment, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Endpoint to create a new comment
    @PostMapping
    public ResponseEntity<Comment> createComment(@RequestBody Comment comment) {
        Comment createdComment = commentService.createComment(comment);
        return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
    }

    // Endpoint to update an existing comment
    @PutMapping("/{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable("id") Long id, @RequestBody Comment commentDetails) {
        Comment updatedComment = commentService.updateComment(id, commentDetails);
        return new ResponseEntity<>(updatedComment, HttpStatus.OK);
    }

    // Endpoint to delete a comment by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable("id") Long id) {
        commentService.deleteComment(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}


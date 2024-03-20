package SpectraSystems.Nexus.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import SpectraSystems.Nexus.exceptions.ResourceNotFoundException;
import SpectraSystems.Nexus.models.Comment;
import SpectraSystems.Nexus.repositories.CommentRepository;
import SpectraSystems.Nexus.repositories.FlightRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    
    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    
    public List<Comment> getCommentsByFlightId(Long flightId) {
        return commentRepository.findByFlightId(flightId);
    }

    
    public Optional<Comment> getCommentById(Long id) {
        return commentRepository.findById(id);
    }

    
    public Comment createComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public Comment updateComment(Long id, Comment commentDetails) {
        Optional<Comment> optionalComment = commentRepository.findById(id);
        if (optionalComment.isPresent()) {
            Comment existingComment = optionalComment.get();
            existingComment.setCreationDate(commentDetails.getCreationDate());
            existingComment.setContent(commentDetails.getContent());
            return commentRepository.save(existingComment);
        } else {
            // Handle if the comment with the given id is not found
            return null;
        }
    }
    
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }
}


package SpectraSystems.Nexus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    public Optional<Comment> getCommentById(Long id) {
        return commentRepository.findById(id);
    }

    public Comment createComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public Comment updateComment(Long id, Comment commentDetails) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found with id: " + id));

        // Update comment details
        comment.setUser(commentDetails.getUser());
        comment.setTitle(commentDetails.getTitle());
        comment.setDate(commentDetails.getDate());
        comment.setMessage(commentDetails.getMessage());

        return commentRepository.save(comment);
    }

    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }
}


package SpectraSystems.Nexus.models;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "NEXUSCOMMENTS")
public class Comment {

     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COMMENT_ID")
    private Long id;

    @Column(name = "USER_ID", nullable = false)
    private Long userId;

    @Column(name = "CONTENT", nullable = false, length = 1000)
    private String message;

    @Column(name = "CREATION_DATE", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.DATE)
    private Date creationDate;

    @Column(name = "PATH", nullable = false, length = 500)
    private String path;

    @ManyToOne
    @JoinColumn(name = "PARENT_COMMENT_ID")
    private Comment parentComment;

    @Column(name = "FLIGHT_ID", nullable = false)
    private Long flightId;

    // Constructors, getters, setters, and other methods...
    
    public Comment() {
        // Default constructor
    }

    public Comment(Long userId, String message, Date creationDate, String path, Long flightId) {
        this.userId = userId;
        this.message = message;
        this.creationDate = creationDate;
        this.path = path;
        this.flightId = flightId;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
    
    public void setPath(String path) {
        this.path = path;
    }
    
    public void setParentComment(Comment parentComment) {
        this.parentComment = parentComment;
    }
    
    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }

    public Long getId() {
        return id;
    }
    
    public Long getUserId() {
        return userId;
    }
    
    public String getMessage() {
        return message;
    }
    
    public Date getCreationDate() {
        return creationDate;
    }
    
    public String getPath() {
        return path;
    }
    
    public Comment getParentComment() {
        return parentComment;
    }
    
    public Long getFlightId() {
        return flightId;
    }
}

package SpectraSystems.Nexus;
import java.util.Date;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "NEXUSCOMMENTS")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "userid", nullable = false)
    private Long userid;

    @Column(nullable = false)
    private String title;

    @Column(name = "datepost", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date datePost;


    @Column(nullable = false)
    private String message;

    // Getters, setters, constructors, and other methods...

    public Comment(){

    }

    public Comment(Long user, String title, Date date, String message) {
        this.userid = user;
        this.title = title;
        this.datePost = date;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUser() {
        return userid;
    }

    public void setUser(Long user) {
        this.userid = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return datePost;
    }

    public void setDate(Date datePost) {
        this.datePost = datePost;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}

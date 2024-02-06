@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userid", nullable = false)
    private User user;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Date date;

    @Column(nullable = false)
    private String message;

    public Comment(Long id, User user, String title, Date date, String message) {
        this.id = id;
        this.user = user;
        this.title = title;
        this.date = date;
        this.message = message;
    }

    // Getters, setters, constructors, and other methods...
}

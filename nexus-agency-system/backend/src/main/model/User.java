@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    private Integer age;

    @Column(nullable = false)
    private String password;

    private String country;

    private String passport;

    public User(Long id, String firstName, String lastName, String email,
                Integer age, String password, String country, String passport) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
        this.password = password;
        this.country = country;
        this.passport = passport;
    }

    // Getters, setters, constructors, and other methods...
}


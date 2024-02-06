@Entity
@Table(name = "flight")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userid", nullable = false)
    private User user;

    @Column(nullable = false)
    private String flightNumber;

    @Column(nullable = false)
    private Date date;

    @Column(nullable = false)
    private String departureLocation;

    @Column(nullable = false)
    private String arrivalLocation;

    private Date returnDate;

    public Flight(Long id, User user, String flightNumber, Date date, String departureLocation, String arrivalLocation, Date returnDate) {
        this.id = id;
        this.user = user;
        this.flightNumber = flightNumber;
        this.date = date;
        this.departureLocation = departureLocation;
        this.arrivalLocation = arrivalLocation;
        this.returnDate = returnDate;
    }
    // Getters, setters, constructors, and other methods...
}

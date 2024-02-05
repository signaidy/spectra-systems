@Entity
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userid", nullable = false)
    private User user;

    @Column(nullable = false)
    private String hotel;

    @Column(nullable = false)
    private Date dateStart;

    @Column(nullable = false)
    private Date dateEnd;

    private String roomNumber;

    @Column(nullable = false, unique = true)
    private String reservationNumber;

    private String location;

    public Reservation(Long id, User user, String hotel, Date dateStart,
                       Date dateEnd, String roomNumber, String reservationNumber,
                       String location) {
        this.id = id;
        this.user = user;
        this.hotel = hotel;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.roomNumber = roomNumber;
        this.reservationNumber = reservationNumber;
        this.location = location;
    }

    // Getters, setters, constructors, and other methods...
}

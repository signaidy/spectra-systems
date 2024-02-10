package SpectraSystems.Nexus;
import java.util.Date;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.TemporalType;


@Entity
@Table(name = "nexusreservation")

public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userid", nullable = false)
    private User user;

    @Column(nullable = false)
    private String hotel;

    @Column(name = "dateStart", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateStart;

    @Column(name = "dateEnd", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateEnd;

    private String roomNumber;

    @Column(nullable = false, unique = true)
    private String reservationNumber;

    private String location;
    
    // Getters, setters, constructors, and other methods...

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getHotel() {
        return hotel;
    }

    public void setHotel(String hotel) {
        this.hotel = hotel;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getReservationNumber() {
        return reservationNumber;
    }

    public void setReservationNumber(String reservationNumber) {
        this.reservationNumber = reservationNumber;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}


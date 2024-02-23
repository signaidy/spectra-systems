package SpectraSystems.Nexus.models;
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
@Table(name = "NEXUSRESERVATION")

public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "userid", nullable = false)
    private Long userid;

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

    public Reservation(){

    }

    public Reservation(Long user, String hotel, Date dateStart,
            Date dateEnd, String roomNumber, String reservationNumber,
            String location) {
        this.userid = user;
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

    public Long getUser() {
        return userid;
    }

    public void setUser(Long user) {
        this.userid = user;
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


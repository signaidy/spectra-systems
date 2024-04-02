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

    @Column(name = "hotel_id")
    private String hotel_id;

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

    @Column(name = "roomType")
    private String roomType;

    @Column(nullable = false, unique = true)
    private String reservationNumber;

    @Column(name = "location")
    private String location;

    @Column(name = "rating")
    private Long rating;

    @Column(name = "bed_size")
    private String bedSize;

    @Column(name = "bed_amount")
    private Integer bedAmount;

    @Column(name = "price")
    private Integer price;
    
    @Column(name = "total_days")
    private Integer totalDays;

    @Column(name = "total_price")
    private Double totalPrice;

    @Column(name = "guests")
    private Integer guests;
    // Getters, setters, constructors, and other methods...

    public Reservation() {

    }

    public Reservation(Long user, String hotel_id, String hotel, Date dateStart, Date dateEnd, String roomType,
            String reservationNumber, String location, String bedSize, Integer bedAmount, Integer price, Integer totalDays, Double totalPrice, Integer guests) {
        this.userid = user;
        this.hotel_id = hotel_id;
        this.hotel = hotel;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.roomType = roomType;
        this.reservationNumber = reservationNumber;
        this.location = location;
        this.bedSize = bedSize;
        this.bedAmount = bedAmount;
        this.price = price;
        this.totalDays = totalDays;
        this.totalPrice = totalPrice;
        this.guests = guests;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHotelId(){
        return hotel_id;
    }

    public void setHotelId(String HotelId){
        this.hotel_id = hotel_id;
    }

    public void setRating(Long rating) {
        this.rating = rating;
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

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
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

    public String getBedSize() {
        return bedSize;
    }
    
    public void setBedSize(String bedSize) {
        this.bedSize = bedSize;
    }
    
    public Integer getBedAmount() {
        return bedAmount;
    }
    
    public void setBedAmount(Integer bedAmount) {
        this.bedAmount = bedAmount;
    }
    
    public Integer getPrice() {
        return price;
    }
    
    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getTotalDays() {
        return totalDays;
    }

    public void setTotalDays(Integer totalDays) {
        this.totalDays = totalDays;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getGuests() {
        return guests;
    }

    public void setGuests(Integer guests) {
        this.guests = guests;
    }
}


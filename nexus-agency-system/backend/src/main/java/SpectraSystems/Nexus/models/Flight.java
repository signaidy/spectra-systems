package SpectraSystems.Nexus.models;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.cglib.core.Local;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.TemporalType;
import lombok.Builder;

@Entity
@Builder
@Table(name = "NEXUSFLIGHT")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "userid", nullable = false)
    private Long userid;

    @Column(nullable = false)
    private String flightNumber;

    @Column(name = "departureDate", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date departureDate;

    @Column(nullable = false)
    private String departureLocation;

    @Column(nullable = false)
    private String arrivalLocation;

    private Date returnDate;

    @OneToMany(mappedBy = "ticketId")
    private List<TicketPurchase> tickets = new ArrayList<>();

    @Column(name = "TYPE")
    private String type;

    @Column(name = "purchase_date", nullable = false)
    private LocalDate purchaseDate;

    @Column(nullable = false)
    private Double price;

    @Column(name = "state")
    private String state;

    private Long rating;
    
    // Getters, setters, constructors, and other methods...

    public Flight(){

    }

    public Flight(Long id, Long user, String flightNumber, Date departureDate, String departureLocation, String arrivalLocation, Date returnDate, List<TicketPurchase> ticketPurchases, String type, LocalDate purchaseDate, Double price, String state, Long rating) {
        this.userid = user;
        this.flightNumber = flightNumber;
        this.departureDate = departureDate;
        this.departureLocation = departureLocation;
        this.arrivalLocation = arrivalLocation;
        this.returnDate = returnDate;
        this.type = type;
        this.price = price;
        this.state = "active";
        this.purchaseDate = LocalDate.now();
    }

    public Flight(Long user, String flightNumber, Date departureDate, String departureLocation, String arrivalLocation, Date returnDate, String type, Double price) {
        this.userid = user;
        this.flightNumber = flightNumber;
        this.departureDate = departureDate;
        this.departureLocation = departureLocation;
        this.arrivalLocation = arrivalLocation;
        this.returnDate = returnDate;
        this.type = type;
        this.price = price;
        this.state = "active";
        this.purchaseDate = LocalDate.now();
    }

    public Long getId() {
        return id;
    }

    public String getState(){
        return state;
    }

    public void setState(String state){
        this.state = state;
    }

    public void setRating(Long rating){
        this.rating = rating;
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

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public String getDepartureLocation() {
        return departureLocation;
    }

    public void setDepartureLocation(String departureLocation) {
        this.departureLocation = departureLocation;
    }

    public String getArrivalLocation() {
        return arrivalLocation;
    }

    public void setArrivalLocation(String arrivalLocation) {
        this.arrivalLocation = arrivalLocation;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getRating(){
        return rating;
    }
}

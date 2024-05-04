package SpectraSystems.Nexus.models;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Builder;

@Entity
@Builder
@Table(name = "FLIGHT")
public class Flight {

    

    @Id
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

    @Column(name = "bundle", nullable = true)
    private String bundle;

    private Long rating;

    private Long providerId;
    
    // Getters, setters, constructors, and other methods...

    public Flight(){

    }

    public Flight(Long id, Long user, String flightNumber, Date departureDate, String departureLocation, String arrivalLocation, Date returnDate, List<TicketPurchase> ticketPurchases, String type, LocalDate purchaseDate, Double price, String state, String bundle, Long rating, Long providerId) {
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

    public Flight(Long id, Long user, String flightNumber, Date departureDate, String departureLocation, String arrivalLocation, Date returnDate, String type, Double price, String bundle) {
        this.id = id;
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
        this.bundle = bundle;
    }

    
    /** 
     * @return Long
     */
    public Long getId() {
        return id;
    }

    
    /** 
     * @return String
     */
    public String getState(){
        return state;
    }

    
    /** 
     * @param state
     */
    public void setState(String state){
        this.state = state;
    }

    
    /** 
     * @param rating
     */
    public void setRating(Long rating){
        this.rating = rating;
    }

    
    /** 
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    
    /** 
     * @return Long
     */
    public Long getUser() {
        return userid;
    }

    
    /** 
     * @param user
     */
    public void setUser(Long user) {
        this.userid = user;
    }

    
    /** 
     * @return String
     */
    public String getFlightNumber() {
        return flightNumber;
    }

    
    /** 
     * @param flightNumber
     */
    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    
    /** 
     * @return Date
     */
    public Date getDepartureDate() {
        return departureDate;
    }

    
    /** 
     * @param departureDate
     */
    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    
    /** 
     * @return String
     */
    public String getDepartureLocation() {
        return departureLocation;
    }

    
    /** 
     * @param departureLocation
     */
    public void setDepartureLocation(String departureLocation) {
        this.departureLocation = departureLocation;
    }

    
    /** 
     * @return String
     */
    public String getArrivalLocation() {
        return arrivalLocation;
    }

    
    /** 
     * @param arrivalLocation
     */
    public void setArrivalLocation(String arrivalLocation) {
        this.arrivalLocation = arrivalLocation;
    }

    
    /** 
     * @return Date
     */
    public Date getReturnDate() {
        return returnDate;
    }

    
    /** 
     * @param returnDate
     */
    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    
    /** 
     * @return String
     */
    public String getType() {
        return type;
    }

    
    /** 
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    
    /** 
     * @return LocalDate
     */
    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    
    /** 
     * @param purchaseDate
     */
    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    
    /** 
     * @return Double
     */
    public Double getPrice() {
        return price;
    }

    
    /** 
     * @param price
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    
    /** 
     * @return Long
     */
    public Long getRating(){
        return rating;
    }

    
    /** 
     * @param bundle
     */
    public void setBundle(String bundle){
        this.bundle = bundle;
    }

    
    /** 
     * @return String
     */
    public String getBundle(){
        return bundle;
    }

    
    /** 
     * @return Long
     */
    public Long getProviderId(){
        return providerId;
    }

    
    /** 
     * @param providerId
     */
    public void setProviderId(Long providerId){
        this.providerId = providerId;
    }

}

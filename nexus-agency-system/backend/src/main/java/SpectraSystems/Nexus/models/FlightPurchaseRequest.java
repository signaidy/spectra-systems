package SpectraSystems.Nexus.models;

import java.util.Date;

public class FlightPurchaseRequest {
    private Long user_id;
    private Long flightId;
    private String state;
    private String type;
    private Long userId;
    private Date departureDate;
    private String departureLocation;
    private String arrivalLocation;
    private Date returnDate;
    private Date purchaseDate;
    private Double price;
    private Long rating;
    private String bundle;

    public FlightPurchaseRequest() {
        // Default constructor
    }

    public FlightPurchaseRequest(Long userId, Long flightId, String state, String type, Date departureDate, String departureLocation, String arrivalLocation, Date returnDate, Long rating, Date purchaseDate, String bundle, Double price) {
        this.userId = userId;
        this.flightId = flightId;
        this.state = state;
        this.type = type;
        this.departureDate = departureDate;
        this.departureLocation = departureLocation;
        this.arrivalLocation = arrivalLocation;
        this.returnDate = returnDate;
        this.rating = rating;
        this.purchaseDate = purchaseDate;
        this.bundle = bundle;
        this.price = price;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getFlightId() {
        return flightId;
    }

    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public Long getRating() {
        return rating;
    }

    public void setRating(Long rating) {
        this.rating = rating;
    }
    
    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setBundle(String bundle){
        this.bundle = bundle;
    }

    public String getBundle(){
        return bundle;
    }
}

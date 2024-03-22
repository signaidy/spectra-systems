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
    private Long rating;

    public FlightPurchaseRequest() {
        // Default constructor
    }

    public FlightPurchaseRequest(Long user_id, Long flightId, String state, String type, Long userId, Date departureDate, String departureLocation, String arrivalLocation, Date returnDate, Long rating) {
        this.user_id = user_id;
        this.flightId = flightId;
        this.state = state;
        this.type = type;
        this.userId = userId;
        this.departureDate = departureDate;
        this.departureLocation = departureLocation;
        this.arrivalLocation = arrivalLocation;
        this.returnDate = returnDate;
        this.rating = rating;
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
}

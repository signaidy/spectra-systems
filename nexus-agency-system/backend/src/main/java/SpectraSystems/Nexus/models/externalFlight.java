package SpectraSystems.Nexus.models;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class externalFlight {
    private Long flightId;
    private Long originCityId;
    private String originCityName;
    private Long destinationCityId;
    private String destinationCityName;
    private Long providerId;

    @JsonProperty("commentaries")
    private List<Comment> commentaries;

    private Rating rating;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date departureDate;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date arrivalDate;
    private double touristPrice;
    private double businessPrice;
    private String detail;
    private int touristQuantity;
    private int businessQuantity;
    private int touristCapacity;
    private int businessCapacity;
    private int state;
    private externalFlight scale;
    
    private externalFlight returnFlight;

    // Constructors, getters, and setters
    public externalFlight() {
        this.scale = null;
        this.returnFlight = null;
        
    }
    public Long getFlightId() {
        return flightId;
    }

    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }

    public Long getOriginCityId() {
        return originCityId;
    }

    public void setOriginCityId(Long originCityId) {
        this.originCityId = originCityId;
    }

    public String getOriginCityName() {
        return originCityName;
    }

    public void setOriginCityName(String originCityName) {
        this.originCityName = originCityName;
    }

    public Long getDestinationCityId() {
        return destinationCityId;
    }

    public void setDestinationCityId(Long destinationCityId) {
        this.destinationCityId = destinationCityId;
    }

    public String getDestinationCityName() {
        return destinationCityName;
    }

    public void setDestinationCityName(String destinationCityName) {
        this.destinationCityName = destinationCityName;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public double getTouristPrice() {
        return touristPrice;
    }

    public void setTouristPrice(double touristPrice) {
        this.touristPrice = touristPrice;
    }

    public double getBusinessPrice() {
        return businessPrice;
    }

    public void setBusinessPrice(double businessPrice) {
        this.businessPrice = businessPrice;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getTouristQuantity() {
        return touristQuantity;
    }

    public void setTouristQuantity(int touristQuantity) {
        this.touristQuantity = touristQuantity;
    }

    public int getBusinessQuantity() {
        return businessQuantity;
    }

    public void setBusinessQuantity(int businessQuantity) {
        this.businessQuantity = businessQuantity;
    }

    public int getTouristCapacity() {
        return touristCapacity;
    }

    public void setTouristCapacity(int touristCapacity) {
        this.touristCapacity = touristCapacity;
    }

    public int getBusinessCapacity() {
        return businessCapacity;
    }

    public void setBusinessCapacity(int businessCapacity) {
        this.businessCapacity = businessCapacity;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public void setCommentaries(List<Comment> commentaries) {
        if (commentaries == null) {
            this.commentaries = Collections.emptyList(); // Initialize with an empty list
        } else {
            this.commentaries = commentaries;
        }
    }

    public List<Comment> getComments(){
        return commentaries;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public externalFlight getScale() {
        return scale;
    }

    public void setScale(externalFlight scale) {
        this.scale = scale;
    }

    public externalFlight getReturnFlight() {
        return returnFlight;
    }

    public void setReturnFlight(externalFlight returnFlight) {
        this.returnFlight = returnFlight;
    }

    public Long getProviderId(){
        return providerId;
    }

    public void setProviderId(Long providerId){
        this.providerId = providerId;
    }
}


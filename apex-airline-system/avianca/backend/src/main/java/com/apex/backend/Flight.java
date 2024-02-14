package com.apex.backend;

public class Flight {
    String originCity;
    String destinationCity;
    String businessCapacity;
    String touristCapacity;
    String type;
    String departureDate;
    String arrivalDate;

    public Flight(String originCity, String destinationCity, String businessCapacity, String touristCapacity,
            String type, String departureDate, String arrivalDate) {
        this.originCity = originCity;
        this.destinationCity = destinationCity;
        this.businessCapacity = businessCapacity;
        this.touristCapacity = touristCapacity;
        this.type = type;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
    }
}

package com.apex.backend;

public class Flight {
    String originCity;
    String destinationCity;
    String type;
    String departureDate;
    String arrivalDate;

    public Flight(String originCity, String destinationCity, String type, String departureDate, String arrivalDate) {
        this.originCity = originCity;
        this.destinationCity =destinationCity;
        this.type = type;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
    }
}

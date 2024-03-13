package com.apex.backend;

public class CityFlight {
    String originCityFlight;
    String destinationCityFlight;
    String departureDate;
    String arrivalDate;
    String detail;

    public CityFlight(
            String originCityFlight,
            String destinationCityFlight,
            String departureDate,
            String arrivalDate,
            String detail) {
        this.originCityFlight = originCityFlight;
        this.destinationCityFlight = destinationCityFlight;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.detail = detail;
    }
}
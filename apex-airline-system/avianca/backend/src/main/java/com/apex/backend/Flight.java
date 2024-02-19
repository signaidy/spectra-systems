package com.apex.backend;

public class Flight {
    int originCity;
    int destinationCity;
    String departureDate;
    String arrivalDate;
    int touristCapacity;
    int businessCapacity;
    int touristPrice;
    int businessPrice;
    int touristQuantity;
    int businessQuantity;
    String detail;
    int type;

    public Flight(
            int originCity,
            int destinationCity,
            String departureDate,
            String arrivalDate,
            int touristCapacity,
            int businessCapacity,
            int touristPrice,
            int businessPrice,
            int touristQuantity,
            int businessQuantity,
            String detail,
            int type) {
        this.originCity = originCity;
        this.destinationCity = destinationCity;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.touristCapacity = touristCapacity;
        this.businessCapacity = businessCapacity;
        this.touristPrice = touristPrice;
        this.businessPrice = businessPrice;
        this.touristQuantity = touristQuantity;
        this.businessQuantity = businessQuantity;
        this.detail = detail;
        this.type = type;
    }
}

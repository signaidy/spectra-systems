package com.apex.backend;

public record CompleteFlightRecord(
        int flightId,
        int originCityId,
        String originCityName,
        int destinationCityId,
        String destinationCityName,
        String departureDate,
        String arrivalDate,
        int touristPrice,
        int businessPrice,
        String detail,
        int touristQuantity,
        int businessQuantity,
        int touristCapacity,
        int businessCapacity,
        int state) {

}

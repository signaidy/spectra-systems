package com.apex.backend;

import java.util.List;

public record FlightRecord(
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
                String type, 
                List<CommentaryRecord> commentaries,
                RatingRecord rating) {

}

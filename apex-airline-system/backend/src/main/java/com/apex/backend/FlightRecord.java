package com.apex.backend;

import java.util.List;

/**
 * La clase `FlightRecord` es un registro que representa información completa de un vuelo, 
 * incluyendo comentarios y valoración.

 * Esta clase crear los detalles de un vuelo, información de las ciudades de origen y destino, fechas, 
 * precios, capacidades, comentarios asociados y valoración general.

  * @author Juan Pablo Estrada Lucero
  * @version 1.0
  */
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
                List<CommentaryRecord> commentaries,
                RatingRecord rating) {

}

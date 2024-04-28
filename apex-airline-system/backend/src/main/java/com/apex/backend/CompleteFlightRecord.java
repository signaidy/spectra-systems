package com.apex.backend;

/**
 * La clase `CompleteFlightRecord` es un registro que representa información completa de un vuelo.

 * Esta clase utiliza el registro de Java para crear todos los detalles de un vuelo, 
 * incluyendo ciudades de origen y destino, fechas, precios, capacidades y estado.

  * @author Juan Pablo Estrada Lucero
  * @version 1.0
  */
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

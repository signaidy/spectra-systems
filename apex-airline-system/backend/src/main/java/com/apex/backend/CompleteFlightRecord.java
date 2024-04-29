package com.apex.backend;

/**
 * La clase `CompleteFlightRecord` es un registro que representa información completa de un vuelo.

 * Esta clase utiliza el registro de Java para crear todos los detalles de un vuelo, 
 * incluyendo ciudades de origen y destino, fechas, precios, capacidades y estado.

  * @author Juan Pablo Estrada Lucero
  * @version 1.0
  */

     /**
     * Constructor de la clase `CompleteFlightRecord`.
     * 
     * @param flightId Identificador del vuelo
     * @param originCityId ID de ciudad de origen
     * @param originCityName Nombre de la ciudad de origen
     * @param destinationCityId ID de ciudad de destino
     * @param destinationCityName Nombre de la ciudad de destino
     * @param departureDate Fecha de salida del vuelo
     * @param arrivalDate Fecha de llegada del vuelo
     * @param touristPrice Precio del boleto de turista
     * @param businessPrice Precio del boleto premium
     * @param detail Detalle adicional
     * @param touristQuantity Cantidad de boletos de turista
     * @param businessQuantity Cantidad de boletos premium
     * @param touristCapacity Cantidad de asietnos de turista
     * @param businessCapacity Cantidad de asientos premium
     * @param state Estado del vuelo
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

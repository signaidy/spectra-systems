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

   /**
     * Constructor de la clase `FlightRecord`.
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
     * @param commentaries Listado de comentarios asociados
     * @param rating Rating asociado al vuelo
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

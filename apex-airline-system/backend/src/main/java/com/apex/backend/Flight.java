package com.apex.backend;

/**
 * La clase `Flight` representa la información de un vuelo.

 * Esta clase almacena los detalles de un vuelo, incluyendo:

 *  * Ciudades de origen y destino (identificadores).
 *  * Fechas de salida y llegada (formato cadena de texto).
 *  * Capacidades y cantidades disponibles para las clases turista y ejecutiva.
 *  * Precios de los boletos para las clases turista y ejecutiva.
 *  * Detalle adicional del vuelo.
 *  * Tipo de vuelo Activo o Inactivo.

  * @author Juan Pablo Estrada Lucero
  * @version 1.0
  */
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

     /**
     * Constructor de la clase `Flight`.
     * 
     * @param originCity Identificador de la ciudad de origen del vuelo.
     * @param destinationCity Identificador de la ciudad de destino del vuelo.
     * @param departureDate Fecha de salida del vuelo (formato cadena de texto).
     * @param arrivalDate Fecha de llegada del vuelo (formato cadena de texto).
     * @param touristCapacity Capacidad total de pasajeros en la clase turista.
     * @param businessCapacity Capacidad total de pasajeros en la clase ejecutiva.
     * @param touristPrice Precio del boleto para la clase turista.
     * @param businessPrice Precio del boleto para la clase ejecutiva.
     * @param touristQuantity Capacidad total de pasajeros en la clase turista.
     * @param businessQuantity Capacidad total de pasajeros en la clase ejecutiva.
     * @param detail Detalle adicional o descripción del vuelo.
     * @param type Tipo de vuelo Activo o Inactivo.
     */

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

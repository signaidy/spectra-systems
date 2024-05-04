package com.apex.backend;

/**
 * Clase que representa los criterios de búsqueda para vuelos.

 * Esta clase toma los detalles de una búsqueda de vuelo, incluyendo origen, destino, 
 * fecha de salida, fecha de regreso (si aplica), número de pasajeros, tipo de vuelo (ida y vuelta 
 * o solo ida), tipo de búsqueda (Rest o webservice) y la fecha en que se realizó 
 * la búsqueda.

 * @author Juan Pablo Estrada Lucero
 */
public class Search {
    int origin;
    int destination;
    String departure_date;
    String return_date;
    int passengers;
    String flight_type;
    String type_search;
    String date_made;

    /**
     * Constructor para la clase `Search`.

     * Este constructor inicializa todos los campos de criterios de búsqueda.

     * @param origin Código de vuelo de origen.
     * @param destination Código de vuelo de destino.
     * @param departure_date Fecha de salida para la búsqueda de vuelo.
     * @param return_date Fecha de regreso para la búsqueda de vuelo(opcional, puede ser null para vuelos solo de ida).
     * @param passengers Número de pasajeros incluidos en la búsqueda de vuelo.
     * @param flight_type Tipo de búsqueda de vuelo (Ida y vuelta o directo).
     * @param type_search Tipo de búsqueda realizada (Rest o webservice).
     * @param date_made Fecha en que se realizó la búsqueda.
     */

    public Search(int origin,
    int destination,
    String departure_date,
    String return_date,
    int passengers,
    String flight_type,
    String type_search,
    String date_made) {
        this.origin = origin;
        this.destination = destination;
        this.departure_date = departure_date;
        this.return_date = return_date;
        this.passengers = passengers;
        this.flight_type = flight_type;
        this.type_search = type_search;
        this.date_made = date_made;
    }

}
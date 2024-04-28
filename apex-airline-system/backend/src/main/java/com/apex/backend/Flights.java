package com.apex.backend;

/**
 * La clase `Flights` representa la información de un vuelo 
 *
 * Esta clase almacena los detalles de un vuelo, incluyendo:
 *  * Ciudades de origen y destino (nombres completos).
 *  * Fechas de salida y llegada (formato cadena de texto).
 *  * Cantidades disponibles para boletos normales y premium.
 *  * Precios base para boletos normales y premium.
 *  * Tipo de vuelo (verdadero para ida y vuelta?, valor booleano poco descriptivo).
 *  * Detalle adicional del vuelo (opcional).
 *  * Estado del vuelo (activo/inactivo?, valor booleano poco descriptivo).

  * @author Juan Pablo Estrada Lucero
  * @version 1.0
  */
public class Flights {
    String origin;
    String destination;
    String departure_date;
    String arrival_date;
    int amount_normal_tickets;
    int amount_premium_tickets;
    int price_normal;
    int price_premium;
    boolean type;
    String detail;
    boolean state;

    /**
     * Constructor de la clase `Flights`.
     * 
     * @param origin Nombre de la ciudad de origen. 
     * @param destination Nombre de la ciudad de destino. 
     * @param departure_date Fecha de salida del vuelo (formato cadena de texto).
     * @param arrival_date Fecha de llegada del vuelo (formato cadena de texto).
     * @param amount_normal_tickets Cantidad de boletos turistas disponibles.
     * @param amount_premium_tickets Cantidad de boletos premium disponibles.
     * @param price_normal Precio base del boleto turista.
     * @param price_premium Precio base del boleto premium.
     * @param type Indica si el vuelo es de ida y vuelta (verdadero) o solo de ida (falso).
     * @param detail Detalle adicional
     * @param state Activo o Inactivo
     */
    

  public Flights(String origin, String destination, String departure_date, String arrival_date, int amount_normal_tickets, int amount_premium_tickets,
  int price_normal, int price_premium, boolean type, String detail,boolean state) {
        this.origin = origin;
        this.destination = destination;
        this.departure_date = departure_date;
        this.arrival_date = arrival_date;
        this.amount_normal_tickets = amount_normal_tickets;
        this.amount_premium_tickets = amount_premium_tickets;
        this.price_normal = price_normal;
        this.price_premium = price_premium;
        this.price_premium = price_premium;
        this.type = type;
        this.detail = detail;
        this.state = state;
    }

}
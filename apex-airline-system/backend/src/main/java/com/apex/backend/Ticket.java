package com.apex.backend;

/**
 * La clase `Ticket` representa un boleto de vuelo.

 * Esta clase almacena la información básica de un boleto, incluyendo el identificador único 
 * del boleto, su precio, el identificador del vuelo asociado, el tipo de boleto 
 * y el estado del boleto.

  * @author Juan Pablo Estrada Lucero
  * @version 1.0
  */
public class Ticket {
    /**
     * Identificador único del boleto.
     */
    Integer ticket_id;
    /**
     * Precio del boleto.
     */
    Integer price;
    /**
     * Identificador del vuelo asociado al boleto.
     */
    Integer flight_id; 
     /**
     * Tipo de boleto
     */
    Integer type;
    /**
     * Estado de boleto
     */
    Integer state;

    /**
     * Constructor de la clase `Ticket`.
     * 
     * @param ticket_id Identificador único del boleto.
     * @param price Precio del boleto.
     * @param flight_id Identificador del vuelo asociado al boleto.
     * @param type Tipo de boleto
     * @param state Estado del boleto
     */
    public Ticket(Integer ticket_id, Integer price, Integer flight_id, Integer type, Integer state) {
        this.ticket_id = ticket_id; 
        this.price = price; 
        this.type = type; 
        this.state = state;
        this.flight_id = flight_id;  
    }
}

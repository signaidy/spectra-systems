package com.apex.backend;

/**
 * La clase `Ticket_purchase` representa la información de una compra de boleto.

 * Esta clase almacena los detalles de una compra de boleto, incluyendo el identificador 
 * del boleto, el identificador del usuario que realizó la compra, el identificador del vuelo 
 * asociado, el tipo de boleto y el estado del vuelo el cual sera enviado en todo momento como activo
 * por la logica establecida del negocio y en el FE
  * @author Juan Pablo Estrada Lucero
  * @version 1.0
  */
public class Ticket_purchase {
    /**
     * Identificador único del boleto comprado.
     */
    int ticket_id;
    /**
     * Identificador del usuario que realizó la compra del boleto.
     */
    int user_id;
    /**
     * Identificador del vuelo asociado a la compra del boleto.
     */
    int flight_id; 
    /**
     * Tipo de boleto comprado.
     */
    String type;
     /**
     * Estado actual de la compra del vuelo. 
     */
    String state;

    /**
     * Constructor de la clase `Ticket_purchase`.
     * 
     * @param ticket_id Identificador único del boleto comprado.
     * @param user_id Identificador del usuario que realizó la compra del boleto.
     * @param flight_id Identificador del vuelo asociado a la compra del boleto.
     * @param type Tipo de boleto comprado (opcional, depende del sistema).
     * @param state Estado actual de la compra del vuelo. 
     */
    public Ticket_purchase(int ticket_id, int user_id, int flight_id, String type, String state) {
        this.ticket_id = ticket_id; 
        this.user_id = user_id; 
        this.type = type; 
        this.state = state;
        this.flight_id = flight_id;  
    }
}

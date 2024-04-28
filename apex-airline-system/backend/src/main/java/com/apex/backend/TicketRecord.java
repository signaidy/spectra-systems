package com.apex.backend;

/**
 * La clase `TicketRecord` es un registro que representa un resumen detallado de un boleto.

 * Esta clase registra la información de un boleto junto con detalles del usuario asociado a la compra 

  * @author Juan Pablo Estrada Lucero
  * @version 1.0
  */

  /**
   * Constructor de la clase `TicketRecord`.
   * 
   * @param ticketId Identificador único del boleto.
   * @param price Precio del boleto.
   * @param flightId Identificador del vuelo asociado al boleto.
   * @param type Tipo de boleto.
   * @param state Estado actual del boleto.
   * @param userId  Identificador del usuario que compró el boleto .
   * @param userName Nombre del usuario que compró el boleto.
   * @param email Correo electrónico del usuario que compró el boleto .
   */
public record TicketRecord(
    /**
     * Identificador único del boleto.
     */
    int ticketId, 
    /**
     * Precio del boleto.
     */
    int price, 
    /**
     * Identificador del vuelo asociado al boleto.
     */
    int flightId, 
    /**
     * Tipo de boleto.
     */
    String type, 
    /**
     * Estado actual del boleto.
     */
    String state, 
    /** 
     * Identificador del usuario que compró el boleto .
     */
    int userId, 
     /** 
      * Nombre del usuario que compró el boleto.
      */
    String userName, 
    /** 
     * Correo electrónico del usuario que compró el boleto .
     */
    String email) {
    
}

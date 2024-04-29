package com.apex.backend;

/**
 * La clase `Purchase` representa una compra realizada por un usuario.

 * Esta clase almacena la información básica de una compra, incluyendo el identificador 
 * del usuario que realizó la compra, el identificador del boleto comprado y el método 
 * de pago utilizado.

  * @author Juan Pablo Estrada Lucero
  * @version 1.0
  */
public class Purchase {
    /**
     * Identificador del usuario que realizó la compra.
     */
    Integer user_id; 
    /**
     * Identificador del boleto comprado.
     */
    Integer ticket_id;
    /**
     * Método de pago utilizado para la compra (por ejemplo, "tarjeta de crédito", "débito").
     */
    String paymenth_method; 

    
    /**
     * Constructor de la clase `Purchase`.
     * 
     * @param user_id Identificador del usuario que realizó la compra.
     * @param ticket_id Identificador del boleto comprado.
     * @param paymenth_method Método de pago utilizado para la compra (por ejemplo, "tarjeta de crédito", "débito").
     */

    public Purchase(Integer user_id, Integer ticket_id, String paymenth_method){
        this.user_id = user_id;
        this.ticket_id = ticket_id;
        this.paymenth_method = paymenth_method; 
    }
    
}
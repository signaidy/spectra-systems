package com.apex.backend;

/**
 * La clase `Rating` representa una calificación de un vuelo hecha por un usuario.

 * Esta clase almacena la información básica de una calificación, incluyendo el identificador 
 * del usuario que realizó la calificación, el identificador del vuelo calificado y el valor 
 * numérico de la calificación.

  * @author Juan Pablo Estrada Lucero
  * @version 1.0
  */
public class Rating {
    /**
     * Identificador del usuario que realizó la calificación.
     */
    int userId;
    /**
     * Identificador del vuelo calificado.
     */
    int flightId;
    /**
     * Valor numérico de la calificación.
     */
    int value;

    /**
     * Constructor de la clase `Rating`.
     * 
     * @param userId Identificador del usuario que realizó la calificación.
     * @param flightId Identificador del vuelo calificado.
     * @param value Valor numérico de la calificación.
     */
    public Rating(int userId, int flightId, int value) {
        this.userId = userId;
        this.flightId = flightId;
        this.value = value;
    }
}

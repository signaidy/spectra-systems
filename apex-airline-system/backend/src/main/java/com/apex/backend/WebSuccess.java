package com.apex.backend;

/**
 * La clase `WebSuccess` es un registro que representa una respuesta exitosa de la API.

 * Este registro se utiliza para un mensaje de confirmación 
 * o éxito simple tras una interacción con el backend.

  * @author Juan Pablo Estrada Lucero
  * @version 1.0
  */
record WebSuccess(
    /**
     * Mensaje de confirmación o éxito simple.
     */
    String success) {
}


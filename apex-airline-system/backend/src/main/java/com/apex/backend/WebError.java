package com.apex.backend;

/**
 * La clase `WebError` es un registro que representa un error web.

 * Este registro se utiliza para  información sobre un error ocurrido 
 * durante una interacción con el backend.

  * @author Juan Pablo Estrada Lucero
  * @version 1.0
  */
record WebError(
    /**
     * Mensaje de error descriptivo.
     */
    String error) {
}

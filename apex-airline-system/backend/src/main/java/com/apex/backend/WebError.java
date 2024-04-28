package com.apex.backend;

/**
 * La clase `WebError` es un registro inmutable que representa un error web.

 * Este registro se utiliza para encapsular información sobre un error ocurrido 
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

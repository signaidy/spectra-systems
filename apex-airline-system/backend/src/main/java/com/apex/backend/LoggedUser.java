package com.apex.backend;

/**
 * La clase `LoggedUser` es un registro que representa la información de un usuario 
 * que ha iniciado sesión en la aplicación.

 * Esta clase contiene los detalles del usuario logueado, incluyendo su identificador, correo electrónico, 
 * nombres, información de pasaporte, rol en el sistema, edad, 
 * porcentaje asociado (opcional) y fecha de inicio de sesión.

  * @author Juan Pablo Estrada Lucero
  * @version 1.0
  */
public record LoggedUser(String userId, String email, String firstName, String lastName, String originCountry,
        String passportNumber, String role, String age, String percentage, String entryDate) {
}

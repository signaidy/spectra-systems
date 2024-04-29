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

  /**
   * Constructor de la clase `LoggedUser`.
   * 
   *@param userId Identificador del usuario
   * @param email Correo electronico asociado al perfil del usuario
   * @param firstName Nombre del usuario
   * @param lastName Apellidos del usuario
   * @param originCountry Pais de origen
   * @param passportNumber  Numero de pasaporte de la persona
   * @param role Rol del usuario dentro del portal
   * @param age Edad del usuario
   * @param percentage Porcentaje de descuento asociado al usuario (si es webservice)
   * @param entryDate Fecha de ingreso del usuario
   */
public record LoggedUser(String userId, String email, String firstName, String lastName, String originCountry,
        String passportNumber, String role, String age, String percentage, String entryDate) {
}

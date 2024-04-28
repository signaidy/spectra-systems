package com.apex.backend;

/**
 * La clase `User` representa la información de un usuario en el sistema.

 * Esta clase almacena los datos básicos de un usuario, incluyendo su correo electrónico 
 * (unico y no repetible entre los usuarios), contraseña (hasheada), nombre, apellido, 
 * país de origen, número de pasaporte, edad, porcentaje, rol del usuario 
 * e identificador único del usuario.
  * @author Juan Pablo Estrada Lucero
  * @version 1.0
  */

public class User {
    /**
     * Correo electrónico del usuario (credencial única para iniciar sesión).
     */
    String email;
     /**
     * Contraseña del usuario (almacenada de forma segura con hash).
     */
    String password;
    /**
     * Nombre del usuario.
     */
    String firstName;
    /**
     * Apellido del usuario.
     */
    String lastName;
    /**
     * País de origen del usuario.
     */
    String originCountry;
     /**
     * Número de pasaporte del usuario.
     */
    String passportNumber;
     /**
     * Edad del usuario.
     */
    String age;
    /**
     * Porcentaje asociado al usuario.
     */
    String percentage;
    /**
     * Rol del usuario dentro del sistema
     */
    String role;
     /**
     * Identificador único del usuario dentro del sistema.
     */
    String userId;

    /**
     * Constructor de la clase `User`.
     * 
     * @param email Correo electrónico del usuario (credencial única para iniciar sesión).
     * @param password Contraseña del usuario (almacenada de forma segura con hash).
     * @param firstName Nombre del usuario.
     * @param lastName Apellido del usuario.
     * @param originCountry País de origen del usuario.
     * @param passportNumber Número de pasaporte del usuario.
     * @param age Edad del usuario.
     * @param percentage Porcentaje asociado al usuario.
     * @param role Rol del usuario dentro del sistema.
     * @param userId Identificador único del usuario dentro del sistema.
     */
    public User(String email, String password, String firstName, String lastName, String originCountry,
            String passportNumber, String age, String percentage, String role, String userId) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.originCountry = originCountry;
        this.passportNumber = passportNumber;
        this.age = age;
        this.percentage = percentage;
        this.role = role;
        this.userId = userId;
    }
}

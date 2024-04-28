package com.apex.backend;

/**
 * La clase `City` representa una ciudad con su identificador y nombre.

 * Esta clase utiliza el registro de Java para crear un tipo inmutable que encapsula 
 * los datos de una ciudad.

 * @author Juan Pablo Estrada Lucero
 * @version 1.0
 */
public record City(String cityId, String name) {
}

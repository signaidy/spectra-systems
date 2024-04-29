package com.apex.backend;

/**
 * La clase `City` representa una ciudad con su identificador y nombre.

 * Esta clase utiliza el registro de Java para crear un tipo inmutable que encapsula 
 * los datos de una ciudad.

 * @author Juan Pablo Estrada Lucero
 * @version 1.0
 */

 /**
   * Constructor de la clase `CIty`.
   * 
   * @param cityId ID de ciudad
   * @param name Nombre de la ciudad asociado al ID
   */
public record City(String cityId, String name) {
}

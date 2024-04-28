package com.apex.backend;

/**
 * La clase `RatingRecord` es un registro que representa un rating realizado por el usuario considerando
 * la toma de los valores correspondientes

 * Esta clase devuelve el promedio y la cantidad de calificaciones registradas.

  * @author Juan Pablo Estrada Lucero
  * @version 1.0
  */
public record RatingRecord(
                /**
                 * Promedio de las calificaciones registradas.
                 */
                int average,
                /**
                 * Cantidad total de calificaciones registradas.
                 */
                int count) {

}

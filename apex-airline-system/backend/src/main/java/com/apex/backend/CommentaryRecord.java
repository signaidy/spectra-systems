package com.apex.backend;

import java.util.List;
/**
 * La clase `CommentaryRecord` es un registro que representa un comentario asociado a un vuelo, 
 * incluyendo información adicional.

 * Esta clase permite tener un record del commentario al momento de utilizarlo en algunas APIS tiene la misma, 
 * funcionalidad que la clase `Commentary`

  * @author Juan Pablo Estrada Lucero
  * @version 1.0
  */

  /**
   * Constructor de la clase `CommentaryRecord`.
   * 
   * @param commentId Identificador único del comentario.
   * @param parentCommentId Identificador del comentario al que responde (padre)
   * @param userId Identificador del usuario
   * @param content Contenido del comentario
   * @param creationDate Fecha de creacion del comentario
   * @param path  Path de creacion del comentario
   * @param flightId Identificador del vuelo en el que se hizo el comentario
   * @param userName Nombre del usuario .
   * @param children Creacion de comentario hijo al listado de los demas .
   */
public record CommentaryRecord(int commentId, int parentCommentId, int userId, String content, String creationDate,
        String path, int flightId, String userName, List<CommentaryRecord> children) {

}

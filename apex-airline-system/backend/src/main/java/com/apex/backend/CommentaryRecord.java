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
public record CommentaryRecord(int commentId, int parentCommentId, int userId, String content, String creationDate,
        String path, int flightId, String userName, List<CommentaryRecord> children) {

}

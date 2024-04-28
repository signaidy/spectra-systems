package com.apex.backend;

/**
 * La clase `Commentary` representa un comentario asociado a un vuelo.

  * Esta clase almacena la información de un comentario realizado por un usuario 
  * sobre un vuelo específico.

  * @author Juan Pablo Estrada Lucero
  * @version 1.0
  */
public class Commentary {
    /**
     * Identificador del comentario padre (opcional).
     * 
     * Este atributo se utiliza para crear relacion de comentarions, donde un comentario
     * puede responder a otro comentario existente. Si el comentario no es una respuesta
     * a otro, este valor será 0.
     */
    int parentId;
     /**
     * Identificador del usuario que realizó el comentario.
     * 
     * Este atributo permite vincular el comentario a un usuario específico del sistema.
     */
    int userId;
    /**
     * Contenido del comentario.
     * 
     * Este atributo representa el texto que el usuario escribió en el comentario.
     */
    String content;
     /**
     * Identificador del vuelo al que está asociado el comentario.
     * 
     * Este atributo vincula el comentario a un vuelo específico del sistema.
     */
    int flightId;

    /**
     * Constructor de la clase `Commentary`.
     * 
     * @param parentId Identificador del comentario padre (opcional).
     * @param userId Identificador del usuario que realizó el comentario.
     * @param content Contenido del comentario.
     * @param flightId Identificador del vuelo al que está asociado el comentario.
     */
    public Commentary(
            int parentId,
            int userId,
            String content,
            int flightId) {
        this.parentId = parentId;
        this.userId = userId;
        this.content = content;
        this.flightId = flightId;

    }
}


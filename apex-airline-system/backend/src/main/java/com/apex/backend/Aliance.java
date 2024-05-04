package com.apex.backend;

/**
 * Clase que representa los criterios de inserción de alianza.

 * Esta clase toma los detalles de una alianza de hoteles, 
 * para poder insertar su inforamción respectiva para llevar a cabo la respuesta respectiva.

 * @author Juan Pablo Estrada Lucero
 */
public class Aliance {
    String IP;
    String endpoint; 
    String key; 

    /**
     * Constructor para la clase `Aliance`.

     * Este constructor inicializa todos los campos de criterios de alianzas.

     * @param IP Representa la IP del hotel aliado.
     * @param endpoint Endpoint de la API al cual hará el llamado.
     * @param key Clave necesaria para zen.
     */

    public Aliance(String IP,
    String endpoint,
    String key) {
        this.IP = IP;
        this.endpoint = endpoint;
        this.key = key;
    }

}
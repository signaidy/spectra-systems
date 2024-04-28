package com.apex.backend;

/**
 * La clase `Partners` representa la información de la sección de socios.

 * Esta clase almacena el título, descripción general y detalles de hasta cinco socios 
 * comerciales. Cada socio se representa por un nombre y un texto asociado 

  * @author Juan Pablo Estrada Lucero
  * @version 1.0 
  */
public class Partners {
    String Title;
    String Description;
    String Partner1;
    String L1;
    String Partner2;
    String L2;
    String Partner3;
    String L3;
    String Partner4;
    String L4;
    String Partner5;
    String L5;

    /**
     * Constructor de la clase `Partners`.
     * 
     * @param Title Título de la sección de socios.
     * @param Description Descripción general de la sección de socios.
     * @param Partner1 Nombre del primer socio comercial.
     * @param L1 Enlace a pagina del primer socio.
     * @param Partner2 Nombre del segundo socio comercial.
     * @param L2 Enlace a pagina del segundo socio.
     * @param Partner3 Nombre del tercer socio comercial.
     * @param L3 Enlace a pagina del tercer socio.
     * @param Partner4 Nombre del cuarto socio comercial.
     * @param L4 Enlace a pagina del cuarto socio.
     * @param Partner5 Nombre del quinto socio comercial.
     * @param L5 Enlace a pagina del quinto socio.
     */

    public Partners(String Title, String Description, String Partner1, String L1, String Partner2, String L2, String Partner3, String L3, String Partner4, String L4,
    String Partner5, String L5) {
        this.Title = Title;
        this.Description = Description;
        this.Partner1 = Partner1;
        this.L1 = L1;
        this.Partner2 = Partner2;
        this.L2 = L2;
        this.Partner3 = Partner3;
        this.L3 = L3;
        this.Partner4 = Partner4;
        this.L4 = L4;
        this.Partner5 = Partner5;
        this.L5 = L5; 
    }
}

package com.apex.backend;

/**
 * La clase `Footer` representa la información del pie de página de sitio.

 * Esta clase almacena los textos y enlaces que se muestran en el pie de página 
 * agrupados en secciones para una mejor organización.

  * @author Juan Pablo Estrada Lucero
  * @version 1.0 
  */
public class Footer {
    String Title_1;
    String Section_1;
    String L1;
    String Section_2;
    String L2;
    String Section_3;
    String L3;
    String Section_4;
    String L4;
    String Section_5;
    String L5;
    String Section_6;
    String L6;

    String Title_2;
    String Quick_Section_1;
    String Link_quick_1;
    String Quick_Section_2;
    String Link_quick_2;

    String Title_3;
    String Contact_1;
    String Contact_2;

    String copyright; 

     /**
     * Constructor de la clase `Footer`.
     * 
     * @param Title_1 Título principal de la primera sección del pie de página.
     * @param Section_1 Título de la primera subsección de la primera sección.
     * @param L1 Link de la primera seccion.
     * @param Section_2 Título de la segunda subsección de la primera sección.
     * @param L2 Link de la segunda seccion.
     * @param Section_3 Título de la tercera subsección de la primera sección.
     * @param L3 Link de la tercera seccion.
     * @param Section_4 Título de la cuarta subsección de la primera sección.
     * @param L4 Link de la cuarta seccion.
     * @param Section_5 Título de la quinta subsección de la primera sección.
     * @param L5 Link de la quinta seccion.
     * @param Section_6 Título de la sexta subsección de la primera sección.
     * @param L6 Link de la sexta seccion.
     * @param Title_2 Título principal de la segunda sección del pie de página.
     * @param  Quick_Section_1 Titulo de la primera subseccion de la segunda seccion
     * @param  Link_quick_1 Link de la primera subseccion de la segunda seccion
     * @param  Quick_Section_2 Titulo de la segunda subseccion de la segunda seccion
     * @param  Link_quick_2 Link de la segunda subseccion de la segunda seccion
     * @param Title_3 Título principal de la tercera sección del pie de página.
     * @param Contact_1 Informacion de contacto 1.
     * @param Contact_2 Informacion de contacto 2.
     * @param copyright Texto de copyright del pie de página.
     */

    public Footer(    String Title_1,
    String Section_1,
    String L1,
    String Section_2,
    String L2,
    String Section_3,
    String L3,
    String Section_4,
    String L4,
    String Section_5,
    String L5,
    String Section_6,
    String L6,

    String Title_2,
    String Quick_Section_1,
    String Link_quick_1,
    String Quick_Section_2,
    String Link_quick_2,

    String Title_3,
    String Contact_1,
    String Contact_2,

    String copyright) {
        this.Title_1 = Title_1;
        this.Section_1 = Section_1;
        this.L1 = L1;
        this.Section_2 = Section_2;
        this.L2 = L2;
        this.Section_3 = Section_3;
        this.L3 = L3;
        this.Section_4 = Section_4;
        this.L4 = L4;
        this.Section_5 = Section_5;
        this.L5 = L5;
        this.Section_6 = Section_6;
        this.L6 = L6;

        this.Title_2 = Title_2;
        this.Quick_Section_1 = Quick_Section_1;
        this.Link_quick_1 = Link_quick_1; 
        this.Quick_Section_2 = Quick_Section_2;
        this.Link_quick_2 = Link_quick_2; 

        this.Title_3 = Title_3; 
        this.Contact_1 = Contact_1; 
        this.Contact_2 = Contact_2; 

        this.copyright = copyright; 
    }
}

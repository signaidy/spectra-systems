package com.apex.backend;

/**
 * La clase `Aboutus` representa la información de la sección "Acerca de nosotros".

 * Esta clase contiene toda la informacion necesaria respecto a los atributos de 
 * guardado para el apartado de la pagina de about us de la pagina. 

 * @author Juan Pablo Estrada Lucero
 * @version 1.0
 */


public class Aboutus {
    String slogan;
    String gif;
    String yt;
    int cards_amoun;
    String title_one;
    String text_one;
    String img_one;
    String title_two;
    String text_two;
    String img_two;
    String title_three;
    String text_three;
    String img_three;
    String title_four;
    String text_four;
    String img_four;

      /**
   * Constructor de la clase `Aboutus`.
   * 
    * @param slogan El eslogan de la compañía.
    * @param gif La URL de un GIF animado de la compañía a mostrar.
    * @param yt URL del video a mostrar dentro de esta sección relacionado con la compañía.
    * @param cards_amoun La cantidad de tarjetas informativas que se mostrarán.
    * @param title_one Título de la primera tarjeta informativa
    * @param text_one Texto de la primera tarjeta informativa
    * @param img_one Imagen de la primera tarjeta informativa.
    * @param title_two Título de la segunda tarjeta informativa
    * @param text_two Texto de la segunda tarjeta informativa
    * @param img_two Imagen de la segunda tarjeta informativa.
    * @param title_three Título de la tercera tarjeta informativa
    * @param text_three Texto de la tercera tarjeta informativa
    * @param img_three Imagen de la tercera tarjeta informativa.
    * @param title_four Título la cuarta tarjeta informativa
    * @param text_four Textode la cuarta tarjeta informativa
    * @param img_four Imagen de la cuarta tarjeta informativa.   
   */

    public Aboutus(String slogan,
            String gif,
            String yt,
            int cards_amoun,
            String title_one,
            String text_one,
            String img_one,
            String title_two,
            String text_two,
            String img_two,
            String title_three,
            String text_three,
            String img_three,
            String title_four,
            String text_four,
            String img_four) {
        this.slogan = slogan;
        this.gif = gif;
        this.yt = yt;
        this.cards_amoun = cards_amoun;
        this.title_one = title_one;
        this.text_one = text_one;
        this.img_one = img_one;
        this.title_two = title_two;
        this.text_two = text_two;
        this.img_two = img_two;
        this.title_three = title_three;
        this.text_three = text_three;
        this.img_three = img_three;
        this.title_four = title_four;
        this.text_four = text_four;
        this.img_four = img_four;

    }
}

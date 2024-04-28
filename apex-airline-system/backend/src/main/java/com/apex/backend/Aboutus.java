package com.apex.backend;

/**
 * La clase `Aboutus` representa la información de la sección "Acerca de nosotros".

 * Esta clase almacena los datos necesarios para construir dicha sección, incluyendo:

 * `slogan`: El eslogan de la compañía.
 * `gif`: La URL de un GIF animado de la compañía a mostrar.
 * `yt`: URL del video a mostrar dentro de esta sección relacionado con la compañía.
 * `cards_amount`: La cantidad de tarjetas informativas que se mostrarán.
 * `title_one`, `text_one`, `img_one`: Título, texto e imagen de la primera tarjeta informativa.
 * `title_two`, `text_two`, `img_two`: Título, texto e imagen de la segunda tarjeta informativa.
 * `title_three`, `text_three`, `img_three`: Título, texto e imagen de la tercera tarjeta informativa.
 * `title_four`, `text_four`, `img_four`: Título, texto e imagen de la cuarta tarjeta informativa.

 * @author (Juan Pablo Estrada Lucero)
 * @version (1)
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

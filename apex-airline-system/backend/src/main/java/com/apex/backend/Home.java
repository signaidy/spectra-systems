package com.apex.backend;

/**
 * La clase `Home` representa la información de la página principal.

 * Esta clase almacena las rutas o referencias a las imágenes de fondo y de vuelos 
 * destacados, junto con los títulos y contenidos asociados a cada sección destacada 
 * en la página principal.

  * @author Juan Pablo Estrada Lucero
  * @version 1.0
  */

public class Home {
    String Background;
    String FlightImage1;
    String Title1;
    String Content1;
    String FlightImage2;
    String Title2;
    String Content2;
    String FlightImage3;
    String Title3;
    String Content3;

    /**
     * Constructor de la clase `Home`.
     * 
     * @param Background Ruta de la imagen de fondo de la página principal.
     * @param FlightImage1 Ruta o referencia a la imagen del primer vuelo destacado.
     * @param Title1 Título del primer vuelo destacado.
     * @param Content1 Contenido descriptivo del primer vuelo destacado.
     * @param FlightImage2 Ruta o referencia a la imagen del segundo vuelo destacado.
     * @param Title2 Título del segundo vuelo destacado.
     * @param Content2 Contenido descriptivo del segundo vuelo destacado.
     * @param FlightImage3 Ruta o referencia a la imagen del tercer vuelo destacado.
     * @param Title3 Título del tercer vuelo destacado.
     * @param Content3 Contenido descriptivo del tercer vuelo destacado.
     */

  public Home(String Background,
  String FlightImage1,
  String Title1,
  String Content1,
  String FlightImage2,
  String Title2,
  String Content2,
  String FlightImage3,
  String Title3,
  String Content3) {
        this.Background = Background;
        this.FlightImage1 = FlightImage1;
        this.Title1 = Title1;
        this.Content1 = Content1;
        this.FlightImage2 = FlightImage2;
        this.Title2 = Title2;
        this.Content2 = Content2;
        this.FlightImage3 = FlightImage3;
        this.Title3 = Title3;
        this.Content3 = Content3;
    }

}
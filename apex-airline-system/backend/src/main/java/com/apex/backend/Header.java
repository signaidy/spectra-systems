package com.apex.backend;

/**
 * La clase `Header` representa la información del encabezado.

 * Esta clase almacena los textos, enlaces e imagen del logo que se muestran en el encabezado 

  * @author Juan Pablo Estrada Lucero
  * @version 1.0
  */
public class Header {
    String Text_Logo;
    String Section;
    String Link_Section;
    String Link_Profile;
    String Link_Login;
    String Logo; 

    /**
     * Constructor de la clase `Header`.
     * 
     * @param Text_Logo Texto del logo.
     * @param Section Título de la sección principal que aparacera en el encabezado.
     * @param Link_Section Enlace asociado a la sección principal del encabezado.
     * @param Link_Profile Enlace asociado al perfil de usuario (si aplica).
     * @param Link_Login Enlace asociado al inicio de sesión (si aplica).
     * @param Logo URL de la imagen del logo.
     */

    public Header(String Text_Logo,
    String Section,
    String Link_Section,
    String Link_Profile,
    String Link_Login, 
    String Logo) {
        this.Text_Logo = Text_Logo;
        this.Section = Section;
        this.Link_Section = Link_Section;
        this.Link_Profile = Link_Profile;
        this.Link_Login = Link_Login;
        this.Logo = Logo; 

    }
}

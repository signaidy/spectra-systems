package com.apex.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

/**
 * Clase principal de aplicación para el backend del sistema Apex Airline.

 * Esta clase sirve como punto de entrada para la aplicación backend. Aprovecha Spring Boot 
 * para simplificar la configuración y el arranque.

 * SpringBootApplication: Anota esta clase como una aplicación Spring Boot, habilitando 
 * el escaneo automático de componentes, la vinculación de propiedades de configuración y la autoconfiguración.

 * exclude SecurityAutoConfiguration: Excluye la autoconfiguración predeterminada de Spring Security 
 * de ser aplicada a la aplicación. Esto podría ser necesario si elige implementar 
 * mecanismos de seguridad personalizados o utilizar un marco de seguridad diferente.
 */
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class BackendApplication {
	
	/**
	 * Punto de entrada principal de la aplicación backend Apex Airline.

	* Este método inicia la aplicación Spring Boot, cargando la configuración y ejecutando los beans de Spring.

	* @param args Argumentos de línea de comandos pasados ​​al ejecutar la aplicación.
	*/
	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

}

package com.apex.backend;

import java.sql.*;
import oracle.jdbc.pool.OracleDataSource;


/**
 * Clase para establecer una conexión a una base de datos Oracle.

 * Esta clase ofrece una forma conveniente de conectarse a una base de datos Oracle al manejar 
 * los detalles de conexión y proporcionar un método para recuperar la conexión establecida.
 
 */
public class OracleConnector {
    private Connection conn;

    /**
     * Crea una nueva instancia de `OracleConnector` e intenta establecer una conexión 
     * a la base de datos Oracle configurada.

     * Este constructor intenta crear una conexión a la base de datos Oracle utilizando los 
     * detalles de conexión proporcionados (URL, nombre de usuario y contraseña). En caso de 
     * fallo de conexión, se imprime un mensaje de error y se lanza una excepción.

     */
    public OracleConnector(String username) {
        try {
            System.out.println("USER:   " + username);
            OracleDataSource ods = new OracleDataSource();
            ods = new OracleDataSource();
            ods.setURL("jdbc:oracle:thin:@137.184.88.75:1521/FREE");
            ods.setUser(username);
            ods.setPassword("sudoadmin");
            conn = ods.getConnection();
        } catch (SQLException e) {
            System.out.println("Failed to connect to the database.");
            e.printStackTrace();
        }
    }

    /**
     * Devuelve la conexión establecida a la base de datos Oracle.

     * Este método permite que otras partes de la aplicación recuperen el objeto de conexión 
     * establecido durante la construcción de la instancia `OracleConnector`.

     * @return El objeto de conexión establecido, o null si no se pudo establecer una conexión.
     */
    public Connection getConnection() {
        return conn;
    }
}
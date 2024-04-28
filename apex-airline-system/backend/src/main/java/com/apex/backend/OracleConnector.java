package com.apex.backend;

import java.sql.*;
import oracle.jdbc.pool.OracleDataSource;

public class OracleConnector {
    private Connection conn;

    public OracleConnector() {
        try {
            OracleDataSource ods = new OracleDataSource();
            ods = new OracleDataSource();
            ods.setURL("jdbc:oracle:thin:@137.184.88.75:1521/FREE");
            ods.setUser("system");
            ods.setPassword("sudoadmin");
            conn = ods.getConnection();
        } catch (SQLException e) {
            System.out.println("Failed to connect to the database.");
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return conn;
    }
}
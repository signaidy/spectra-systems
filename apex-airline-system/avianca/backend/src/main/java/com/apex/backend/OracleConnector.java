package com.apex.backend;

import java.sql.*;
import oracle.jdbc.pool.OracleDataSource;

public class OracleConnector {
    private Connection conn;

    public OracleConnector() {
        try {
            OracleDataSource ods = new OracleDataSource();
            ods = new OracleDataSource();
            ods.setURL("");
            ods.setUser("");
            ods.setPassword("");
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
package com.nathan.javafxperiode2faopdrenergiecurrent.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {

    public Connection getConnection() {
        String url = "jdbc:mysql://localhost:3306/energy_current?user=root&password=";
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e){
            e.printStackTrace();
        }
        return conn;
    }
}

package com.nathan.javafxperiode2faopdrenergiecurrent.dao;

import com.nathan.javafxperiode2faopdrenergiecurrent.model.Rates;
import com.nathan.javafxperiode2faopdrenergiecurrent.service.DBConnector;

import java.sql.*;

public class RatesDAO {
    private Connection connection;

    public RatesDAO() {
        try {
            DBConnector db = new DBConnector();
            connection = db.getConnection();
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public void addRate(Rates rate) {
        String query = "INSERT INTO rates (id, gas_rate, current_rate) VALUES (?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            // Directly set the customer_id as the rate_id because they're the same
            pstmt.setInt(1, rate.getCustomerId());  // Set the id equal to customer_id
            pstmt.setDouble(2, rate.getGasRate());
            pstmt.setDouble(3, rate.getCurrentRate());
            pstmt.executeUpdate();

            // Now update the 'customer_rates' table to set the rate_id
            String updateCustomerRateQuery = "UPDATE customer_rates SET rates_id = ? WHERE customer_id = ?";
            try (PreparedStatement updateStmt = connection.prepareStatement(updateCustomerRateQuery)) {
                updateStmt.setInt(1, rate.getCustomerId());  // Set rate_id as the same as customer_id
                updateStmt.setInt(2, rate.getCustomerId());  // Set customer_id
                updateStmt.executeUpdate();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateRate(int id, Rates rate) {
        String query = "UPDATE rates SET gas_rate = ?, current_rate = ? WHERE customer_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)){
            pstmt.setDouble(1, rate.getGasRate());
            pstmt.setDouble(2, rate.getCurrentRate());
            pstmt.setInt(3, id);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public ResultSet getRates() {
        ResultSet rates;
        try {
            Statement getRates = connection.createStatement();
            rates = getRates.executeQuery("SELECT r.id, r.current_rate, r.gas_rate FROM rates r JOIN customer c ON r.id = c.id");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rates;
    }
}

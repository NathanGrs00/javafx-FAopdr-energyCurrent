package com.nathan.javafxperiode2faopdrenergiecurrent.dao;

import com.nathan.javafxperiode2faopdrenergiecurrent.model.Customer;
import com.nathan.javafxperiode2faopdrenergiecurrent.service.DBConnector;

import java.sql.*;

public class CustomerDAO {
    private final Connection connection;

    public CustomerDAO() {
        try {
            DBConnector dbConnector = new DBConnector();
            connection = dbConnector.getConnection();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void addCustomer(Customer customer) {
        String query = "INSERT INTO customer (id, first_name, last_name, advance) VALUES (?,?,?,?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, customer.getCustomerID());
            pstmt.setString(2, customer.getCustomerFirstName());
            pstmt.setString(3, customer.getCustomerLastName());
            pstmt.setFloat(4, customer.getCustomerAdvance());
            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int id = generatedKeys.getInt(1);

                    String insertCustomerRates = "INSERT INTO customer_rates (customer_id, rates_id) VALUES (?,?)";
                    try (PreparedStatement rateStmt = connection.prepareStatement(insertCustomerRates)) {
                        rateStmt.setInt(1, id); // Set the customer_id
                        rateStmt.setNull(2, Types.INTEGER); // Set rate_id to NULL
                        rateStmt.executeUpdate();
                    }
                    String insertCustomerUsage = "INSERT INTO customer_usage (customer_id, usage_id) VALUES (?,?)";
                    try (PreparedStatement usageStmt = connection.prepareStatement(insertCustomerUsage)) {
                        usageStmt.setInt(1, id);
                        usageStmt.setNull(2, Types.INTEGER);
                        usageStmt.executeUpdate();
                    }
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean existCustomer(int customerID) {
        String query = "SELECT * FROM customer WHERE id=?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)){
            pstmt.setInt(1, customerID);
            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}

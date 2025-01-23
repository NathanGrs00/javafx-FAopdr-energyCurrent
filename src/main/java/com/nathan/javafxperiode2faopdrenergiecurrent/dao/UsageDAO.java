package com.nathan.javafxperiode2faopdrenergiecurrent.dao;

import com.nathan.javafxperiode2faopdrenergiecurrent.model.Usage;
import com.nathan.javafxperiode2faopdrenergiecurrent.service.CustomerService;
import com.nathan.javafxperiode2faopdrenergiecurrent.service.DBConnector;

import java.sql.*;

public class UsageDAO {
    private Connection connection;

    public UsageDAO() {
        try{
            DBConnector db = new DBConnector();
            connection = db.getConnection();
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    public void addUsage(String kind, Usage usage){
        String query = "INSERT INTO `usage` (usage_kind, date_start, date_end, amount) VALUES (?,?,?,?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
            pstmt.setString(1, kind);
            pstmt.setDate(2, usage.getDateStart());
            pstmt.setDate(3, usage.getDateEnd());
            pstmt.setDouble(4, usage.getUsage());
            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int usageId = generatedKeys.getInt(1);  // Get the generated usage_id
                    int customerId = CustomerService.getCurrentCustomerId();

                    // Now insert the customer_id and usage_id into the 'customer_usage' table
                    String insertCustomerUsageQuery = "INSERT INTO customer_usage (customer_id, usage_id) VALUES (?, ?)";
                    try (PreparedStatement customerUsageStmt = connection.prepareStatement(insertCustomerUsageQuery)) {
                        customerUsageStmt.setInt(1, customerId);  // Set the customer_id
                        customerUsageStmt.setInt(2, usageId);     // Set the generated usage_id
                        customerUsageStmt.executeUpdate();
                    }
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet getAllUsage() {
        String query = "SELECT u.* FROM `usage` u JOIN customer_usage cu ON u.id = cu.usage_id WHERE cu.customer_id = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, CustomerService.getCurrentCustomerId());

            return pstmt.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

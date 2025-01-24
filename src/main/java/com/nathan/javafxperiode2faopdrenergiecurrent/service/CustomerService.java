package com.nathan.javafxperiode2faopdrenergiecurrent.service;

import com.nathan.javafxperiode2faopdrenergiecurrent.dao.CustomerDAO;
import com.nathan.javafxperiode2faopdrenergiecurrent.model.Customer;

public class CustomerService {
    private CustomerDAO customerDAO;
    private static int currentCustomerId;

    //Constructor
    public CustomerService() {
        customerDAO = new CustomerDAO();
    }

    //Save new customer.
    public void saveCustomer(int id, String firstName, String lastName, float advance) {
        Customer customer = new Customer(id, firstName, lastName, advance);
        customerDAO.addCustomer(customer);
    }

    // Passing to DAO class.
    public boolean existsInDatabase(int id) {
        return customerDAO.existCustomer(id);
    }

    public static int getCurrentCustomerId() {
        return currentCustomerId;
    }

    public static void setCurrentCustomerId(int currentCustomerId) {
        CustomerService.currentCustomerId = currentCustomerId;
    }
}

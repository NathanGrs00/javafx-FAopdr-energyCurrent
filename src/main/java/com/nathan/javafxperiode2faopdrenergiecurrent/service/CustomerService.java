package com.nathan.javafxperiode2faopdrenergiecurrent.service;

import com.nathan.javafxperiode2faopdrenergiecurrent.dao.CustomerDAO;
import com.nathan.javafxperiode2faopdrenergiecurrent.model.Customer;

public class CustomerService {
    private CustomerDAO customerDAO;
    private static int currentCustomerId;

    public CustomerService() {
        customerDAO = new CustomerDAO();
    }

    public void saveCustomer(int id, String firstName, String lastName, float advance) {
        Customer customer = new Customer(id, firstName, lastName, advance);
        customerDAO.addCustomer(customer);
    }

    public static int getCurrentCustomerId() {
        return currentCustomerId;
    }
    public static void setCurrentCustomerId(int currentCustomerId) {
        CustomerService.currentCustomerId = currentCustomerId;
    }
}

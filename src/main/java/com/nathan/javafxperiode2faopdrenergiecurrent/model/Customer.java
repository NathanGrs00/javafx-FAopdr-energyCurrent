package com.nathan.javafxperiode2faopdrenergiecurrent.model;

//Customer class.
public class Customer {
    private int customerID;
    private String customerFirstName;
    private String customerLastName;
    private float customerAdvance;

    public Customer(int customerID, String customerFirstName, String customerLastName, float customerAdvance) {
        this.customerID = customerID;
        this.customerFirstName = customerFirstName;
        this.customerLastName = customerLastName;
        this.customerAdvance = customerAdvance;
    }

    public int getCustomerID() {
        return customerID;
    }
    public String getCustomerLastName() {
        return customerLastName;
    }
    public String getCustomerFirstName() {
        return customerFirstName;
    }
    public float getCustomerAdvance() {
        return customerAdvance;
    }
}

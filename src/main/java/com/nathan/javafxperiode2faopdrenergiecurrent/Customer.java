package com.nathan.javafxperiode2faopdrenergiecurrent;

public class Customer {
    private int customerID;
    private String customerFirstName;
    private String customerLastName;
    private float customerAdvance;


    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }
    public void setCustomerFirstName(String customerFirstName) {
        this.customerFirstName = customerFirstName;
    }
    public void setCustomerLastName(String customerLastName) {
        this.customerLastName = customerLastName;
    }
    public void setCustomerAdvance(float customerAdvance) {
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

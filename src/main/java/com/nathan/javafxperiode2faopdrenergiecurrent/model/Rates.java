package com.nathan.javafxperiode2faopdrenergiecurrent.model;

//Rates class.
public class Rates {
    private int customerID;
    private double currentRate;
    private double gasRate;

    public Rates(int customerID, double currentRate, double gasRate) {
        this.customerID = customerID;
        this.currentRate = currentRate;
        this.gasRate = gasRate;
    }

    public double getCurrentRate() {
        return currentRate;
    }

    public double getGasRate() {
        return gasRate;
    }

    public int getCustomerId() {
        return customerID;
    }
}

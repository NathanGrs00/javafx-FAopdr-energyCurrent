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

    public void setCurrentRate(double currentRate) {
        this.currentRate = currentRate;
    }

    public void setGasRate(double gasRate) {
        this.gasRate = gasRate;
    }

    public void setCustomerId(int customerID) {this.customerID = customerID;}

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

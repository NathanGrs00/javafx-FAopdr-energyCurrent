package com.nathan.javafxperiode2faopdrenergiecurrent.model;

public class Rates {
    private double currentRate;
    private double gasRate;

    public void setCurrentRate(double currentRate) {
        this.currentRate = currentRate;
    }

    public void setGasRate(double gasRate) {
        this.gasRate = gasRate;
    }

    public double getCurrentRate() {
        return currentRate;
    }

    public double getGasRate() {
        return gasRate;
    }
}

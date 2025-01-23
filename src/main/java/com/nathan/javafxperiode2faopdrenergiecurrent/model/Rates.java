package com.nathan.javafxperiode2faopdrenergiecurrent.model;

//Rates class.
public class Rates {
    private static Rates instance;

    private double currentRate;
    private double gasRate;

    private Rates() {

    }
    public static Rates getInstance() {
        if (instance == null) {
            instance = new Rates();
        }
        return instance;
    }

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

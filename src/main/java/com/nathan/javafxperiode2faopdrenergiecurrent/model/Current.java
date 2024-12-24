package com.nathan.javafxperiode2faopdrenergiecurrent.model;

import java.time.LocalDate;

public class Current extends Date {
    private double currentPrice;

    public Current(LocalDate dateStart, LocalDate dateEnd, double currentPrice) {
        super(dateStart, dateEnd);
        this.currentPrice = currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }
}

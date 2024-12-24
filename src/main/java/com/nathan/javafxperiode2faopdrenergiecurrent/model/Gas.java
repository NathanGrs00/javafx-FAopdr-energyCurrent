package com.nathan.javafxperiode2faopdrenergiecurrent.model;

import java.time.LocalDate;

public class Gas extends Date {
    private double gasPrice;

    public Gas(LocalDate dateStart, LocalDate dateEnd, double gasPrice) {
        super(dateStart, dateEnd);
        this.gasPrice = gasPrice;
    }

    public void setGas(double gas) {
        this.gasPrice = gas;
    }

    public double getGas() {
        return gasPrice;
    }
}

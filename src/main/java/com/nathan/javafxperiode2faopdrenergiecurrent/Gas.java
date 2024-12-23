package com.nathan.javafxperiode2faopdrenergiecurrent;

import java.time.LocalDate;

public class Gas {
    private double gas;
    private LocalDate dateGasStart;
    private LocalDate dateGasEnd;

    public void setGas(double gas) {
        this.gas = gas;
    }

    public void setDateGasStart(LocalDate dateGasStart) {
        this.dateGasStart = dateGasStart;
    }

    public void setDateGasEnd(LocalDate dateGasEnd) {
        this.dateGasEnd = dateGasEnd;
    }

    public double getGas() {
        return gas;
    }

    public LocalDate getDateGasStart() {
        return dateGasStart;
    }

    public LocalDate getDateGasEnd() {
        return dateGasEnd;
    }
}

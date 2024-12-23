package com.nathan.javafxperiode2faopdrenergiecurrent;

import java.time.LocalDate;

public class Current {
    private double current;
    private LocalDate dateCurrentStart;
    private LocalDate dateCurrentEnd;

    public void setCurrent(double current) {
        this.current = current;
    }

    public void setDateCurrentStart(LocalDate dateCurrentStart) {
        this.dateCurrentStart = dateCurrentStart;
    }

    public void setDateCurrentEnd(LocalDate dateCurrentEnd) {
        this.dateCurrentEnd = dateCurrentEnd;
    }

    public double getCurrent() {
        return current;
    }

    public LocalDate getDateCurrentStart() {
        return dateCurrentStart;
    }

    public LocalDate getDateCurrentEnd() {
        return dateCurrentEnd;
    }
}

package com.nathan.javafxperiode2faopdrenergiecurrent;

import java.time.LocalDate;

public class Usage extends Date{
    private double currentUsage;
    private double gasUsage;

    public Usage(LocalDate dateStart, LocalDate dateEnd, double currentUsage, double gasUsage) {
        super(dateStart, dateEnd);
        this.currentUsage = currentUsage;
        this.gasUsage = gasUsage;
    }

    public void setCurrentUsage(double currentUsage) {
        this.currentUsage = currentUsage;
    }

    public void setGasUsage(double gasUsage) {
        this.gasUsage = gasUsage;
    }

    public double getCurrentUsage() {
        return currentUsage;
    }

    public double getGasUsage() {
        return gasUsage;
    }
}

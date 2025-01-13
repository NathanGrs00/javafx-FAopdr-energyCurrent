package com.nathan.javafxperiode2faopdrenergiecurrent.model;

import java.time.LocalDate;

//Abstract class.
public abstract class Usage {
    protected double usage;
    protected double rate;
    protected LocalDate dateStart;
    protected LocalDate dateEnd;

    public Usage(double rate, double usage, LocalDate dateStart, LocalDate dateEnd) {
        this.rate = rate;
        this.usage = usage;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }

    public double getUsage() {
        return usage;
    }

    public void setUsage(double usage) {
        this.usage = usage;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public LocalDate getDateStart() {
        return dateStart;
    }

    public void setDateStart(LocalDate dateStart) {
        this.dateStart = dateStart;
    }

    public LocalDate getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(LocalDate dateEnd) {
        this.dateEnd = dateEnd;
    }
}


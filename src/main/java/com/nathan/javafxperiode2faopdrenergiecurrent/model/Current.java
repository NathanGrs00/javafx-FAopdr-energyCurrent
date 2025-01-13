package com.nathan.javafxperiode2faopdrenergiecurrent.model;

import java.time.LocalDate;

//Inheritance the Usage class.
public class Current extends Usage {

    public Current(double currentRate, double currentUsage, LocalDate dateStart, LocalDate dateEnd) {
        super(currentRate, currentUsage, dateStart, dateEnd);
    }

    //Returning a string.
    @Override
    public String toString() {
        return "Current Usage: " + usage + " kWh from " + dateStart + " to " + dateEnd;
    }
}

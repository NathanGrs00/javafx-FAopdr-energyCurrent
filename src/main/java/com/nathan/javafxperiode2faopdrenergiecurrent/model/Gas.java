package com.nathan.javafxperiode2faopdrenergiecurrent.model;

import java.time.LocalDate;

public class Gas extends Usage {

    public Gas(double gasRate, double gasUsage, LocalDate dateStart, LocalDate dateEnd) {
        super(gasRate, gasUsage, dateStart, dateEnd);
    }

    @Override
    public String toString() {
        return "Gas Usage: " + usage + " m³ from " + dateStart + " to " + dateEnd;
    }

}

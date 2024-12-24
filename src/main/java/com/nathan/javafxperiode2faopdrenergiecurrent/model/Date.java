package com.nathan.javafxperiode2faopdrenergiecurrent.model;

import java.time.LocalDate;

public class Date {
    private LocalDate dateStart;
    private LocalDate dateEnd;

    public Date(LocalDate dateStart, LocalDate dateEnd) {
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }

    public void setDateStart(LocalDate dateStart) {
        this.dateStart = dateStart;
    }

    public void setDatumEnd(LocalDate datumEnd) {
        this.dateEnd = datumEnd;
    }

    public LocalDate getDateStart() {
        return dateStart;
    }

    public LocalDate getDatumEnd() {
        return dateEnd;
    }
}

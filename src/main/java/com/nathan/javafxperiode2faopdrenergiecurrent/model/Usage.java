package com.nathan.javafxperiode2faopdrenergiecurrent.model;

import java.sql.Date;

//Abstract class.
public abstract class Usage {

    protected double usage;
    protected Date dateStart;
    protected Date dateEnd;

    public Usage(double usage, Date dateStart, Date dateEnd) {
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

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }
}


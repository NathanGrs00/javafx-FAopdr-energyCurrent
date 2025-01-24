package com.nathan.javafxperiode2faopdrenergiecurrent.model;

import java.sql.Date;

//Abstract class.
public abstract class Usage {

    protected int id;
    protected double usage;
    protected Date dateStart;
    protected Date dateEnd;

    public Usage(int id, double usage, Date dateStart, Date dateEnd) {
        this.id = id;
        this.usage = usage;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Date getDateEnd() {
        return dateEnd;
    }
}



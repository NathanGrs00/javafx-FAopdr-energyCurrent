package com.nathan.javafxperiode2faopdrenergiecurrent.model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

//Inheritance the Usage class.
public class Current extends Usage {

    public Current(ResultSet rs) throws SQLException {
        super(rs.getDouble("amount"), rs.getDate("date_start"), rs.getDate("date_end"));
    }

    public Current(double currentUsage, Date dateStart, Date dateEnd) {
        super(currentUsage, dateStart, dateEnd);
    }

    //Returning a string.
    @Override
    public String toString() {
        return "Current Usage: " + usage + " kWh from " + dateStart + " to " + dateEnd;
    }
}

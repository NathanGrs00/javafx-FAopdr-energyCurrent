package com.nathan.javafxperiode2faopdrenergiecurrent.model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

//Gas class inheriting from Usage.
public class Gas extends Usage {

    public Gas(ResultSet rs) throws SQLException {
        super(rs.getDouble("amount"), rs.getDate("date_start"), rs.getDate("date_end"));
    }

    public Gas(double gasUsage, Date dateStart, Date dateEnd) {
        super(gasUsage, dateStart, dateEnd);
    }

    // Returning a string.
    @Override
    public String toString() {
        return "Gas Usage: " + usage + " m³ from " + dateStart + " to " + dateEnd;
    }

}

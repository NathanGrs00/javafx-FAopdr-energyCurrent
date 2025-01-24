package com.nathan.javafxperiode2faopdrenergiecurrent.service;

import com.nathan.javafxperiode2faopdrenergiecurrent.dao.UsageDAO;
import com.nathan.javafxperiode2faopdrenergiecurrent.model.Current;
import com.nathan.javafxperiode2faopdrenergiecurrent.model.Gas;
import com.nathan.javafxperiode2faopdrenergiecurrent.model.Usage;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UsageService {
    private UsageDAO usageDAO;

    public UsageService() {
        usageDAO = new UsageDAO();
    }

    //Save a new Usage. Either as Gas or Current and pass it to the database.
    public void saveNewUsage(double usage, Date dateStart, Date dateEnd, String instanceKind){
        int id = 0;
        if (instanceKind.equals("Gas")){
            Gas gas = new Gas(id, usage, dateStart, dateEnd);
            usageDAO.addUsage("gas", gas);
        }
        else if (instanceKind.equals("Current")){
            Current current = new Current(id, usage, dateStart, dateEnd);
            usageDAO.addUsage("current", current);
        }
    }

    // Passing to DAO class.
    public void deleteUsage(int id){
        usageDAO.deleteUsage(id);
    }

    // Get List of all usage and saving them as models.
    public ArrayList<Usage> getAllUsage(){
        ArrayList<Usage> usageList = new ArrayList<>();
        ResultSet allUsageResults = usageDAO.getAllUsage();

        try{
            while (allUsageResults.next()){
                if (allUsageResults.getString("usage_kind").equals("gas")){
                    usageList.add(new Gas(allUsageResults));
                } else if (allUsageResults.getString("usage_kind").equals("current")){
                    usageList.add(new Current(allUsageResults));
                }
            }
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
        return usageList;
    }
}

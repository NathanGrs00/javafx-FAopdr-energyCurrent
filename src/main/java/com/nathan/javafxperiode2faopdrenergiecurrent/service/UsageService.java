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

    //Save a new Usage. Either as Gas or Current and adding it to the list.
    public void saveNewUsage(double usage, Date dateStart, Date dateEnd, String instanceKind){
        if (instanceKind.equals("Gas")){
            Gas gas = new Gas(usage, dateStart, dateEnd);
            usageDAO.addUsage("gas", gas);
        }
        else if (instanceKind.equals("Current")){
            Current current = new Current(usage, dateStart, dateEnd);
            usageDAO.addUsage("current", current);
        }
    }

    public ArrayList<Usage> getAllUsage(){
        ResultSet allUsage = usageDAO.getAllUsage();
        ArrayList<Usage> usages = new ArrayList<Usage>();
        try{
            while (allUsage.next()){
                if (allUsage.getString("usage_kind").equals("gas")){
                    usages.add(new Gas(allUsage));
                } else if (allUsage.getString("usage_kind").equals("current")){
                    usages.add(new Current(allUsage));
                }
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return usages;
    }
}

package com.nathan.javafxperiode2faopdrenergiecurrent.controller;

import com.nathan.javafxperiode2faopdrenergiecurrent.model.Current;
import com.nathan.javafxperiode2faopdrenergiecurrent.model.Gas;
import com.nathan.javafxperiode2faopdrenergiecurrent.model.Usage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.ArrayList;

public class UsageController {
    private static UsageController instance;
    private ArrayList<Usage> usageList;

    private UsageController() {
        usageList = new ArrayList<>();
    }
    public static UsageController getInstance() {
        if (instance == null) {
            instance = new UsageController();
        }
        return instance;
    }

    //Returning all usages.
    public ObservableList<Usage> getList(){
        return FXCollections.observableArrayList(usageList);
    }

    //Save a new Usage. Either as Gas or Current and adding it to the list.
    public void saveNewUsage(double usage, double gasRate, double currentRate, LocalDate dateStart, LocalDate dateEnd, String instanceKind){
        if (instanceKind.equals("Gas")){
            Gas gas = new Gas(gasRate, usage, dateStart, dateEnd);
            usageList.add(gas);
        }
        else if (instanceKind.equals("Current")){
            Current current = new Current(currentRate, usage, dateStart, dateEnd);
            usageList.add(current);
        }
    }

    public ArrayList<Usage> getUsageList(){
        return usageList;
    }
}
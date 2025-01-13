package com.nathan.javafxperiode2faopdrenergiecurrent.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;

public class UsageController {
    private ArrayList<Usage> usageList = new ArrayList<>();

    public ObservableList<Usage> getList(){
        return FXCollections.observableArrayList(usageList);
    }

    public void saveNewUsage(double usage, double gasRate, double currentRate, LocalDate dateStart, LocalDate dateEnd, String instanceKind){
        if (instanceKind.equals("Gas")){
            Gas gas = new Gas(gasRate, usage, dateStart, dateEnd);
            usageList.add(gas);
        }
        else if (instanceKind.equals("Current")){
            Current current = new Current(currentRate, usage, dateStart, dateEnd);
            usageList.add(current);
        }
        else System.out.println("Invalid instance kind");
    }

    public double dailyUsageGas(){
        double totalUsageGas = 0;
        double totalDaysGas = 1;
        double dailyUsageGas = totalUsageGas / totalDaysGas;

        usageList.forEach(usage->{
            if (usage instanceof Gas){
                totalUsageGas = totalUsageGas + usage.getUsage();
                totalDaysGas = totalDaysGas + Duration.between(usage.getDateStart(), usage.getDateEnd()).toDays();
            }
        });
        return dailyUsageGas;
    }

    public double dailyUsageCurrent(){
        double totalUsageCurrent = 0;
        double totalDaysCurrent = 1;
        double dailyUsageCurrent = totalUsageCurrent / totalDaysCurrent;

        usageList.forEach(usage->{
            if (usage instanceof Current){
                totalUsageCurrent = totalUsageCurrent + usage.getUsage();
                totalDaysCurrent = totalDaysCurrent + Duration.between(usage.getDateStart(), usage.getDateEnd()).toDays();
            }
        });
        return dailyUsageCurrent;
    }
}
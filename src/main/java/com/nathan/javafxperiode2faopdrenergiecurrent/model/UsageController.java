package com.nathan.javafxperiode2faopdrenergiecurrent.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class UsageController {
    double averageGas = 0;
    double averageCurrent = 0;

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

    public ArrayList<Double> getTotalDays(){
        ArrayList<Double> days = new ArrayList<>();
        final double[] totalDays = {0,0};

        usageList.forEach(usage->{
            if (usage instanceof Gas){
                long amountOfDaysGas = ChronoUnit.DAYS.between(usage.getDateStart(), usage.getDateEnd());
                totalDays[0] += amountOfDaysGas;
            } else if (usage instanceof Current){
                long amountOfDaysCurrent = ChronoUnit.DAYS.between(usage.getDateStart(), usage.getDateEnd());
                totalDays[1] += amountOfDaysCurrent;
            }
        });
        days.add(totalDays[0]);
        days.add(totalDays[1]);
        return days;
    }

    public double[] getTotalUsage(){
        final double[] totalUsage = {0,0};
        usageList.forEach(usage->{
            if (usage instanceof Gas){
                totalUsage[0] += usage.getUsage();
            } else if (usage instanceof Current){
                totalUsage[1] += usage.getUsage();
            }
        });
        return totalUsage;
    }

    public ArrayList<Double> getWeeklyUsage(){
        ArrayList<Double> weeklyUsage = new ArrayList<>();
        ArrayList<Double> totalDays = getTotalDays();
        double[] totalUsage = getTotalUsage();

        Double averageGas = totalUsage[0] / totalDays.getFirst() * 7;
        Double averageCurrent = totalUsage[1] / totalDays.getLast() * 7;

        weeklyUsage.add(averageGas);
        weeklyUsage.add(averageCurrent);
        return weeklyUsage;
    }

    public ArrayList<Double> getMonthlyUsage(){
        ArrayList<Double> monthlyUsage = new ArrayList<>();
        double[] totalUsage = getTotalUsage();
        ArrayList<Double> totalDays = getTotalDays();

        Double averageGas = totalUsage[0] / totalDays.getFirst() * 30;
        Double averageCurrent = totalUsage[1] / totalDays.getLast() * 30;
        monthlyUsage.add(averageGas);
        monthlyUsage.add(averageCurrent);
        return monthlyUsage;
    }

    public ArrayList<Double> getYearlyUsage(){
        ArrayList<Double> yearlyUsage = new ArrayList<>();
        double[] totalUsage = getTotalUsage();
        ArrayList<Double> totalDays = getTotalDays();

        Double averageGas = totalUsage[0] / totalDays.getFirst() * 365;
        Double averageCurrent = totalUsage[1] / totalDays.getLast() * 365;
        yearlyUsage.add(averageGas);
        yearlyUsage.add(averageCurrent);
        return yearlyUsage;
    }

    public ArrayList<Double> getCost(Double usageAmount){
        ArrayList<Double> cost = new ArrayList<>();
        double[] totalCost = {0,0};
        usageList.forEach(usage->{
            if (usage instanceof Gas){
                totalCost[0] += (usage.getRate() * usageAmount);
            }else if (usage instanceof Current){
                totalCost[1] += (usage.getRate() * usageAmount);
            }
        });
        cost.add(totalCost[0]);
        cost.add(totalCost[1]);
        return cost;
    }

    public static double roundTwoDecimals(double value){
        return Math.round(value * 100.0) / 100.0;
    }
}
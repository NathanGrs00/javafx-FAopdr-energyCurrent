package com.nathan.javafxperiode2faopdrenergiecurrent.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class UsageController {
    private ArrayList<Usage> usageList = new ArrayList<>();

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
        else System.out.println("Invalid instance kind");
    }
    //Calculate total days in gas and current usages.
    public ArrayList<Double> getTotalDays(){
        ArrayList<Double> days = new ArrayList<>();
        //Resetting so it doesn't keep adding.
        final double[] totalDays = {0,0};

        //Foreach loop through the list.
        usageList.forEach(usage->{
            if (usage instanceof Gas){
                //Should be always 7, because it's restricted weekly.
                totalDays[0] += 7;
            } else if (usage instanceof Current){
                totalDays[1] += 7;
            }
        });
        days.add(totalDays[0]);
        days.add(totalDays[1]);
        return days;
    }

    // getting the total Usage. (with a while loop because of the assignment).
    public double[] getTotalUsage() {
        final double[] totalUsage = {0, 0};
        int i = 0;
        while (i < usageList.size()) {
            Usage usage = usageList.get(i);
            if (usage instanceof Gas) {
                totalUsage[0] += usage.getUsage();
            } else if (usage instanceof Current) {
                totalUsage[1] += usage.getUsage();
            }
            i++;
        }
        return totalUsage;
    }

    // Calculate weekly usage.
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

    // Calculate monthly usage.
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

    //Calculate yearly usage.
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

    // Calculating costs.
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

    //Homemade Round up function.
    public static double roundTwoDecimals(double value){
        return Math.round(value * 100.0) / 100.0;
    }
}
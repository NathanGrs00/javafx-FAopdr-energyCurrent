package com.nathan.javafxperiode2faopdrenergiecurrent.service;

import com.nathan.javafxperiode2faopdrenergiecurrent.controller.UsageController;
import com.nathan.javafxperiode2faopdrenergiecurrent.model.Current;
import com.nathan.javafxperiode2faopdrenergiecurrent.model.Gas;
import com.nathan.javafxperiode2faopdrenergiecurrent.model.Usage;

import java.util.ArrayList;

public class CalculatorService {
    //Calculate total days in gas and current usages.
    public ArrayList<Double> getTotalDays(){
        ArrayList<Double> days = new ArrayList<>();
        //Resetting so it doesn't keep adding.
        final double[] totalDays = {0,0};

        //Foreach loop through the list.
        UsageController.getInstance().getUsageList().forEach(usage->{
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
        while (i < UsageController.getInstance().getUsageList().size()) {
            Usage usage = UsageController.getInstance().getUsageList().get(i);
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
        UsageController.getInstance().getUsageList().forEach(usage->{
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
    public double roundTwoDecimals(double value){
        return Math.round(value * 100.0) / 100.0;
    }
}

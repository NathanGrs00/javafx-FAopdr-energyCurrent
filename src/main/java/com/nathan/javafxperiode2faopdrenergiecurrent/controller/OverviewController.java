package com.nathan.javafxperiode2faopdrenergiecurrent.controller;

import com.nathan.javafxperiode2faopdrenergiecurrent.model.Current;
import com.nathan.javafxperiode2faopdrenergiecurrent.model.Gas;
import com.nathan.javafxperiode2faopdrenergiecurrent.model.Usage;
import com.nathan.javafxperiode2faopdrenergiecurrent.service.CalculatorService;
import javafx.scene.control.ListView;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class OverviewController {
    CalculatorService calculatorService = new CalculatorService();

    public ListView<String> getUsageList(){
        ListView<String> listView = new ListView<>();
        listView.getItems().add("Usage Overview");
        listView.getItems().add("----------------------------");
        listView.setPrefWidth(1000);

        //Formatting date to display european style.
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        //Polymorphism to show different text for gas or current.
        for (Usage usage : UsageController.getInstance().getUsageList()) {
            if (usage instanceof Gas) {
                listView.getItems().add("Gas Usage: " + usage.getUsage() + " m³ from " + usage.getDateStart().format(formatter) + " to " + usage.getDateEnd().format(formatter));
            } else if (usage instanceof Current) {
                listView.getItems().add("Current Usage: " + usage.getUsage() + " kWh from " + usage.getDateStart().format(formatter) + " to " + usage.getDateEnd().format(formatter));
            }
        }
        return listView;
    }
    public ArrayList<Double> getUsageOverviewGas(){
        ArrayList<Double> usageOverviewGas = new ArrayList<>();
        //Gas getters
        Double weeklyGasAmount = calculatorService.getWeeklyUsage().getFirst();
        Double roundedWeeklyGasAmount = calculatorService.roundTwoDecimals(weeklyGasAmount);
        Double weeklyGasCost = calculatorService.getCost(weeklyGasAmount).getFirst();
        Double roundedWeeklyGasCost = calculatorService.roundTwoDecimals(weeklyGasCost);
        Double monthlyGasAmount = calculatorService.getMonthlyUsage().getFirst();
        Double roundedMonthlyGasAmount = calculatorService.roundTwoDecimals(monthlyGasAmount);
        Double monthlyGasCost = calculatorService.getCost(monthlyGasAmount).getFirst();
        Double roundedMonthlyGasCost = calculatorService.roundTwoDecimals(monthlyGasCost);
        Double yearlyGasAmount = calculatorService.getYearlyUsage().getFirst();
        Double roundedYearlyGasAmount = calculatorService.roundTwoDecimals(yearlyGasAmount);
        Double yearlyGasCost = calculatorService.getCost(yearlyGasAmount).getFirst();
        Double roundedYearlyGasCost = calculatorService.roundTwoDecimals(yearlyGasCost);

        usageOverviewGas.add(roundedWeeklyGasAmount);
        usageOverviewGas.add(roundedMonthlyGasAmount);
        usageOverviewGas.add(roundedYearlyGasAmount);
        usageOverviewGas.add(roundedWeeklyGasCost);
        usageOverviewGas.add(roundedMonthlyGasCost);
        usageOverviewGas.add(roundedYearlyGasCost);
        return usageOverviewGas;
    }
    public ArrayList<Double> getUsageOverviewCurrent(){
        ArrayList<Double> usageOverviewCurrent = new ArrayList<>();
        //Current getters
        Double weeklyCurrentAmount = calculatorService.getWeeklyUsage().getLast();
        Double roundedWeeklyCurrentAmount = calculatorService.roundTwoDecimals(weeklyCurrentAmount);
        Double weeklyCurrentCost = calculatorService.getCost(weeklyCurrentAmount).getLast();
        Double roundedWeeklyCurrentCost = calculatorService.roundTwoDecimals(weeklyCurrentCost);
        Double monthlyCurrentAmount = calculatorService.getMonthlyUsage().getLast();
        Double roundedMonthlyCurrentAmount = calculatorService.roundTwoDecimals(monthlyCurrentAmount);
        Double monthlyCurrentCost = calculatorService.getCost(monthlyCurrentAmount).getLast();
        Double roundedMonthlyCurrentCost = calculatorService.roundTwoDecimals(monthlyCurrentCost);
        Double yearlyCurrentAmount = calculatorService.getYearlyUsage().getLast();
        Double roundedYearlyCurrentAmount = calculatorService.roundTwoDecimals(yearlyCurrentAmount);
        Double yearlyCurrentCost = calculatorService.getCost(yearlyCurrentAmount).getLast();
        Double roundedYearlyCurrentCost = calculatorService.roundTwoDecimals(yearlyCurrentCost);

        usageOverviewCurrent.add(roundedWeeklyCurrentAmount);
        usageOverviewCurrent.add(roundedMonthlyCurrentAmount);
        usageOverviewCurrent.add(roundedYearlyCurrentAmount);
        usageOverviewCurrent.add(roundedWeeklyCurrentCost);
        usageOverviewCurrent.add(roundedMonthlyCurrentCost);
        usageOverviewCurrent.add(roundedYearlyCurrentCost);

        return usageOverviewCurrent;
    }
}

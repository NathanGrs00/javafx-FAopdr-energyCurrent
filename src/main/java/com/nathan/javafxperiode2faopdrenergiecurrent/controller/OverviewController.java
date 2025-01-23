package com.nathan.javafxperiode2faopdrenergiecurrent.controller;

import com.nathan.javafxperiode2faopdrenergiecurrent.model.Current;
import com.nathan.javafxperiode2faopdrenergiecurrent.model.Gas;
import com.nathan.javafxperiode2faopdrenergiecurrent.model.Rates;
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
        calculatorService.roundTwoDecimals(weeklyGasAmount);
        Double weeklyGasCost = calculatorService.getCost(weeklyGasAmount).getFirst();
        calculatorService.roundTwoDecimals(weeklyGasCost);
        Double monthlyGasAmount = calculatorService.getMonthlyUsage().getFirst();
        calculatorService.roundTwoDecimals(monthlyGasAmount);
        Double monthlyGasCost = calculatorService.getCost(monthlyGasAmount).getFirst();
        calculatorService.roundTwoDecimals(monthlyGasCost);
        Double yearlyGasAmount = calculatorService.getYearlyUsage().getFirst();
        calculatorService.roundTwoDecimals(yearlyGasAmount);
        Double yearlyGasCost = calculatorService.getCost(yearlyGasAmount).getFirst();
        calculatorService.roundTwoDecimals(yearlyGasCost);

        usageOverviewGas.add(weeklyGasAmount);
        usageOverviewGas.add(monthlyGasAmount);
        usageOverviewGas.add(yearlyGasAmount);
        usageOverviewGas.add(weeklyGasCost);
        usageOverviewGas.add(monthlyGasCost);
        usageOverviewGas.add(yearlyGasCost);
        return usageOverviewGas;
    }
    public ArrayList<Double> getUsageOverviewCurrent(){
        ArrayList<Double> usageOverviewCurrent = new ArrayList<>();
        //Current getters
        Double weeklyCurrentAmount = calculatorService.getWeeklyUsage().getLast();
        calculatorService.roundTwoDecimals(weeklyCurrentAmount);
        Double weeklyCurrentCost = calculatorService.getCost(weeklyCurrentAmount).getLast();
        calculatorService.roundTwoDecimals(weeklyCurrentCost);
        Double monthlyCurrentAmount = calculatorService.getMonthlyUsage().getLast();
        calculatorService.roundTwoDecimals(monthlyCurrentAmount);
        Double monthlyCurrentCost = calculatorService.getCost(monthlyCurrentAmount).getLast();
        calculatorService.roundTwoDecimals(monthlyCurrentCost);
        Double yearlyCurrentAmount = calculatorService.getYearlyUsage().getLast();
        calculatorService.roundTwoDecimals(yearlyCurrentAmount);
        Double yearlyCurrentCost = calculatorService.getCost(yearlyCurrentAmount).getLast();
        calculatorService.roundTwoDecimals(yearlyCurrentCost);

        usageOverviewCurrent.add(weeklyCurrentAmount);
        usageOverviewCurrent.add(monthlyCurrentAmount);
        usageOverviewCurrent.add(yearlyCurrentAmount);
        usageOverviewCurrent.add(weeklyCurrentCost);
        usageOverviewCurrent.add(monthlyCurrentCost);
        usageOverviewCurrent.add(yearlyCurrentCost);

        return usageOverviewCurrent;
    }
}

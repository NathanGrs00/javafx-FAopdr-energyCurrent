package com.nathan.javafxperiode2faopdrenergiecurrent.controller;

import com.nathan.javafxperiode2faopdrenergiecurrent.model.Current;
import com.nathan.javafxperiode2faopdrenergiecurrent.model.Gas;
import com.nathan.javafxperiode2faopdrenergiecurrent.model.Usage;
import com.nathan.javafxperiode2faopdrenergiecurrent.service.AlertService;
import com.nathan.javafxperiode2faopdrenergiecurrent.service.CalculatorService;
import com.nathan.javafxperiode2faopdrenergiecurrent.service.UsageService;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class OverviewController {
    CalculatorService calculatorService = new CalculatorService();
    UsageService usageService = new UsageService();
    AlertService alertService = new AlertService();

    public GridPane getUsageGrid() {
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPrefWidth(1000);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        Label headerUsage = new Label("Usage Details");
        gridPane.add(headerUsage, 0, 0);
        headerUsage.setStyle("-fx-font-weight: bold;");

        // Loop through all usage items and add them to the grid.
        int row = 1;
        for (Usage usage : usageService.getAllUsage()) {
            // Create usage details text.
            String usageText;
            if (usage instanceof Gas) {
                usageText = "Gas Usage: " + usage.getUsage() + " m³ from " +
                        usage.getDateStart().toLocalDate().format(formatter) + " to " +
                        usage.getDateEnd().toLocalDate().format(formatter);
            } else if (usage instanceof Current) {
                usageText = "Current Usage: " + usage.getUsage() + " kWh from " +
                        usage.getDateStart().toLocalDate().format(formatter) + " to " +
                        usage.getDateEnd().toLocalDate().format(formatter);
            } else {
                usageText = "Unknown Usage Type";
            }

            Label usageLabel = new Label(usageText);

            // Create a delete button for the row.
            Button deleteButton = new Button("Delete");
            deleteButton.setOnAction(event -> handleDeleteButton(usage.getId()));

            gridPane.add(usageLabel, 0, row);
            gridPane.add(deleteButton, 1, row);

            GridPane.setHgrow(usageLabel, Priority.ALWAYS);

            // Increment the row index for the next entry.
            row++;
        }

        return gridPane;
    }

    public void handleDeleteButton(int buttonId) {
        try {
            usageService.deleteUsage(buttonId);
            alertService.getAlert("Item succesfully deleted");
        } catch (Exception e) {
            alertService.getAlert("Error deleting usage");
        }
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

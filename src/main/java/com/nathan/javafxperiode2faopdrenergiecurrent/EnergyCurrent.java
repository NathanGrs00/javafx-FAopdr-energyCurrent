package com.nathan.javafxperiode2faopdrenergiecurrent;

import com.nathan.javafxperiode2faopdrenergiecurrent.view.OverviewView;
import com.nathan.javafxperiode2faopdrenergiecurrent.view.UsageView;
import com.nathan.javafxperiode2faopdrenergiecurrent.view.NewRatesView;
import com.nathan.javafxperiode2faopdrenergiecurrent.view.CustomerView;
import javafx.application.Application;

import javafx.stage.Stage;

public class EnergyCurrent extends Application {

    // Starting by going to the settings.
    @Override
    public void start(Stage primaryStage) {
        CustomerView customerView = new CustomerView();
        customerView.setCustomer(primaryStage);
    }

    // Navigating to the rates page.
    public void navigateToRates(Stage primaryStage) {
        NewRatesView newRatesView = new NewRatesView();
        newRatesView.setRates(primaryStage);
    }

    // Navigate to the new usage screen.
    public void navigateToNewUsage(Stage primaryStage) {
        UsageView usageView = new UsageView();
        usageView.setNewUsage(primaryStage);
    }

    // Navigate to the overview screen.
    public void navigateToOverview(Stage primaryStage) {
        OverviewView allUsageView = new OverviewView();
        allUsageView.getUsageOverview(primaryStage);
    }

    // Launch it to the moon.
    public static void main(String[] args) {
        launch();
    }
}
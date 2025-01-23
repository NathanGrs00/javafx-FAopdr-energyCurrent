package com.nathan.javafxperiode2faopdrenergiecurrent;

import com.nathan.javafxperiode2faopdrenergiecurrent.view.OverviewView;
import com.nathan.javafxperiode2faopdrenergiecurrent.view.UsageView;
import com.nathan.javafxperiode2faopdrenergiecurrent.view.NewRatesView;
import com.nathan.javafxperiode2faopdrenergiecurrent.view.CustomerView;
import javafx.application.Application;

import javafx.stage.Stage;

public class MainClass extends Application {

    // Starting by going to the settings.
    @Override
    public void start(Stage primaryStage) {
        CustomerView customerView = new CustomerView();
        customerView.setCustomer(primaryStage);
    }

    public void navigateToRates(int customerId, Stage primaryStage) {
        NewRatesView newRatesView = new NewRatesView();
        newRatesView.setRates(customerId, primaryStage);
    }

    public void navigateToHomepage(Stage primaryStage) {
        UsageView usageView = new UsageView();
        usageView.setNewUsage(primaryStage);
    }

    public void navigateToOverview(Stage primaryStage) {
        OverviewView allUsageView = new OverviewView();
        allUsageView.getUsageOverview(primaryStage);
    }

    // Launch it to the moon.
    public static void main(String[] args) {
        launch();
    }
}
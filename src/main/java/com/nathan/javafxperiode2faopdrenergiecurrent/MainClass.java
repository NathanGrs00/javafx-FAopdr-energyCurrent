package com.nathan.javafxperiode2faopdrenergiecurrent;

import com.nathan.javafxperiode2faopdrenergiecurrent.model.*;
import com.nathan.javafxperiode2faopdrenergiecurrent.view.OverviewView;
import com.nathan.javafxperiode2faopdrenergiecurrent.view.HomepageView;
import com.nathan.javafxperiode2faopdrenergiecurrent.view.NewRatesView;
import com.nathan.javafxperiode2faopdrenergiecurrent.view.UserSettingsView;
import javafx.application.Application;

import javafx.stage.Stage;

public class MainClass extends Application {


    // Starting by going to the settings.
    @Override
    public void start(Stage primaryStage) {
        UserSettingsView userSettingsView = new UserSettingsView();
        userSettingsView.setUserSettings(primaryStage);
    }

    public void navigateToRates(Stage primaryStage, Customer customer) {
        NewRatesView newRatesView = new NewRatesView();
        newRatesView.setRates(primaryStage, customer);
    }

    public void navigateToHomepage(Stage primaryStage) {
        HomepageView homepageView = new HomepageView();
        homepageView.getHomepage(primaryStage);
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
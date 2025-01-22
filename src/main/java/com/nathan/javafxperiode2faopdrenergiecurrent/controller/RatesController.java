package com.nathan.javafxperiode2faopdrenergiecurrent.controller;

import com.nathan.javafxperiode2faopdrenergiecurrent.MainClass;
import com.nathan.javafxperiode2faopdrenergiecurrent.model.Rates;
import com.nathan.javafxperiode2faopdrenergiecurrent.model.Usage;
import com.nathan.javafxperiode2faopdrenergiecurrent.service.AlertService;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;

public class RatesController {
    Rates rates = new Rates();
    MainClass main = new MainClass();
    AlertService alert = new AlertService();

    public void ValidateFields(TextField rateCurrent, TextField rateGas, Stage primaryStage) {
        // First trying the users input.
        try {
            // Setting users input if catch not activated.
            double gasRate = Double.parseDouble(rateGas.getText());
            double currentRate = Double.parseDouble(rateCurrent.getText());

            rates.setGasRate(gasRate);
            rates.setCurrentRate(currentRate);

            try {
                main.navigateToHomepage(primaryStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // If formats are incorrect, show error.
        catch(NumberFormatException ex) {
            alert.getAlert("Please fill in the fields with the correct format.");
        }
        // If anything went wrong, show last error.
        catch(Exception ex) {
            alert.getAlert("Oops! Something went wrong.");
        }
    }
}

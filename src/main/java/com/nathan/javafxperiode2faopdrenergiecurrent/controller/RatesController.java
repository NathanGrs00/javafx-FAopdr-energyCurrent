package com.nathan.javafxperiode2faopdrenergiecurrent.controller;

import com.nathan.javafxperiode2faopdrenergiecurrent.EnergyCurrent;
import com.nathan.javafxperiode2faopdrenergiecurrent.service.AlertService;
import com.nathan.javafxperiode2faopdrenergiecurrent.service.RatesService;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RatesController {
    EnergyCurrent main = new EnergyCurrent();
    AlertService alert = new AlertService();

    public void ValidateFields(int customerId, TextField rateCurrent, TextField rateGas, Stage primaryStage) {
        // First trying the users input.
        try {
            // Setting users input if catch not activated.
            double gasRate = Double.parseDouble(rateGas.getText());
            double currentRate = Double.parseDouble(rateCurrent.getText());

            RatesService ratesService = new RatesService();
            //Checking if customer already has rates, if so send it to update rates instead of making new ones.
            if(ratesService.hasRates(customerId)){
                ratesService.updateRates(customerId, gasRate, currentRate);
                main.navigateToNewUsage(primaryStage);
            } else {
                ratesService.saveRates(customerId, gasRate, currentRate);
                main.navigateToNewUsage(primaryStage);
            }
        }
        // If formats are incorrect, show error.
        catch(NumberFormatException ex) {
            alert.getAlert("Please fill in the fields with the correct format.");
            throw new RuntimeException(ex);
        }
        // If anything went wrong, show last error.
        catch(Exception ex) {
            alert.getAlert("Oops! Something went wrong.");
            throw new RuntimeException(ex);
        }
    }
}

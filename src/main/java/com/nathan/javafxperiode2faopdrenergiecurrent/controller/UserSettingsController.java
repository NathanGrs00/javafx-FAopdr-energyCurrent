package com.nathan.javafxperiode2faopdrenergiecurrent.controller;

import com.nathan.javafxperiode2faopdrenergiecurrent.MainClass;
import com.nathan.javafxperiode2faopdrenergiecurrent.model.Customer;
import com.nathan.javafxperiode2faopdrenergiecurrent.service.AlertService;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UserSettingsController {
    AlertService alert = new AlertService();

    public void ValidateFields(TextField id, TextField firstName, TextField lastName, TextField yearlyAdvance, Stage primaryStage) {
        Customer customer = new Customer();
        // First trying the users input.
        try {
            //If it's empty, return nothing and give an alert.
            if (id.getText().isEmpty() || firstName.getText().isEmpty() || lastName.getText().isEmpty() || yearlyAdvance.getText().isEmpty()) {
                alert.getAlert("Please, fill out all fields.");
                return;
            }
            //Parsing and getting values.

            int intCustomerID = Integer.parseInt(id.getText());
            String strFirstName = firstName.getText();
            String strLastName = lastName.getText();
            float fltAdvance = Float.parseFloat(yearlyAdvance.getText());
            // Setting users input.
            customer.setCustomerID(intCustomerID);
            customer.setCustomerFirstName(strFirstName);
            customer.setCustomerLastName(strLastName);
            customer.setCustomerAdvance(fltAdvance);
        }
        // If formats are incorrect, show error.
        catch(NumberFormatException ex) {
            alert.getAlert("Please fill in the fields with the correct format.");
            return;
        }
        // If inputs are still invalid, show error.
        catch(Exception ex) {
            alert.getAlert("Oops! Something went wrong.");
            return;
        }
        MainClass mainClass = new MainClass();
        mainClass.navigateToRates(primaryStage, customer);
    }
}

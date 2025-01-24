package com.nathan.javafxperiode2faopdrenergiecurrent.controller;

import com.nathan.javafxperiode2faopdrenergiecurrent.EnergyCurrent;
import com.nathan.javafxperiode2faopdrenergiecurrent.service.AlertService;
import com.nathan.javafxperiode2faopdrenergiecurrent.service.CustomerService;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CustomerController {
    EnergyCurrent main = new EnergyCurrent();
    CustomerService customerService = new CustomerService();
    AlertService alert = new AlertService();

    public void ValidateFields(TextField id, TextField firstName, TextField lastName, TextField yearlyAdvance, Stage primaryStage) {
        // First trying the users input.
        int intCustomerID;

        try {
            //Parsing values.
            intCustomerID = Integer.parseInt(id.getText());
            String strFirstName = firstName.getText();
            String strLastName = lastName.getText();
            float fltAdvance = Float.parseFloat(yearlyAdvance.getText());

            boolean exists = customerService.existsInDatabase(intCustomerID);
            if (exists) {
                alert.getAlert("User already exists!");
                return;
            }

            CustomerService customerService = new CustomerService();
            CustomerService.setCurrentCustomerId(intCustomerID);
            customerService.saveCustomer(intCustomerID, strFirstName, strLastName, fltAdvance);

            try {
                main.navigateToRates(intCustomerID, primaryStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // If formats are incorrect, show error.
        catch (NumberFormatException ex) {
            alert.getAlert("Please fill in the fields with the correct format.");
            ex.printStackTrace();
        }
        // If inputs are still invalid, show error.
        catch (Exception ex) {
            alert.getAlert("Oops! Something went wrong.");
            ex.printStackTrace();
        }
    }
}

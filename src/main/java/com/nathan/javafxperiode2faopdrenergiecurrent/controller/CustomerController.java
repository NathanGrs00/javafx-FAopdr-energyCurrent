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

    // Validating the inputs
    public void ValidateFields(TextField id, TextField firstName, TextField lastName, TextField yearlyAdvance, Stage primaryStage) {
        try {
            //Parsing values.
            int intCustomerID = Integer.parseInt(id.getText());
            String strFirstName = firstName.getText();
            String strLastName = lastName.getText();
            float fltAdvance = Float.parseFloat(yearlyAdvance.getText());

            // Check if customer exists in database.
            boolean exists = customerService.existsInDatabase(intCustomerID);
            if (exists) {
                alert.getAlert("User already exists!");
                return;
            }

            //Sending it to the service page to save it. Also setting the current customer id.
            CustomerService customerService = new CustomerService();
            CustomerService.setCurrentCustomerId(intCustomerID);
            customerService.saveCustomer(intCustomerID, strFirstName, strLastName, fltAdvance);
            main.navigateToRates(primaryStage);
        }
        // If formats are incorrect, show error.
        catch (NumberFormatException ex) {
            alert.getAlert("Please fill in the fields with the correct format.");
            throw new RuntimeException(ex);
        }
        // If inputs are still invalid, show error.
        catch (Exception ex) {
            alert.getAlert("Oops! Something went wrong.");
            throw new RuntimeException(ex);
        }
    }
}

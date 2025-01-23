package com.nathan.javafxperiode2faopdrenergiecurrent.controller;

import com.nathan.javafxperiode2faopdrenergiecurrent.model.Rates;
import com.nathan.javafxperiode2faopdrenergiecurrent.service.AlertService;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.time.LocalDate;

public class HomepageController {
    public void ValidateFields(ComboBox newWeeklyUsage, TextField newWeeklyUsageTextField, DatePicker dateUsageStart, DatePicker dateUsageEnd) {
        AlertService alert = new AlertService();

        try {
            //Checking if inputs are empty.
            if (newWeeklyUsage.getValue() == null || dateUsageEnd.getValue() == null || dateUsageStart.getValue() == null || newWeeklyUsageTextField.getText().isEmpty()) {
                alert.getAlert("Please, fill out all fields.");
                return;
            }
            //Saving inputs as usageclass.
            double usageNew = Double.parseDouble(newWeeklyUsageTextField.getText());
            LocalDate startDateUsage = dateUsageStart.getValue();
            LocalDate endDateUsage = dateUsageEnd.getValue();
            String instanceKind = newWeeklyUsage.getSelectionModel().getSelectedItem().toString();

            UsageController.getInstance().saveNewUsage(usageNew, Rates.getInstance().getGasRate(), Rates.getInstance().getCurrentRate(), startDateUsage, endDateUsage, instanceKind);
            alert.getAlert("Usage successfully added to the list.");
        }
        // If formats are incorrect, show error.
        catch(NumberFormatException ex) {
            alert.getAlert("Please fill in the fields with the correct format.");
        }
        // More errors.
        catch(Exception ex) {
            System.out.println(ex.getMessage());
            alert.getAlert("Oops! Something went wrong.");
        }
    }
}

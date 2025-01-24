package com.nathan.javafxperiode2faopdrenergiecurrent.controller;

import com.nathan.javafxperiode2faopdrenergiecurrent.service.AlertService;
import com.nathan.javafxperiode2faopdrenergiecurrent.service.UsageService;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.time.LocalDate;

public class UsageController {
    AlertService alert = new AlertService();

    public void ValidateFields(ComboBox newWeeklyUsage, TextField newWeeklyUsageTextField, DatePicker dateUsageStart, DatePicker dateUsageEnd) {
        // First trying the user inputs.
        try {
            //Saving inputs as usage class.
            double usageNew = Double.parseDouble(newWeeklyUsageTextField.getText());
            String instanceKind = newWeeklyUsage.getSelectionModel().getSelectedItem().toString();
            LocalDate usageStartDate = dateUsageStart.getValue();
            LocalDate usageEndDate = dateUsageEnd.getValue();
            java.sql.Date sqlUsageStartDate = java.sql.Date.valueOf(usageStartDate);
            java.sql.Date sqlUsageEndDate = java.sql.Date.valueOf(usageEndDate);

            UsageService usageService = new UsageService();
            usageService.saveNewUsage(usageNew, sqlUsageStartDate, sqlUsageEndDate, instanceKind);
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

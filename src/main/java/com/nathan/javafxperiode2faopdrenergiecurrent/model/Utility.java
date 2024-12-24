package com.nathan.javafxperiode2faopdrenergiecurrent.model;

import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Utility {
    // Function for creating labeled inputs.
    public VBox createLabeledInput(String labelText, String promptText, TextField inputField) {
        Label label = new Label(labelText);
        inputField.setPromptText(promptText);

        return new VBox(label, inputField);
    }

    // Function to create start and end date inputs.
    public VBox createDateInput(DatePicker startDate,  DatePicker endDate) {
        Label txtDate = new Label("Please enter the start and end date.");

        startDate.setPromptText("Start Date");
        endDate.setPromptText("End Date");
        HBox hbox = new HBox(startDate, endDate);

        return new VBox(txtDate, hbox);
    }
}

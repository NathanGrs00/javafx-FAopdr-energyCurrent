package com.nathan.javafxperiode2faopdrenergiecurrent.view;

import com.nathan.javafxperiode2faopdrenergiecurrent.controller.UsageController;
import com.nathan.javafxperiode2faopdrenergiecurrent.service.UtilityService;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.Objects;

public class UsageView {
    private final UtilityService util = new UtilityService();
    private final UsageController usageController = new UsageController();
    private final MenuBarView menuBar = new MenuBarView();

    public void setNewUsage(Stage primaryStage) {
        // Root pane setup
        BorderPane root = new BorderPane();
        root.setTop(menuBar.getMenuBar(primaryStage));

        // Usage type selection
        Label usageTypeLabel = new Label("Select type of usage:");
        ComboBox<String> usageKind = new ComboBox<>();
        usageKind.getItems().addAll("Gas", "Current");

        // Usage amount input
        TextField usageAmount = new TextField();
        VBox vboxUsageAmount = util.createLabeledInput("Add new weekly usage", "Usage amount", usageAmount);
        usageKind.getStyleClass().add("combo-weekly-usage");
        usageAmount.getStyleClass().add("input-weekly-usage");

        // Date range selection
        DatePicker dateUsageStart = new DatePicker();
        DatePicker dateUsageEnd = new DatePicker();
        // Disable manual editing for the second DatePicker
        dateUsageEnd.setEditable(false);

        // Add a listener to the first DatePicker
        dateUsageStart.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                // Add 7 days to the selected date
                LocalDate endDate = newValue.plusDays(7);

                // Set the value of the second DatePicker
                dateUsageEnd.setValue(endDate);

                // Disable the second DatePicker to "lock" it
                dateUsageEnd.setDisable(true);
            } else {
                // Clear and enable the second DatePicker if the first is cleared
                dateUsageEnd.setValue(null);
                dateUsageEnd.setDisable(false);
            }
        });

        dateUsageStart.getStyleClass().add("input-date-start");
        dateUsageEnd.getStyleClass().add("input-date-end");
        VBox vboxUsageDate = util.createDateInput(dateUsageStart, dateUsageEnd);
        VBox vboxUsage = new VBox(usageTypeLabel, usageKind, vboxUsageAmount, vboxUsageDate);

        // Submit button for new usage
        Button submitNewUsage = new Button("Submit New Usage");

        // Submit button action
        submitNewUsage.setOnAction(e -> {
            // Call the controller to validate and handle the form submission
            usageController.ValidateFields(usageKind, usageAmount, dateUsageStart, dateUsageEnd);
        });

        // Layout configuration
        VBox vboxCenter = new VBox(vboxUsage, submitNewUsage);
        vboxCenter.setAlignment(Pos.CENTER_LEFT);
        vboxCenter.setSpacing(12);
        vboxCenter.setPadding(new Insets(0, 20, 0, 20));

        root.setCenter(vboxCenter);

        // Create and set the scene
        Scene scene = new Scene(root, 480, 270);
        scene.getStylesheets().add(Objects.requireNonNull(
                getClass().getResource("/com/nathan/javafxperiode2faopdrenergiecurrent/stylesheet.css")
        ).toExternalForm());
        primaryStage.setScene(scene);
    }
}
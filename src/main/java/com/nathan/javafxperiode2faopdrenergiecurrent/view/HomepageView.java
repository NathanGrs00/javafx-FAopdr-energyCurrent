package com.nathan.javafxperiode2faopdrenergiecurrent.view;

import com.nathan.javafxperiode2faopdrenergiecurrent.controller.HomepageController;
import com.nathan.javafxperiode2faopdrenergiecurrent.model.Usage;
import com.nathan.javafxperiode2faopdrenergiecurrent.service.UtilityService;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class HomepageView {
    private final MenuBarView menuBar = new MenuBarView();
    private final UtilityService util = new UtilityService();
    private final HomepageController hpController = new HomepageController();

    public void getHomepage(Stage primaryStage) {
        // Root pane setup
        BorderPane root = new BorderPane();
        root.setTop(menuBar.getMenuBar(primaryStage));

        // Usage type selection
        Label usageTypeLabel = new Label("Select type of usage:");
        ComboBox<String> newWeeklyUsage = new ComboBox<>();
        newWeeklyUsage.getItems().addAll("Gas", "Current");

        // Usage amount input
        TextField newWeeklyUsageTextField = new TextField();
        VBox vboxUsageAmount = util.createLabeledInput("Add new weekly usage", "Usage amount", newWeeklyUsageTextField);
        newWeeklyUsage.getStyleClass().add("combo-weekly-usage");
        newWeeklyUsageTextField.getStyleClass().add("input-weekly-usage");

        // Date range selection
        DatePicker dateUsageStart = new DatePicker();
        DatePicker dateUsageEnd = new DatePicker();
        dateUsageStart.getStyleClass().add("input-date-start");
        dateUsageEnd.getStyleClass().add("input-date-end");
        VBox vboxUsageDate = util.createDateInput(dateUsageStart, dateUsageEnd);
        VBox vboxUsage = new VBox(usageTypeLabel, newWeeklyUsage, vboxUsageAmount, vboxUsageDate);

        // Submit button for new usage
        Button submitNewUsage = new Button("Submit New Usage");

        // Submit button action
        submitNewUsage.setOnAction(e -> {
            // Call the controller to validate and handle the form submission
            hpController.ValidateFields(newWeeklyUsage, newWeeklyUsageTextField, dateUsageStart, dateUsageEnd);
        });

        // Layout configuration
        VBox vboxCenter = new VBox(vboxUsage, submitNewUsage);
        vboxCenter.setAlignment(Pos.CENTER_LEFT);
        vboxCenter.setSpacing(12);
        vboxCenter.setPadding(new Insets(0, 20, 0, 20));

        root.setCenter(vboxCenter);

        // Create and set the scene
        Scene scene = new Scene(root, 480, 270);
        //scene.getStylesheets().add(getClass().getResource("stylesheet.css").toExternalForm());
        primaryStage.setScene(scene);
    }
}
package com.nathan.javafxperiode2faopdrenergiecurrent.view;

import com.nathan.javafxperiode2faopdrenergiecurrent.controller.RatesController;
import com.nathan.javafxperiode2faopdrenergiecurrent.model.Customer;
import com.nathan.javafxperiode2faopdrenergiecurrent.model.Usage;
import com.nathan.javafxperiode2faopdrenergiecurrent.service.UtilityService;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class NewRatesView {
    RatesController ratesController = new RatesController();
    UtilityService util = new UtilityService();

    public void setRates(Stage primaryStage, Customer customer) {
        Label helloMessage = new Label("Hello, " + customer.getCustomerFirstName() + ". Please insert the following rates.");

        //User inputs
        TextField inputCurrentRate = new TextField();
        VBox vboxCurrentRate = util.createLabeledInput("Please enter the rate for Current per kWh:", "Current Rate", inputCurrentRate);
        inputCurrentRate.getStyleClass().add("input-current-rate");

        TextField inputGasRate = new TextField();
        VBox vboxGasRate = util.createLabeledInput("Please enter the rate for Gas per m3:", "Gas Rate", inputGasRate);
        inputGasRate.getStyleClass().add("input-gas-rate");

        VBox vboxRates = new VBox(vboxCurrentRate, vboxGasRate);

        //Button with actions when pressed.
        Button buttonSend = new Button("Send");
        buttonSend.setOnAction(e ->{
            ratesController.ValidateFields(inputCurrentRate, inputGasRate, primaryStage);
        });

        // Layout options
        VBox inputFieldsRates = new VBox(helloMessage, vboxRates, buttonSend);
        inputFieldsRates.setAlignment(Pos.CENTER_LEFT);
        inputFieldsRates.setSpacing(12);
        inputFieldsRates.setPadding(new Insets(0,20,0,20));

        Scene scene = new Scene(inputFieldsRates, 480, 270);
        //scene.getStylesheets().add(getClass().getResource("stylesheet.css").toExternalForm());
        primaryStage.setScene(scene);
    }
}

package com.nathan.javafxperiode2faopdrenergiecurrent;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MainClass extends Application {
    @Override
    public void start(Stage primaryStage) {
        setUserSettings(primaryStage);
    }

    public void setUserSettings(Stage primaryStage){
        // User input for Customer class.
        TextField inputCustomerID = new TextField();
        VBox vboxID = createLabeledInput("Customer ID:", "Customer ID", inputCustomerID);

        TextField inputFirstName = new TextField();
        VBox vboxFirstName = createLabeledInput("First name:", "First name", inputFirstName);

        TextField inputLastName = new TextField();
        VBox vboxLastName = createLabeledInput("Last name:", "Last name", inputLastName);

        TextField inputAdvance = new TextField();
        VBox vboxAdvance = createLabeledInput("Advance:", "Advance", inputAdvance);

        // Saving the input to the setters of the Customer class.
        Button buttonSend = new Button("Send");
        buttonSend.setOnAction(e ->{
            // First trying the users input.
            try {
                Customer customer = new Customer();
                int intCustomerID = Integer.parseInt(inputCustomerID.getText());
                String strFirstName = inputFirstName.getText();
                String strLastName = inputLastName.getText();
                float fltAdvance = Float.parseFloat(inputAdvance.getText());
                // Setting users input.
                customer.setCustomerID(intCustomerID);
                customer.setCustomerFirstName(strFirstName);
                customer.setCustomerLastName(strLastName);
                customer.setCustomerAdvance(fltAdvance);
                setRates(primaryStage, customer);
            }
            // If formats are incorrect, show error.
            catch(NumberFormatException ex) {
                getAlert("Please fill in the fields with the correct format.");
            }
            // If inputs are still invalid, show error.
            catch(Exception ex) {
                getAlert("Oops! Something went wrong.");
            }
        });

        VBox inputFieldsCustomer = new VBox(vboxID, vboxFirstName, vboxLastName, vboxAdvance, buttonSend);
        inputFieldsCustomer.setAlignment(Pos.CENTER_LEFT);
        inputFieldsCustomer.setSpacing(12);
        inputFieldsCustomer.setPadding(new Insets(0,20,0,20));
        Scene scene = new Scene(inputFieldsCustomer, 480, 270);

        primaryStage.setTitle("Energy company 'Current'");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public void setRates(Stage primaryStage, Customer customer) {
        Label helloMessage = new Label("Hello, " + customer.getCustomerFirstName() + ". Please insert the following rates.");

        TextField inputCurrentRate = new TextField();
        VBox vboxCurrentRate = createLabeledInput("Please enter the rate for Current per kWh:", "Current Rate", inputCurrentRate);

        Label txtDateCurrent = new Label("Please enter the start and end date.");
        DatePicker dateCurrentStart = new DatePicker();
        dateCurrentStart.setPromptText("Start Date");
        DatePicker dateCurrentEnd = new DatePicker();
        dateCurrentEnd.setPromptText("End Date");
        HBox hboxDatesCurrent = new HBox(dateCurrentStart, dateCurrentEnd);

        VBox vboxCurrent = new VBox(vboxCurrentRate, txtDateCurrent, hboxDatesCurrent);

        TextField inputGasRate = new TextField();
        VBox vboxGasRate = createLabeledInput("Please enter the rate for Gas per m3:", "Gas Rate", inputGasRate);

        Label txtDateGas = new Label("Please enter the start and end date.");
        DatePicker dateGasStart = new DatePicker();
        dateGasStart.setPromptText("Start Date");
        DatePicker dateGasEnd = new DatePicker();
        dateGasEnd.setPromptText("End Date");
        HBox hboxDatesGas = new HBox(dateGasStart, dateGasEnd);

        VBox vboxGas = new VBox(vboxGasRate, txtDateGas, hboxDatesGas);

        Button buttonSend = new Button("Send");
        buttonSend.setOnAction(e ->{
            // First trying the users input.
            try {
                double rateCurrent = Double.parseDouble(inputCurrentRate.getText());
                LocalDate startDateCurrent = dateCurrentStart.getValue();
                LocalDate endDateCurrent = dateCurrentEnd.getValue();
                double rateGas = Double.parseDouble(inputGasRate.getText());
                LocalDate startDateGas = dateGasStart.getValue();
                LocalDate endDateGas = dateGasEnd.getValue();

                // Setting users input.
                Current current = new Current(startDateCurrent, endDateCurrent, rateCurrent);
                Gas gas = new Gas(startDateGas, endDateGas, rateGas);

                getHomepage(primaryStage);
            }
            // If formats are incorrect, show error.
            catch(NumberFormatException ex) {
                getAlert("Please fill in the fields with the correct format.");
            }
            catch(Exception ex) {
                getAlert("Oops! Something went wrong.");
            }
        });

        VBox inputFieldsRates = new VBox(helloMessage, vboxCurrent, vboxGas, buttonSend);
        inputFieldsRates.setAlignment(Pos.CENTER_LEFT);
        inputFieldsRates.setSpacing(12);
        inputFieldsRates.setPadding(new Insets(0,20,0,20));

        Scene scene = new Scene(inputFieldsRates, 480, 270);
        primaryStage.setScene(scene);
    }

    // Function to show Menu bar.
    public Node getMenuBar(Stage primaryStage) {
        Menu menuNew = new Menu("New");
        Menu menuUsage = new Menu("Usage");
        Menu menuSettings = new Menu("Settings");
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(menuNew, menuUsage, menuSettings);

        // TODO: make all navigation work.
        MenuItem menuItemNew1 = new MenuItem("New Usage");
        MenuItem menuItemUsage1 = new MenuItem("Per Week");
        MenuItem menuItemUsage2 = new MenuItem("Per Month");
        MenuItem menuItemUsage3 = new MenuItem("Per Year");
        MenuItem menuItemSettings = new MenuItem("Change Settings");
        menuNew.getItems().addAll(menuItemNew1);
        menuUsage.getItems().addAll(menuItemUsage1, menuItemUsage2, menuItemUsage3);
        menuSettings.getItems().addAll(menuItemSettings);

        menuItemSettings.setOnAction(e ->{
            setUserSettings(primaryStage);
        });
        return menuBar;
    }

    // Function to show the homepage.
    public void getHomepage(Stage primaryStage) {
        //TODO: make homepage.
        BorderPane root = new BorderPane();
        root.setTop(getMenuBar(primaryStage));

        Label titleLabel = new Label("Add new weekly usage");
        TextField newGasUsage = new TextField();
        VBox vboxNewGas = createLabeledInput("Gas usage this week:", "Gas Usage in m3", newGasUsage);

        Label txtDateGas = new Label("Please enter the start and end date.");
        DatePicker dateGasStart = new DatePicker();
        DatePicker dateGasEnd = new DatePicker();
        HBox hboxDatesGas = new HBox(dateGasStart, dateGasEnd);
        VBox vboxGas = new VBox(vboxNewGas, txtDateGas, hboxDatesGas);
        dateGasStart.setPromptText("Start Date");
        dateGasEnd.setPromptText("End Date");

        TextField newCurrentUsage = new TextField();
        VBox vboxNewCurrent = createLabeledInput("Current usage this week:", "Current usage in kWh", newCurrentUsage);

        Label txtDateCurrent = new Label("Please enter the start and end date.");
        DatePicker dateCurrentStart = new DatePicker();
        DatePicker dateCurrentEnd = new DatePicker();
        HBox hboxDatesCurrent = new HBox(dateCurrentStart, dateCurrentEnd);
        VBox vboxCurrent = new VBox(vboxNewCurrent, txtDateCurrent, hboxDatesCurrent);
        dateCurrentStart.setPromptText("Start Date");
        dateCurrentEnd.setPromptText("End Date");

        Button submitNewUsage = new Button("Submit New Usage");

        submitNewUsage.setOnAction(e ->{
            try {
                double usageGas = Double.parseDouble(newGasUsage.getText());
                LocalDate startDateCurrent = dateCurrentStart.getValue();
                LocalDate endDateCurrent = dateCurrentEnd.getValue();
                double usageCurrent = Double.parseDouble(newCurrentUsage.getText());
                LocalDate startDateGas = dateGasStart.getValue();
                LocalDate endDateGas = dateGasEnd.getValue();
                List<Date> contracts = new ArrayList<>();

                contracts.add(new Current(startDateCurrent, endDateCurrent, usageCurrent));
                contracts.add(new Gas(startDateGas, endDateGas, usageGas));
            }
            // If formats are incorrect, show error.
            catch(NumberFormatException ex) {
                getAlert("Please fill in the fields with the correct format.");
            }
            catch(Exception ex) {
                getAlert("Oops! Something went wrong.");
            }
        });

        VBox vboxCenter = new VBox(titleLabel, vboxGas, vboxCurrent, submitNewUsage);
        vboxCenter.setAlignment(Pos.CENTER_LEFT);
        vboxCenter.setSpacing(12);
        vboxCenter.setPadding(new Insets(0,20,0,20));

        root.setCenter(vboxCenter);
        Scene scene = new Scene(root, 1280, 720);
        primaryStage.setScene(scene);
    }

    // Function for creating labeled inputs.
    private VBox createLabeledInput(String labelText, String promptText, TextField inputField) {
        Label label = new Label(labelText);
        inputField.setPromptText(promptText);
        return new VBox(label, inputField);
    }

    // Alert function to get alerts on screen.
    public void getAlert(String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Oops!");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch();
    }
}
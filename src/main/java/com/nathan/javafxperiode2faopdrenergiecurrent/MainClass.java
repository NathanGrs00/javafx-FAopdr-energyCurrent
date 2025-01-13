package com.nathan.javafxperiode2faopdrenergiecurrent;

import com.nathan.javafxperiode2faopdrenergiecurrent.model.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class MainClass extends Application {
    Utility util = new Utility();
    UsageController usageController = new UsageController();

    @Override
    public void start(Stage primaryStage) {
        setUserSettings(primaryStage);
    }

    public void setUserSettings(Stage primaryStage){
        // User input for Customer class.
        TextField inputCustomerID = new TextField();
        VBox vboxID = util.createLabeledInput("Customer ID:", "Customer ID", inputCustomerID);

        TextField inputFirstName = new TextField();
        VBox vboxFirstName = util.createLabeledInput("First name:", "First name", inputFirstName);

        TextField inputLastName = new TextField();
        VBox vboxLastName = util.createLabeledInput("Last name:", "Last name", inputLastName);

        TextField inputAdvance = new TextField();
        VBox vboxAdvance = util.createLabeledInput("Advance:", "Advance", inputAdvance);

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
        VBox vboxCurrentRate = util.createLabeledInput("Please enter the rate for Current per kWh:", "Current Rate", inputCurrentRate);

        TextField inputGasRate = new TextField();
        VBox vboxGasRate = util.createLabeledInput("Please enter the rate for Gas per m3:", "Gas Rate", inputGasRate);

        VBox vboxRates = new VBox(vboxCurrentRate, vboxGasRate);

        Button buttonSend = new Button("Send");
        buttonSend.setOnAction(e ->{
            // First trying the users input.
            try {
                // Setting users input.
                double gasRate = Double.parseDouble(inputGasRate.getText());
                double currentRate = Double.parseDouble(inputCurrentRate.getText());
                Rates rates = new Rates();
                rates.setGasRate(gasRate);
                rates.setCurrentRate(currentRate);

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

        VBox inputFieldsRates = new VBox(helloMessage, vboxRates, buttonSend);
        inputFieldsRates.setAlignment(Pos.CENTER_LEFT);
        inputFieldsRates.setSpacing(12);
        inputFieldsRates.setPadding(new Insets(0,20,0,20));

        Scene scene = new Scene(inputFieldsRates, 480, 270);
        primaryStage.setScene(scene);
    }

    // Function to show the homepage.
    public void getHomepage(Stage primaryStage) {
        //TODO: make homepage.
        BorderPane root = new BorderPane();
        root.setTop(getMenuBar(primaryStage));

        Label usageTypeLabel = new Label("Select type of usage:");
        ComboBox newWeeklyUsage = new ComboBox();
        newWeeklyUsage.getItems().addAll("Gas","Current");
        TextField newWeeklyUsageTextField = new TextField();
        VBox vboxUsageAmount = util.createLabeledInput("Add new weekly usage","Usage amount", newWeeklyUsageTextField);

        DatePicker dateUsageStart = new DatePicker();
        DatePicker dateUsageEnd = new DatePicker();
        VBox vboxUsageDate = util.createDateInput(dateUsageStart, dateUsageEnd);
        VBox vboxUsage = new VBox(usageTypeLabel, newWeeklyUsage, vboxUsageAmount, vboxUsageDate);

        Button submitNewUsage = new Button("Submit New Usage");
        Rates rates = new Rates();

        dateUsageStart.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                dateUsageEnd.setValue(newValue.plusDays(7));
                restrictEndDatePicker(dateUsageEnd, newValue);
            }
        });

        dateUsageEnd.setEditable(false);

        submitNewUsage.setOnAction(e ->{
            try {
                double usageNew = Double.parseDouble(newWeeklyUsageTextField.getText());
                LocalDate startDateUsage = dateUsageStart.getValue();
                LocalDate endDateUsage = dateUsageEnd.getValue();
                String instanceKind = newWeeklyUsage.getSelectionModel().getSelectedItem().toString();
                usageController.saveNewUsage(usageNew, rates.getGasRate(), rates.getCurrentRate(), startDateUsage, endDateUsage, instanceKind);
            }
            // If formats are incorrect, show error.
            catch(NumberFormatException ex) {
                getAlert("Please fill in the fields with the correct format.");
            }
            catch(Exception ex) {
                getAlert("Oops! Something went wrong.");
            }
        });

        VBox vboxCenter = new VBox(vboxUsage, submitNewUsage);
        vboxCenter.setAlignment(Pos.CENTER_LEFT);
        vboxCenter.setSpacing(12);
        vboxCenter.setPadding(new Insets(0,20,0,20));

        root.setCenter(vboxCenter);
        Scene scene = new Scene(root, 1280, 720);
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
        MenuItem menuItemUsage1 = new MenuItem("All Usage");
        MenuItem menuItemSettings = new MenuItem("Change Settings");
        menuNew.getItems().addAll(menuItemNew1);
        menuUsage.getItems().addAll(menuItemUsage1);
        menuSettings.getItems().addAll(menuItemSettings);

        menuItemNew1.setOnAction(e ->{
            getHomepage(primaryStage);
        });
        menuItemUsage1.setOnAction(e ->{
            getUsageOverview(primaryStage);
        });
        menuItemSettings.setOnAction(e ->{
            setUserSettings(primaryStage);
        });

        return menuBar;
    }

    public void getUsageOverview(Stage primaryStage) {
        BorderPane root = new BorderPane();
        root.setTop(getMenuBar(primaryStage));
        FlowPane centerPane = new FlowPane();
        root.setCenter(centerPane);

        ArrayList<Double> weeklyUsage = usageController.getWeeklyUsage();
        Double weeklyUsageGas = weeklyUsage.getFirst();
        Double weeklyUsageCurrent = weeklyUsage.getLast();
        ArrayList<Double> monthlyUsage = usageController.getMonthlyUsage();
        Double monthlyUsageGas = monthlyUsage.getFirst();
        Double monthlyUsageCurrent = monthlyUsage.getLast();
        ArrayList<Double> yearlyUsage = usageController.getYearlyUsage();
        Double yearlyUsageGas = yearlyUsage.getFirst();
        Double yearlyUsageCurrent = yearlyUsage.getLast();

        //TODO: make total usage for both gas and current and cost calculations.
        Label weeklyUsageLabelGas = new Label("Average weekly gas usage: " + weeklyUsageGas);
        Label monthlyUsageLabelGas = new Label("Average monthly gas usage: " + monthlyUsageGas);
        Label yearlyUsageLabelGas = new Label("Average yearly gas usage: " + yearlyUsageGas);
        VBox vboxGasUsage = new VBox(weeklyUsageLabelGas, monthlyUsageLabelGas, yearlyUsageLabelGas);

        Label weeklyUsageLabelCurrent = new Label(" Average weekly current usage: " + weeklyUsageCurrent);
        Label monthlyUsageLabelCurrent = new Label(" Average monthly current usage: " + monthlyUsageCurrent);
        Label yearlyUsageLabelCurrent = new Label(" Average yearly current usage: " + yearlyUsageCurrent);
        VBox vboxUsageCurrent = new VBox(weeklyUsageLabelCurrent, monthlyUsageLabelCurrent, yearlyUsageLabelCurrent);

        Label weeklyUsageLabelGasCost = new Label(" Weekly Usage Cost Gas: € "+ usageController.getWeeklyCost());
        Label monthlyUsageLabelGasCost = new Label(" Monthly Usage Cost Gas: € " + usageController.getMonthlyCost());
        Label yearlyUsageLabelGasCost = new Label(" Yearly Usage Cost Gas: € " + usageController.getYearlyCost());
        VBox gasCosts = new VBox(weeklyUsageLabelGasCost, monthlyUsageLabelGasCost, yearlyUsageLabelGasCost);

        Label weeklyUsageLabelCurrentCost = new Label(" Weekly Usage Cost Current: € "+ usageController.getWeeklyCost());
        Label monthlyUsageLabelCurrentCost = new Label(" Monthly Usage Cost Current: € " + usageController.getMonthlyCost());
        Label yearlyUsageLabelCurrentCost = new Label(" Yearly Usage Cost Current: € " + usageController.getYearlyCost());
        VBox currentCosts = new VBox(weeklyUsageLabelCurrentCost, monthlyUsageLabelCurrentCost, yearlyUsageLabelCurrentCost);

        ListView<String> listView = new ListView<>();
        listView.getItems().add("Usage Overview");
        listView.getItems().add("----------------------------");
        listView.setPrefWidth(1000);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        for (Usage usage : usageController.getList()) {
            if (usage instanceof Gas) {
                listView.getItems().add("Gas Usage: " + usage.getUsage() + " m³ from " + usage.getDateStart().format(formatter) + " to " + usage.getDateEnd().format(formatter));
            } else if (usage instanceof Current) {
                listView.getItems().add("Current Usage: " + usage.getUsage() + " kWh from " + usage.getDateStart().format(formatter) + " to " + usage.getDateEnd().format(formatter));
            }
        }

        centerPane.getChildren().addAll(vboxGasUsage, vboxUsageCurrent, gasCosts, currentCosts, listView);

        Scene scene = new Scene(root, 1280, 720);
        primaryStage.setScene(scene);
    }

    private void restrictEndDatePicker(DatePicker endDatePicker, LocalDate startDate) {
        endDatePicker.setDayCellFactory(d -> new DateCell() {
            @Override
            public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);
                setDisable(item.isBefore(startDate.plusDays(7)) || item.isAfter(startDate.plusDays(7)));
            }
        });
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
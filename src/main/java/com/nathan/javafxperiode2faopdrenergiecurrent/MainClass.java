package com.nathan.javafxperiode2faopdrenergiecurrent;

import com.nathan.javafxperiode2faopdrenergiecurrent.model.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MainClass extends Application {
    Utility util = new Utility();
    UsageController uController = new UsageController();
    Rates rates = new Rates();

    @Override
    public void start(Stage primaryStage) {
        setUserSettings(primaryStage);
    }

    public void setUserSettings(Stage primaryStage){
        // User input for Customer class.
        TextField inputCustomerID = new TextField();
        VBox vboxID = util.createLabeledInput("Customer ID:", "Customer ID", inputCustomerID);
        inputCustomerID.getStyleClass().add("input-customer-id");

        TextField inputFirstName = new TextField();
        VBox vboxFirstName = util.createLabeledInput("First name:", "First name", inputFirstName);
        inputFirstName.getStyleClass().add("input-first-name");

        TextField inputLastName = new TextField();
        VBox vboxLastName = util.createLabeledInput("Last name:", "Last name", inputLastName);
        inputLastName.getStyleClass().add("input-last-name");

        TextField inputAdvance = new TextField();
        VBox vboxAdvance = util.createLabeledInput("Advance:", "Advance", inputAdvance);
        inputAdvance.getStyleClass().add("input-advance");

        // Saving the input to the setters of the Customer class.
        Button buttonSend = new Button("Send");
        buttonSend.setOnAction(e ->{
            // First trying the users input.
            try {
                if (inputCustomerID.getText().isEmpty() || inputFirstName.getText().isEmpty() || inputLastName.getText().isEmpty() || inputAdvance.getText().isEmpty()) {
                    getAlert("Please, fill out all fields.");
                    return;
                }

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
        scene.getStylesheets().add(getClass().getResource("stylesheet.css").toExternalForm());

        Image icon = new Image(getClass().getResourceAsStream("energy.png"));

        primaryStage.getIcons().add(icon);
        primaryStage.setTitle("Energy company 'Current'");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public void setRates(Stage primaryStage, Customer customer) {
        Label helloMessage = new Label("Hello, " + customer.getCustomerFirstName() + ". Please insert the following rates.");

        TextField inputCurrentRate = new TextField();
        VBox vboxCurrentRate = util.createLabeledInput("Please enter the rate for Current per kWh:", "Current Rate", inputCurrentRate);
        inputCurrentRate.getStyleClass().add("input-current-rate");

        TextField inputGasRate = new TextField();
        VBox vboxGasRate = util.createLabeledInput("Please enter the rate for Gas per m3:", "Gas Rate", inputGasRate);
        inputGasRate.getStyleClass().add("input-gas-rate");

        VBox vboxRates = new VBox(vboxCurrentRate, vboxGasRate);

        Button buttonSend = new Button("Send");
        buttonSend.setOnAction(e ->{
            // First trying the users input.
            try {
                // Setting users input.
                double gasRate = Double.parseDouble(inputGasRate.getText());
                double currentRate = Double.parseDouble(inputCurrentRate.getText());

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
        scene.getStylesheets().add(getClass().getResource("stylesheet.css").toExternalForm());
        primaryStage.setScene(scene);
    }

    // Function to show the homepage.
    public void getHomepage(Stage primaryStage) {
        BorderPane root = new BorderPane();
        root.setTop(getMenuBar(primaryStage));

        Label usageTypeLabel = new Label("Select type of usage:");
        ComboBox newWeeklyUsage = new ComboBox();
        newWeeklyUsage.getItems().addAll("Gas","Current");
        TextField newWeeklyUsageTextField = new TextField();
        VBox vboxUsageAmount = util.createLabeledInput("Add new weekly usage","Usage amount", newWeeklyUsageTextField);
        newWeeklyUsage.getStyleClass().add("combo-weekly-usage");
        newWeeklyUsageTextField.getStyleClass().add("input-weekly-usage");

        DatePicker dateUsageStart = new DatePicker();
        DatePicker dateUsageEnd = new DatePicker();
        dateUsageStart.getStyleClass().add("input-date-start");
        dateUsageEnd.getStyleClass().add("input-date-end");
        VBox vboxUsageDate = util.createDateInput(dateUsageStart, dateUsageEnd);
        VBox vboxUsage = new VBox(usageTypeLabel, newWeeklyUsage, vboxUsageAmount, vboxUsageDate);

        Button submitNewUsage = new Button("Submit New Usage");

        dateUsageStart.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                dateUsageEnd.setValue(newValue.plusDays(7));
                restrictEndDatePicker(dateUsageEnd, newValue);
            }
        });

        dateUsageEnd.setEditable(false);

        submitNewUsage.setOnAction(e ->{
            try {
                if (newWeeklyUsage.getValue() == null || dateUsageEnd.getValue() == null || dateUsageStart.getValue() == null || newWeeklyUsageTextField.getText().isEmpty()) {
                    getAlert("Please, fill out all fields.");
                    return;
                }

                double usageNew = Double.parseDouble(newWeeklyUsageTextField.getText());
                LocalDate startDateUsage = dateUsageStart.getValue();
                LocalDate endDateUsage = dateUsageEnd.getValue();
                String instanceKind = newWeeklyUsage.getSelectionModel().getSelectedItem().toString();
                uController.saveNewUsage(usageNew, rates.getGasRate(), rates.getCurrentRate(), startDateUsage, endDateUsage, instanceKind);
                getAlert("Usage succesfully added to the list.");
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
        Scene scene = new Scene(root, 480, 270);
        scene.getStylesheets().add(getClass().getResource("stylesheet.css").toExternalForm());
        primaryStage.setScene(scene);
    }

    // Function to show Menu bar.
    public Node getMenuBar(Stage primaryStage) {
        Menu menuNew = new Menu("New");
        Menu menuUsage = new Menu("Usage");
        Menu menuSettings = new Menu("Settings");
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(menuNew, menuUsage, menuSettings);

        MenuItem menuItemNew = new MenuItem("New Usage");
        MenuItem menuItemUsage = new MenuItem("All Usage");
        MenuItem menuItemSettings = new MenuItem("Change Settings");
        menuNew.getItems().addAll(menuItemNew);
        menuUsage.getItems().addAll(menuItemUsage);
        menuSettings.getItems().addAll(menuItemSettings);

        menuItemNew.setOnAction(e ->{
            getHomepage(primaryStage);
        });
        menuItemUsage.setOnAction(e ->{
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
        centerPane.getStyleClass().add("flow-pane");
        root.setCenter(centerPane);

        Double weeklyGasAmount = uController.getWeeklyUsage().getFirst();
        Double weeklyGasCost = uController.getCost(weeklyGasAmount).getFirst();
        Double monthlyGasAmount = uController.getMonthlyUsage().getFirst();
        Double monthlyGasCost = uController.getCost(monthlyGasAmount).getFirst();
        Double yearlyGasAmount = uController.getYearlyUsage().getFirst();
        Double yearlyGasCost = uController.getCost(yearlyGasAmount).getFirst();

        Double weeklyCurrentAmount = uController.getWeeklyUsage().getLast();
        Double weeklyCurrentCost = uController.getCost(weeklyCurrentAmount).getLast();
        Double monthlyCurrentAmount = uController.getMonthlyUsage().getLast();
        Double monthlyCurrentCost = uController.getCost(monthlyCurrentAmount).getLast();
        Double yearlyCurrentAmount = uController.getYearlyUsage().getLast();
        Double yearlyCurrentCost = uController.getCost(yearlyCurrentAmount).getLast();

        Label weeklyUsageLabelGas = new Label("Average weekly gas usage: " + UsageController.roundTwoDecimals(weeklyGasAmount) + " m3.");
        Label monthlyUsageLabelGas = new Label("Average monthly gas usage: " + UsageController.roundTwoDecimals(monthlyGasAmount) + " m3.");
        Label yearlyUsageLabelGas = new Label("Average yearly gas usage: " + UsageController.roundTwoDecimals(yearlyGasAmount) + " m3.");
        VBox vboxGasUsage = new VBox(weeklyUsageLabelGas, monthlyUsageLabelGas, yearlyUsageLabelGas);

        Label weeklyUsageLabelCurrent = new Label(" Average weekly current usage: " + UsageController.roundTwoDecimals(weeklyCurrentAmount) + " kWh.");
        Label monthlyUsageLabelCurrent = new Label(" Average monthly current usage: " + UsageController.roundTwoDecimals(monthlyCurrentAmount) + " kWh.");
        Label yearlyUsageLabelCurrent = new Label(" Average yearly current usage: " + UsageController.roundTwoDecimals(yearlyCurrentAmount) + " kWh.");
        VBox vboxUsageCurrent = new VBox(weeklyUsageLabelCurrent, monthlyUsageLabelCurrent, yearlyUsageLabelCurrent);

        Label weeklyUsageLabelGasCost = new Label(" Weekly Usage Cost Gas: € " + UsageController.roundTwoDecimals(weeklyGasCost));
        Label monthlyUsageLabelGasCost = new Label(" Monthly Usage Cost Gas: € " + UsageController.roundTwoDecimals(monthlyGasCost));
        Label yearlyUsageLabelGasCost = new Label(" Yearly Usage Cost Gas: € " + UsageController.roundTwoDecimals(yearlyGasCost));
        VBox gasCosts = new VBox(weeklyUsageLabelGasCost, monthlyUsageLabelGasCost, yearlyUsageLabelGasCost);

        Label weeklyUsageLabelCurrentCost = new Label(" Weekly Usage Cost Current: € "+ UsageController.roundTwoDecimals(weeklyCurrentCost));
        Label monthlyUsageLabelCurrentCost = new Label(" Monthly Usage Cost Current: € " + UsageController.roundTwoDecimals(monthlyCurrentCost));
        Label yearlyUsageLabelCurrentCost = new Label(" Yearly Usage Cost Current: € " + UsageController.roundTwoDecimals(yearlyCurrentCost));
        VBox currentCosts = new VBox(weeklyUsageLabelCurrentCost, monthlyUsageLabelCurrentCost, yearlyUsageLabelCurrentCost);

        ListView<String> listView = new ListView<>();
        listView.getItems().add("Usage Overview");
        listView.getItems().add("----------------------------");
        listView.setPrefWidth(1000);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        for (Usage usage : uController.getList()) {
            if (usage instanceof Gas) {
                listView.getItems().add("Gas Usage: " + usage.getUsage() + " m³ from " + usage.getDateStart().format(formatter) + " to " + usage.getDateEnd().format(formatter));
            } else if (usage instanceof Current) {
                listView.getItems().add("Current Usage: " + usage.getUsage() + " kWh from " + usage.getDateStart().format(formatter) + " to " + usage.getDateEnd().format(formatter));
            }
        }

        centerPane.getChildren().addAll(vboxGasUsage, vboxUsageCurrent, gasCosts, currentCosts, listView);

        Scene scene = new Scene(root, 1280, 720);
        scene.getStylesheets().add(getClass().getResource("stylesheet.css").toExternalForm());
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
package com.nathan.javafxperiode2faopdrenergiecurrent;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainClass extends Application {
    @Override
    public void start(Stage stage) {
        // User input for Customer class.
        Label txtCustomerID = new Label("Customer ID:");
        TextField inputCustomerID = new TextField();
        inputCustomerID.setPromptText("Customer ID");
        VBox vboxID = new VBox(txtCustomerID, inputCustomerID);

        Label txtFirstName = new Label("First name:");
        TextField inputFirstName = new TextField();
        inputFirstName.setPromptText("First Name");
        VBox vboxFirstName = new VBox(txtFirstName, inputFirstName);

        Label txtLastName = new Label("Last name:");
        TextField inputLastName = new TextField();
        inputLastName.setPromptText("Last Name");
        VBox vboxLastName = new VBox(txtLastName, inputLastName);

        Label txtAdvance = new Label("Yearly advance:");
        TextField inputAdvance = new TextField();
        inputAdvance.setPromptText("Yearly advance");
        VBox vboxAdvance = new VBox(txtAdvance, inputAdvance);

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
                //Setting users input.
                customer.setCustomerID(intCustomerID);
                customer.setCustomerFirstName(strFirstName);
                customer.setCustomerLastName(strLastName);
                customer.setCustomerAdvance(fltAdvance);
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

        VBox centerPane = new VBox(vboxID, vboxFirstName, vboxLastName, vboxAdvance, buttonSend);
        centerPane.setAlignment(Pos.CENTER_LEFT);
        centerPane.setSpacing(12);
        centerPane.setPadding(new Insets(0,20,0,20));
        Scene scene = new Scene(centerPane, 480, 270);

        stage.setTitle("Energy company 'Current'");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    // Function to show Menu bar.
    public Node getMenuBar(){
        Menu menuItemNew = new Menu("New");
        Menu menuItemUsage = new Menu("Usage");
        Menu menuItemSettings = new Menu("Settings");
        MenuBar menuBar = new MenuBar();

        // TODO: make navigation work.
        menuItemNew.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });
        menuBar.getMenus().addAll(menuItemNew, menuItemUsage, menuItemSettings);
        return menuBar;
    }

    // Function to show the homepage.
    public void getHomepage() {
        //TODO: make homepage.
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
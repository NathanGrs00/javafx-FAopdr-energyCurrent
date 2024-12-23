package com.nathan.javafxperiode2faopdrenergiecurrent;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainClass extends Application {
    @Override
    public void start(Stage stage) {
        Label txtCustomerID = new Label("Customer ID:");
        TextField inputCustomerID = new TextField();
        inputCustomerID.setPromptText("Customer ID");

        Label txtFirstName = new Label("First name:");
        TextField inputFirstName = new TextField();
        inputFirstName.setPromptText("First Name");

        Label txtLastName = new Label("Last name:");
        TextField inputLastName = new TextField();
        inputLastName.setPromptText("Last Name");

        Label txtAdvance = new Label("Yearly advance:");
        TextField inputAdvance = new TextField();
        inputAdvance.setPromptText("Yearly advance");

        Button buttonSend = new Button("Send");
        buttonSend.setOnAction(e ->{
            Customer customer = new Customer();
            int intCustomerID = Integer.parseInt(inputCustomerID.getText());
            String strFirstName = inputFirstName.getText();
            String strLastName = inputLastName.getText();
            float fltAdvance = Float.parseFloat(inputAdvance.getText());

            customer.setCustomerID(intCustomerID);
            customer.setCustomerFirstName(strFirstName);
            customer.setCustomerLastName(strLastName);
            customer.setCustomerAdvance(fltAdvance);
        });

        VBox centerPane = new VBox(txtCustomerID, inputCustomerID, txtFirstName, inputFirstName, txtLastName, inputLastName, txtAdvance, inputAdvance, buttonSend);
        BorderPane root = new BorderPane();
        root.setTop(getMenuBar());
        root.setCenter(centerPane);
        Scene scene = new Scene(root, 1280, 720);
        stage.setTitle("Energy company 'Current'");
        stage.setScene(scene);
        stage.show();
    }

    public Node getMenuBar(){
        Menu menuItemNew = new Menu("New");
        Menu menuItemUsage = new Menu("Usage");
        Menu menuItemSettings = new Menu("Settings");
        MenuBar menuBar = new MenuBar();

        menuItemNew.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });
        menuBar.getMenus().addAll(menuItemNew, menuItemUsage, menuItemSettings);
        return menuBar;
    }

    public void getHomepage(){

    }

    public static void main(String[] args) {
        launch();
    }
}
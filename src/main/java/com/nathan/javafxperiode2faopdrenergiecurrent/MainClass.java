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

import java.io.IOException;

public class MainClass extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Customer customer = new Customer();
        Label txtFirstName = new Label("First Name: ");
        TextField inputFirstName = new TextField();
        Label txtLastName = new Label("Last Name: ");
        TextField inputLastName = new TextField();

        Button buttonSend = new Button("Send");
        buttonSend.setOnAction(e ->{
            String strFirstName = inputFirstName.getText();
            String strLastName = inputLastName.getText();
            customer.setCustomerFirstName(strFirstName);
            customer.setCustomerLastName(strLastName);
        });

        VBox centerPane = new VBox(inputFirstName, inputLastName, buttonSend);
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
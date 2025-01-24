package com.nathan.javafxperiode2faopdrenergiecurrent.view;

import com.nathan.javafxperiode2faopdrenergiecurrent.EnergyCurrent;
import com.nathan.javafxperiode2faopdrenergiecurrent.service.CustomerService;
import javafx.scene.Node;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;


public class MenuBarView {
    public Node getMenuBar(Stage primaryStage) {
        EnergyCurrent mainClass = new EnergyCurrent();
        //Menu tabs
        Menu menuNew = new Menu("New");
        Menu menuUsage = new Menu("Usage");
        Menu menuSettings = new Menu("Settings");
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(menuNew, menuUsage, menuSettings);

        // Menu Items
        MenuItem menuItemNew = new MenuItem("New Usage");
        MenuItem menuItemUsage = new MenuItem("All Usage");
        MenuItem menuItemSettings = new MenuItem("Change Settings");
        MenuItem menuItemRates = new MenuItem("Change Rates");

        menuNew.getItems().add(menuItemNew);
        menuUsage.getItems().add(menuItemUsage);
        menuSettings.getItems().addAll(menuItemRates, menuItemSettings);

        // Linking to pages.
        menuItemNew.setOnAction(e ->{
            mainClass.navigateToHomepage(primaryStage);
        });
        menuItemUsage.setOnAction(e ->{
            mainClass.navigateToOverview(primaryStage);
        });
        menuItemSettings.setOnAction(e ->{
            mainClass.start(primaryStage);
        });
        menuItemRates.setOnAction(e ->{
            mainClass.navigateToRates(CustomerService.getCurrentCustomerId(), primaryStage);
        });

        return menuBar;
    }
}

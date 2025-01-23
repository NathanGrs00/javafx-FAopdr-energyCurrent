package com.nathan.javafxperiode2faopdrenergiecurrent.view;

import com.nathan.javafxperiode2faopdrenergiecurrent.MainClass;
import javafx.scene.Node;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;


public class MenuBarView {
    public Node getMenuBar(Stage primaryStage) {
        MainClass mainClass = new MainClass();
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
        menuNew.getItems().addAll(menuItemNew);
        menuUsage.getItems().addAll(menuItemUsage);
        menuSettings.getItems().addAll(menuItemSettings);

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

        return menuBar;
    }
}

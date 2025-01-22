package com.nathan.javafxperiode2faopdrenergiecurrent.service;

import javafx.scene.control.Alert;

public class AlertService {
    public void getAlert(String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Alert");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

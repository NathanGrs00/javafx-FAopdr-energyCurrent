module com.nathan.javafxperiode2faopdrenergiecurrent {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.nathan.javafxperiode2faopdrenergiecurrent to javafx.fxml;
    exports com.nathan.javafxperiode2faopdrenergiecurrent;
    exports com.nathan.javafxperiode2faopdrenergiecurrent.model;
    opens com.nathan.javafxperiode2faopdrenergiecurrent.model to javafx.fxml;
    exports com.nathan.javafxperiode2faopdrenergiecurrent.controller;
    opens com.nathan.javafxperiode2faopdrenergiecurrent.controller to javafx.fxml;
    exports com.nathan.javafxperiode2faopdrenergiecurrent.service;
    opens com.nathan.javafxperiode2faopdrenergiecurrent.service to javafx.fxml;
}
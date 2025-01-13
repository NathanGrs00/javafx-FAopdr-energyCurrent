module com.nathan.javafxperiode2faopdrenergiecurrent {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.nathan.javafxperiode2faopdrenergiecurrent to javafx.fxml;
    exports com.nathan.javafxperiode2faopdrenergiecurrent;
    exports com.nathan.javafxperiode2faopdrenergiecurrent.model;
    opens com.nathan.javafxperiode2faopdrenergiecurrent.model to javafx.fxml;
}
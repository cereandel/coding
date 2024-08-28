module com.cereandel {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.cereandel to javafx.fxml;
    exports com.cereandel;
}

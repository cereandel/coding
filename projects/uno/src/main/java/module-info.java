module com.cereandel {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens com.cereandel to javafx.fxml;

    exports com.cereandel;
}

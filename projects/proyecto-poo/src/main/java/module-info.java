module ucab.proyectopooprueba {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires com.fasterxml.jackson.databind;

    opens ucab to javafx.fxml;
    exports ucab;

    exports ucab.clasesUtilidad to com.fasterxml.jackson.databind;
    exports ucab.gestionJugador to com.fasterxml.jackson.databind;
    exports ucab.clasesCarta to com.fasterxml.jackson.databind;
    opens ucab.gestionJugador to com.fasterxml.jackson.databind;
    opens ucab.gestionJuego to com.fasterxml.jackson.databind;
}
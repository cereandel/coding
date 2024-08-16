package ucab.gestionJuego;

import java.io.IOException;
import java.util.Optional;
import javafx.scene.control.ChoiceDialog;
import ucab.clasesCarta.ListaCartas;

public final class IoPartida {

    public static String seleccionarCartaMaquina(String input, Turno turno, ListaCartas mazoDescarte) {
        input = PartidaUtilidades.primeraCartaElegible(turno.getReferenciaJugador().getMazo(), mazoDescarte.get(0));
        return input;
    }

    public static String elegirColor() throws IOException {
        ChoiceDialog<String> dialog = new ChoiceDialog<>("Red", "Blue", "Green", "Yellow");
        dialog.setTitle("Elegir color");
        dialog.setHeaderText("Elija un color");
        dialog.setContentText("Color:");
        Optional<String> result = dialog.showAndWait();
        return result.orElse("Red");
    }
}
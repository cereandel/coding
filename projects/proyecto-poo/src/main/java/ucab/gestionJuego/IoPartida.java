package ucab.gestionJuego;

import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;

import javafx.scene.control.ChoiceDialog;
import ucab.gestionJugador.Jugador;
import ucab.clasesCarta.ListaCartas;

public final class IoPartida {
    public static Scanner scanner = new Scanner(System.in);

    public static String seleccionarCartaMaquina(String input, Turno turno, ListaCartas mazoDescarte) {
        input = PartidaUtilidades.primeraCartaElegible(turno.getReferenciaJugador().getMazo(), mazoDescarte.get(0));
        return input;
    }

    public static Jugador generarJugador(int i, int cantidadDeJugadores, int id, String nombreJugador,
            ListaCartas mazo) {
        Jugador jugador;
        if (i < cantidadDeJugadores - 1) {
            jugador = new Jugador(nombreJugador, "#00" + Integer.toString(id), GestorDePartida.repartirMazo(mazo));
        } else {
            jugador = new Jugador("Computadora", "#00", GestorDePartida.repartirMazo(mazo));
        }
        return jugador;
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
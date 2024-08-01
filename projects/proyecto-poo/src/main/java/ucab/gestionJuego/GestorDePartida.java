package ucab.gestionJuego;

import java.io.File;
import java.io.IOException;
import java.util.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import ucab.SceneController;
import ucab.clasesCarta.GeneradorMazo;
import ucab.clasesCarta.ListaCartas;
import ucab.clasesUtilidad.ArchivoJson;
import ucab.clasesUtilidad.Juego;
import ucab.gestionJugador.Jugador;
import ucab.gestionJugador.ListaJugadores;

public final class GestorDePartida {
    private static Stage stage;
    private static Parent root;

    public static ListaCartas crearMazo() {
        return GeneradorMazo.crear();
    }

    public static void eliminarPrimeraCarta(ListaCartas mazo, ListaCartas mazoDescarte) {
        // Utilizar la primera carta del mazo como la primera carta del mazo de descarte
        mazoDescarte.agregarCarta(mazo.get(0));
        mazo.remove(0);
    }

    public static ListaCartas repartirMazo(ListaCartas mazo) {
        ListaCartas mazoJugador = new ListaCartas();
        // Repartir el mazo para cada uno
        for (int i = 0; i <= 6; i++) {
            Random rand = new Random();
            int numran = rand.nextInt(mazo.size());
            mazoJugador.agregarCarta(mazo.get(numran));
            mazo.remove(numran);
        }
        return mazoJugador;
    }

    private static ListaJugadores crearJugadores(int cantidadDeJugadores, String nombreJugador, ListaCartas mazo) {
        ListaJugadores jugadores = new ListaJugadores();
        int id = 0;
        for (int i = 0; i < cantidadDeJugadores; i++) {
            id = i + 1;
            jugadores.agregarJugador(IoPartida.generarJugador(i, cantidadDeJugadores, id, nombreJugador, mazo));
        }
        return jugadores;
    }

    private static ListaTurnos crearTurnos(ListaJugadores jugadores) {
        ListaTurnos turnos = new ListaTurnos();
        for (Jugador jugador : jugadores.getListaJugadores()) {
            Turno turno = new Turno(jugador);
            turnos.agregarTurno(turno);
        }
        return turnos;
    }

    public static void crear(int cantidadDeJugadores, String nombreJugador, ActionEvent event) throws IOException {

        ListaCartas mazoGeneral = crearMazo();
        ListaCartas mazoDescarte = new ListaCartas();

        ListaJugadores jugadores = crearJugadores(cantidadDeJugadores, nombreJugador, mazoGeneral);
        ListaTurnos turnos = crearTurnos(jugadores);

        eliminarPrimeraCarta(mazoGeneral, mazoDescarte);
        Partida partida = new Partida(jugadores, turnos, mazoGeneral, mazoDescarte, 0, true, false);
        partida.iniciar(event);
    }

    public static void generarEscena(String ruta, ActionEvent event) throws IOException {
        root = FXMLLoader.load(SceneController.class.getResource(ruta));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
        Scene scene = new Scene(root, screenSize.getWidth(), screenSize.getHeight());
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();
    }

    public static void cargar(ActionEvent event) throws IOException {
        File file = new File("archivos/partida.json");
        if (file.exists() && !file.isDirectory()) {
            Juego juego = ArchivoJson.leer(file.getPath());

            ListaCartas mazoGeneral = juego.getMazoGeneral();
            ListaCartas mazoDescarte = juego.getMazoDescarte();

            ListaJugadores jugadores = juego.getJugadores();
            ListaTurnos turnos = juego.getTurnos();

            if (PartidaUtilidades.hayGanador(jugadores)) {
                generarEscena("YaFinalizo.fxml", event);
            } else {
                Partida partida = new Partida(jugadores, turnos, mazoGeneral, mazoDescarte, 0, true, false);
                partida.iniciar(event);
            }
        } else {
            generarEscena("NoExiste.fxml", event);
        }
    }

    public static int gestorTurnos(int indiceTurno, ListaCartas mazoDescarte, ListaTurnos turnos,
            boolean sentidoRegular) {
        if (sentidoRegular) {
            indiceTurno++;
            if (indiceTurno >= turnos.size()) {
                indiceTurno = 0;
            }
        } else {
            indiceTurno--;
            if (indiceTurno < 0) {
                indiceTurno = turnos.size() - 1;
            }
        }
        return indiceTurno;
    }
}
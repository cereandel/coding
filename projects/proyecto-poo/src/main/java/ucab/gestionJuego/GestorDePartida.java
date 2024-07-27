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

/**
 * Esta clase gestiona las partidas del juego.
 */
public final class GestorDePartida {
    private static Stage stage;
    private static Parent root;

    /**
     * Crea un mazo de cartas para la partida.
     *
     * @return Un mazo de cartas.
     */
    public static ListaCartas crearMazo() {
        return GeneradorMazo.crear();
    }

    /**
     * Elimina la primera carta del mazo y la agrega al mazo de descarte.
     *
     * @param mazo         El mazo de cartas.
     * @param mazoDescarte El mazo de descarte.
     */
    public static void eliminarPrimeraCarta(ListaCartas mazo, ListaCartas mazoDescarte) {
        // Utilizar la primera carta del mazo como la primera carta del mazo de descarte
        mazoDescarte.agregarCarta(mazo.get(0));
        mazo.remove(0);
    }

    /**
     * Reparte el mazo de cartas entre los jugadores.
     *
     * @param mazo El mazo de cartas.
     * @return El mazo de cartas del jugador.
     */
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

    /**
     * Crea una lista de jugadores para la partida.
     *
     * @param cantidadDeJugadores La cantidad de jugadores en la partida.
     * @param mazo                El mazo de cartas.
     * @return Una lista de jugadores.
     */
    private static ListaJugadores crearJugadores(int cantidadDeJugadores, String nombreJugador, ListaCartas mazo) {
        ListaJugadores jugadores = new ListaJugadores();
        int id = 0;
        for (int i = 0; i < cantidadDeJugadores; i++) {
            id = i + 1;
            jugadores.agregarJugador(IoPartida.generarJugador(i, cantidadDeJugadores, id, nombreJugador, mazo));
        }
        return jugadores;
    }

    /**
     * Crea una lista de turnos para la partida.
     *
     * @param jugadores La lista de jugadores.
     * @return Una lista de turnos.
     */
    private static ListaTurnos crearTurnos(ListaJugadores jugadores) {
        ListaTurnos turnos = new ListaTurnos();
        for (Jugador jugador : jugadores.getListaJugadores()) {
            Turno turno = new Turno(jugador);
            turnos.agregarTurno(turno);
        }
        return turnos;
    }

    /**
     * Crea una nueva partida con la cantidad de jugadores especificada.
     *
     * @param cantidadDeJugadores La cantidad de jugadores en la partida.
     */
    public static void crear(int cantidadDeJugadores, String nombreJugador, ActionEvent event) throws IOException {

        ListaCartas mazoGeneral = crearMazo();
        ListaCartas mazoDescarte = new ListaCartas();

        ListaJugadores jugadores = crearJugadores(cantidadDeJugadores, nombreJugador, mazoGeneral);
        ListaTurnos turnos = crearTurnos(jugadores);

        eliminarPrimeraCarta(mazoGeneral, mazoDescarte);
        Partida partida = new Partida(jugadores, turnos, mazoGeneral, mazoDescarte, 0, true, false);
        partida.iniciar(event);
    }

    /**
     * Genera una nueva escena cargando el archivo FXML especificado y muestra la ventana correspondiente.
     *
     * @param ruta  La ruta al archivo FXML.
     * @param event El evento que desencadenó la acción (por ejemplo, un clic en un botón).
     * @throws IOException Si ocurre algún error al cargar el archivo FXML.
     */
    public static void generarEscena(String ruta, @SuppressWarnings("exports") ActionEvent event) throws IOException {
        root = FXMLLoader.load(SceneController.class.getResource(ruta));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
        Scene scene = new Scene(root, screenSize.getWidth(), screenSize.getHeight());
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Carga una partida guardada desde un archivo JSON y continúa con la partida o muestra un mensaje
     * si no existe un archivo válido.
     *
     * @param event El evento que desencadenó la acción (un clic en un botón).
     * @throws IOException Si ocurre algún error al leer el archivo JSON.
     */
    public static void cargar(@SuppressWarnings("exports") ActionEvent event) throws IOException {
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

    /**
     * Gestiona el cambio de turno en sentido regular o inverso.
     *
     * @param indiceTurno     El índice actual del turno.
     * @param mazoDescarte    El mazo de descarte.
     * @param turnos          La lista de turnos.
     * @param sentidoRegular  Indica si el sentido del juego es regular (true) o inverso (false).
     * @return El nuevo índice del turno.
     */
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
package ucab.gestionJuego;

import java.io.File;
import java.io.IOException;
import java.util.*;
import javafx.event.ActionEvent;
import ucab.SceneController;
import ucab.clasesCarta.GeneradorMazo;
import ucab.clasesCarta.ListaCartas;
import ucab.clasesUtilidad.ArchivoJson;
import ucab.clasesUtilidad.Juego;
import ucab.gestionJugador.Jugador;
import ucab.gestionJugador.ListaJugadores;

public final class GestorDePartida {

    private static ListaTurnos crearTurnos(ListaJugadores jugadores) {
        ListaTurnos turnos = new ListaTurnos();
        for (Jugador jugador : jugadores.getListaJugadores()) {
            Turno turno = new Turno(jugador);
            turnos.agregarTurno(turno);
        }
        return turnos;
    }

    public static ListaCartas repartirMazo(ListaCartas mazo) {
        ListaCartas mazoJugador = new ListaCartas();
        for (int i = 0; i <= 6; i++) {
            Random rand = new Random();
            int numran = rand.nextInt(mazo.size());
            mazoJugador.agregarCarta(mazo.get(numran));
            mazo.remove(numran);
        }
        return mazoJugador;
    }

    private static Jugador crearJugador(int i, int cantidadDeJugadores, int id, String nombreJugador,
            ListaCartas mazo) {
        Jugador jugador;
        if (i < cantidadDeJugadores - 1) {
            jugador = new Jugador(nombreJugador, "#00" + Integer.toString(id), GestorDePartida.repartirMazo(mazo));
        } else {
            jugador = new Jugador("Computadora", "#00", GestorDePartida.repartirMazo(mazo));
        }
        return jugador;
    }

    private static ListaJugadores crearJugadores(int cantidadJugadores, ArrayList<String> nombreJugadores,
            ListaCartas mazo) {
        ListaJugadores jugadores = new ListaJugadores();
        int id = 0;
        for (int i = 0; i < cantidadJugadores; i++) {
            id = i + 1;
            if (i == cantidadJugadores - 1) {
                jugadores
                        .agregarJugador(
                                crearJugador(i, cantidadJugadores, id, null, mazo));
            } else {
                jugadores
                        .agregarJugador(
                                crearJugador(i, cantidadJugadores, id, nombreJugadores.get(i), mazo));
            }

        }
        return jugadores;
    }

    public static void crear(int cantidadDeJugadores, ArrayList<String> nombreJugadores, ActionEvent event)
            throws IOException {

        ListaCartas mazoGeneral = GeneradorMazo.crear();
        ListaCartas mazoDescarte = new ListaCartas();
        ListaCartas.eliminarPrimeraCarta(mazoGeneral, mazoDescarte);

        ListaJugadores jugadores = crearJugadores(cantidadDeJugadores, nombreJugadores, mazoGeneral);
        ListaTurnos turnos = crearTurnos(jugadores);

        Partida partida = new Partida(jugadores, turnos, mazoGeneral, mazoDescarte, 0, true, true);
        partida.iniciar(event);
    }

    // meter en clase correspondiente
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

    public static void cargar(ActionEvent event) throws IOException {
        File file = new File("archivos/partida.json");
        if (file.exists() && !file.isDirectory()) {
            Juego juego = ArchivoJson.leer(file.getPath());

            ListaCartas mazoGeneral = juego.getMazoGeneral();
            ListaCartas mazoDescarte = juego.getMazoDescarte();

            ListaJugadores jugadores = juego.getJugadores();
            ListaTurnos turnos = juego.getTurnos();

            if (PartidaUtilidades.hayGanador(jugadores)) {
                SceneController.generarEscena("YaFinalizo.fxml", event);
            } else {
                Partida partida = new Partida(jugadores, turnos, mazoGeneral, mazoDescarte, 0, true, false);
                partida.iniciar(event);
            }
        } else {
            SceneController.generarEscena("NoExiste.fxml", event);
        }
    }
}
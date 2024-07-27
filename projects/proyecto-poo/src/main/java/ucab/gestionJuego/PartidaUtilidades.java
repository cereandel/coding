package ucab.gestionJuego;

import ucab.SceneController;
import ucab.clasesCarta.CartaEnumerada;
import ucab.gestionJugador.Jugador;
import ucab.gestionJugador.ListaJugadores;
import ucab.clasesCarta.Carta;
import ucab.clasesCarta.ListaCartas;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

import static ucab.gestionJuego.GestorDePartida.eliminarPrimeraCarta;

/**
 * Esta clase final proporciona métodos de utilidad para una sesión de juego.
 * Incluye métodos para validar cartas, lanzar cartas, verificar si una carta
 * existe, devolver la posición de una carta,
 * verificar cartas jugables, tomar dos o cuatro cartas, revertir la acción,
 * saltar el turno, cambiar de color,
 * obtener la primera carta elegible, anunciar "Uno", y vaciar las cartas
 * descartadas.
 */
public final class PartidaUtilidades {
    static Scanner scanner = new Scanner(System.in);

    /**
     * Valida si el color de la carta lanzada coincide con el color de la carta
     * descartada.
     */
    public static boolean validarCartaColor(String cartaLanzada, Carta cartaDeDescarte) {
        char color = cartaLanzada.charAt(0);
        return (color == cartaDeDescarte.getColor() || cartaLanzada.equals("CT4") || cartaLanzada.equals("CC"));
    }

    /**
     * Valida si el número de la carta lanzada coincide con el número de la carta
     * descartada.
     */
    public static boolean validarCartaNumero(String cartaLanzada, Carta cartaDeDescarte) {
        char numero = cartaLanzada.charAt(1);
        if (!cartaDeDescarte.getIdCarta().equals("CT2")) {
            char numero2 = cartaDeDescarte.getIdCarta().charAt(1);
            return (numero == numero2);
        } else {
            char numero2 = cartaDeDescarte.getIdCarta().charAt(2);
            return (numero == numero2);
        }
    }

    /**
     * Lanza una carta del mazo del jugador al mazo de descartes.
     */
    public static void lanzarCarta(ListaCartas mazoJugador, ListaCartas mazoDescartes, int i) {
        mazoDescartes.agregarCartaAlInicio(mazoJugador.get(i));
        mazoJugador.remove(i);
    }

    /**
     * Verifica si una carta existe en el mazo del jugador.
     */
    public static boolean cartaExiste(String cartaLanzada, ListaCartas mazoJugador) {
        for (Carta mazo : mazoJugador.getListaCartas()) {
            if (cartaLanzada.equals(mazo.getIdCarta()))
                return true;
        }
        return false;
    }

    /**
     * Devuelve la posición de una carta en el mazo del jugador.
     */
    public static int regresarCartaPosicion(String cartaLanzada, ListaCartas mazoJugador) {
        int i = 0;
        for (Carta mazo : mazoJugador.getListaCartas()) {
            if (cartaLanzada.equals(mazo.getIdCarta()))
                return i;
            i++;
        }
        return i;
    }

    /**
     * Verifica si hay cartas jugables en el mazo del jugador.
     */
    public static boolean cartasJugables(ListaCartas mazoJugador, Carta cartaDescarte) {
        for (Carta carta : mazoJugador.getListaCartas()) {
            if (validarCartaColor(carta.getIdCarta(), cartaDescarte)) {
                return true;
            }
            if (validarCartaNumero(carta.getIdCarta(), cartaDescarte)) {
                return true;
            }

        }
        return false;
    }

    /**
     * Hace que el jugador reciba dos cartas.
     */
    public static int tomarDosCartas(Jugador jugadorRecibe, ListaCartas mazo, ListaCartas mazoDescartes,
                                     int cartasAcumuladas) {
        IoPartida.imprimirTomaCartas(2);
        for (int i = 1; i <= 2 + (2 * cartasAcumuladas); i++) {
            eliminarPrimeraCarta(mazo, jugadorRecibe.getMazo());
        }
        return 0;
    }

    /**
     * Hace que el jugador reciba cuatro cartas.
     */
    public static int tomarCuatroCartas(Jugador jugadorRecibe, ListaCartas mazo, ListaCartas mazoDescartes,
            int cartasAcumuladas, ListaTurnos turnos, Turno turno) throws IOException {
        IoPartida.imprimirTomaCartas(4);
        for (int i = 1; i <= 4 + (4 * cartasAcumuladas); i++) {
            eliminarPrimeraCarta(mazo, jugadorRecibe.getMazo());
        }
        cambiarColor(turno, turnos, cartasAcumuladas, mazoDescartes);
        return 0;
    }

    /**
     * Revierte la acción.
     */
    public static boolean accionReversa(boolean sentidoRegular) {
        System.out.println("¡Se ha jugado la carta Reversa!");
        if (sentidoRegular) {
            return sentidoRegular = false;
        } else {
            return sentidoRegular = true;
        }
    }

    /**
     * Salta el turno.
     */
    public static int saltarTurno(boolean sentidoRegular, int indiceTurno, int totalJugadores) {
        if (sentidoRegular) {
            indiceTurno += 2;
            if (indiceTurno >= totalJugadores) {
                indiceTurno = indiceTurno - totalJugadores;
            }
        } else {
            indiceTurno -= 2;
            if (indiceTurno <= -1) {
                indiceTurno = indiceTurno + totalJugadores;
            }
        }
        System.out.println("Se ha jugado la carta Saltar");
        return indiceTurno;
    }

    /**
     * Cambia el color.
     */
    public static void cambiarColor(Turno turno, ListaTurnos turnos, int indiceJugadorActual,
            ListaCartas mazoDescarte) throws IOException {

        String nuevoColor = "";
        char charNuevoColor = ' ';
        if (turno.getReferenciaJugador().getId().equals("#00")) {
            Random rand = new Random();
            int randomNum = rand.nextInt(4) + 1;
            switch (randomNum) {
                case 1:
                    charNuevoColor = 'R';
                    break;
                case 2:
                    charNuevoColor = 'G';
                    break;
                case 3:
                    charNuevoColor = 'B';
                    break;
                case 4:
                    charNuevoColor = 'Y';
                    break;
            }
        } else {
            nuevoColor = IoPartida.elegirColor();
            charNuevoColor = nuevoColor.charAt(0);
        }
        System.out.println("¡Se ha jugado la carta Cambio de Color! El nuevo color es " + charNuevoColor);
        SceneController.cambioColor(String.valueOf(charNuevoColor));
        mazoDescarte.get(0).setColor(charNuevoColor);
    }

    /**
     * Obtiene la primera carta elegible.
     */
    public static String primeraCartaElegible(ListaCartas mazoJugador, Carta cartaDescarte) {
        for (Carta carta : mazoJugador.getListaCartas()) {
            if (validarCartaColor(carta.getIdCarta(), cartaDescarte)) {
                return carta.getIdCarta();
            }
            if (validarCartaNumero(carta.getIdCarta(), cartaDescarte)) {
                return carta.getIdCarta();
            }
        }
        return " ";
    }

    /**
     * Vacia las cartas descartadas.
     */
    public static void vaciarDescartes(ListaCartas mazoDescartes, ListaCartas mazoGeneral) {
        ListaCartas mazoAuxiliar = new ListaCartas();
        while (mazoDescartes.size() > 1) {
            mazoAuxiliar.agregarCarta(mazoDescartes.get(1));
            mazoDescartes.remove(1);
        }
        mazoAuxiliar.shuffle();
        while (mazoAuxiliar.size() > 0) {
            mazoGeneral.agregarCarta(mazoAuxiliar.get(0));
            mazoAuxiliar.remove(0);
        }
    }

    /**
     * Verifica si hay un ganador en la partida.
     *
     * @param jugadores Lista de jugadores.
     * @return `true` si algún jugador tiene un mazo vacío, `false` en caso contrario.
     */
    public static boolean hayGanador(ListaJugadores jugadores) {
        for (Jugador jugador : jugadores.getListaJugadores()) {
            if (jugador.getMazo().size() == 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * Calcula el puntaje de una carta.
     *
     * @param carta La carta para la que se calcula el puntaje.
     * @return El puntaje asignado a la carta.
     */
    public static int puntajeCarta(Carta carta){
        int puntos = 0 ;
        if (carta.getIdCarta().equals("CT4") || carta.getIdCarta().equals("CC")){
            puntos = 50;
        } else {
            if (carta instanceof CartaEnumerada) {
                CartaEnumerada cartaNumero = (CartaEnumerada)carta;
                puntos = cartaNumero.getNumero();
            } else {
                puntos = 20;
            }
        }
        return puntos;
    }

    /**
     * Calcula el puntaje total de un mazo de cartas.
     *
     * @param mazo El mazo de cartas.
     * @return El puntaje total del mazo.
     */
    public static int puntaje(ListaCartas mazo){
        int puntos = 0;
        for (Carta carta: mazo.getListaCartas()){
            puntos += puntajeCarta(carta);
        }
        return puntos;
    }

    /**
     * Obtiene la ruta de la imagen asociada a un nombre de carta.
     *
     * @param nombre El nombre de la carta.
     * @return La ruta de la imagen correspondiente.
     */
    public static String obtenerRuta(String nombre) {
        return "images/cartas/" + nombre + ".jpg";
    }
}
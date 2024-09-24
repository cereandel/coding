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

public final class PartidaUtilidades {
    static Scanner scanner = new Scanner(System.in);

    public static boolean validarCartaColor(String cartaLanzada, Carta cartaDeDescarte) {
        char color = cartaLanzada.charAt(0);
        return (color == cartaDeDescarte.getColor() || cartaLanzada.equals("CT4") || cartaLanzada.equals("CC"));
    }

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

    public static void lanzarCarta(ListaCartas mazoJugador, ListaCartas mazoDescartes, int i) {
        mazoDescartes.agregarCartaAlInicio(mazoJugador.get(i));
        mazoJugador.remove(i);
    }

    public static int regresarCartaPosicion(String cartaLanzada, ListaCartas mazoJugador) {
        int i = 0;
        for (Carta mazo : mazoJugador.getListaCartas()) {
            if (cartaLanzada.equals(mazo.getIdCarta()))
                return i;
            i++;
        }
        return i;
    }

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

    public static int tomarDosCartas(Jugador jugadorRecibe, ListaCartas mazo, ListaCartas mazoDescartes,
            int cartasAcumuladas) {
        for (int i = 1; i <= 2 + (2 * cartasAcumuladas); i++) {
            ListaCartas.eliminarPrimeraCarta(mazo, jugadorRecibe.getMazo());
        }
        return 0;
    }

    public static int tomarCuatroCartas(Jugador jugadorRecibe, ListaCartas mazo, ListaCartas mazoDescartes,
            int cartasAcumuladas, ListaTurnos turnos, Turno turno) throws IOException {
        for (int i = 1; i <= 4 + (4 * cartasAcumuladas); i++) {
            ListaCartas.eliminarPrimeraCarta(mazo, jugadorRecibe.getMazo());
        }
        cambiarColor(turno, turnos, cartasAcumuladas, mazoDescartes);
        return 0;
    }

    public static boolean accionReversa(boolean sentidoRegular) {
        if (sentidoRegular) {
            return sentidoRegular = false;
        } else {
            return sentidoRegular = true;
        }
    }

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
        return indiceTurno;
    }

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
        SceneController.cambioColor(String.valueOf(charNuevoColor));
        mazoDescarte.get(0).setColor(charNuevoColor);
    }

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

    public static boolean hayGanador(ListaJugadores jugadores) {
        for (Jugador jugador : jugadores.getListaJugadores()) {
            if (jugador.getMazo().size() == 0) {
                return true;
            }
        }
        return false;
    }

    public static int puntajeCarta(Carta carta) {
        int puntos = 0;
        if (carta.getIdCarta().equals("CT4") || carta.getIdCarta().equals("CC")) {
            puntos = 50;
        } else {
            if (carta instanceof CartaEnumerada) {
                CartaEnumerada cartaNumero = (CartaEnumerada) carta;
                puntos = cartaNumero.getNumero();
            } else {
                puntos = 20;
            }
        }
        return puntos;
    }

    public static int puntaje(ListaCartas mazo) {
        int puntos = 0;
        for (Carta carta : mazo.getListaCartas()) {
            puntos += puntajeCarta(carta);
        }
        return puntos;
    }

    public static String obtenerRuta(String nombre) {
        return "images/cartas/" + nombre + ".jpg";
    }
}
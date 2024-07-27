package ucab.gestionJuego;


import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.scene.control.ChoiceDialog;
import ucab.SceneController;
import ucab.gestionJugador.Jugador;
import ucab.clasesCarta.ListaCartas;
import ucab.clasesUtilidad.Consola;

public final class IoPartida {
    public static Scanner scanner = new Scanner(System.in);

    /**
     * Imprime el progreso de la partida, mostrando información relevante como el turno actual,
     * la carta en el descarte y el estado del mazo del jugador y la máquina (si aplica).
     *
     * @param mazoDescarte El mazo de descarte.
     * @param maquina      El jugador de la máquina.
     * @param turno        El turno actual.
     */
    public static void imprimirProgresoPartida(ListaCartas mazoDescarte,
                                               Jugador maquina, Turno turno) {
        System.out.println("--------------------------");
        System.out.println("Turno de " + turno.getReferenciaJugador().getNombre());

        System.out.print("Descarte: ");
        System.out.println(mazoDescarte.get(0));

        if (turno.getReferenciaJugador().getId() != "#00") {
            System.out.print("mazo: ");
            System.out.println(turno.getReferenciaJugador().getMazo());
            System.out.println("cartas oponente: " + maquina.getMazo().size());
        }
    }

    /**
     * Imprime el encabezado de la partida, mostrando la carta en el descarte.
     *
     * @param mazoDescarte El mazo de descarte.
     */
    public static void imprimirEncabezadoPartida(ListaCartas mazoDescarte) {
        System.out.println("\tPARTIDA\n");
        System.out.print("Descarte: ");
        System.out.println(mazoDescarte.get(0));
    }

    /**
     * Imprime un mensaje indicando que el jugador no tiene cartas para jugar y ha tomado una del montón.
     *
     * @param turno El turno actual.
     */
    public static void imprimirSinCartas(Turno turno) {
        System.out.println(turno.getReferenciaJugador().getNombre()
                + " no tiene cartas para jugar, agarró una del montón.");
    }

    /**
     * Imprime el movimiento realizado por la máquina.
     *
     * @param input El movimiento de la máquina.
     */
    public static void imprimirJuegoMaquina(String input) {
        System.out.println("Computadora jugó: " + input);
    }

    /**
     * Selecciona una carta válida para la máquina y la imprime como su movimiento.
     *
     * @param input        El movimiento inicial (puede ser ignorado).
     * @param turno        El turno actual.
     * @param mazoDescarte El mazo de descarte.
     * @return La carta seleccionada por la máquina.
     */
    public static String seleccionarCartaMaquina(String input, Turno turno, ListaCartas mazoDescarte) {
        input = PartidaUtilidades.primeraCartaElegible(turno.getReferenciaJugador().getMazo(), mazoDescarte.get(0));
        imprimirJuegoMaquina(input);
        return input;
    }

    /**
     * Imprime un mensaje indicando que la carta jugada es inválida.
     */
    public static void imprimirCartaInvalida() {
        SceneController.cartaInvalida();
    }

    /**
     * Imprime un mensaje al final de un turno indicando que el jugador ha jugado.
     *
     * @param turno El turno actual.
     */
    public static void imprimirFinalVuelta(Turno turno) {
        System.out.println(turno.getReferenciaJugador().getNombre() + " ha jugado.");
        System.out.println("--------------------------");
        Consola.delay(3000);
    }

    /**
     * Imprime un mensaje indicando al ganador de la partida.
     *
     * @param turno El turno del jugador ganador.
     */
    public static void imprimirGanador(Turno turno) {
        Consola.clrscr();
        System.out.println(turno.getReferenciaJugador().getNombre() + " " + turno.getReferenciaJugador().getId() + " ha ganado.");
        Consola.delay(5000);
    }

    /**
     * Genera un objeto de tipo Jugador con el nombre, ID y mazo especificados.
     *
     * @param i                 Indice del jugador en la lista.
     * @param cantidadDeJugadores Cantidad total de jugadores.
     * @param id                ID numérico del jugador.
     * @param nombreJugador     Nombre del jugador.
     * @param mazo              Mazo de cartas del jugador.
     * @return El objeto Jugador generado.
     */
    public static Jugador generarJugador(int i, int cantidadDeJugadores, int id, String nombreJugador, ListaCartas mazo) {
        Jugador jugador;
        if (i < cantidadDeJugadores - 1) {
            jugador = new Jugador(nombreJugador, "#00" + Integer.toString(id), GestorDePartida.repartirMazo(mazo));
        } else {
            jugador = new Jugador("Computadora", "#00", GestorDePartida.repartirMazo(mazo));
        }
        return jugador;
    }

    /**
     * Imprime un mensaje indicando que el siguiente jugador debe tomar una cantidad específica de cartas.
     *
     * @param cantidad La cantidad de cartas a tomar.
     */
    public static void imprimirTomaCartas(int cantidad) {
        System.out.println("el siguiente jugador, toma " + cantidad + " cartas");
    }

    /**
     * Muestra un cuadro de diálogo para que el usuario elija un color y devuelve la opción seleccionada.
     *
     * @return El color elegido por el usuario (o "Red" por defecto).
     * @throws IOException Si ocurre algún error de entrada/salida.
     */
    public static String elegirColor() throws IOException {
        ChoiceDialog<String> dialog = new ChoiceDialog<>("Red", "Blue", "Green", "Yellow");
        dialog.setTitle("Elegir color");
        dialog.setHeaderText("Elija un color");
        dialog.setContentText("Color:");
        Optional<String> result = dialog.showAndWait();
        return result.orElse("Red");
    }

    /**
     * Anuncia "Uno".
     */
    public static void cantarUno(ListaCartas mazoJugador, ListaCartas mazoGeneral, int jugador) {
        String uno = "";
        if (jugador == 1) System.out.println("¡¡¡¡¡¡UNO!!!!!!!!");
        else {
            System.out.println("Te queda una sola carta");
            uno = scanner.nextLine().toUpperCase();
            if (uno.equals("UNO")) {
                System.out.println("¡¡¡¡¡¡¡¡¡¡UNO!!!!!!!");
            } else {
                System.out.println("No has cantado uno, toma una carta");
                mazoJugador.agregarCarta(mazoGeneral.get(0));
                mazoGeneral.remove(0);
            }
        }
    }
}
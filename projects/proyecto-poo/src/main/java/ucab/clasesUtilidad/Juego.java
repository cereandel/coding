package ucab.clasesUtilidad;

import ucab.gestionJuego.ListaTurnos;
import ucab.gestionJugador.ListaJugadores;
import ucab.clasesCarta.ListaCartas;

public class Juego {
    private ListaJugadores jugadores;
    private ListaTurnos turnos;
    private ListaCartas mazoGeneral;
    private ListaCartas mazoDescarte;
    private int indiceTurno;
    private boolean sentidoRegular;

    /**
     * Constructor vacío para la clase Juego.
     */
    public Juego() {
    }

    /**
     * Constructor completo para la clase Juego.
     *
     * @param jugadores      La lista de jugadores.
     * @param turnos         La lista de turnos.
     * @param mazoGeneral    El mazo general de cartas.
     * @param mazoDescarte   El mazo de descarte.
     * @param indiceTurno    El índice del turno actual.
     * @param sentidoRegular Indica si el juego sigue un sentido regular (true) o inverso (false).
     */
    public Juego(ListaJugadores jugadores, ListaTurnos turnos, ListaCartas mazoGeneral, ListaCartas mazoDescarte,
            int indiceTurno, boolean sentidoRegular) {
        this.jugadores = jugadores;
        this.turnos = turnos;
        this.mazoGeneral = mazoGeneral;
        this.mazoDescarte = mazoDescarte;
        this.indiceTurno = indiceTurno;
        this.sentidoRegular = sentidoRegular;
    }

    public ListaJugadores getJugadores() {
        return jugadores;
    }

    public void setJugadores(ListaJugadores jugadores) {
        this.jugadores = jugadores;
    }

    public ListaTurnos getTurnos() {
        return turnos;
    }

    public void setTurnos(ListaTurnos turnos) {
        this.turnos = turnos;
    }

    public ListaCartas getMazoGeneral() {
        return mazoGeneral;
    }

    public void setMazoGeneral(ListaCartas mazoGeneral) {
        this.mazoGeneral = mazoGeneral;
    }

    public ListaCartas getMazoDescarte() {
        return mazoDescarte;
    }

    public void setMazoDescarte(ListaCartas mazoDescarte) {
        this.mazoDescarte = mazoDescarte;
    }

    public int getIndiceTurno() {
        return indiceTurno;
    }

    public void setIndiceTurno(int indiceTurno) {
        this.indiceTurno = indiceTurno;
    }

    public boolean isSentidoRegular() {
        return sentidoRegular;
    }

    public void setSentidoRegular(boolean sentidoRegular) {
        this.sentidoRegular = sentidoRegular;
    }

    @Override
    public String toString() {
        return "Juego [jugadores=" + jugadores + ", turnos=" + turnos + ", mazoGeneral=" + mazoGeneral
                + ", mazoDescarte=" + mazoDescarte + ", indiceTurno=" + indiceTurno + ", sentidoRegular="
                + sentidoRegular + "]";
    }
}
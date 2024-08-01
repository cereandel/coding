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

    public Juego() {
    }

    public Juego(ListaJugadores jugadores, @SuppressWarnings("exports") ListaTurnos turnos, ListaCartas mazoGeneral,
            ListaCartas mazoDescarte,
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

    @SuppressWarnings("exports")
    public ListaTurnos getTurnos() {
        return turnos;
    }

    public void setTurnos(@SuppressWarnings("exports") ListaTurnos turnos) {
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
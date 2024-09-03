package com.cereandel;

import java.util.ArrayList;
import java.util.Arrays;

public class Juego {

    private int jugadorActual;
    private String[] idJugadores;

    private Mazo mazo;
    private ArrayList<Carta> cartasDescarte;
    private ArrayList<ArrayList<Carta>> mazoJugadores;

    private Carta.Color colorActual;
    private Carta.Valor valorActual;

    boolean sentidoHorario;

    public Juego(String[] idJugadores) {
        this.idJugadores = idJugadores;
        this.jugadorActual = 0;

        mazo = new Mazo();
        cartasDescarte = new ArrayList<Carta>();
        mazoJugadores = new ArrayList<ArrayList<Carta>>();

        sentidoHorario = true;

        for (int i = 0; i < idJugadores.length; i++) {
            ArrayList<Carta> mazoJugador = new ArrayList<Carta>(Arrays.asList(mazo.agarrarCartas(7)));
            mazoJugadores.add(mazoJugador);
        }
    }

    private static int saltarTurno(int jugadorActual, int cantidadJugadores, boolean sentidoHorario) {
        if (sentidoHorario) {
            jugadorActual += 2;
            if (jugadorActual >= cantidadJugadores)
                jugadorActual = jugadorActual - cantidadJugadores;
        } else {
            jugadorActual -= 2;
            if (jugadorActual <= -1)
                jugadorActual = jugadorActual + cantidadJugadores;
        }
        return jugadorActual;
    }

    public void empezarJuego(Juego juego) {
        Carta cartaInicial = mazo.agarrarCarta();
        colorActual = cartaInicial.getColor();
        valorActual = cartaInicial.getValor();

        if (valorActual == Carta.Valor.CAMBIA_COLOR || valorActual == Carta.Valor.TOMA_CUATRO) {
            empezarJuego(juego);
        }

        if (valorActual == Carta.Valor.SALTO) {
            jugadorActual = saltarTurno(jugadorActual, idJugadores.length, sentidoHorario);
        }

        if (valorActual == Carta.Valor.REVERSA) {
            sentidoHorario = !sentidoHorario;
            jugadorActual = saltarTurno(jugadorActual, idJugadores.length, sentidoHorario);
        }

        cartasDescarte.add(cartaInicial);
    }

    public boolean isJuegoAcabado() {
        for (ArrayList<Carta> mazoJugador : mazoJugadores) {
            if (mazoJugador.size() == 0) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Carta> getMazoJugador(String idJugador) {
        for (int i = 0; i < idJugadores.length; i++) {
            if (idJugadores[i].equals(idJugador)) {
                return mazoJugadores.get(i);
            }
        }
        return null;
    }

    public int getCantidadCartasJugador(String idJugador) {
        return (getMazoJugador(idJugador)).size();
    }

    public Carta getCartaJugador(String idJugador, int index) {
        ArrayList<Carta> mazoJugador = getMazoJugador(idJugador);
        return mazoJugador.get(index);
    }

    public boolean isVacioMazoJugador(String idJugador) {
        return getMazoJugador(idJugador).isEmpty();
    }

    public boolean isCartaValida(Carta carta) {
        return carta.getColor() == colorActual || carta.getValor() == valorActual;
    }

    public int avanzarTurno() {
        if (sentidoHorario) {
            jugadorActual++;
            if (jugadorActual >= idJugadores.length)
                jugadorActual = 0;
        } else {
            jugadorActual--;
            if (jugadorActual <= -1)
                jugadorActual = idJugadores.length - 1;
        }
        return jugadorActual;
    }

    public void agarrarCarta(String idjugador) {
        if (mazo.isEmpty()) {
            mazo.reemplazarMazo(cartasDescarte);
            mazo.barajear();
        }

        getMazoJugador(idjugador).add(mazo.agarrarCarta());
        jugadorActual = avanzarTurno();
    }

    public void setColorActual(@SuppressWarnings("exports") Carta.Color color) {
        this.colorActual = color;
    }

    public ArrayList<Carta> getMazoJugador(int i) {
        return mazoJugadores.get(i);
    }

}
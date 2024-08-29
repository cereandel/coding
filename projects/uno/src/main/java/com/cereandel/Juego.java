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

        System.out.println("cantidad de cartas en mazo: " + mazo.cantidadCartas());
        System.out.println("cartas de jugador 1: " + mazoJugadores.get(0));
        System.out.println("cartas de jugador 2: " + mazoJugadores.get(1));
    }

}
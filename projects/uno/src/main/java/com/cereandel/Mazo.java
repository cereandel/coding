package com.cereandel;

import java.util.ArrayList;
import java.util.Collections;

public class Mazo {

    private ArrayList<Carta> cartas;

    public Mazo() {
        cartas = new ArrayList<Carta>();
        inicializar();
        barajear();
    }

    public void inicializar() {

        Carta.Color[] colores = Carta.Color.values();

        for (int i = 0; i < colores.length - 1; i++) {
            Carta.Color color = colores[i];
            cartas.add(new Carta(color, Carta.Valor.getValor(0)));

            for (int j = 1; j < 10; j++) {
                cartas.add(new Carta(color, Carta.Valor.getValor(j)));
                cartas.add(new Carta(color, Carta.Valor.getValor(j)));
            }

            Carta.Valor[] valores = new Carta.Valor[] { Carta.Valor.TOMA_2, Carta.Valor.SALTO, Carta.Valor.REVERSA };
            for (Carta.Valor valor : valores) {
                cartas.add(new Carta(color, valor));
                cartas.add(new Carta(color, valor));
            }
        }

        Carta.Valor[] valores = new Carta.Valor[] { Carta.Valor.CAMBIA_COLOR, Carta.Valor.TOMA_4 };
        for (Carta.Valor valor : valores) {
            for (int i = 0; i < 4; i++) {
                cartas.add(new Carta(Carta.Color.COMODIN, valor));
            }
        }

    }

    public void barajear() {
        Collections.shuffle(cartas);
    }

    public Carta[] agarrarCartas(int cantidad) {
        Carta[] cartas = new Carta[cantidad];
        for (int i = 0; i < cantidad; i++) {
            cartas[i] = this.cartas.remove(0);
        }
        return cartas;
    }

    public int cantidadCartas() {
        return cartas.size();
    }

}
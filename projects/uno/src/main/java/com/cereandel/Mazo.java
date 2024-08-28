package com.cereandel;

import java.util.ArrayList;
import java.util.Collections;

public class Mazo {

    private ArrayList<Carta> cartas;

    public Mazo() {
        cartas = new ArrayList<Carta>();
        inicializar();
        Collections.shuffle(cartas);
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

            Carta.Valor[] valores = new Carta.Valor[] { Carta.Valor.MAS2, Carta.Valor.SALTO, Carta.Valor.REVERSA };
            for (Carta.Valor valor : valores) {
                cartas.add(new Carta(color, valor));
                cartas.add(new Carta(color, valor));
            }
        }

        Carta.Valor[] valores = new Carta.Valor[] { Carta.Valor.CAMBIA_COLOR, Carta.Valor.COMODIN4 };
        for (Carta.Valor valor : valores) {
            for (int i = 0; i < 4; i++) {
                cartas.add(new Carta(Carta.Color.COMODIN, valor));
            }
        }

    }

}
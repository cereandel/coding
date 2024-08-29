package com.cereandel;

public class Carta {

    enum Color {
        ROJO, AZUL, VERDE, AMARILLO, COMODIN;

        private static final Color[] colores = Color.values();

        public static Color getColor(int i) {
            return colores[i];
        }
    }

    enum Valor {
        CERO, UNO, DOS, TRES, CUATRO, CINCO, SEIS, SIETE, OCHO, NUEVE, TOMA_2, REVERSA, SALTO, CAMBIA_COLOR, TOMA_4;

        private static final Valor[] valores = Valor.values();

        public static Valor getValor(int i) {
            return valores[i];
        }
    }

    private final Color color;
    private final Valor valor;

    public Carta(@SuppressWarnings("exports") Color color, @SuppressWarnings("exports") Valor valor) {
        this.color = color;
        this.valor = valor;
    }

    @SuppressWarnings("exports")
    public Color getColor() {
        return color;
    }

    @SuppressWarnings("exports")
    public Valor getValor() {
        return valor;
    }

    public String toString() {
        return color + "_" + valor;
    }
}
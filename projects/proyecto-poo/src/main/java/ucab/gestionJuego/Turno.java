/**
 * La clase Turno representa un turno en un juego.
 * Cada turno está asociado con un jugador.
 */
package ucab.gestionJuego;

import ucab.gestionJugador.Jugador;

public class Turno {
    private Jugador referenciaJugador;

    public Turno() {

    }

    public Turno(Jugador referenciaJugador) {
        this.referenciaJugador = referenciaJugador;
    }

    public Jugador getReferenciaJugador() {
        return referenciaJugador;
    }

    public void setReferenciaJugador(Jugador referenciaJugador) {
        this.referenciaJugador = referenciaJugador;
    }

    @Override
    public String toString() {
        return "[Jugador = " + referenciaJugador.getNombre() + " " + referenciaJugador.getId() + "]";
    }
}
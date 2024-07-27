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

    /**
     * Constructor de la clase Turno.
     * 
     * @param referenciaJugador El jugador asociado con este turno.
     */
    public Turno(Jugador referenciaJugador) {
        this.referenciaJugador = referenciaJugador;
    }

    /**
     * Método para obtener el jugador asociado con este turno.
     * 
     * @return El jugador asociado con este turno.
     */
    public Jugador getReferenciaJugador() {
        return referenciaJugador;
    }

    /**
     * Método para establecer el jugador asociado con este turno.
     * 
     * @param referenciaJugador El jugador a asociar con este turno.
     */
    public void setReferenciaJugador(Jugador referenciaJugador) {
        this.referenciaJugador = referenciaJugador;
    }

    /**
     * Método para obtener una representación en cadena de este turno.
     * 
     * @return Una cadena que representa este turno.
     */
    @Override
    public String toString() {
        return "[Jugador = " + referenciaJugador.getNombre() + " " + referenciaJugador.getId() + "]";
    }
}
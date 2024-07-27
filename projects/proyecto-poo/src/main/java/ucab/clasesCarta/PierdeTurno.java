package ucab.clasesCarta;

/**
 * La clase PierdeTurno representa una carta especial que provoca que el
 * siguiente jugador
 * pierda su turno en el juego.
 * Extiende la clase Comodin y proporciona una acción específica.
 * Aunque la acción está por programar, se puede implementar lógica para saltar
 * el turno
 * del siguiente jugador.
 *
 *
 * @version 1.0
 */
public class PierdeTurno extends Comodin {
    public PierdeTurno() {
        super();
    }

    /**
     * Crea una nueva carta PierdeTurno con el ID especificado y el color deseado.
     *
     * @param idCarta El ID de la carta.
     * @param color   El color deseado ('B' para azul, 'Y' para amarillo, 'R' para
     *                rojo, 'G' para verde).
     */
    public PierdeTurno(String idCarta, char color) {
        super(idCarta, color);
    }

    /**
     * Realiza la acción asociada a la carta PierdeTurno.
     * En este caso, la acción está por programar (puede ser saltar el turno del
     * siguiente jugador).
     */
    @Override
    public void accion() {
        // Acción por programar: saltar el turno del siguiente jugador
    }

    /**
     * Devuelve una representación en cadena del ID de la carta.
     *
     * @return El ID de la carta como cadena.
     */
    @Override
    public String toString() {
        return getIdCarta();
    }
}

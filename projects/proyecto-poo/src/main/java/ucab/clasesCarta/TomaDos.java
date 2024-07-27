package ucab.clasesCarta;

/**
 * La clase TomaDos representa una carta especial que obliga al siguiente
 * jugador
 * a tomar dos cartas del mazo en el juego.
 * Extiende la clase Comodin y proporciona una acción específica.
 * Aunque la acción está por programar, se puede implementar lógica para hacer
 * que
 * el siguiente jugador tome las cartas correspondientes.
 *
 *
 * @version 1.0
 */
public class TomaDos extends Comodin {
    public TomaDos() {
        super();
    }

    /**
     * Crea una nueva carta TomaDos con el ID especificado y el color deseado.
     *
     * @param idCarta El ID de la carta.
     * @param color   El color deseado ('B' para azul, 'Y' para amarillo, 'R' para
     *                rojo, 'G' para verde).
     */
    public TomaDos(String idCarta, char color) {
        super(idCarta, color);
    }

    /**
     * Realiza la acción asociada a la carta TomaDos.
     * En este caso, la acción está por programar (puede ser hacer que el siguiente
     * jugador tome dos cartas).
     */
    @Override
    public void accion() {
        // Acción por programar: hacer que el siguiente jugador tome dos cartas
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

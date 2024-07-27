package ucab.clasesCarta;

/**
 * La clase TomaCuatro representa una carta especial que obliga al siguiente
 * jugador
 * a tomar cuatro cartas del mazo en el juego.
 * Extiende la clase Comodin y proporciona una acción específica.
 * Aunque la acción está por programar, se puede implementar lógica para hacer
 * que
 * el siguiente jugador tome las cartas correspondientes.
 *
 *
 * @version 1.0
 */
public class TomaCuatro extends Comodin {
    public TomaCuatro() {
        super();
    }

    /**
     * Crea una nueva carta TomaCuatro con el ID especificado y el color deseado.
     *
     * @param idCarta El ID de la carta.
     * @param color   El color deseado ('B' para azul, 'Y' para amarillo, 'R' para
     *                rojo, 'G' para verde).
     */
    public TomaCuatro(String idCarta, char color) {
        super(idCarta, color);
    }

    /**
     * Realiza la acción asociada a la carta TomaCuatro.
     * En este caso, la acción está por programar (puede ser hacer que el siguiente
     * jugador tome cuatro cartas).
     */
    @Override
    public void accion() {
        // Acción por programar: hacer que el siguiente jugador tome cuatro cartas
    }

    /**
     * Devuelve una representación en cadena del ID de la carta.
     *
     * @return El ID de la carta como cadena.
     */
    @Override
    public String toString() {
        return "CT4";
    }
}

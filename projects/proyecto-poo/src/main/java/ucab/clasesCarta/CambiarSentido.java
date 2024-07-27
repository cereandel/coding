package ucab.clasesCarta;

/**
 * La clase CambiarSentido representa una carta especial que permite cambiar el
 * sentido
 * del juego (por ejemplo, de izquierda a derecha o viceversa).
 * Extiende la clase Comodin y proporciona una acción específica.
 *
 *
 * @version 1.0
 */
public class CambiarSentido extends Comodin {
    public CambiarSentido() {
        super();
    }

    /**
     * Crea una nueva carta CambiarSentido con el ID especificado y el color
     * deseado.
     *
     * @param idCarta El ID de la carta.
     * @param color   El color deseado ('B' para azul, 'Y' para amarillo, 'R' para
     *                rojo, 'G' para verde).
     */
    public CambiarSentido(String idCarta, char color) {
        super(idCarta, color);
    }

    /**
     * Realiza la acción asociada a la carta CambiarSentido.
     * En este caso, no se realiza ninguna acción específica.
     */
    @Override
    public void accion() {
        // No se realiza ninguna acción específica para CambiarSentido
    }

    /**
     * Devuelve una representación en cadena de la carta CambiarSentido.
     *
     * @return El ID de la carta como cadena.
     */
    @Override
    public String toString() {
        return getIdCarta();
    }
}

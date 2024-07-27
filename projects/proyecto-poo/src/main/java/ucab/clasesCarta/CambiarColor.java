package ucab.clasesCarta;

/**
 * La clase CambiarColor representa una carta especial que permite cambiar el
 * color
 * de la carta actual en el juego.
 * Extiende la clase Comodin y proporciona una acción específica.
 *
 *
 * @version 1.0
 */
public class CambiarColor extends Comodin {
    public CambiarColor() {
        super();
    }

    /**
     * Crea una nueva carta CambiarColor con el ID especificado y el color deseado.
     *
     * @param idCarta El ID de la carta.
     * @param color   El color deseado ('B' para azul, 'Y' para amarillo, 'R' para
     *                rojo, 'G' para verde).
     */
    public CambiarColor(String idCarta, char color) {
        super(idCarta, color);
    }

    /**
     * Realiza la acción asociada a la carta CambiarColor.
     * En este caso, no se realiza ninguna acción específica.
     */
    @Override
    public void accion() {
        // No se realiza ninguna acción específica para CambiarColor
    }

    /**
     * Devuelve una representación en cadena de la carta CambiarColor.
     *
     * @return La cadena "CC" para representar la carta CambiarColor.
     */
    @Override
    public String toString() {
        return "CC";
    }
}

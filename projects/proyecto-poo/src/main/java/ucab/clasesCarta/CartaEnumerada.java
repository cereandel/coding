package ucab.clasesCarta;

/**
 * La clase CartaEnumerada representa una carta con un número específico.
 * Extiende la clase Carta y proporciona un campo adicional para el número.
 *
 *
 * @version 1.0
 */
public class CartaEnumerada extends Carta {
    private int numero;

    public CartaEnumerada() {
        super();
    }

    /**
     * Crea una nueva carta enumerada con el ID, color y número especificados.
     *
     * @param idCarta El ID de la carta.
     * @param color   El color de la carta.
     * @param numero  El número asociado a la carta.
     */

    public CartaEnumerada(String idCarta, char color, int numero) {
        super(idCarta, color);
        this.numero = numero;
    }

    /**
     * Obtiene el número asociado a la carta.
     *
     * @return El número de la carta.
     */
    public int getNumero() {
        return numero;
    }

    /**
     * Establece el número asociado a la carta.
     *
     * @param numero El nuevo número de la carta.
     */
    public void setNumero(int numero) {
        this.numero = numero;
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

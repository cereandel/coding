package ucab.clasesCarta;

/**
 * La clase abstracta Comodin representa una carta especial en un juego de
 * cartas.
 * Extiende la clase Carta y proporciona un método abstracto para la acción
 * específica
 * que se debe realizar cuando se juega esta carta.
 * Las clases concretas que heredan de Comodin deben implementar su propio
 * método accion().
 *
 *
 * @version 1.0
 */
public abstract class Comodin extends Carta {
    public Comodin() {
        super();
    }

    /**
     * Crea una nueva carta comodín con el ID y el color especificados.
     *
     * @param idCarta El ID de la carta.
     * @param color   El color de la carta.
     */
    public Comodin(String idCarta, char color) {
        super(idCarta, color);
    }

    /**
     * Realiza la acción específica asociada a esta carta comodín.
     * Las clases concretas deben implementar este método.
     */
    public abstract void accion();
}
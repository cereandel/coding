package ucab.clasesCarta;

/**
 * La clase GeneradorMazo se encarga de crear un mazo de cartas para un juego
 * específico.
 * Contiene métodos para crear cartas de colores, comodines y generar el mazo
 * completo.
 * Las cartas se agregan a una lista de cartas (ListaCartas).
 *
 *
 * @version 1.0
 */
public final class GeneradorMazo {
    /**
     * Crea cartas enumeradas de un color específico y las agrega al mazo.
     *
     * @param color El color de las cartas (por ejemplo, 'B' para azul).
     * @param mazo  La lista de cartas donde se agregarán las cartas creadas.
     */
    public static void crearCartaColor(char color, ListaCartas mazo) {
        for (int i = 0; i < 10; i++) {
            String colorString = Character.toString(color);
            mazo.agregarCarta(new CartaEnumerada(colorString + i, color, i));
            if (i > 0)
                mazo.agregarCarta(new CartaEnumerada(colorString + i, color, i));
        }
        crearComodinesColor(color, mazo);
    }

    /**
     * Crea comodines estándar (TomaCuatro y CambiarColor) y los agrega al mazo.
     *
     * @param mazo La lista de cartas donde se agregarán los comodines.
     */
    public static void crearComodines(ListaCartas mazo) {
        for (int i = 0; i < 4; i++) {
            mazo.agregarCarta(new TomaCuatro("CT4", 'N'));
            mazo.agregarCarta(new CambiarColor("CC", 'N'));
        }
    }

    /**
     * Crea comodines de un color específico (TomaDos, PierdeTurno y CambiarSentido)
     * y los agrega al mazo.
     *
     * @param color El color de los comodines (por ejemplo, 'B' para azul).
     * @param mazo  La lista de cartas donde se agregarán los comodines de color.
     */
    public static void crearComodinesColor(char color, ListaCartas mazo) {
        for (int i = 0; i < 2; i++) {
            String colorString = Character.toString(color);
            mazo.agregarCarta(new TomaDos((colorString + "T2"), color));
            mazo.agregarCarta(new PierdeTurno((colorString + "S"), color));
            mazo.agregarCarta(new CambiarSentido((colorString + "R"), color));
        }
    }

    /**
     * Crea el mazo completo de cartas (colores y comodines) y lo mezcla.
     *
     * @return El mazo completo de cartas.
     */
    public static ListaCartas crear() {
        ListaCartas mazo = new ListaCartas();

        crearCartaColor('B', mazo);
        crearCartaColor('Y', mazo);
        crearCartaColor('R', mazo);
        crearCartaColor('G', mazo);
        crearComodines(mazo);
        mazo.shuffle();
        return mazo;
    }
}

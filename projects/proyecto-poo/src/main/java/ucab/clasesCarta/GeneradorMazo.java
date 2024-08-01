package ucab.clasesCarta;

public final class GeneradorMazo {

    public static void crearCartaColor(char color, ListaCartas mazo) {
        for (int i = 0; i < 10; i++) {
            String colorString = Character.toString(color);
            mazo.agregarCarta(new CartaEnumerada(colorString + i, color, i));
            if (i > 0)
                mazo.agregarCarta(new CartaEnumerada(colorString + i, color, i));
        }
        crearComodinesColor(color, mazo);
    }

    public static void crearComodines(ListaCartas mazo) {
        for (int i = 0; i < 4; i++) {
            mazo.agregarCarta(new TomaCuatro("CT4", 'N'));
            mazo.agregarCarta(new CambiarColor("CC", 'N'));
        }
    }

    public static void crearComodinesColor(char color, ListaCartas mazo) {
        for (int i = 0; i < 2; i++) {
            String colorString = Character.toString(color);
            mazo.agregarCarta(new TomaDos((colorString + "T2"), color));
            mazo.agregarCarta(new PierdeTurno((colorString + "S"), color));
            mazo.agregarCarta(new CambiarSentido((colorString + "R"), color));
        }
    }

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

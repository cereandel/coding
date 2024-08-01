package ucab.clasesCarta;

public abstract class Comodin extends Carta {
    public Comodin() {
        super();
    }

    public Comodin(String idCarta, char color) {
        super(idCarta, color);
    }

    public abstract void accion();
}
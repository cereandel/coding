package ucab.clasesCarta;

public class CambiarColor extends Comodin {
    public CambiarColor() {
        super();
    }

    public CambiarColor(String idCarta, char color) {
        super(idCarta, color);
    }

    @Override
    public void accion() {
    }

    @Override
    public String toString() {
        return "CC";
    }
}

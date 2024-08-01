package ucab.clasesCarta;

public class CambiarSentido extends Comodin {
    public CambiarSentido() {
        super();
    }

    public CambiarSentido(String idCarta, char color) {
        super(idCarta, color);
    }

    @Override
    public void accion() {
    }

    @Override
    public String toString() {
        return getIdCarta();
    }
}

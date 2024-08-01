package ucab.clasesCarta;

public class TomaDos extends Comodin {
    public TomaDos() {
        super();
    }

    public TomaDos(String idCarta, char color) {
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

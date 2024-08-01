package ucab.clasesCarta;

public class PierdeTurno extends Comodin {
    public PierdeTurno() {
        super();
    }

    public PierdeTurno(String idCarta, char color) {
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

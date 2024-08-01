package ucab.clasesCarta;

public class CartaEnumerada extends Carta {
    private int numero;

    public CartaEnumerada() {
        super();
    }

    public CartaEnumerada(String idCarta, char color, int numero) {
        super(idCarta, color);
        this.numero = numero;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return getIdCarta();
    }
}

package ucab.clasesCarta;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListaCartas {
    public List<Carta> listaCartas;

    public ListaCartas() {
        this.listaCartas = new ArrayList<>();
    }

    public void agregarCarta(Carta carta) {
        this.listaCartas.add(carta);
    }

    public void agregarCartaAlInicio(Carta carta) {
        this.listaCartas.add(0, carta);
    }

    public List<Carta> getListaCartas() {
        return this.listaCartas;
    }

    public Carta get(int i) {
        return listaCartas.get(i);
    }

    public Carta remove(int i) {
        return listaCartas.remove(i);
    }

    public List<Carta> shuffle() {
        Collections.shuffle(listaCartas);
        return listaCartas;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Carta carta : this.listaCartas) {
            sb.append(carta.toString()).append(" ");
        }
        return sb.toString();
    }

    public static void eliminarPrimeraCarta(ListaCartas mazo, ListaCartas mazoDescarte) {
        mazoDescarte.agregarCarta(mazo.get(0));
        mazo.remove(0);
    }

    public int size() {
        return this.listaCartas.size();
    }
}
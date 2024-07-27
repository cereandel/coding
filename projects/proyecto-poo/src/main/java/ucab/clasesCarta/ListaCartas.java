package ucab.clasesCarta;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * La clase ListaCartas representa una lista de cartas en un juego de cartas.
 * Proporciona métodos para agregar, obtener, eliminar y mezclar cartas.
 * También incluye métodos específicos para manejar cartas enumeradas.
 *
 *
 * @version 1.0
 */
public class ListaCartas {
    public List<Carta> listaCartas;

    /**
     * Crea una nueva lista de cartas vacía.
     */
    public ListaCartas() {
        this.listaCartas = new ArrayList<>();
    }

    /**
     * Agrega una carta a la lista.
     *
     * @param carta La carta a agregar.
     */
    public void agregarCarta(Carta carta) {
        this.listaCartas.add(carta);
    }

    /**
     * Agrega una carta al inicio de la lista.
     *
     * @param carta La carta a agregar al inicio.
     */
    public void agregarCartaAlInicio(Carta carta) {
        this.listaCartas.add(0, carta);
    }

    /**
     * Obtiene la lista completa de cartas.
     *
     * @return La lista de cartas.
     */
    public List<Carta> getListaCartas() {
        return this.listaCartas;
    }

    /**
     * Obtiene la carta en la posición especificada.
     *
     * @param i El índice de la carta.
     * @return La carta en la posición especificada.
     */
    public Carta get(int i) {
        return listaCartas.get(i);
    }

    /**
     * Elimina la carta en la posición especificada.
     *
     * @param i El índice de la carta a eliminar.
     * @return La carta eliminada.
     */
    public Carta remove(int i) {
        return listaCartas.remove(i);
    }

    /**
     * Mezcla las cartas en la lista.
     *
     * @return La lista de cartas mezclada.
     */
    public List<Carta> shuffle() {
        Collections.shuffle(listaCartas);
        return listaCartas;
    }

    /**
     * Devuelve una representación en cadena de todas las cartas en la lista.
     *
     * @return Una cadena que muestra las cartas separadas por espacios.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Carta carta : this.listaCartas) {
            sb.append(carta.toString()).append(" ");
        }
        return sb.toString();
    }

    /**
     * Encuentra el número de la carta enumerada más alta en la lista.
     *
     * @return El número de la carta enumerada más alta.
     */
    public int cartaMasAlta() {
        CartaEnumerada cartaAlta = null;
        for (Carta carta : this.listaCartas) {
            if (carta instanceof CartaEnumerada) {
                CartaEnumerada cartaEnumerada = (CartaEnumerada) carta;
                if (cartaAlta == null || cartaEnumerada.getNumero() > cartaAlta.getNumero()) {
                    cartaAlta = cartaEnumerada;
                }
            }
        }
        if (cartaAlta == null) {
            return -11;
        } else {
            return cartaAlta.getNumero();
        }
    }

    /**
     * Devuelve el tamaño de la lista de cartas.
     *
     * @return el tamaño de la lista de cartas.
     */
    public int size() {
        return this.listaCartas.size();
    }
}
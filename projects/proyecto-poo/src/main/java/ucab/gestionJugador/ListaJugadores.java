package ucab.gestionJugador;

import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase representa una lista de jugadores en el juego.
 */
public class ListaJugadores {
    private List<Jugador> listaJugadores;

    /**
     * Constructor para la clase ListaJugadores.
     */
    public ListaJugadores() {
        this.listaJugadores = new ArrayList<>();
    }

    /**
     * Agrega un jugador a la lista.
     *
     * @param jugador El jugador a agregar.
     */
    public void agregarJugador(Jugador jugador) {
        this.listaJugadores.add(jugador);
    }

    /**
     * Obtiene la lista de jugadores.
     *
     * @return La lista de jugadores.
     */
    public List<Jugador> getListaJugadores() {
        return this.listaJugadores;
    }

    /**
     * Busca un jugador en la lista por su ID.
     *
     * @param id El ID del jugador a buscar.
     * @return El jugador si se encuentra, null en caso contrario.
     */
    public Jugador buscar(String id) {
        for (Jugador jugador : listaJugadores) {
            if (jugador.getId().equals(id)) {
                return jugador;
            }
        }
        return null;
    }
}
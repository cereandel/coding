package ucab.gestionJugador;

import java.util.ArrayList;
import java.util.List;

public class ListaJugadores {
    private List<Jugador> listaJugadores;

    public ListaJugadores() {
        this.listaJugadores = new ArrayList<>();
    }

    public void agregarJugador(Jugador jugador) {
        this.listaJugadores.add(jugador);
    }

    public List<Jugador> getListaJugadores() {
        return this.listaJugadores;
    }
    
    public Jugador buscar(String id) {
        for (Jugador jugador : listaJugadores) {
            if (jugador.getId().equals(id)) {
                return jugador;
            }
        }
        return null;
    }
}
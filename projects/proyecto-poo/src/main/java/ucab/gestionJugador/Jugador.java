package ucab.gestionJugador;

import ucab.clasesCarta.Carta;
import ucab.clasesCarta.ListaCartas;

/**
 * Esta clase representa a un jugador en el juego.
 */
public class Jugador {
    private String nombre;
    private String id;
    private ListaCartas mazo;

    public Jugador() {
    }

    /**
     * Constructor para la clase Jugador.
     *
     * @param nombre El nombre del jugador.
     * @param id     El ID del jugador.
     * @param mazo   El mazo de cartas del jugador.
     */
    public Jugador(String nombre, String id, ListaCartas mazo) {
        this.nombre = nombre;
        this.id = id;
        this.mazo = mazo;
    }

    /**
     * Obtiene el nombre del jugador.
     *
     * @return El nombre del jugador.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del jugador.
     *
     * @param nombre El nuevo nombre del jugador.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el ID del jugador.
     *
     * @return El ID del jugador.
     */
    public String getId() {
        return id;
    }

    /**
     * Establece el ID del jugador.
     *
     * @param id El nuevo ID del jugador.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Obtiene el mazo de cartas del jugador.
     *
     * @return El mazo de cartas del jugador.
     */
    public ListaCartas getMazo() {
        return mazo;
    }

    public Carta seleccionarCartaMaquina(Carta carta) {
        return carta;
    }
}
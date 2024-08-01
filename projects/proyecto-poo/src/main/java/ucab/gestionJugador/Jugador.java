package ucab.gestionJugador;

import ucab.clasesCarta.ListaCartas;

public class Jugador {
    private String nombre;
    private String id;
    private ListaCartas mazo;

    public Jugador() {
    }

    public Jugador(String nombre, String id, ListaCartas mazo) {
        this.nombre = nombre;
        this.id = id;
        this.mazo = mazo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ListaCartas getMazo() {
        return mazo;
    }
}
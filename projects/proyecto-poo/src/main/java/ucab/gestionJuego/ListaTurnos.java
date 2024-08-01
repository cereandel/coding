/**
 * La clase ListaTurnos se utiliza para gestionar una lista de turnos en un juego.
 * Proporciona métodos para agregar turnos a la lista y obtener la lista de turnos.
 */
package ucab.gestionJuego;

import java.util.ArrayList;
import java.util.List;

public class ListaTurnos {
    private List<Turno> listaTurnos;

    public ListaTurnos() {
        this.listaTurnos = new ArrayList<>();
    }

    public void agregarTurno(Turno turno) {
        this.listaTurnos.add(turno);
    }

    public List<Turno> getListaTurnos() {
        return this.listaTurnos;
    }

    public int size() {
        return listaTurnos.size();
    }
}
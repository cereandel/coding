/**
 * La clase ListaTurnos se utiliza para gestionar una lista de turnos en un juego.
 * Proporciona métodos para agregar turnos a la lista y obtener la lista de turnos.
 */
package ucab.gestionJuego;

import java.util.ArrayList;
import java.util.List;

public class ListaTurnos {
    private List<Turno> listaTurnos;

    /**
     * Constructor de la clase ListaTurnos.
     * Inicializa la lista de turnos como un ArrayList vacío.
     */
    public ListaTurnos() {
        this.listaTurnos = new ArrayList<>();
    }

    /**
     * Método para agregar un turno a la lista de turnos.
     * 
     * @param turno El turno a agregar.
     */
    public void agregarTurno(Turno turno) {
        this.listaTurnos.add(turno);
    }

    /**
     * Método para obtener la lista de turnos.
     * 
     * @return La lista de turnos.
     */
    public List<Turno> getListaTurnos() {
        return this.listaTurnos;
    }

    /**
     * Método para obtener el tamaño de la lista de turnos.
     * 
     * @return El tamaño de la lista de turnos.
     */
    public int size() {
        return listaTurnos.size();
    }
}
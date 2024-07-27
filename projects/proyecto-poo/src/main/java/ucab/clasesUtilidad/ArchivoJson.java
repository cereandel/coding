package ucab.clasesUtilidad;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import ucab.gestionJuego.ListaTurnos;
import ucab.gestionJugador.ListaJugadores;
import ucab.clasesCarta.ListaCartas;

/**
 * Clase para guardar y leer datos de una partida en formato JSON.
 */
public final class ArchivoJson {
    static ObjectMapper mapper = new ObjectMapper();

    /**
     * Guarda los datos de la partida en un archivo JSON.
     *
     * @param jugadores      La lista de jugadores.
     * @param turnos         La lista de turnos.
     * @param mazoGeneral    El mazo general de cartas.
     * @param mazoDescarte   El mazo de descarte.
     * @param indiceTurno    El índice del turno actual.
     * @param sentidoRegular Indica si el juego sigue un sentido regular (true) o inverso (false).
     */
    public static void guardar(ListaJugadores jugadores, ListaTurnos turnos, ListaCartas mazoGeneral,
                               ListaCartas mazoDescarte, int indiceTurno, boolean sentidoRegular) {
        Juego juego = new Juego(jugadores, turnos, mazoGeneral, mazoDescarte, indiceTurno, sentidoRegular);
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File("archivos/partida.json"), juego);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void guardarEstadisticas(LinkedList<String> estadisticas) {
        LinkedList<String> estadisticasGuardadas = ArchivoJson.cargarEstadisticas("archivos/estadisticas.json");
        if (estadisticasGuardadas == null) {
            try {
                mapper.writerWithDefaultPrettyPrinter().writeValue(new File("archivos/estadisticas.json"), estadisticas);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            estadisticas.addAll(estadisticasGuardadas);
            try {
                mapper.writerWithDefaultPrettyPrinter().writeValue(new File("archivos/estadisticas.json"), estadisticas);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Lee los datos de una partida desde un archivo JSON.
     *
     * @param filePath La ruta al archivo JSON.
     * @return El objeto Juego con los datos cargados desde el archivo.
     */
    public static Juego leer(String filePath) {
        Juego juego = null;
        try {
            juego = mapper.readValue(new File(filePath), Juego.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return juego;
    }

    public static LinkedList cargarEstadisticas(String filePath) {
        File file = new File("archivos/estadisticas.json");
        if (file.exists() && !file.isDirectory()) {
            LinkedList estadisticas = null;
            try {
                estadisticas = mapper.readValue(new File(filePath), LinkedList.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return estadisticas;
        } else {
            System.out.println("No se pudo cargar las estadisticas, no existe el archivo");
            return null;
        }
    }
}
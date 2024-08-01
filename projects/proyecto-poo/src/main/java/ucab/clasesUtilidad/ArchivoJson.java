package ucab.clasesUtilidad;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import ucab.gestionJuego.ListaTurnos;
import ucab.gestionJugador.ListaJugadores;
import ucab.clasesCarta.ListaCartas;

public final class ArchivoJson {
    static ObjectMapper mapper = new ObjectMapper();

    public static void guardar(ListaJugadores jugadores, @SuppressWarnings("exports") ListaTurnos turnos,
            ListaCartas mazoGeneral,
            ListaCartas mazoDescarte, int indiceTurno, boolean sentidoRegular) {
        Juego juego = new Juego(jugadores, turnos, mazoGeneral, mazoDescarte, indiceTurno, sentidoRegular);
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File("archivos/partida.json"), juego);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Juego leer(String filePath) {
        Juego juego = null;
        try {
            juego = mapper.readValue(new File(filePath), Juego.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return juego;
    }
}
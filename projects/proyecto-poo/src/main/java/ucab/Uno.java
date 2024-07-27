package ucab;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import ucab.clasesUtilidad.ArchivoJson;

import java.util.LinkedList;

/**
 * Clase principal que inicia la aplicación Uno.
 */
public class Uno extends Application {

    /**
     * Método principal que inicia la aplicación.
     *
     * @param stage El escenario principal de la aplicación.
     * @throws Exception Si ocurre algún error al cargar la interfaz gráfica.
     */
    @Override
    public void start(@SuppressWarnings("exports") Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Inicio.fxml"));
        Image iconImage = new Image(String.valueOf(getClass().getResource("images/GameIcon.png")));
        Scene scene = new Scene(root);
        stage.setTitle("Uno");
        stage.setMaximized(true);
        stage.getIcons().add(iconImage);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Método principal que lanza la aplicación.
     *
     * @param args Los argumentos de línea de comandos (no se utilizan en este caso).
     */
    public static void main(String[] args) {
        /*LinkedList<String> estadisticas = new LinkedList<>();
        estadisticas.add("El pepe, puntos: 200");
        ArchivoJson.guardarEstadisticas(estadisticas);*/
        launch(args);
    }
}
package ucab;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Screen;
import javafx.stage.Stage;
import ucab.clasesCarta.Carta;
import ucab.clasesCarta.ListaCartas;
import ucab.clasesUtilidad.ArchivoJson;
import ucab.gestionJuego.GestorDePartida;
import ucab.gestionJuego.Partida;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import static ucab.gestionJuego.PartidaUtilidades.obtenerRuta;

/**
 * Controlador para las escenas de la aplicación.
 */
public final class SceneController implements javafx.fxml.Initializable {
    private static Stage stage;
    private static Parent root;
    private static final int cantidadDeJugadores = 2;
    public static String sharedInput = "";

    @FXML
    private TextField username;

    @FXML
    private Button button;

    @FXML
    private Label nombreUsuario;
    private static Label nombreUsuarioStatic;

    @FXML
    private Label cantidadCartasMaquina;
    private static Label cantidadCartasMaquinaStatic;

    @FXML
    private ImageView cartaDescarte;
    private static ImageView cartaDescarteStatic;

    @FXML
    private ImageView carta2Imagen;
    private static ImageView carta2ImagenStatic;

    @FXML
    private ImageView carta3Imagen;
    private static ImageView carta3ImagenStatic;

    @FXML
    private ImageView carta4Imagen;
    private static ImageView carta4ImagenStatic;

    @FXML
    private ImageView carta5Imagen;
    private static ImageView carta5ImagenStatic;

    @FXML
    private ImageView carta6Imagen;
    private static ImageView carta6ImagenStatic;

    @FXML
    private ImageView carta7Imagen;
    private static ImageView carta7ImagenStatic;

    @FXML
    private Button carta1Button;

    @FXML
    private ImageView carta1Imagen;
    private static ImageView carta1ImagenStatic;

    @FXML
    private Button carta2Button;

    @FXML
    private Button carta3Button;

    @FXML
    private Button carta4Button;

    @FXML
    private Button carta5Button;

    @FXML
    private Button carta6Button;

    @FXML
    private Button carta7Button;

    @FXML
    private Button estadisticasButton;

    @FXML
    private Label infoEstadisticas;
    private static Label infoEstadisticasStatic;

    /**
     * Inicializa el controlador.
     *
     * @param url            La URL de la ubicación del archivo FXML.
     * @param resourceBundle El ResourceBundle utilizado para localizar objetos.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nombreUsuarioStatic = nombreUsuario;
        cantidadCartasMaquinaStatic = cantidadCartasMaquina;
        cartaDescarteStatic = cartaDescarte;
        carta1ImagenStatic = carta1Imagen;
        carta2ImagenStatic = carta2Imagen;
        carta3ImagenStatic = carta3Imagen;
        carta4ImagenStatic = carta4Imagen;
        carta5ImagenStatic = carta5Imagen;
        carta6ImagenStatic = carta6Imagen;
        carta7ImagenStatic = carta7Imagen;
        infoEstadisticasStatic = infoEstadisticas;
    }

    /**
     * Carga la escena del menú principal y muestra la ventana correspondiente.
     *
     * @param event El evento que desencadenó la acción (un clic en un botón).
     * @throws IOException Si ocurre algún error al cargar la escena.
     */
    @FXML
    private void escenaMenuPrincipal(@SuppressWarnings("exports") ActionEvent event) throws IOException {
        root = FXMLLoader.load(SceneController.class.getResource("MenuPrincipal.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Rectangle2D screenSize = Screen.getPrimary().getBounds();
        Scene scene = new Scene(root, screenSize.getWidth(), screenSize.getHeight());
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Sale de la aplicación.
     */
    @FXML
    private void salir() {
        System.exit(0);
    }

    /**
     * Carga la escena del menú secundario y muestra la ventana correspondiente.
     *
     * @param event El evento que desencadenó la acción (un clic en un botón).
     * @throws IOException Si ocurre algún error al cargar la escena.
     */
    @FXML
    private void escenaMenuSecundario(@SuppressWarnings("exports") ActionEvent event) throws IOException {
        root = FXMLLoader.load(SceneController.class.getResource("MenuSecundario2.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
        Scene scene = new Scene(root, screenSize.getWidth(), screenSize.getHeight());
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Carga la escena final y muestra la ventana correspondiente.
     *
     * @param event El evento que desencadenó la acción (un clic en un botón).
     * @throws IOException Si ocurre algún error al cargar la escena.
     */
    @FXML
    private void escenaFinal(@SuppressWarnings("exports") ActionEvent event) throws IOException {
        root = FXMLLoader.load(SceneController.class.getResource("Final.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
        Scene scene = new Scene(root, screenSize.getWidth(), screenSize.getHeight());
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Carga la escena de registro y muestra la ventana correspondiente.
     *
     * @param event El evento que desencadenó la acción (un clic en un botón).
     * @throws IOException Si ocurre algún error al cargar la escena.
     */
    @FXML
    private void escenaRegistro(@SuppressWarnings("exports") ActionEvent event) throws IOException {
        root = FXMLLoader.load(SceneController.class.getResource("RegistroDatos.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
        Scene scene = new Scene(root, screenSize.getWidth(), screenSize.getHeight());
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Crea la partida.
     *
     * @param event El evento que desencadenó la acción (un clic en un botón).
     * @throws IOException Si ocurre algún error relacionado con el registro de datos.
     */
    @FXML
    private void RegistroDatos(@SuppressWarnings("exports") ActionEvent event) throws IOException {
        GestorDePartida.crear(cantidadDeJugadores, username.getText(), event);
    }

    /**
     * Carga la escena de la partida y muestra la ventana correspondiente con los datos proporcionados.
     *
     * @param event          El evento que desencadenó la acción (un clic en un botón).
     * @param nombre         El nombre del jugador.
     * @param cantidadCartas La cantidad de cartas del jugador.
     * @param cartaDescarte  La carta en el descarte.
     * @param cartasJugador  Las cartas del jugador.
     * @throws IOException Si ocurre algún error al cargar la escena.
     */
    public static void escenaPartida(@SuppressWarnings("exports") ActionEvent event, String nombre, int cantidadCartas, String cartaDescarte, ListaCartas cartasJugador) throws IOException {
        root = FXMLLoader.load(SceneController.class.getResource("Tablero.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
        Scene scene = new Scene(root, screenSize.getWidth(), screenSize.getHeight());
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();

        nombreUsuarioStatic.setText(nombre);
        cantidadCartasMaquinaStatic.setText("cartas: " + cantidadCartas);
        cartaDescarteStatic.setImage(new Image(String.valueOf(SceneController.class.getResource(obtenerRuta(cartaDescarte)))));
        carta1ImagenStatic.setImage(new Image(String.valueOf(SceneController.class.getResource(obtenerRuta(cartasJugador.get(0).getIdCarta())))));
        carta2ImagenStatic.setImage(new Image(String.valueOf(SceneController.class.getResource(obtenerRuta(cartasJugador.get(1).getIdCarta())))));
        carta3ImagenStatic.setImage(new Image(String.valueOf(SceneController.class.getResource(obtenerRuta(cartasJugador.get(2).getIdCarta())))));
        carta4ImagenStatic.setImage(new Image(String.valueOf(SceneController.class.getResource(obtenerRuta(cartasJugador.get(3).getIdCarta())))));
        carta5ImagenStatic.setImage(new Image(String.valueOf(SceneController.class.getResource(obtenerRuta(cartasJugador.get(4).getIdCarta())))));
        carta6ImagenStatic.setImage(new Image(String.valueOf(SceneController.class.getResource(obtenerRuta(cartasJugador.get(5).getIdCarta())))));
        carta7ImagenStatic.setImage(new Image(String.valueOf(SceneController.class.getResource(obtenerRuta(cartasJugador.get(6).getIdCarta())))));
    }

    /**
     * Carga una partida guardada y continúa con la partida o muestra un mensaje si no existe un archivo válido.
     *
     * @param event El evento que desencadenó la acción (un clic en un botón).
     * @throws IOException Si ocurre algún error al cargar la partida.
     */
    @FXML
    private void cargarPartida(@SuppressWarnings("exports") ActionEvent event) throws IOException {
        GestorDePartida.cargar(event);
    }

    /**
     * Maneja la elección de una carta por parte del jugador.
     *
     * @param event El evento que desencadenó la acción (un clic en un botón).
     * @throws IOException Si ocurre algún error relacionado con la elección de la carta.
     */
    @FXML
    private void elegirCarta(@SuppressWarnings("exports") ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        String buttonId = button.getId();
        ImageView imageViewCorresponding;

        switch (buttonId) {
            case "carta1Button":
                imageViewCorresponding = carta1ImagenStatic;
                break;
            case "carta2Button":
                imageViewCorresponding = carta2ImagenStatic;
                break;
            case "carta3Button":
                imageViewCorresponding = carta3ImagenStatic;
                break;
            case "carta4Button":
                imageViewCorresponding = carta4ImagenStatic;
                break;
            case "carta5Button":
                imageViewCorresponding = carta5ImagenStatic;
                break;
            case "carta6Button":
                imageViewCorresponding = carta6ImagenStatic;
                break;
            case "carta7Button":
                imageViewCorresponding = carta7ImagenStatic;
                break;
            default:
                imageViewCorresponding = null;
        }

        if (imageViewCorresponding != null) {
            Image image = imageViewCorresponding.getImage();
            String imagePath = image.getUrl();
            sharedInput = imagePath.substring(imagePath.lastIndexOf("/") + 1, imagePath.lastIndexOf("."));
            Partida.actualizarJuego(sharedInput);
        }
    }

    /**
     * Muestra un diálogo de aviso indicando que el jugador no tiene cartas y se tomarán del montón.
     */
    public static void sinCartas() {
        Dialog<String> dialog = new Dialog<String>();
        dialog.setTitle("Aviso");
        ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        dialog.setContentText("No tienes cartas, se agarran del montón");
        dialog.getDialogPane().getButtonTypes().add(type);
        dialog.showAndWait();
    }

    /**
     * Muestra un diálogo de aviso indicando que la carta jugada es incorrecta y debe intentarse de nuevo.
     */
    public static void cartaInvalida() {
        Dialog<String> dialog = new Dialog<String>();
        dialog.setTitle("Aviso");
        ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        dialog.setContentText("carta incorrecta, intente de nuevo");
        dialog.getDialogPane().getButtonTypes().add(type);
        dialog.showAndWait();
    }

    /**
     * Muestra un diálogo con el nombre del jugador ganador y su puntaje.
     *
     * @param jugador El nombre del jugador ganador.
     * @param puntaje El puntaje del jugador ganador.
     */
    public static void mostrarGanador(String jugador, int puntaje) {
        Dialog<String> dialog = new Dialog<String>();
        Dialog<String> dialog2 = new Dialog<String>();
        dialog.setTitle("FINAL");
        ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        dialog.setContentText("El jugador: "+ jugador +" ha ganado.\nPuntaje: " + puntaje + ". \nPulse la x para salir");
        dialog.getDialogPane().getButtonTypes().add(type);
        dialog.showAndWait();
    }

    /**
     * Muestra un diálogo de aviso indicando que es el turno de la máquina.
     *
     * @throws IOException Si ocurre algún error relacionado con el turno de la máquina.
     */
    public static void turnoMaquina() throws IOException {
        Dialog<String> dialog = new Dialog<String>();
        dialog.setTitle("Aviso");
        ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        dialog.setContentText("Turno de la máquina");
        dialog.getDialogPane().getButtonTypes().add(type);
        dialog.showAndWait();
        Partida.actualizarJuego("");
    }

    /**
     * Actualiza la escena del tablero con las imagenes de las cartas.
     *
     * @param idDescarte         El ID de la carta en el descarte.
     * @param cartasJugador      Las cartas del jugador.
     * @param cantidadCartasMaquina La cantidad de cartas de la máquina.
     */
    public static void actualizarEscena(String idDescarte, ListaCartas cartasJugador, int cantidadCartasMaquina) {
        cartaDescarteStatic.setImage(new Image(String.valueOf(SceneController.class.getResource(obtenerRuta(idDescarte)))));
        cantidadCartasMaquinaStatic.setText("cartas: " + cantidadCartasMaquina);

        if (cartasJugador.size() == 1) {
            carta1ImagenStatic.setImage(new Image(String.valueOf(SceneController.class.getResource(obtenerRuta(cartasJugador.get(0).getIdCarta())))));
            carta2ImagenStatic.setImage(null);
            carta3ImagenStatic.setImage(null);
            carta4ImagenStatic.setImage(null);
            carta5ImagenStatic.setImage(null);
            carta6ImagenStatic.setImage(null);
            carta7ImagenStatic.setImage(null);
        } else if (cartasJugador.size() == 2) {
            carta1ImagenStatic.setImage(new Image(String.valueOf(SceneController.class.getResource(obtenerRuta(cartasJugador.get(0).getIdCarta())))));
            carta2ImagenStatic.setImage(new Image(String.valueOf(SceneController.class.getResource(obtenerRuta(cartasJugador.get(1).getIdCarta())))));
            carta3ImagenStatic.setImage(null);
            carta4ImagenStatic.setImage(null);
            carta5ImagenStatic.setImage(null);
            carta6ImagenStatic.setImage(null);
            carta7ImagenStatic.setImage(null);
        } else if (cartasJugador.size() == 3) {
            carta1ImagenStatic.setImage(new Image(String.valueOf(SceneController.class.getResource(obtenerRuta(cartasJugador.get(0).getIdCarta())))));
            carta2ImagenStatic.setImage(new Image(String.valueOf(SceneController.class.getResource(obtenerRuta(cartasJugador.get(1).getIdCarta())))));
            carta3ImagenStatic.setImage(new Image(String.valueOf(SceneController.class.getResource(obtenerRuta(cartasJugador.get(2).getIdCarta())))));
            carta4ImagenStatic.setImage(null);
            carta5ImagenStatic.setImage(null);
            carta6ImagenStatic.setImage(null);
            carta7ImagenStatic.setImage(null);
        } else if (cartasJugador.size() == 4) {
            carta1ImagenStatic.setImage(new Image(String.valueOf(SceneController.class.getResource(obtenerRuta(cartasJugador.get(0).getIdCarta())))));
            carta2ImagenStatic.setImage(new Image(String.valueOf(SceneController.class.getResource(obtenerRuta(cartasJugador.get(1).getIdCarta())))));
            carta3ImagenStatic.setImage(new Image(String.valueOf(SceneController.class.getResource(obtenerRuta(cartasJugador.get(2).getIdCarta())))));
            carta4ImagenStatic.setImage(new Image(String.valueOf(SceneController.class.getResource(obtenerRuta(cartasJugador.get(3).getIdCarta())))));
            carta5ImagenStatic.setImage(null);
            carta6ImagenStatic.setImage(null);
            carta7ImagenStatic.setImage(null);
        } else if (cartasJugador.size() == 5) {
            carta1ImagenStatic.setImage(new Image(String.valueOf(SceneController.class.getResource(obtenerRuta(cartasJugador.get(0).getIdCarta())))));
            carta2ImagenStatic.setImage(new Image(String.valueOf(SceneController.class.getResource(obtenerRuta(cartasJugador.get(1).getIdCarta())))));
            carta3ImagenStatic.setImage(new Image(String.valueOf(SceneController.class.getResource(obtenerRuta(cartasJugador.get(2).getIdCarta())))));
            carta4ImagenStatic.setImage(new Image(String.valueOf(SceneController.class.getResource(obtenerRuta(cartasJugador.get(3).getIdCarta())))));
            carta5ImagenStatic.setImage(new Image(String.valueOf(SceneController.class.getResource(obtenerRuta(cartasJugador.get(4).getIdCarta())))));
            carta6ImagenStatic.setImage(null);
            carta7ImagenStatic.setImage(null);
        } else if (cartasJugador.size() == 6) {
            carta1ImagenStatic.setImage(new Image(String.valueOf(SceneController.class.getResource(obtenerRuta(cartasJugador.get(0).getIdCarta())))));
            carta2ImagenStatic.setImage(new Image(String.valueOf(SceneController.class.getResource(obtenerRuta(cartasJugador.get(1).getIdCarta())))));
            carta3ImagenStatic.setImage(new Image(String.valueOf(SceneController.class.getResource(obtenerRuta(cartasJugador.get(2).getIdCarta())))));
            carta4ImagenStatic.setImage(new Image(String.valueOf(SceneController.class.getResource(obtenerRuta(cartasJugador.get(3).getIdCarta())))));
            carta5ImagenStatic.setImage(new Image(String.valueOf(SceneController.class.getResource(obtenerRuta(cartasJugador.get(4).getIdCarta())))));
            carta6ImagenStatic.setImage(new Image(String.valueOf(SceneController.class.getResource(obtenerRuta(cartasJugador.get(5).getIdCarta())))));
            carta7ImagenStatic.setImage(null);
        } else if (cartasJugador.size() == 7) {
            carta1ImagenStatic.setImage(new Image(String.valueOf(SceneController.class.getResource(obtenerRuta(cartasJugador.get(0).getIdCarta())))));
            carta2ImagenStatic.setImage(new Image(String.valueOf(SceneController.class.getResource(obtenerRuta(cartasJugador.get(1).getIdCarta())))));
            carta3ImagenStatic.setImage(new Image(String.valueOf(SceneController.class.getResource(obtenerRuta(cartasJugador.get(2).getIdCarta())))));
            carta4ImagenStatic.setImage(new Image(String.valueOf(SceneController.class.getResource(obtenerRuta(cartasJugador.get(3).getIdCarta())))));
            carta5ImagenStatic.setImage(new Image(String.valueOf(SceneController.class.getResource(obtenerRuta(cartasJugador.get(4).getIdCarta())))));
            carta6ImagenStatic.setImage(new Image(String.valueOf(SceneController.class.getResource(obtenerRuta(cartasJugador.get(5).getIdCarta())))));
            carta7ImagenStatic.setImage(new Image(String.valueOf(SceneController.class.getResource(obtenerRuta(cartasJugador.get(6).getIdCarta())))));
        } else {
            carta1ImagenStatic.setImage(new Image(String.valueOf(SceneController.class.getResource(obtenerRuta(cartasJugador.get(0).getIdCarta())))));
            carta2ImagenStatic.setImage(new Image(String.valueOf(SceneController.class.getResource(obtenerRuta(cartasJugador.get(1).getIdCarta())))));
            carta3ImagenStatic.setImage(new Image(String.valueOf(SceneController.class.getResource(obtenerRuta(cartasJugador.get(2).getIdCarta())))));
            carta4ImagenStatic.setImage(new Image(String.valueOf(SceneController.class.getResource(obtenerRuta(cartasJugador.get(3).getIdCarta())))));
            carta5ImagenStatic.setImage(new Image(String.valueOf(SceneController.class.getResource(obtenerRuta(cartasJugador.get(4).getIdCarta())))));
            carta6ImagenStatic.setImage(new Image(String.valueOf(SceneController.class.getResource(obtenerRuta(cartasJugador.get(5).getIdCarta())))));
            carta7ImagenStatic.setImage(new Image(String.valueOf(SceneController.class.getResource(obtenerRuta(cartasJugador.get(6).getIdCarta())))));
        }
    }

    /**
     * Muestra un diálogo de aviso indicando el color elegido y su equivalente en palabras.
     *
     * @param color El código de color (por ejemplo, "R" para rojo).
     */
    public static void cambioColor(String color) {
        switch (color) {
            case "R" -> color = "Rojo";
            case "G" -> color = "Verde";
            case "B" -> color = "Azul";
            case "Y" -> color = "Amarillo";
        }
        Dialog<String> dialog = new Dialog<String>();
        dialog.setTitle("Aviso");
        ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        dialog.setContentText("Color elegido: " + color);
        dialog.getDialogPane().getButtonTypes().add(type);
        dialog.showAndWait();
    }

    /**
     * Muestra un cuadro de diálogo para que el usuario elija una carta de entre las disponibles.
     *
     * @param cartasJugador Las cartas disponibles para elegir.
     * @return El ID de la carta seleccionada por el usuario (o una cadena vacía si no se selecciona ninguna).
     */
    public static String masDeSieteCartas(ListaCartas cartasJugador) {
        List<String> opcionesCartas = new ArrayList<>();
        for (Carta carta : cartasJugador.getListaCartas()) {
            opcionesCartas.add(carta.getIdCarta());
        }
        ChoiceDialog<String> dialog = new ChoiceDialog<>(null,opcionesCartas);
        dialog.setTitle("Elegir carta");
        dialog.setHeaderText("Elija una carta");
        dialog.setContentText("Cartas disponibles:");
        Optional<String> result = dialog.showAndWait();
        return result.orElse(" ");
    }

    /**
     * Muestra un diálogo de aviso indicando que se debe cantar UNO o se recibirá una carta.
     *
     * @param opcion 0 para el jugador, 1 para la máquina.
     */
    public static void cantarUno(int opcion) {
        Dialog<String> dialog = new Dialog<String>();
        dialog.setTitle("UNO");
        ButtonType type = new ButtonType("UNO", ButtonBar.ButtonData.OK_DONE);
        if (opcion == 0){
            dialog.setContentText("Pulse el boton para cantar UNO o recibira una carta: ");
            dialog.getDialogPane().getButtonTypes().add(type);
            dialog.showAndWait();
        }else{
            dialog.setContentText("La Máquina ha cantado UNO ");
            dialog.getDialogPane().getButtonTypes().add(type);
            dialog.showAndWait();
        }
    }

    @FXML
    private void mostrarEstadisticas(ActionEvent event) throws IOException {
        root = FXMLLoader.load(SceneController.class.getResource("Estadisticas.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
        Scene scene = new Scene(root, screenSize.getWidth(), screenSize.getHeight());
        LinkedList<String> estadisticas = ArchivoJson.cargarEstadisticas("archivos/estadisticas.json");
        infoEstadisticasStatic.setText(String.join("\n", estadisticas));
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();
    }
}
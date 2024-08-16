package ucab;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import ucab.clasesCarta.Carta;
import ucab.clasesCarta.ListaCartas;
import ucab.gestionJuego.GestorDePartida;
import ucab.gestionJuego.Partida;
import ucab.gestionJuego.Turno;
//import javafx.animation.PauseTransition;
//import javafx.util.Duration;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import static ucab.gestionJuego.PartidaUtilidades.obtenerRuta;

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
        }

        @FXML
        private void escenaMenuPrincipal(ActionEvent event) throws IOException {
                root = FXMLLoader.load(SceneController.class.getResource("MenuPrincipal.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
        }

        @FXML
        private void salir() {
                System.exit(0);
        }

        @FXML
        private void escenaMenuSecundario(ActionEvent event) throws IOException {
                root = FXMLLoader.load(SceneController.class.getResource("MenuSecundario.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
        }

        @FXML
        private void escenaFinal(ActionEvent event) throws IOException {
                root = FXMLLoader.load(SceneController.class.getResource("Final.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setMaximized(true);
                stage.setScene(scene);
                stage.show();
        }

        @FXML
        private void escenaRegistro(ActionEvent event) throws IOException {
                root = FXMLLoader.load(SceneController.class.getResource("RegistroDatos.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
        }

        @FXML
        private void RegistroDatos(ActionEvent event) throws IOException {
                ArrayList<String> nombreJugadores = new ArrayList<>();
                nombreJugadores.add(username.getText());
                GestorDePartida.crear(cantidadDeJugadores, nombreJugadores, event);
        }

        public static void escenaPartida(@SuppressWarnings("exports") ActionEvent event, String nombre,
                        int cantidadCartas,
                        String cartaDescarte, ListaCartas cartasJugador, @SuppressWarnings("exports") Turno turno)
                        throws IOException {
                root = FXMLLoader.load(SceneController.class.getResource("Tablero.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

                nombreUsuarioStatic.setText(nombre);
                cantidadCartasMaquinaStatic.setText("cartas: " + cantidadCartas);
                cartaDescarteStatic
                                .setImage(new Image(String.valueOf(
                                                SceneController.class.getResource(obtenerRuta(cartaDescarte)))));
                if (cartasJugador.size() > 0) {
                        carta1ImagenStatic.setImage(new Image(
                                        String.valueOf(SceneController.class
                                                        .getResource(obtenerRuta(cartasJugador.get(0).getIdCarta())))));
                }
                if (cartasJugador.size() > 1) {
                        carta2ImagenStatic.setImage(new Image(
                                        String.valueOf(SceneController.class
                                                        .getResource(obtenerRuta(cartasJugador.get(1).getIdCarta())))));
                }
                if (cartasJugador.size() > 2) {
                        carta3ImagenStatic.setImage(new Image(
                                        String.valueOf(SceneController.class
                                                        .getResource(obtenerRuta(cartasJugador.get(2).getIdCarta())))));
                }
                if (cartasJugador.size() > 3) {
                        carta4ImagenStatic.setImage(new Image(
                                        String.valueOf(SceneController.class
                                                        .getResource(obtenerRuta(cartasJugador.get(3).getIdCarta())))));
                }
                if (cartasJugador.size() > 4) {
                        carta5ImagenStatic.setImage(new Image(
                                        String.valueOf(SceneController.class
                                                        .getResource(obtenerRuta(cartasJugador.get(4).getIdCarta())))));
                }
                if (cartasJugador.size() > 5) {
                        carta6ImagenStatic.setImage(new Image(
                                        String.valueOf(SceneController.class
                                                        .getResource(obtenerRuta(cartasJugador.get(5).getIdCarta())))));
                }
                if (cartasJugador.size() > 6) {
                        carta7ImagenStatic.setImage(new Image(
                                        String.valueOf(SceneController.class
                                                        .getResource(obtenerRuta(cartasJugador.get(6).getIdCarta())))));
                }
        }

        public static void generarEscena(String ruta, @SuppressWarnings("exports") ActionEvent event)
                        throws IOException {
                root = FXMLLoader.load(SceneController.class.getResource(ruta));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setMaximized(true);
                stage.setScene(scene);
                stage.show();
        }

        @FXML
        private void cargarPartida(ActionEvent event) throws IOException {
                GestorDePartida.cargar(event);
        }

        @FXML
        private void elegirCarta(ActionEvent event) throws IOException, InterruptedException {
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

        public static void sinCartas() {
                Dialog<String> dialog = new Dialog<String>();
                dialog.setTitle("Aviso");
                ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
                dialog.setContentText("No tienes cartas, se agarran del montón");
                dialog.getDialogPane().getButtonTypes().add(type);
                dialog.showAndWait();
        }

        public static void cartaInvalida() {
                Dialog<String> dialog = new Dialog<String>();
                dialog.setTitle("Aviso");
                ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
                dialog.setContentText("carta incorrecta, intente de nuevo");
                dialog.getDialogPane().getButtonTypes().add(type);
                dialog.showAndWait();
        }

        public static void mostrarGanador(String jugador, int puntaje) {
                Dialog<String> dialog = new Dialog<String>();
                dialog.setTitle("FINAL");
                ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
                dialog.setContentText(
                                "El jugador: " + jugador + " ha ganado.\nPuntaje: " + puntaje
                                                + ". \nPulse la x para salir");
                dialog.getDialogPane().getButtonTypes().add(type);
                dialog.showAndWait();
        }

        public static void actualizarEscena(String idDescarte, ListaCartas cartasJugador, int cantidadCartasMaquina,
                        String nombreTurno, int indiceTurno) throws InterruptedException, IOException {
                cartaDescarteStatic
                                .setImage(new Image(String
                                                .valueOf(SceneController.class.getResource(obtenerRuta(idDescarte)))));
                cantidadCartasMaquinaStatic.setText("cartas: " + cantidadCartasMaquina);
                if (cartasJugador.size() == 1) {
                        carta1ImagenStatic.setImage(new Image(
                                        String.valueOf(SceneController.class
                                                        .getResource(obtenerRuta(cartasJugador.get(0).getIdCarta())))));
                        carta2ImagenStatic.setImage(null);
                        carta3ImagenStatic.setImage(null);
                        carta4ImagenStatic.setImage(null);
                        carta5ImagenStatic.setImage(null);
                        carta6ImagenStatic.setImage(null);
                        carta7ImagenStatic.setImage(null);
                } else if (cartasJugador.size() == 2) {
                        carta1ImagenStatic.setImage(new Image(
                                        String.valueOf(SceneController.class
                                                        .getResource(obtenerRuta(cartasJugador.get(0).getIdCarta())))));
                        carta2ImagenStatic.setImage(new Image(
                                        String.valueOf(SceneController.class
                                                        .getResource(obtenerRuta(cartasJugador.get(1).getIdCarta())))));
                        carta3ImagenStatic.setImage(null);
                        carta4ImagenStatic.setImage(null);
                        carta5ImagenStatic.setImage(null);
                        carta6ImagenStatic.setImage(null);
                        carta7ImagenStatic.setImage(null);
                } else if (cartasJugador.size() == 3) {
                        carta1ImagenStatic.setImage(new Image(
                                        String.valueOf(SceneController.class
                                                        .getResource(obtenerRuta(cartasJugador.get(0).getIdCarta())))));
                        carta2ImagenStatic.setImage(new Image(
                                        String.valueOf(SceneController.class
                                                        .getResource(obtenerRuta(cartasJugador.get(1).getIdCarta())))));
                        carta3ImagenStatic.setImage(new Image(
                                        String.valueOf(SceneController.class
                                                        .getResource(obtenerRuta(cartasJugador.get(2).getIdCarta())))));
                        carta4ImagenStatic.setImage(null);
                        carta5ImagenStatic.setImage(null);
                        carta6ImagenStatic.setImage(null);
                        carta7ImagenStatic.setImage(null);
                } else if (cartasJugador.size() == 4) {
                        carta1ImagenStatic.setImage(new Image(
                                        String.valueOf(SceneController.class
                                                        .getResource(obtenerRuta(cartasJugador.get(0).getIdCarta())))));
                        carta2ImagenStatic.setImage(new Image(
                                        String.valueOf(SceneController.class
                                                        .getResource(obtenerRuta(cartasJugador.get(1).getIdCarta())))));
                        carta3ImagenStatic.setImage(new Image(
                                        String.valueOf(SceneController.class
                                                        .getResource(obtenerRuta(cartasJugador.get(2).getIdCarta())))));
                        carta4ImagenStatic.setImage(new Image(
                                        String.valueOf(SceneController.class
                                                        .getResource(obtenerRuta(cartasJugador.get(3).getIdCarta())))));
                        carta5ImagenStatic.setImage(null);
                        carta6ImagenStatic.setImage(null);
                        carta7ImagenStatic.setImage(null);
                } else if (cartasJugador.size() == 5) {
                        carta1ImagenStatic.setImage(new Image(
                                        String.valueOf(SceneController.class
                                                        .getResource(obtenerRuta(cartasJugador.get(0).getIdCarta())))));
                        carta2ImagenStatic.setImage(new Image(
                                        String.valueOf(SceneController.class
                                                        .getResource(obtenerRuta(cartasJugador.get(1).getIdCarta())))));
                        carta3ImagenStatic.setImage(new Image(
                                        String.valueOf(SceneController.class
                                                        .getResource(obtenerRuta(cartasJugador.get(2).getIdCarta())))));
                        carta4ImagenStatic.setImage(new Image(
                                        String.valueOf(SceneController.class
                                                        .getResource(obtenerRuta(cartasJugador.get(3).getIdCarta())))));
                        carta5ImagenStatic.setImage(new Image(
                                        String.valueOf(SceneController.class
                                                        .getResource(obtenerRuta(cartasJugador.get(4).getIdCarta())))));
                        carta6ImagenStatic.setImage(null);
                        carta7ImagenStatic.setImage(null);
                } else if (cartasJugador.size() == 6) {
                        carta1ImagenStatic.setImage(new Image(
                                        String.valueOf(SceneController.class
                                                        .getResource(obtenerRuta(cartasJugador.get(0).getIdCarta())))));
                        carta2ImagenStatic.setImage(new Image(
                                        String.valueOf(SceneController.class
                                                        .getResource(obtenerRuta(cartasJugador.get(1).getIdCarta())))));
                        carta3ImagenStatic.setImage(new Image(
                                        String.valueOf(SceneController.class
                                                        .getResource(obtenerRuta(cartasJugador.get(2).getIdCarta())))));
                        carta4ImagenStatic.setImage(new Image(
                                        String.valueOf(SceneController.class
                                                        .getResource(obtenerRuta(cartasJugador.get(3).getIdCarta())))));
                        carta5ImagenStatic.setImage(new Image(
                                        String.valueOf(SceneController.class
                                                        .getResource(obtenerRuta(cartasJugador.get(4).getIdCarta())))));
                        carta6ImagenStatic.setImage(new Image(
                                        String.valueOf(SceneController.class
                                                        .getResource(obtenerRuta(cartasJugador.get(5).getIdCarta())))));
                        carta7ImagenStatic.setImage(null);
                } else if (cartasJugador.size() == 7) {
                        carta1ImagenStatic.setImage(new Image(
                                        String.valueOf(SceneController.class
                                                        .getResource(obtenerRuta(cartasJugador.get(0).getIdCarta())))));
                        carta2ImagenStatic.setImage(new Image(
                                        String.valueOf(SceneController.class
                                                        .getResource(obtenerRuta(cartasJugador.get(1).getIdCarta())))));
                        carta3ImagenStatic.setImage(new Image(
                                        String.valueOf(SceneController.class
                                                        .getResource(obtenerRuta(cartasJugador.get(2).getIdCarta())))));
                        carta4ImagenStatic.setImage(new Image(
                                        String.valueOf(SceneController.class
                                                        .getResource(obtenerRuta(cartasJugador.get(3).getIdCarta())))));
                        carta5ImagenStatic.setImage(new Image(
                                        String.valueOf(SceneController.class
                                                        .getResource(obtenerRuta(cartasJugador.get(4).getIdCarta())))));
                        carta6ImagenStatic.setImage(new Image(
                                        String.valueOf(SceneController.class
                                                        .getResource(obtenerRuta(cartasJugador.get(5).getIdCarta())))));
                        carta7ImagenStatic.setImage(new Image(
                                        String.valueOf(SceneController.class
                                                        .getResource(obtenerRuta(cartasJugador.get(6).getIdCarta())))));
                } else {
                        carta1ImagenStatic.setImage(new Image(
                                        String.valueOf(SceneController.class
                                                        .getResource(obtenerRuta(cartasJugador.get(0).getIdCarta())))));
                        carta2ImagenStatic.setImage(new Image(
                                        String.valueOf(SceneController.class
                                                        .getResource(obtenerRuta(cartasJugador.get(1).getIdCarta())))));
                        carta3ImagenStatic.setImage(new Image(
                                        String.valueOf(SceneController.class
                                                        .getResource(obtenerRuta(cartasJugador.get(2).getIdCarta())))));
                        carta4ImagenStatic.setImage(new Image(
                                        String.valueOf(SceneController.class
                                                        .getResource(obtenerRuta(cartasJugador.get(3).getIdCarta())))));
                        carta5ImagenStatic.setImage(new Image(
                                        String.valueOf(SceneController.class
                                                        .getResource(obtenerRuta(cartasJugador.get(4).getIdCarta())))));
                        carta6ImagenStatic.setImage(new Image(
                                        String.valueOf(SceneController.class
                                                        .getResource(obtenerRuta(cartasJugador.get(5).getIdCarta())))));
                        carta7ImagenStatic.setImage(new Image(
                                        String.valueOf(SceneController.class
                                                        .getResource(obtenerRuta(cartasJugador.get(6).getIdCarta())))));
                }
                /*
                 * AQUI ES EL DELAY DE LAS ANIMACIONES, DA UNAS EXCEPCIONES CON LOS PANELES DE
                 * DIALOGO
                 * if (indiceTurno == 1) {
                 * PauseTransition pause = new PauseTransition(Duration.seconds(1.5));
                 * pause.setOnFinished(event -> {
                 * try {
                 * Partida.actualizarJuego(sharedInput);
                 * } catch (IOException | InterruptedException e) {
                 * e.printStackTrace();
                 * }
                 * });
                 * pause.play();
                 * }
                 */
        }

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

        public static String masDeSieteCartas(ListaCartas cartasJugador) {
                List<String> opcionesCartas = new ArrayList<>();
                for (Carta carta : cartasJugador.getListaCartas()) {
                        opcionesCartas.add(carta.getIdCarta());
                }
                ChoiceDialog<String> dialog = new ChoiceDialog<>(null, opcionesCartas);
                dialog.setTitle("Elegir carta");
                dialog.setHeaderText("Elija una carta");
                dialog.setContentText("Cartas disponibles:");
                Optional<String> result = dialog.showAndWait();
                return result.orElse(" ");
        }

        public static void cantarUno(int opcion) {
                Dialog<String> dialog = new Dialog<String>();
                dialog.setTitle("UNO");
                ButtonType type = new ButtonType("UNO", ButtonBar.ButtonData.OK_DONE);
                if (opcion == 0) {
                        dialog.setContentText("Pulse el boton para cantar UNO o recibira una carta: ");
                        dialog.getDialogPane().getButtonTypes().add(type);
                        dialog.showAndWait();
                } else {
                        dialog.setContentText("La Máquina ha cantado UNO ");
                        dialog.getDialogPane().getButtonTypes().add(type);
                        dialog.showAndWait();
                }
        }
}
package com.cereandel;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class Controller {

    private static Stage stage;
    private static Parent root;

    @FXML
    private TextField username;

    @FXML
    private Label usuarioTablero;

    @FXML
    private Label cantidadCartasMaquina;

    @FXML
    private ImageView carta1;

    @FXML
    private ImageView carta2;

    @FXML
    private ImageView carta3;

    @FXML
    private ImageView carta4;

    @FXML
    private ImageView carta5;

    @FXML
    private ImageView carta6;

    @FXML
    private ImageView carta7;

    @FXML
    private ImageView cartaDescarte;

    public String obtenerRuta(String nombreCarta) {
        return "images/cartas/" + nombreCarta + ".jpg";
    }

    public void inicializarTablero(Controller controller) {

        controller.usuarioTablero.setText(username.getText());
        String[] idJugadores = { username.getText(), "Maquina" };
        Juego juego = new Juego(idJugadores);

        ImageView[] cartas = { controller.carta1, controller.carta2, controller.carta3, controller.carta4,
                controller.carta5, controller.carta6, controller.carta7 };

        for (int i = 0; i < juego.getMazoJugador(0).size(); i++) {
            cartas[i].setImage(new Image(String
                    .valueOf(Controller.class.getResource(obtenerRuta(juego.getMazoJugador(0).get(i).toString())))));
        }

        controller.cantidadCartasMaquina.setText(String.valueOf(juego.getMazoJugador(1).size()));

    }

    @FXML
    private void salir() {
        System.exit(0);
    }

    @FXML
    private void showMenuSecundario(ActionEvent event) throws Exception {
        root = FXMLLoader.load(Controller.class.getResource("MenuSecundario.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void showRegistroDatos(ActionEvent event) throws Exception {
        root = FXMLLoader.load(Controller.class.getResource("RegistroDatos.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void showMenuPrincipal(ActionEvent event) throws Exception {
        root = FXMLLoader.load(Controller.class.getResource("MenuPrincipal.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void showTablero(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(Controller.class.getResource("Tablero.fxml"));
        root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        Controller controller = loader.getController();
        inicializarTablero(controller);
    }

}
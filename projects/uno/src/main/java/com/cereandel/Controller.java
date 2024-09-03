package com.cereandel;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class Controller implements Initializable {

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

    @FXML
    private void salir() {
        System.exit(0);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        usuarioTablero.setText("Usuario: ");
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
        root = FXMLLoader.load(Controller.class.getResource("Tablero.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
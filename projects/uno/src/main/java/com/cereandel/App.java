package com.cereandel;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.util.Objects;

public class App extends Application {

    @Override
    public void start(@SuppressWarnings("exports") Stage stage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MenuPrincipal.fxml")));
        Image iconImage = new Image(String.valueOf(getClass().getResource("images/GameIcon.png")));
        Scene scene = new Scene(root);
        stage.setTitle("Uno");
        stage.getIcons().add(iconImage);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
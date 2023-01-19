package com.example.nabo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        try{
            Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("grafica/LoginForm.fxml")));
            Scene scene = new Scene(root);
            stage.setTitle("Effettua il login per entrare in Naboo");
            stage.setScene(scene);
            stage.show();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println("Naboo è in esecuzione. Si sta caricando");
        launch();
    }
}
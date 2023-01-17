package com.example.nabo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try{
            Parent root = FXMLLoader.load(Main.class.getResource("grafica/Login.fxml"));
            Scene scene = new Scene(root);

            primaryStage.setTitle("Benvenuto in Naboo. Effettua il login");

            primaryStage.setScene(scene);
            primaryStage.show();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
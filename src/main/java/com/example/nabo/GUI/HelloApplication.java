package com.example.nabo.GUI;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    private Parent root;
    private Stage stage;
    private Scene scene;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader =new FXMLLoader(HelloApplication.class.getResource("Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

       /* metodi per usare nella parte di css
         scene.getStylesheets().add(getClass().getResource("Grafica.css").toExternalForm());per un file, p
       //  String css= this.getClass().getResource("Grafica.css").toExternalForm(); // per tutti i file
        scene.getStylesheets().add(css);*/

        stage.setScene(scene);
        stage.show();
    }

    void NuovoUtente(ActionEvent event) throws IOException {
        root =FXMLLoader.load(getClass().getResource("NuovoUtente.fxml"));
        stage= (Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
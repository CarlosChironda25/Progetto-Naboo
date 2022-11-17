package com.example.nabo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GestioneRss {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
        // Metodo per la cancellazione delle notizie
    void CancellaNotizia(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("CancellaNotizia.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
        // Metodo che aprire la class per il caricamento delle notizie
    void CaricaNotizia(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("CaricaNotizia.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
        // Metodo Per Tornare Indietro
    void Indietro(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("SchermataPrincipale.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
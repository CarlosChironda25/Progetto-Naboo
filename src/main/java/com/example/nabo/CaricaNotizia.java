package com.example.nabo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class CaricaNotizia {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TextField Autore;

    @FXML
    private TextField Link;

    @FXML
    private TextField Tempo;

    @FXML
    private TextField Titolo;

    @FXML
    void AggiungereNotizia(ActionEvent event) {

    }

    @FXML
    void CercaNotizia(ActionEvent event) {

    }


    @FXML
    void Resetcampi(ActionEvent event) {

    }
    @FXML
        // Metodo per tornare nella class GestioneRss
    void Indietro(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("GestioneRss.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
}

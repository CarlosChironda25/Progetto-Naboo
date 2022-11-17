package com.example.nabo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class CancellaNotizia {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TextField Autore;

    @FXML
    private Label Avviso;

    @FXML
    private TextField Link;

    @FXML
        // Metodo per la cancellazione delle notizie
    void CancellaNotizia(ActionEvent event) {

    }

    @FXML
        // Metodo per la ricerca delle notizie
    void CercaNotizia(ActionEvent event) {

    }

    @FXML
        // Metodo per risetare i campi
    void Resetcampi(ActionEvent event) {

    }
    // Metodo per torna nella class GestioneRss
    @FXML
    void Indietro(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("GestioneRss.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

}

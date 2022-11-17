package com.example.nabo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class Commento {
    private Stage stage;
    private Scene scene;
    private Parent root;
    // variabile che serve per avvisare all'utente che i campi sono vuoti
    private Label Avviso;
    @FXML
        // Metodo per aggiungere un nuovo commento
    void AggiungeComment(ActionEvent event) {

    }


    // Metodo per la cancellazione dei commenti
    @FXML
    void CancellaComment(ActionEvent event) {

    }

    // Metodo per cercare commento
    @FXML
    void CercaCommento(ActionEvent event) {

    }

    @FXML
// Metodo per tornare nel pannello GestioneCommenti
    void Indietro(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("GestioneCommenti.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


}

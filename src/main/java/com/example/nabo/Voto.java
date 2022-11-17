package com.example.nabo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class Voto {

    private Stage stage;
    private Scene scene;
    private Parent root;
    // variabile che serve per avvisare all'utente che i campi sono vouti
    private Label Avviso;
    @FXML
        // Metodo per aggiungere in voto
    void AggiugeVoto(ActionEvent event) {

    }




    @FXML
        // Metodo per cancellare voto
    void CancellaVoto(ActionEvent event) {

    }


    @FXML
        // Metodo per cercare Voto
    void CercaVoto(ActionEvent event) {

    }

    @FXML
        // Metodo per tornare indietro nel pannello di gestione utente
    void Indietro(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("GestioneCommenti.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}


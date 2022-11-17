package com.example.nabo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GestioneCommenti {
    private Stage stage;
    private Scene scene;
    private Parent root;



    @FXML
        // Metodo per aprire il panello di commento
    void Commento(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Commento.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }



    @FXML
        //Metodo per aprire il pannello di Voto
    void Voto(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Voto.fxml"));
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

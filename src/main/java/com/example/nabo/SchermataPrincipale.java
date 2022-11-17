package com.example.nabo;

import com.example.nabo.Utente;
import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class SchermataPrincipale {
    ArrayList<Utente> utentes = new ArrayList<>();
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Button AggiornaCommenti;

    @FXML
    private Button AggiornaUtente;

    @FXML
    private Button AggiungeNotizia;

    @FXML
    private Button NuovoUtente;

    @FXML
    private Button Telegram;

    @FXML
        // Metodo per la Gestione dei commenti
    void AggiornaCommenti(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("C:\\Users\\feder\\IdeaProjects\\Progetto-Naboo\\src\\main\\resources\\com\\example\\nabo\\grafica\\GestioneCommenti.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
        // Metodo per la cancellazione di un utente
    void AggiornaUtente(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("CancellaUtente.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
        // Metodo per il caricamneto delle notizie
    void CaricaNotizie(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("GestioneRss.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    // Metodo per l'inserimento di un nuovo utente
    @FXML
    void NuovoUtente(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("NuovoUtente.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void goToTelegram(ActionEvent event) {

    }

    @FXML
        // Impostazione dell' Applicazione
    void Impostazione(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Impostazione.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}

package com.example.nabo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class NuovoUtente {

    private Stage stage;
    private Scene scene;
    private Parent root;

    //     Path
    static String  PathUtente= "C:\\Users\\carlosvasco.chironda\\OneDrive - Alma Mater Studiorum Università di Bologna\\Desktop\\Grafica\\src\\main\\resources\\com\\example\\DataBase\\Dati.json";
    // arrayList dei Utente
    public   static ArrayList<Utente> utentes = new ArrayList<>();


    @FXML
    private Button Cancel;

    @FXML
    private TextField Mail;

    @FXML
    private TextField Password;

    @FXML
    private Label AvisoSave;

    @FXML
    private Button Save;

    @FXML
    private TextField Username;

    @FXML
    void CancelUser(ActionEvent event) {

    }

    @FXML
    void SaveUser(ActionEvent event) {


    }

    @FXML
    void UserMail(ActionEvent event) {
        if (Password.getText().isEmpty() && Username.getText().isEmpty() && Mail.getText().isEmpty()) {

        }
    }

    @FXML
        // Metodo per Aggiungere un nuovo utente nel database
    void UserName(ActionEvent event) {
        String User= String.valueOf(Username);
        String pass= String.valueOf(Password);
        String Ml =String.valueOf(Mail);
        Utente utente1 = new Utente(User,pass,Ml);
        utentes.add(utente1);

    }

    @FXML
    void UserPassword(ActionEvent event) {

    }

    @FXML
        // Metodo per tornare indietro nella Schermata principale
    void Indietro(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("SchermataPrincipale.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
}
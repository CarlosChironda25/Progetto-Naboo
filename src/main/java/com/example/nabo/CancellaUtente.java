package com.example.nabo;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class CancellaUtente {
    private  String PathDati= "C:\\Users\\carlosvasco.chironda\\OneDrive - Alma Mater Studiorum Università di Bologna\\Desktop\\Grafica\\src\\main\\resources\\com\\example\\DataBase\\Dati.json";

    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Label AvisoMail;

    @FXML
    private Label AvisoUsername;

    @FXML
    private Label Avviso;
    @FXML
    private TextField Mail;

    @FXML
    private TextField Username;




    @FXML
    void Indietro(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("SchermataPrincipale.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    @FXML
        // Metodo per la ricerca degli utenti nel database
    void CercaUtente(ActionEvent event) throws FileNotFoundException {
        // Lettura del file
        Gson gson = new Gson();
        JsonReader leggi = new JsonReader(new FileReader(PathDati));
        List<Utente> Utenti = gson.fromJson(leggi, (new TypeToken<List<Utente>>() {}).getType());
        // operatore logico per controllare si i campi inseriti dall'utente non sono vuote
        if( this.Username.getText().equals(null) &&  this.Mail.getText().equals(null)){
            Avviso.setText(" ci sono dei campi vouti, riempili");
        }else
            //Caso non sono vouti chiama il Metodo RimouveUtente(ActionEvent event)
            for( Utente i:Utenti){
                //if( Username.getText().equals(i.Username) &&Mail.getText().equals(i.Mail)){
                    //Avviso.setText("Utente Rimoso");
                    // RimouveUtente(event);
                //}


            }
    }


    @FXML
    void CancellaRimozione(ActionEvent event) {
        Username=null;
        Mail =null;
        AvisoUsername.setText( "La cancellazione è stata fatta");
    }

    /*@FXML
        // Metodo per la rimozione dell' utente

    void RimouveUtente(ActionEvent event) throws FileNotFoundException {
        if( Username.getText()!=null && Mail.getText()!=null){
            // lettura del file degli utenti;
            Gson gson = new Gson();
            JsonReader leggi = new JsonReader(new FileReader(PathDati));
            List<Utente> Utenti = gson.fromJson(leggi, (new TypeToken<List<Utente>>() {}).getType());
            // cilco for che cilca sui file
            for( Utente i:Utenti){
                //   control dei dati inserivo dal utente
                if( i.Username.equals( Username.getText()) && i.Mail.equals(Mail.getText()) )
                    Utenti.remove(i);
                AvisoUsername.setText( "Utente Rimosso, clicca su back ");
                event.getEventType();
            }
        }else
            AvisoUsername.setText(" ci sono dei campi vuoti");
    }*/

}

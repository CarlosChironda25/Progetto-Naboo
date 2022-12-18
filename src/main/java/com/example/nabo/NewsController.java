package com.example.nabo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class NewsController implements Initializable {
    @FXML
    private TableView<Notizia> tablenews;
    //tabella creata per visualizzare tutti gli utenti del database
    @FXML
    private TableColumn<Notizia, String> dataColumn;
    @FXML
    private TableColumn<Notizia, String> titleColumn;
    @FXML
    private TableColumn<Notizia, String> linkColumn;
    @FXML
    private TableColumn<Utente, String> descrizioneColumn;
    @FXML
    private TableColumn<Notizia, String> autoreColumn;

    public void saveUtenti(Utente[] utenti, String file){
        //salva le news che sono stati scritte
        try(FileWriter writer = new FileWriter("Info-Notizie.json")){
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(utenti, writer);
        }catch(JsonIOException | IOException e){
            e.printStackTrace();
        }
    }
    public Notizia[] caricaNews(String file){
        //Carica le news nella tabella
        Notizia[] news;
        try(FileReader reader = new FileReader("Info-Notizie.json")){
            Gson gson = new GsonBuilder().create();
            news = gson.fromJson(reader, Notizia[].class);
        }catch(NullPointerException | IOException | JsonIOException | JsonSyntaxException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void removeCustomer(ActionEvent event){
        //seleziono la riga che voglio eliminare e poi premo il bottone "remove" e si elimina.
        //Anche qui va aggiornato ovviamente il database.
        int selectedID = tablenews.getSelectionModel().getSelectedIndex();
        tablenews.getItems().remove(selectedID);
    }


}

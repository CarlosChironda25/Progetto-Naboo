package com.example.nabo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerUtente implements Initializable {

    @FXML
    private TableView<Utente> tableView;
    //tabella creata per visualizzare tutti gli utenti del database
    @FXML
    private TableColumn<Utente, String> nomeColumn;
    @FXML
    private TableColumn<Utente, String> mailColumn;
    @FXML
    private TableColumn<Utente, String> passwordColumn;

    @FXML
    private TextField nomeInput;
    @FXML
    private TextField mailInput;
    @FXML
    private TextField passwordInput;

    public void saveUtenti(Utente[] utenti, String file){
        //salva gli utenti che sono stati scritti
        try(FileWriter writer = new FileWriter("dati.json")){
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(utenti, writer);
        }catch(JsonIOException | IOException e){
            e.printStackTrace();
        }
    }
    /*public Utente[] caricaUtenti(String file){
        //Carica gli utenti nella tabella
        Utente[] utenti;
        try(FileReader reader = new FileReader("dati.json")){
            Gson gson = new GsonBuilder().create();
            utenti = gson.fromJson(reader, Utente[].class);
        }catch(NullPointerException | IOException | JsonIOException | JsonSyntaxException e){
            e.printStackTrace();
        }
    }*/

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nomeColumn.setCellValueFactory(new PropertyValueFactory<Utente, String>("nome"));
        mailColumn.setCellValueFactory(new PropertyValueFactory<Utente, String>("mail"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<Utente, String>("password"));

    }

    @FXML
    void submit(ActionEvent event){
        //In questo metodo scrivo gli attributi degli utenti che voglio aggiungere. Poi premo il bottone SUbmit
        //e li aggiunge alla tableview che ho creato. Non capisco ancora come però aggiornare anche il file json.
        Utente utente = new Utente(nomeInput.getText(), mailInput.getText(), passwordInput.getText());
        ObservableList<Utente> utenti = tableView.getItems();
        utenti.add(utente);
        tableView.setItems(utenti);
    }
    //Iniziano i metodi per modificare gli attributi di un utente

    //cliccando sulla cella che corrisponde alla password che voglio cambiare, posso modificarla direttamente
    //sul posto
    public void usernameColumn_EDIT(Event e){
        TableColumn.CellEditEvent<Utente, String> cellEditEvent;
        cellEditEvent = (TableColumn.CellEditEvent<Utente, String>) e;
        Utente utente = cellEditEvent.getRowValue();
        utente.setUsername(cellEditEvent.getNewValue());
    }
    //cliccando sulla cella che corrisponde alla password che voglio cambiare, posso modificarla direttamente
    //sul posto
    public void passwordColumn_EDIT(Event e){
        TableColumn.CellEditEvent<Utente, String> cellEditEvent;
        cellEditEvent = (TableColumn.CellEditEvent<Utente, String>) e;
        Utente utente = cellEditEvent.getRowValue();
        utente.setPassword(cellEditEvent.getNewValue());
    }

    //cliccando sulla cella che corrisponde alla email che voglio cambiare, posso modificarla direttamente
    //sul posto
    public void emailColumn_EDIT(Event e){
        TableColumn.CellEditEvent<Utente, String> cellEditEvent;
        cellEditEvent = (TableColumn.CellEditEvent<Utente, String>) e;
        Utente utente = cellEditEvent.getRowValue();
        utente.setMail(cellEditEvent.getNewValue());
    }
    @FXML
    void removeCustomer(ActionEvent event){
        //seleziono la riga che voglio eliminare e poi premo il bottone "remove" e si elimina.
        //Anche qui va aggiornato ovviamente il database.
        int selectedID = tableView.getSelectionModel().getSelectedIndex();
        tableView.getItems().remove(selectedID);
    }
}


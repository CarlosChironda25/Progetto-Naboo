package com.example.nabo.GUI;

import com.example.nabo.Utente;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class provaController {
    @FXML
    private Button search;
    @FXML
    private Button save;
    @FXML
    private Label error;
    @FXML
    private Label ok;
    @FXML
    private TextField inputSearchUser;
    @FXML
    private CheckBox adminbox;

    private String path = "C:\\Users\\feder\\IdeaProjects\\Progetto-Naboo\\src\\main\\resources\\com\\example\\nabo\\DataBase\\Dati.json";
    public List<Utente> readFile(String path) throws FileNotFoundException {
        Gson gson = new Gson();
        JsonReader reader = new JsonReader(new FileReader(path));
        return gson.fromJson(reader, new TypeToken<List<Utente>>(){}.getType());
    }
    public void writeFile(List<Utente> utente, String path) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<Utente> listUser = new ArrayList<>(utente);
        String jsonString = gson.toJson(listUser);
        FileWriter fw = new FileWriter(path);
        fw.write(jsonString);
        fw.close();
    }

    @FXML
    public void searchUser(ActionEvent event) throws FileNotFoundException {
        //parto con i campi tutti vuoti, label comprese
        error.setText("");
        ok.setText("");
        List<Utente> utente = readFile(path);
        boolean userFound = false;
        for (Utente value : utente) {
            if (value.getUsername().equals(inputSearchUser.getText())) {
                userFound = true;
                ok.setText("l'utente " + value.getUsername() + " da te cercato esiste");
                adminbox.setSelected(value.getIsAdmin());
            }
        }
        if (!userFound) {
            error.setText("L'utente che stai cercando non esiste nel database. Riprova ");
            adminbox.setSelected(false);
        }
    }
    @FXML
    public void modifyUserCredentials(ActionEvent event) throws IOException {
        ok.setText("");
        error.setText("");
        List<Utente> utente = readFile(path);
        if (inputSearchUser.getText().isEmpty()) {
            error.setText("non hai cercato nessuno");
        } else {
                for (Utente value : utente) {
                    if (value.getUsername().equals(inputSearchUser.getText())) {

                        value.setIsAdmin(adminbox.isSelected());
                        writeFile(utente, path);
                    }
                }
                ok.setText("utente " + inputSearchUser.getText() + " modificato senza problemi");
                inputSearchUser.setText("");
                adminbox.setSelected(false);

        }
    }


}

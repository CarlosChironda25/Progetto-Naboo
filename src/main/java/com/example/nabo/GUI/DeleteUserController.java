package com.example.nabo.GUI;

import com.example.nabo.Main;
import com.example.nabo.Utente;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
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


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DeleteUserController {
    @FXML
    private Stage stage;
    @FXML
    private Scene scene;
    @FXML
    private Parent root;
    @FXML
    public Label labelError;
    @FXML
    public Label labelUsername;
    @FXML
    public Label labelProperRemoval;
    @FXML
    public TextField inputUsername;
    private static String path = "C:\\Users\\feder\\IdeaProjects\\Progetto-Naboo\\src\\main\\resources\\com\\example\\nabo\\DataBase\\Dati.json";
    public static List<Utente> readFile(String path) throws FileNotFoundException {
        Gson gson = new Gson();
        JsonReader reader = new JsonReader(new FileReader(path));
        List<Utente> utenti = gson.fromJson(reader, new TypeToken<List<Utente>>(){}.getType());
        return utenti;
    }

    public static void writeFile(List<Utente> utente, String path) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<Utente> listUser = new ArrayList<>();
        listUser.addAll(utente);
        String jsonString = gson.toJson(listUser);
        FileWriter fw = new FileWriter(path);
        fw.write(jsonString);
        fw.close();
    }

    @FXML
    public void search(ActionEvent event) throws FileNotFoundException {
        labelError.setText("");
        List<Utente> utente = readFile(path);
        boolean userFound = false;
        for(int i = 0; i < utente.size(); i++){
            if(utente.get(i).getUsername().equals(inputUsername.getText())){
                userFound = true;
                labelUsername.setText(utente.get(i).getUsername());
            }
        }
        if(!userFound){
            labelError.setText("Utente non esistente");
            labelUsername.setText("");
        }
    }

    @FXML
    public void deleteUser(ActionEvent event) throws IOException {
        List<Utente> utente = readFile(path);
        if(labelUsername.getText().isEmpty()){
            labelError.setText("Attenzione, sembra che tu non abbia cercato nessun utente");
        }else{
            labelError.setText("");
            for(int i = 0; i < utente.size(); i++){
                if(utente.get(i).getUsername().equals(inputUsername.getText())){
                    utente.remove(i);
                    writeFile(utente, path);
                }
            }
            labelProperRemoval.setText("l'utente " + inputUsername.getText() + "da te cercato, è stato rimosso correttamente");
            inputUsername.setText("");
            labelUsername.setText("");
        }
    }
    @FXML
    public void goBackHomePage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("grafica/HomepageForm.fxml")));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}

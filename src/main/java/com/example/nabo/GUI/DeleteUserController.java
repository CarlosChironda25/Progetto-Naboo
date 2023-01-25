package com.example.nabo.GUI;

import com.example.nabo.Main;
import com.example.nabo.Utente;
import com.google.common.collect.Lists;
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
import java.util.ListIterator;
import java.util.Objects;

public class DeleteUserController {
    @FXML
    private Button searchUser;
    @FXML
    private Button deleteUser;
    @FXML
    private Button goBackHome;
    @FXML
    private Label labelError;
    @FXML
    private Label labelAllRight;
    @FXML
    private TextField inputUsername;
    private static String path = "C:\\Users\\feder\\IdeaProjects\\Progetto-Naboo\\src\\main\\resources\\com\\example\\nabo\\DataBase\\Dati.json";
    public static List<Utente> readFile(String path) throws FileNotFoundException {
        Gson gson = new Gson();
        JsonReader reader = new JsonReader(new FileReader(path));
        return gson.fromJson(reader, new TypeToken<List<Utente>>(){}.getType());
    }

    public static void writeFile(List<Utente> utente, String path) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<Utente> listUser = new ArrayList<>(utente);
        String jsonString = gson.toJson(listUser);
        FileWriter fw = new FileWriter(path);
        fw.write(jsonString);
        fw.close();
    }

    @FXML
    private void search(ActionEvent event) throws FileNotFoundException {
        labelError.setText("");
        List<Utente> utente = readFile(path);
        boolean userFound = false;
        for (Utente value : utente) {
            if (value.getUsername().equals(inputUsername.getText())) {
                userFound = true;
                labelAllRight.setText("l'utente " + value.getUsername() + " da te cercato esiste.");
            }
        }
        if(!userFound){
            labelError.setText("Utente non esistente");
            labelAllRight.setText("");
            inputUsername.setText("");
        }
    }
    /*
    @FXML
    private void deleteUser(ActionEvent event) throws IOException {
        labelAllRight.setText("");
        labelError.setText("");
        List<Utente> utente = readFile(path);
        ListIterator<Utente> ut = utente.listIterator();
        if(inputUsername.getText().isEmpty()){
            labelError.setText("Attenzione, sembra che tu non abbia cercato nessun utente");
        }else {
            labelError.setText("");
            while (ut.hasNext()) {
               String valueName = ut.next().getUsername();
               if(valueName.equals(inputUsername.getText())) {
                   ut.remove();
                   utente = Lists.newArrayList(ut);
                   writeFile(utente, path);
               }
            }
        }
    }
    */

    @FXML
    private void deleteUser(ActionEvent event) throws IOException {
        labelAllRight.setText("");
        labelError.setText("");
        List<Utente> utente = readFile(path);
        if(inputUsername.getText().isEmpty()){
            labelError.setText("Attenzione, sembra che tu non abbia cercato nessun utente");
        }else{
            labelError.setText("");
            try {
                for (Utente value : utente) {
                    if (value.getUsername().equals(inputUsername.getText())) {
                        utente.remove(value);
                        writeFile(utente, path);
                        break;
                    }
                }
            }catch(Exception e){
                e.printStackTrace();
            }
            labelAllRight.setText("l'utente " + inputUsername.getText() + " da te cercato, è stato rimosso correttamente");
            inputUsername.setText("");
            labelError.setText("");
        }
    }

    @FXML
    public void goBackHomePage(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("grafica/HomepageForm.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Homepage di Naboo");
        stage.setScene(scene);
        stage.show();
    }

}

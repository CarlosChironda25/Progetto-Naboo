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

public class UserRegistrationController {
    @FXML
    private Label labelErrorUsername;
    @FXML
    private Label labelErrorPassword;
    @FXML
    private TextField inputUsername;
    @FXML
    private TextField inputPassword;
    @FXML
    private TextField inputPassword2;
    @FXML
    private Label labelRegistrazione;
    @FXML
    private CheckBox boxUtente;
    @FXML
    private Button btnConfirm;


    private static String path = "C:\\Users\\feder\\IdeaProjects\\Progetto-Naboo\\src\\main\\resources\\com\\example\\nabo\\Commenti.txt";
    public static List<Utente> readFile(String path) throws FileNotFoundException {
        Gson gson = new Gson();
        JsonReader reader = new JsonReader(new FileReader(path));
        List<Utente> user = gson.fromJson(reader, new TypeToken<List<Utente>>(){}.getType());
        return user;
    }
    public static void writeFile(String username, String password, boolean isAdmin) throws IOException {
        Utente user = new Utente(username, password, isAdmin);
        List<Utente> users = readFile(path);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<Utente> listUser = new ArrayList<>();
        listUser.addAll(users);
        listUser.add(user);
        String jsonString = gson.toJson(listUser);
        FileWriter fw = new FileWriter(path);
        fw.write(jsonString);
        fw.close();
    }
    public boolean somethingIsEmpty() {
        labelErrorUsername.setText("");
        labelErrorPassword.setText("");
        boolean control = true;
        if (inputUsername.getText().isEmpty()) {
            labelErrorUsername.setText("Username non inserito");
            control = false;
        }
        if (inputPassword.getText().isEmpty() || inputPassword2.getText().isEmpty()){
            labelErrorPassword.setText("Non hai inserito una delle password o entrambe!");
            control = false;
        } else if(!inputPassword.getText().equals(inputPassword2.getText())){
            labelErrorPassword.setText("le password sono diverse!");
            control = false;
        }

        return control;
    }


    @FXML
    public void RegistrationOperation(ActionEvent event) throws IOException{
        if(somethingIsEmpty()){
            writeFile(inputUsername.getText(), inputPassword.getText(), boxUtente.isSelected());
            labelRegistrazione.setText("registrazione avvenuta con successo!!");

            inputUsername.setText("");
            inputPassword.setText("");
            inputPassword2.setText("");
            boxUtente.setSelected(true);
        }
    }


}


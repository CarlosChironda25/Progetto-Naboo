package com.example.nabo.GUI;

import com.example.nabo.Utente;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class LoginController {
    @FXML
    public Label labelError;
    @FXML
    public TextField inputUsername;
    @FXML
    public TextField inputPassword;
    @FXML
    public Button btnLogin;
    private static String path = "C:\\Users\\feder\\Downloads\\progetto naboo 23 dicembre\\Progetto-Naboo-main\\src\\main\\resources\\com\\example\\nabo\\DataBase\\Dati.json";
    public static List<Utente> readFile(String path) throws FileNotFoundException {
        Gson gson = new Gson();
        JsonReader reader = new JsonReader(new FileReader(path));
        List<Utente> user = gson.fromJson(reader, new TypeToken<List<Utente>>(){}.getType());
        return user;
    }
    public void login(ActionEvent event) throws IOException {
        List<Utente> user = readFile(path);

        boolean registeredUser = false;
        boolean isAdmin = false;

        for(int i = 0; i < user.size(); i++){
            if(user.get(i).getUsername().equals(inputUsername.getText()) && user.get(i).getPassword().equals(inputPassword.getText())){
                registeredUser = true;
                if(user.get(i).getIsAdmin() == true){
                    isAdmin = true;
                }
            }
        }

        if(registeredUser && isAdmin){
            Parent root = FXMLLoader.load(getClass().getResource("/grafica/schermataPrincipale.fxml"));
            Stage window = (Stage) btnLogin.getScene().getWindow();
            window.setScene(new Scene(root));
            window.setTitle("Welcome to NABOO");
        }else if(registeredUser){
            labelError.setText("Non sei un amministratore quindi non puoi accedere qui");
        }else{
            labelError.setText("Username o password non corrette, riprova. ");
        }
    }
}


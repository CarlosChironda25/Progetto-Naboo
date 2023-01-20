package com.example.nabo.GUI;

import com.example.nabo.Main;
import com.example.nabo.Utente;
import com.google.gson.Gson;
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
import java.io.IOException;
import java.util.List;
import java.util.Objects;


public class LoginController {
    @FXML
    public Button btnLogin;
    @FXML
    private Label labelError;
    @FXML
    private TextField inputUsername;
    @FXML
    private TextField inputPassword;
    private static String path = "C:\\Users\\feder\\IdeaProjects\\Progetto-Naboo\\src\\main\\resources\\com\\example\\nabo\\DataBase\\Dati.json";
    public static List<Utente> readFile(String path) throws FileNotFoundException {
        Gson gson = new Gson();
        JsonReader reader = new JsonReader(new FileReader(path));
        return gson.fromJson(reader, new TypeToken<List<Utente>>(){}.getType());
    }
    @FXML
    public void login(ActionEvent event) throws IOException {
        List<Utente> user = readFile(path);

        boolean registeredUser = false;
        boolean isAdmin = false;

        for (Utente utente : user) {
            if (utente.getUsername().equals(inputUsername.getText()) && utente.getPassword().equals(inputPassword.getText())) {
                registeredUser = true;
                if (utente.getIsAdmin()) {
                    isAdmin = true;
                }
            }
        }

        if(registeredUser && isAdmin){
            Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("grafica/HomepageForm.fxml")));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setTitle("Homepage di Naboo");
            stage.setScene(scene);
            stage.show();
        }else if(registeredUser){
            labelError.setText("Non sei un amministratore quindi non puoi accedere qui");
            System.out.println("non è amministratore quindi deve uscire");
        }else{
            labelError.setText("Username o password non corrette, riprova. ");
            System.out.println("Credenziali errate.");
        }
    }
}


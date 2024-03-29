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
import javafx.scene.control.CheckBox;
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

public class UserRegistrationController {
    @FXML
    public Button goBackHome;
    @FXML
    public Button confirm;
    @FXML
    private Label labelError;
    @FXML
    private TextField inputUsername;
    @FXML
    private TextField inputPassword;
    @FXML
    private TextField inputPassword2;
    @FXML
    private Label labelAllRight;
    @FXML
    public CheckBox administratorBox;


    //private static String path = "C:\\Users\\feder\\IdeaProjects\\Progetto-Naboo\\src\\main\\resources\\com\\example\\nabo\\DataBase\\Dati.json";
    private static String path = "main/resources/com/example/nabo/DataBase/Dati.json";

    public static List<Utente> readFile(String path) throws FileNotFoundException {
        Gson gson = new Gson();
        JsonReader reader = new JsonReader(new FileReader(path));
        return gson.fromJson(reader, new TypeToken<List<Utente>>(){}.getType());
    }
    public static void writeFile(String username, String password, boolean isAdmin) throws IOException {
        Utente user = new Utente(username, password, isAdmin);
        List<Utente> users = readFile(path);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<Utente> listUser = new ArrayList<>(users);
        listUser.add(user);
        String jsonString = gson.toJson(listUser);
        FileWriter fw = new FileWriter(path);
        fw.write(jsonString);
        fw.close();
    }
    /*
    Controllo che tutti i campi siano riempiti e che le password corrispondano.
     */
    public boolean checkProblems() {
        labelError.setText("");
        boolean control = true;

        if (inputUsername.getText().isEmpty() || inputPassword.getText().isEmpty() || inputPassword2.getText().isEmpty()) {
            labelError.setText("Tutti i campi sono obbligatori!");
            control = false;
        } else if (!inputPassword.getText().equals(inputPassword2.getText())) {
            labelError.setText("le password sono diverse!");
            control = false;
        }

        return control;
    }

    /*
    Controllo se l'utente che voglio inserire esiste già nel database dati.json
     */
    public boolean checkExistence() throws FileNotFoundException {
        labelError.setText("");
        labelAllRight.setText("");
        List<Utente> utente = readFile(path);
        boolean userFound = false;
        for (Utente value : utente) {
            if (value.getUsername().equals(inputUsername.getText())) {
                userFound = true;
                labelError.setText("l'utente esiste già");
                inputUsername.setText("");
                inputPassword.setText("");
                inputPassword2.setText("");
            }
        }
        return userFound;
    }


    @FXML
    public void RegistrationOperation(ActionEvent event) throws IOException{
        labelError.setText("");
        labelAllRight.setText("");
        if(checkProblems() && !checkExistence()){
            writeFile(inputUsername.getText(), inputPassword.getText(), administratorBox.isSelected());
            labelAllRight.setText("registrazione avvenuta con successo!!");
            inputUsername.setText("");
            inputPassword.setText("");
            inputPassword2.setText("");
            administratorBox.setSelected(false);
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


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

public class UserModifyController {
    @FXML
    public Button saveChanges;
    @FXML
    public Button searchUser;
    @FXML
    public Button goBackHome;
    @FXML
    public Label labelInfoSearchedUser;
    @FXML
    public Label labelErrorEmptySpaces;
    @FXML
    public Label labelErrorPasswordMismatching;
    @FXML
    public Label labelSave;
    @FXML
    public TextField inputSearchedUser;
    @FXML
    public TextField inputUsername;
    @FXML
    public TextField inputPassword;
    @FXML
    public TextField inputPassword2;
    @FXML
    public CheckBox administratorBox;


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
    public void search(ActionEvent event) throws FileNotFoundException {
        //parto con i campi tutti vuoti, label comprese
        labelInfoSearchedUser.setText("");
        labelErrorEmptySpaces.setText("");
        labelErrorPasswordMismatching.setText("");
        labelSave.setText("");

        List<Utente> utente = readFile(path);
        boolean userFound = false;
        for (Utente value : utente) {
            if (value.getUsername().equals(inputSearchedUser.getText())) {
                userFound = true;
                labelInfoSearchedUser.setText("l'utente " + value.getUsername() + " da te cercato esiste");
                inputUsername.setText(value.getUsername());
                inputPassword.setText(value.getPassword());
                inputPassword2.setText(value.getPassword());
                administratorBox.setSelected(value.getIsAdmin());
            }
            if (!userFound) {
                labelInfoSearchedUser.setText("L'utente che stai cercando non esiste nel database. Riprova ");
                inputUsername.setText("");
                inputPassword.setText("");
                inputPassword2.setText("");
                administratorBox.setSelected(false);
            }
        }
    }
    @FXML
    public void modifyUserCredentials(ActionEvent event) throws IOException {
        labelInfoSearchedUser.setText("");
        List<Utente> utente = readFile(path);
        if (inputSearchedUser.getText().isEmpty()) {
            labelInfoSearchedUser.setText("non hai cercato nessuno");
        } else {
            if (modifyCredentials()) {

                for (Utente value : utente) {
                    if (value.getUsername().equals(inputSearchedUser.getText())) {
                        value.setUsername(inputUsername.getText());
                        value.setPassword(inputPassword.getText());
                        value.setIsAdmin(administratorBox.isSelected());
                        writeFile(utente, path);
                    }
                }
                labelSave.setText("utente " + inputUsername.getText() + " modificato senza problemi");
                System.out.println("Apportate correttamente le modifiche");
                inputSearchedUser.setText("");
                inputUsername.setText("");
                inputPassword.setText("");
                inputPassword2.setText("");
                administratorBox.setSelected(false);
            }
        }
    }

    private boolean modifyCredentials(){
        labelInfoSearchedUser.setText("");
        labelErrorEmptySpaces.setText("");
        labelErrorPasswordMismatching.setText("");
        labelSave.setText("");

        boolean control = true;

        if (inputUsername.getText().isEmpty() || inputPassword.getText().isEmpty() || inputPassword2.getText().isEmpty()) {
            labelErrorEmptySpaces.setText("Tutti i campi sono obbligatori!");
            System.out.println("Non ha inserito alcuni campi");
            control = false;
        } else if (!inputPassword.getText().equals(inputPassword2.getText())) {
            labelErrorPasswordMismatching.setText("le password sono diverse!");
            System.out.println("le password sono diverse");
            control = false;
        }
        return control;
    }
    @FXML
    public void goBackHomePage(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("grafica/HomepageForm.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        System.out.println("ritorna in homepage");
        stage.setTitle("Homepage di Naboo");
        stage.setScene(scene);
        stage.show();
    }

}

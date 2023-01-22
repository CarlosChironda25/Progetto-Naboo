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
    public Label labelError;
    @FXML
    public Label labelAllRight;
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

    private boolean modifyCredentials(){
        labelError.setText("");
        labelAllRight.setText("");

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
    public boolean checkExistence() throws FileNotFoundException {
        List<Utente> utente = readFile(path);
        boolean userFound = false;
        for (Utente value : utente) {
            if (value.getUsername().equals(inputUsername.getText())) {
                userFound = true;
                labelError.setText("l'utente esiste già");
                inputUsername.setText(value.getUsername());
                inputPassword.setText(value.getPassword());
                inputPassword2.setText(value.getPassword());
                administratorBox.setSelected(value.getIsAdmin());
            }
        }
        if (!userFound) {
            labelAllRight.setText("L'utente che stai cercando non esiste nel database. Quindi si può inserire");
            inputUsername.setText("");
            inputPassword.setText("");
            inputPassword2.setText("");
            administratorBox.setSelected(false);
        }
        return userFound;
    }
    @FXML
    public void search(ActionEvent event) throws FileNotFoundException {
        //parto con i campi tutti vuoti, label comprese
        labelError.setText("");
        labelAllRight.setText("");

        List<Utente> utente = readFile(path);
        boolean userFound = false;
        for (Utente value : utente) {
            if (value.getUsername().equals(inputSearchedUser.getText())) {
                userFound = true;
                labelAllRight.setText("l'utente " + value.getUsername() + " da te cercato esiste");
                inputUsername.setText(value.getUsername());
                inputPassword.setText(value.getPassword());
                inputPassword2.setText(value.getPassword());
                administratorBox.setSelected(value.getIsAdmin());
            }
        }
        if (!userFound) {
            labelError.setText("L'utente che stai cercando non esiste nel database. Riprova ");
            inputUsername.setText("");
            inputPassword.setText("");
            inputPassword2.setText("");
            administratorBox.setSelected(false);
        }
    }

    @FXML
    public void modifyUserCredentials(ActionEvent event) throws IOException {
        labelAllRight.setText("");
        labelError.setText("");
        List<Utente> utente = readFile(path);
        if (inputSearchedUser.getText().isEmpty()) {
            labelError.setText("non hai cercato nessuno");
        } else {
            if (modifyCredentials() && !checkExistence()) {

                for (Utente value : utente) {
                    if (value.getUsername().equals(inputSearchedUser.getText())) {
                        value.setUsername(inputUsername.getText());
                        value.setPassword(inputPassword.getText());
                        value.setIsAdmin(administratorBox.isSelected());
                        writeFile(utente, path);
                    }
                }
                labelAllRight.setText("utente " + inputUsername.getText() + " modificato senza problemi");
                inputSearchedUser.setText("");
                inputUsername.setText("");
                inputPassword.setText("");
                inputPassword2.setText("");
                administratorBox.setSelected(false);
            }
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

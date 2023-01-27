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
    public TextField inputPassword;
    @FXML
    public TextField inputPassword2;
    @FXML
    public CheckBox administratorBox;


    //private String path = "C:\\Users\\feder\\IdeaProjects\\Progetto-Naboo\\src\\main\\resources\\com\\example\\nabo\\DataBase\\Dati.json";
    private static String path = "main/resources/com/example/nabo/DataBase/Dati.json";
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

    public boolean checkPassword(){
        labelError.setText("");
        boolean control = true;
        if (inputPassword.getText().isEmpty() || inputPassword2.getText().isEmpty()) {
            labelError.setText("Tutti i campi sono obbligatori!");
            control = false;
        } else if (!inputPassword.getText().equals(inputPassword2.getText())) {
            labelError.setText("le password sono diverse!");
            control = false;
        }
        return control;
    }
    @FXML
    public void searchUser(ActionEvent event) throws FileNotFoundException {
        labelError.setText("");
        labelAllRight.setText("");
        List<Utente> utente = readFile(path);
        boolean userFound = false;
        for(Utente value : utente){
            if(value.getUsername().equals(inputSearchedUser.getText())){
                userFound = true;
                labelAllRight.setText("l'utente " + inputSearchedUser.getText() + " da te cercato esiste");
                inputPassword.setText(value.getPassword());
                inputPassword2.setText(value.getPassword());
                administratorBox.setSelected(value.getIsAdmin());
            }
        }
        if(!userFound){
            labelError.setText("L'utente che stai cercando non esiste nel database. Riprova");
            administratorBox.setSelected(false);
        }
    }

    @FXML
    public void modifyUserCredentials(ActionEvent event) throws IOException {
        labelAllRight.setText("");
        labelError.setText("");
        List<Utente> utente = readFile(path);
        if(inputSearchedUser.getText().isEmpty()){
            labelError.setText("non hai cercato nessun utente");
        }else{
            if(checkPassword()) {
                for (Utente value : utente) {
                    if (value.getUsername().equals(inputSearchedUser.getText())) {
                        value.setPassword(inputPassword.getText());
                        value.setIsAdmin(administratorBox.isSelected());
                        writeFile(utente, path);
                    }
                }
                labelAllRight.setText("l'utente " + inputSearchedUser.getText() + " è stato modificato correttamente");
                inputSearchedUser.setText("");
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

package com.example.nabo.GUI;

import com.example.nabo.Utente;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.w3c.dom.Text;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserModifyController {
    @FXML
    public Label labelErrorSearchedUser;
    @FXML
    public Label labelErrorUser;
    @FXML
    public Label labelErrorPassword;
    @FXML
    public Label labelSave;
    @FXML
    public TextField txtSearchedUser;
    @FXML
    public TextField txtUsername;
    @FXML
    public TextField txtPassword;
    @FXML
    public TextField txtPassword2;
    @FXML
    public CheckBox boxUtente;


    private static String path = "C:\\Users\\feder\\IdeaProjects\\Progetto-Naboo\\src\\main\\resources\\com\\example\\nabo\\DataBase\\Dati.json";
    public static List<Utente> readFile(String path) throws FileNotFoundException {
        Gson gson = new Gson();
        JsonReader reader = new JsonReader(new FileReader(path));
        List<Utente> user = gson.fromJson(reader, new TypeToken<List<Utente>>(){}.getType());
        return user;
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
        //parto con i campi tutti vuoti, label comprese
        labelErrorSearchedUser.setText("");
        labelErrorUser.setText("");
        labelErrorPassword.setText("");
        labelSave.setText("");

        List<Utente> utente = readFile(path);
        boolean userFound = false;
        for(int i = 0; i < utente.size(); i++){
            if(utente.get(i).getUsername().equals(txtSearchedUser.getText())){
                userFound = true;
                txtUsername.setText(utente.get(i).getUsername());//modifico username
                txtPassword.setText(utente.get(i).getPassword());
                txtPassword2.setText(utente.get(i).getPassword());
                boxUtente.setSelected(utente.get(i).getIsAdmin());//modifico il permesso
            }
            if(!userFound){
               labelErrorSearchedUser.setText("L'utente che stai cercando non esiste nel database. Riprova ");
               txtUsername.setText("");
               txtPassword.setText("");
               txtPassword2.setText("");
               boxUtente.setSelected(true);
            }
        }
    }
    @FXML
    public void save(ActionEvent event) throws IOException {
        labelErrorSearchedUser.setText("");
        List<Utente> utente = readFile(path);
        if (txtSearchedUser.getText().isEmpty()) {
            labelErrorSearchedUser.setText("non hai cercato nessuno");
        } else {
            if (checkModify()) {
                for (int i = 0; i < utente.size(); i++) {
                    if (utente.get(i).getUsername().equals(txtSearchedUser.getText())) {

                        utente.get(i).setUsername(txtUsername.getText());
                        utente.get(i).setPassword(txtPassword.getText());
                        utente.get(i).setIsAdmin(boxUtente.isSelected());
                        writeFile(utente, path);
                    }
                }
                labelSave.setText("utente " + txtUsername.getText() + "modificato senza prblemi");
                //Dopo aver salvato svuoto tutti i campi
                txtSearchedUser.setText("");
                txtUsername.setText("");
                txtPassword.setText("");
                txtPassword2.setText("");
                boxUtente.setSelected(true); //l'utente è di default utente normale
            }
        }
    }

    private boolean checkModify(){
        labelErrorSearchedUser.setText("");
        labelErrorUser.setText("");
        labelErrorPassword.setText("");
        labelSave.setText("");

        boolean control = true;

        if(txtUsername.getText().isEmpty()){
            labelErrorUser.setText("Cambia il nome");
            control = false;
        }
        if(txtPassword.getText().isEmpty()){
            labelErrorPassword.setText("Cambia la password");
            control = false;
        }
        if(txtUsername.getText().isEmpty() || txtPassword2.getText().isEmpty()){
            labelErrorUser.setText("Non hai inserito la password e la password di conferma");
            control = false;
        }else{
            if(!txtPassword.getText().equals(txtPassword2.getText())){
                labelErrorPassword.setText("le password non coincidono");
                control = false;
            }
        }
        return control;
    }


}

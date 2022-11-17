package com.example.nabo;

/*import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}*/

import com.almasb.fxgl.app.MainWindow;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HelloController {
    private  String PathDati = "Dati.json";
    @FXML
    private Button Login;
    private Button NuovoUtente;

    @FXML
    private Label Message;

    @FXML
    private PasswordField Password;

    @FXML
    private TextField Username;
    private Stage stage;
    private  Scene scene;
    private Parent root;
    @FXML


        // metodo per il controll delle credenziale del utente.
    void Login(ActionEvent event) throws IOException {
        //lettura del file
       /*      Gson gson = new Gson();
            JsonReader leggi = new JsonReader(new FileReader(PathDati));
             List<Utente> Utenti = gson.fromJson(leggi, (new TypeToken<List<Utente>>() {}).getType());
            for( Utente i:Utenti)
       control delle credenziale
                if( Password.getText().equals(i.getPassword())&& Username.getText()!=i.Username){
                     Message.setText("L'username inserito é sbagliato. !!RIPROVA");

                if( Password.getText()!=i.getPassword() && Username.getText()==i.Username){
                    Message.setText("La Password inserita é sbagliato. !!RIPROVA");}else
                if( Password.getText()==i.getPassword() && Username.getText()==i.Username ){
                    Message.setText("Scusa non Sei un ammistrattore non poi entrare.");} else*/
        if( Password.getText().equals("c11")  && Username.getText().equals("c11")){

            root =FXMLLoader.load(getClass().getResource("SchermataPrincipale.fxml"));
            stage= (Stage)((Node)event.getSource()).getScene().getWindow();
            scene=new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else
            Message.setText(" Username o Password non corretti.\nRiprova!! ");

    }

}

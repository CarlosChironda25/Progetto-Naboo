package com.example.nabo;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private Label lblMessaggio;

    @FXML
    private TextField txtUsername;

    @FXML
    private TextField txtPassword;

    public void Login(ActionEvent event) throws IOException {

        if(txtUsername.getText().equals("U") && txtPassword.getText().equals("P")) {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("SchermataPrincipale.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("NABOO");
            stage.setScene(scene);
            stage.show();
        } else
            lblMessaggio.setText("Username e Password non corretti");

    }
}
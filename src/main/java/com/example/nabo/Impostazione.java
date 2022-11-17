package com.example.nabo;

import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class Impostazione {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    void Indietro(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("SchermataPrincipale.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
}

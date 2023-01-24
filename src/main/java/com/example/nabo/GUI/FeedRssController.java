package com.example.nabo.GUI;

import com.example.nabo.GestoreNotizia;
import com.example.nabo.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class FeedRssController {
    @FXML
    public Button addRss;
    @FXML
    public Button deleteRss;
    @FXML
    public Button goBackHome;
    @FXML
    public ListView<String> rssFeedView;
    @FXML
    public TextField inputRss;
    @FXML
    public Button caricaFeed;

    GestoreNotizia gestore;
    public FeedRssController() throws IOException {
        gestore = GestoreNotizia.getInstance();
        gestore.caricaNotizie();

    }
    private void setDisabledItems(boolean b){
        addRss.setDisable(b);
        deleteRss.setDisable(b);
        inputRss.setDisable(b);
        rssFeedView.setDisable(b);
        caricaFeed.setDisable(!b);
    }

    @FXML
    public void caricaFeed(ActionEvent event){
        rssFeedView.getItems().clear();
        for (String str : gestore.sources) {
            rssFeedView.getItems().add(str);
        }
        setDisabledItems(false);
    }

    @FXML
    public void addRss(ActionEvent event) throws IOException {
        rssFeedView.getItems().clear();
        for (String str : gestore.sources)
            rssFeedView.getItems().add(str);
        if (!inputRss.getText().isEmpty()) {
            rssFeedView.getItems().add(inputRss.getText());
            gestore.sources.add(inputRss.getText());

            gestore.caricaNotizie();
            inputRss.setText("");
        }
    }
    @FXML
    public void deleteRss(ActionEvent event) throws IOException {
        int selectedID = rssFeedView.getSelectionModel().getSelectedIndex();
        rssFeedView.getItems().remove(selectedID);
        gestore.sources.remove(selectedID);
        gestore.caricaNotizie();
        inputRss.setText("");
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
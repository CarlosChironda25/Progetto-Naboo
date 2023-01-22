package com.example.nabo.GUI;

import com.example.nabo.GestoreNotizia;
import com.example.nabo.Main;
import javafx.collections.ObservableList;
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
import java.net.MalformedURLException;
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

    //GestoreNotizia gestore = new GestoreNotizia();
    //GestoreNotizia gestore = GestoreNotizia.getInstance();


    public FeedRssController() throws MalformedURLException {
        /*
        gestore.sources.add("a");
        gestore.sources.add("b");
        for (String str : gestore.sources)
            rssFeedView.getItems().add(str);
    */
    }



    @FXML
    public void addRss(ActionEvent event){
        rssFeedView.getItems().add(inputRss.getText());
       // gestore.sources.add(inputRss.getText());
        inputRss.setText("");
    }
    @FXML
    public void deleteRss(ActionEvent event){
        int selectedID = rssFeedView.getSelectionModel().getSelectedIndex();
        rssFeedView.getItems().remove(selectedID);
        //gestore.sources.remove(selectedID);
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
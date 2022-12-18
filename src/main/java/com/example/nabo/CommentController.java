package com.example.nabo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CommentController implements Initializable {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    @FXML
    private TableView<Comment_Vote> commentTable;
    @FXML
    private TableColumn<Comment_Vote, String> newsColumn;

    @FXML
    private TableColumn<Comment_Vote, String> releasedCommentColumn;

    @FXML
    private TextField notiziaField;
    @FXML
    private TextField commentoField;

    @FXML
    private Button deleteBtn;

    ObservableList<Voti_Commenti> observableCommentList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        newsColumn.setCellValueFactory(new PropertyValueFactory<Comment_Vote, String>("news"));
        releasedCommentColumn.setCellValueFactory(new PropertyValueFactory<Comment_Vote, String>("released comment"));
    }

    @FXML
    void removeComment(ActionEvent event){
        //seleziono la riga che voglio eliminare e poi premo il bottone "remove" e si elimina.
        //Anche qui va aggiornato ovviamente il database.
        int selectedID = commentTable.getSelectionModel().getSelectedIndex();
        commentTable.getItems().remove(selectedID);
    }

}

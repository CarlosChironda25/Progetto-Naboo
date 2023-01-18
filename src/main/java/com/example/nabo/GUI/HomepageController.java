package com.example.nabo.GUI;

import com.example.nabo.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class HomepageController{
    @FXML
    private Stage stage;
    @FXML
    private Scene scene;
    @FXML
    private Parent root;


   @FXML
   public void addUser(ActionEvent event) throws IOException {

           root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("grafica/UserRegistrationForm.fxml")));
           stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
           scene = new Scene(root);
           stage.setScene(scene);
           stage.show();
       }
    @FXML
    public void modifyUser(ActionEvent event) throws IOException {

        root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("grafica/UserModifyForm.fxml")));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void deleteUser(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("grafica/DeletionUserForm.fxml")));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void addNews(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("grafica/NewsAddForm.fxml")));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void deleteNews(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("grafica/DeletionNewsForm.fxml")));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void deleteComment(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("grafica/deleteCommentForm.fxml")));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void logout(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("grafica/LogoutForm.fxml")));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    /*
    @FXML
    void goToTelegram(ActionEvent event) {

    }
*/

}

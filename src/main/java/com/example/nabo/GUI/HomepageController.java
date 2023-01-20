package com.example.nabo.GUI;

import com.example.nabo.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
    public Button addUser;
    @FXML
    public Button modifyUser;
    @FXML
    public Button deleteUser;
    @FXML
    public Button deleteComment;
    @FXML
    public Button settings;
    @FXML
    public Button deleteNews;
    @FXML
    public Button deleteGrade;


   @FXML
   public void addUser(ActionEvent event) {
       try {
           root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("grafica/UserRegistrationForm.fxml")));
           stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
           scene = new Scene(root);
           stage.setTitle("aggiungi un utente");
           stage.setScene(scene);
           stage.show();
           System.out.println("accesso riuscito in aggiungi utente");
       } catch (Exception e) {
           e.printStackTrace();
           System.out.println("accesso non riuscito in aggiungi utente");
       }
   }
    @FXML
    public void modifyUser(ActionEvent event){
        try {
            root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("grafica/UserModifyForm.fxml")));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setTitle("Modifica utente");
            stage.setScene(scene);
            stage.show();
            System.out.println("accesso riuscito in modifica utente");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("accesso non riuscito in modifica utente");
        }
    }
    @FXML
    public void deleteUser(ActionEvent event){
        try {
            root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("grafica/DeletionUserForm.fxml")));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setTitle("Elimina utente");
            stage.setScene(scene);
            stage.show();
            System.out.println("accesso riuscito in elimina utente");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("accesso non riuscito in elimina utente");
        }
    }

    @FXML
    public void deleteNews(ActionEvent event) {
        try {
            root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("grafica/DeletionNewsForm.fxml")));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setTitle("Elimina notizia");
            stage.setScene(scene);
            stage.show();
            System.out.println("accesso riuscito in elimina notizia");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("accesso non riuscito in elimina notizia");
        }
    }
    @FXML
    public void deleteComment(ActionEvent event){
       try{
            root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("grafica/DeletionCommentForm.fxml")));
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setTitle("Elimina commento rilasciato");
            stage.setScene(scene);
            stage.show();
           System.out.println("accesso riuscito in elimina commento");
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("accesso non riuscito in elimina commento");
           }
    }
    @FXML
    public void logout(ActionEvent event){
       System.out.println("Vorrà effettuare il logout l'amministratore?");
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("Stai per uscire. Tutte le tue modifiche \n sono state salvate correttamente \n in modo automatico.");
        alert.setContentText("Vuoi veramente uscire?");
        if(alert.showAndWait().get() == ButtonType.OK) {
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            System.out.println("Uscito correttamente");
            stage.close();
        }
    }
    @FXML
    public void deleteGrade(ActionEvent event){
       try{
           root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("grafica/DeletionGradeForm.fxml")));
           stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
           scene = new Scene(root);
           stage.setTitle("Impostazioni di Naboo");
           stage.setScene(scene);
           stage.show();
           System.out.println("accesso riuscito in elimina voto");
       }catch (Exception e) {
           e.printStackTrace();
           System.out.println("accesso non riuscito in elimina voto");
       }

    }

}

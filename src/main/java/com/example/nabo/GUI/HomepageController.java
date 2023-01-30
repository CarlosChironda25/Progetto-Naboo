package com.example.nabo.GUI;

import com.example.nabo.Main;
import com.example.nabo.NabooBot;
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
import org.checkerframework.checker.units.qual.A;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.BotSession;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.net.MalformedURLException;
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
    public Button deleteNews;
    @FXML
    public Button deleteGrade;
    @FXML
    public Button telegramButton;
    @FXML
    public Button logout;
    @FXML
    public Button feedManagement;

   @FXML
   public void addUser(ActionEvent event) {
       try {
           root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("grafica/UserRegistrationForm.fxml")));
           stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
           scene = new Scene(root);
           stage.setTitle("aggiungi un utente");
           stage.setScene(scene);
           stage.show();
       } catch (Exception e) {
           e.printStackTrace();
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
        } catch (Exception e) {
            e.printStackTrace();
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
        } catch (Exception e) {
            e.printStackTrace();
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
        } catch (Exception e) {
            e.printStackTrace();
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
       }catch (Exception e) {
            e.printStackTrace();
       }
    }

    @FXML
    public void telegram(ActionEvent event){
       try {
            TelegramBotsApi botApi1 = new TelegramBotsApi(DefaultBotSession.class);
            BotSession botSession = botApi1.registerBot(new NabooBot());
            NabooBot.setSession(botSession);

       } catch (TelegramApiException | MalformedURLException e) {
            e.printStackTrace();
       }
       Alert alert  = new Alert(Alert.AlertType.INFORMATION);
       alert.setTitle("avvio telegram bot");
       alert.setHeaderText("Questo pulsante serve solo per avviare il bot. \nIl bot si chiude all'interno del bot stesso. \nBisogna attendere 50 secondi dopo");
       alert.setContentText("Conferma per aver compreso l'informazione");
       if(alert.showAndWait().get() == ButtonType.OK){
           System.out.println("compresa l'informazione");
       }
    }

    @FXML
    public void logout(ActionEvent event){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("Stai per uscire. Tutte le tue modifiche \n sono state salvate correttamente \n in modo automatico.");
        alert.setContentText("Vuoi veramente uscire?");
        if(alert.showAndWait().get() == ButtonType.OK) {
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.close();
        }
    }
    @FXML
    public void deleteGrade(ActionEvent event){
       try{
           root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("grafica/DeletionGradeForm.fxml")));
           stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
           scene = new Scene(root);
           stage.setTitle("Elimina voto");
           stage.setScene(scene);
           stage.show();
       }catch (Exception e) {
           e.printStackTrace();
       }

    }
    @FXML
    public void feedManagement(ActionEvent event){
        try{
            root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("grafica/FeedRssForm.fxml")));
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setTitle("Gestione feed RSS");
            stage.setScene(scene);
            stage.show();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package com.example.nabo.GUI;

import com.example.nabo.NabooBot;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.BotSession;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.net.MalformedURLException;

public class LogoutController {
    @FXML
    public CheckBox telegramBox;

    @FXML
    public Button logoutButton;
    @FXML
    public AnchorPane scenePane;

    Stage stage;
    @FXML
    public void logout(ActionEvent event){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("Stai per uscire. Tutte le tue modifiche \n sono state salvate correttamente \n in modo automatico.");
        alert.setContentText("Vuoi veramente uscire?");
        if(alert.showAndWait().get() == ButtonType.OK) {
            stage = (Stage) scenePane.getScene().getWindow();
            System.out.println("Uscito correttamente");
            stage.close();
        }
    }

    @FXML
    public void launchTelegram(ActionEvent event){
        if(telegramBox.isSelected()){
            try { //starta il bot
                TelegramBotsApi botApi1 = new TelegramBotsApi(DefaultBotSession.class);
                BotSession botSession = botApi1.registerBot(new NabooBot());
                NabooBot.setSession(botSession);

            } catch (TelegramApiException | MalformedURLException e) {
                e.printStackTrace();
            }
        }
    }
}

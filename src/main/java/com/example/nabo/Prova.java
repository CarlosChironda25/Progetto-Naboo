package com.example.nabo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ForceReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiValidationException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Prova extends TelegramLongPollingBot {

    private static String path = "C:\\Users\\mitug\\OneDrive\\Desktop\\Progetto\\Progetto-Naboo\\src\\main\\resources\\com\\example\\nabo\\DataBase\\Dati.json";
    public static List<Utente>  Utenti;

    public static void main(String[] args) throws IOException {

    }




    @Override
    public String getBotUsername() {
        return "MituNabooBot";
    }

    @Override
    public String getBotToken() {
        return "5457602202:AAEFla9Exb6dxvQgQe_iRLvD_dxs7ls7_Ow";
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {

            String message = update.getMessage().getText();
            System.out.println(message);

            if(message.equals("/login")){
                try {
                    login(update);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            //update.
        }
    }

    private void login(Update update) throws InterruptedException {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));


        sendMessage.setText("inserisci username");
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }


}

/*System.err.println(update.getMessage().getReplyToMessage().getPoll().getTotalVoterCount());*


/ per verificare i voti
 SendPoll sp = new SendPoll();
                               sp.setChatId(new IndexMain().YummyReChat);
                               sp.setOptions(List.of("umu", "umu", "umu"));
                               sp.setQuestion("How to be umu");
                               try {
                                   new IndexMain().execute(sp);
                               } catch (TelegramApiException e) {
                                   throw new RuntimeException(e);
                               }
 */
package com.example.nabo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Prova extends TelegramLongPollingBot {



    /*public   static Scanner scan= new Scanner(System.in);
    public static void main(String[] args)  throws JsonIOException, IOException {
      /* ArrayList<String> tipo= new ArrayList<>();
       tipo.add("lettore");
       tipo.add("scrittore");
       tipo.add("abbonato");

          Utente utente= new Utente("Carlos","Carlos12",tipo);
            Gson gson = new Gson();
        String jsonParser =gson.toJson(utente);
                 System.out.println(jsonParser);


        Gson gson = new Gson();
        JsonReader leggi = new JsonReader(new FileReader("src/main/resources/com/example/nabo/Info-Notizie.json"));
        List<Notizia> Utenti = gson.fromJson(leggi, (new TypeToken<List<Notizia>>() {
        }).getType());
        for (Notizia i : Utenti) {
            System.out.println(i);
        }
    }*/

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
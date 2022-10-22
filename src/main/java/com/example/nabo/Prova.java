package com.example.nabo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Prova {
    public   static Scanner scan= new Scanner(System.in);
    public static void main(String[] args)  throws JsonIOException, IOException {
      /* ArrayList<String> tipo= new ArrayList<>();
       tipo.add("lettore");
       tipo.add("scrittore");
       tipo.add("abbonato");

          Utente utente= new Utente("Carlos","Carlos12",tipo);
            Gson gson = new Gson();
        String jsonParser =gson.toJson(utente);
                 System.out.println(jsonParser);
                 */

        Gson gson = new Gson();
        JsonReader leggi = new JsonReader(new FileReader("src/main/resources/com/example/nabo/Info-Notizie.json"));
        List<Notizia> Utenti = gson.fromJson(leggi, (new TypeToken<List<Notizia>>() {
        }).getType());
        for (Notizia i : Utenti) {
            System.out.println(i);
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
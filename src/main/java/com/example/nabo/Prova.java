package com.example.nabo;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

public class Prova extends TelegramLongPollingBot {

    static List<Notizia> notizia;

    private static final String pathNews = "C:\\Users\\mitug\\OneDrive\\Desktop\\Nuova cartella\\Progetto-Naboo\\src\\main\\resources\\com\\example\\nabo\\DataBase\\Info-Notizie.json";

    public static Scanner scan= new Scanner(System.in);
    public static void main(String[] args)  throws JsonIOException, IOException {
        JsonReader read = new JsonReader(new FileReader(pathNews));
        Gson gson = new Gson();

        Date nowdate = new Date();
        long nowms = nowdate.getTime();
        long differencems = 24 * 60 * 60 * 3000;
        long thenms = nowms - differencems;
        Date thendate = new Date(thenms);
        thendate.setSeconds(0);
        thendate.setHours(0);
        thendate.setMinutes(0);

        System.out.println("QUESTO : " + thendate);
        System.out.println("QUELLO : " + thenms);

        notizia = gson.fromJson(read, (new TypeToken<List<Notizia>>() {
        }).getType());


        int contatore = 0;

        for (Notizia n : notizia) {

            if (contatore == 100 || (n == null )) {
                break;
            }
            if(n.getData().getTime() >= thendate.getTime())
                System.out.println(n);

            contatore++;
        }
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
package com.example.nabo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Commento_Voto {

    private final String pathCommenti = "C:\\Users\\mitug\\OneDrive\\Desktop\\Nuova cartella\\Progetto-Naboo\\src\\main\\resources\\com\\example\\nabo\\DataBase\\Commento.json";
    private final String pathVoti = "C:\\Users\\mitug\\OneDrive\\Desktop\\Nuova cartella\\Progetto-Naboo\\src\\main\\resources\\com\\example\\nabo\\DataBase\\Voto.json";


    public Commento_Voto() {
    }


    private List<VotoBot> readFileVoto(String path) throws FileNotFoundException {
        Gson gson = new Gson();
        JsonReader reader = new JsonReader(new FileReader(path));
        List<VotoBot> votiFile = gson.fromJson(reader, new TypeToken<List<VotoBot>>() {
        }.getType());
        return votiFile;
    }


    private List<CommentoBot> readFileCommento(String path) throws FileNotFoundException {
        Gson gson = new Gson();
        JsonReader reader = new JsonReader(new FileReader(path));
        List<CommentoBot> commentiFile = gson.fromJson(reader, new TypeToken<List<CommentoBot>>() {
        }.getType());
        return commentiFile;
    }


    public void writeFileVoti(String titolo, String username, double votoNews) throws IOException {
        VotoBot voto = new VotoBot(titolo, username, votoNews);
        List<VotoBot> voti = readFileVoto(pathVoti);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<VotoBot> listVoto = new ArrayList<>();

        listVoto.addAll(voti);
        listVoto.add(voto);
        String jsonString = gson.toJson(listVoto);

        FileWriter fw = new FileWriter(pathVoti);
        fw.write(jsonString);

        fw.close();
    }

    public void writeFileCommento(String titolo, String username, String text) throws IOException {
        CommentoBot commento = new CommentoBot(titolo, username, text);
        List<CommentoBot> voti = readFileCommento(pathCommenti);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<CommentoBot> listCommento = new ArrayList<>();
        listCommento.addAll(voti);
        listCommento.add(commento);
        String jsonString = gson.toJson(listCommento);

        FileWriter fw = new FileWriter(pathCommenti);
        fw.write(jsonString);
        fw.close();
    }



    public String getInfo(Notizia notizia) throws IOException {

        //leggiamo i voti dal file json 'Voto.json'
        JsonReader leggiVoti = new JsonReader(new FileReader(pathVoti));
        Gson gson1 = new Gson();

        List<VotoBot> votoBot = gson1.fromJson(leggiVoti, (new TypeToken<List<VotoBot>>() {
        }).getType());


        //leggiamo i commenti dal file json 'Commento.json'
        JsonReader leggiCommenti = new JsonReader(new FileReader(pathCommenti));
        Gson gson2 = new Gson();

        List<CommentoBot> commentoBot = gson2.fromJson(leggiCommenti, (new TypeToken<List<CommentoBot>>() {
        }).getType());


        //salvo i commenti in una stringa
        String outputComment = null;

        for (CommentoBot commento : commentoBot) {
            if ((notizia.getTitle()).equals(commento.getTitolo())) {
                if(outputComment == null) {
                    outputComment = "\n" + commento.getCommentatore() + " : " + commento.getTesto();
                }else {
                    outputComment = outputComment + "\n" + commento.getCommentatore() + " : " + commento.getTesto();
                }
            }
        }


        //salvo i voti in una stringa
        double outputVote = 0;
        int count = 0;

        for (VotoBot voto : votoBot) {
            if ((notizia.getTitle()).equals(voto.getTitolo())) {
                outputVote = outputVote + voto.getVoto();
                count++;
            }
        }

        outputVote = (Math.round((outputVote / count) * 10));
        outputVote = outputVote/10;


        //return
        if(outputComment == null && outputVote == 0)
            return "\n\nCommenti : \nNessun commento. \n\nMedia dei voti : \nNessun voto. ";
        else if(outputComment != null && outputVote == 0)
            return "\n\nCommenti : " + outputComment + "\n\nMedia dei voti : \nNessun voto. ";
        else if(outputComment == null && outputVote!= 0)
            return "\n\nCommenti : \nNessun commento. \n\nMedia dei voti : " + outputVote;
        else
            return "\n\nCommenti : " + outputComment + "\n\nMedia dei voti : " + outputVote;

    }

}

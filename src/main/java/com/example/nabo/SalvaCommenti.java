package com.example.nabo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class SalvaCommenti {
    private static ArrayList<Voti_Commenti> commento_votis;
    public SalvaCommenti(){
        Voti_Commenti commento_voti= new Voti_Commenti(10,"grande notizia ");
        commento_votis.add(commento_voti);
    }
    public static void main(String[] args) throws IOException {


        FileWriter fileWriterVoti = new FileWriter("C:\\Users\\carlosvasco.chironda\\OneDrive - Alma Mater Studiorum Università di Bologna\\Desktop\\Grafica\\src\\main\\resources\\com\\example\\grafica\\Commenti_Voti.json");

        for (Voti_Commenti i:commento_votis)
        {
            System.out.println(i.getCommento());
            Gson gson4 = new GsonBuilder().setPrettyPrinting().create();
            String jsonString = gson4.toJson(i.toString());
            fileWriterVoti.write(jsonString + ",");

        } fileWriterVoti.close();


    }
}

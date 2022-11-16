/*package com.example.nabo;

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

public class Prava_DataBAse {
    static    ArrayList< Voti_Commenti> commentis= new ArrayList<>();
    static  ArrayList<Utente>utentes= new ArrayList<>();
    // scrittura nel file dei commenti e voto

    public static void main(String[] args) throws  IOException {
        // Voti I comenti array
        Voti_Commenti voti_commenti = new Voti_Commenti(19, "Bellisima Notizia");
        Voti_Commenti Commenti2 = new Voti_Commenti(5, "Pessima Notizia");

        commentis.add(voti_commenti);
        commentis.add(Commenti2);


        // dati dai utente
        Utente  utente1=new Utente ( "Carlos12","Carlos200","carlosvas@studio.unibo.it","si","no",true);
        Utente  utente2=new Utente( "Elnn","ellnn","Elln@studio.unibo.it","si","no",false);
        utentes.add(utente2);
        utentes.add(utente1);


        // Lettura del path dei File
        FileWriter votiCometi = new FileWriter("C:\\Users\\39348\\Desktop\\Progetto\\Progetto-Naboo\\src\\main\\resources\\Vote&Commenti.json");
        FileWriter DatiUtente = new FileWriter("C:\\Users\\39348\\Desktop\\Progetto\\Progetto-Naboo\\src\\main\\resources\\com\\example\\nabo\\Dati.json");
        // Skip per scrivere correttamente nel file
        DatiUtente.write("[");
        votiCometi.write("[");

        //  Ciclo for per la scrittura dei voti
        for (Voti_Commenti j : commentis) {
            Gson gson4 = new GsonBuilder().setPrettyPrinting().create();
            String jsonString = gson4.toJson(j);
            votiCometi.write(jsonString+ ",");
        }
        // Cliclo for per la scrittura dei dati del utente
        for (Utente j : utentes) {
            Gson gson4 = new GsonBuilder().setPrettyPrinting().create();
            String jsonString = gson4.toJson(j);
            DatiUtente.write(jsonString+",");
        }
        // metto l'ultima paratense nei due file
        DatiUtente.write("{}  ]");
        votiCometi.write("{}  ]");

        // Chiudo i due file
        DatiUtente.close();
        votiCometi.close();

    }

    public  void letturadati( ) throws FileNotFoundException {
        Gson gson = new Gson();

        JsonReader leggiUtente = new JsonReader(new FileReader("C:\\Users\\39348\\Desktop\\Progetto\\Progetto-Naboo\\src\\main\\resources\\com\\example\\nabo\\Dati.json"));
        List<Utente> DatiUtente = gson.fromJson(leggiUtente, (new TypeToken<List<Utente>>() {}).getType());

        for (Utente i: DatiUtente){
            System.out.println(i.Username);
        }
    }
}
*/
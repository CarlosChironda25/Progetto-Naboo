package com.example.nabo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.util.List;

public class Voti_Commenti {
    public int Voto;
    public String Commento;

    public List<Notizia> notizias;

    public Gson gson = new Gson();

    //public FileReader leggiNews = new FileReader("C:\\Users\\39348\Desktop\\Progetto\\Progetto-Naboo\\src\\main\\resources\\com\\example\\nabo\\Info-Notizie.json");
    //public FileWriter scriviNews = new FileWriter("C:\\Users\\39348\Desktop\\Progetto\\Progetto-Naboo\\src\\main\\resources\\com\\example\\nabo\\Info-Notizie.json");


    public Voti_Commenti(int voto, String commento) {
        this.Voto = voto;
        this.Commento = commento;
    }

    public int getVoto() {
        return Voto;
    }

    public String getCommento() {
        return Commento;
    }

    public void setVoto(int voto) {
        Voto = voto;
    }

    public void setCommento(String commento) {
        Commento = commento;
    }

    public void setCommento(String commento, Notizia notizia) throws IOException {

        System.out.println("Q");

        JsonReader read = new JsonReader(new FileReader("C:\\Users\\39348\\Desktop\\Progetto\\Progetto-Naboo\\src\\main\\resources\\com\\example\\nabo\\Info-Notizie.json"));
        System.out.println("QQ");

        notizias = gson.fromJson(read, (new TypeToken<List<Notizia>>() {
        }).getType());
        System.out.println("QQQ");

        FileWriter scriviNews = new FileWriter("C:\\Users\\39348\\Desktop\\Progetto\\Progetto-Naboo\\src\\main\\resources\\com\\example\\nabo\\Info-Notizie.json");
        System.out.println("FOR");
        for (Notizia n : notizias) {
            Gson gson4 = new GsonBuilder().setPrettyPrinting().create();
            String jsonString = gson4.toJson(n);
            scriviNews.write(jsonString+ ",");
            if(n.equals(notizia)){
                System.out.println("dentro IF");
                scriviNews.write(commento);

            }
            System.out.println("FUORI IF");
        }

        scriviNews.close();
    }

    @Override
    public String toString() {
        return "Voti_Commenti{" +
                "Voto=" + getVoto() +
                ", Commento='" + getCommento() + '\'' +
                '}';
    }
}



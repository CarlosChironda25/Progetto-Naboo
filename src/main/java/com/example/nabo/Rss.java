/*package com.example.nabo;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.http.HttpClient;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.almasb.fxgl.core.collection.Array;
import com.rometools.rome.feed.synd.SyndContent;
import org.xml.sax.InputSource;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import java.io.FileNotFoundException;


public class Rss {
private static Array< Notizia>list= new Array<>();
    public static  void main(String[] args) {

        System.out.println("Esempio libreria Rome RSS: dati ANSA");

        String sourceURL = "https://www.ansa.it/sito/ansait_rss.xml";

        try {
            URL feedUrl = new URL(sourceURL);

            SyndFeedInput input = new SyndFeedInput();

            try {
                SyndFeed feed = input.build(new InputSource(feedUrl.openStream()));

                List<SyndEntry> entries = feed.getEntries();

                Iterator<SyndEntry> itEntries = entries.iterator();
                while (itEntries.hasNext()) {

                    SyndEntry entry = itEntries.next();
                    //Date tempo, String title, String link,SyndContent discrizione, String autore, SyndFeed fonte
                   Notizia notizia =new Notizia((Date) entry.getPublishedDate(),entry.getTitle(),entry.getLink(),entry.getDescription(),entry.getAuthor(), entry.getSource());
                    Gson gson4 = new GsonBuilder().setPrettyPrinting().create();
                    String jsonString = gson4.toJson(notizia);
                       ArrayList<Notizia> n= new ArrayList<>();
                             FileWriter fileWriter = new FileWriter("src/main/resources/com/example/nabo/Info-Notizie.json");
                             fileWriter.write(jsonString);
                             fileWriter.close();
                    System.out.println(notizia);
                                             }
            } catch (IllegalArgumentException | FeedException | IOException e) {
                // Errore lettura feed
                e.printStackTrace();
            }

        } catch (MalformedURLException e) {
            // Errore indirizzo e accesso ai feed
            e.printStackTrace();
        }
       try {


        Gson gson= new Gson();
        JsonReader leggi = new JsonReader(new FileReader("src/main/resources/com/example/nabo/Info-Notizie.json"));
        List<Notizia> notizias = gson.fromJson(leggi, (new TypeToken<List<Notizia>>() {}).getType());
            for (Notizia i:notizias
                 ) {
                System.out.println(i.link);

            }
    }catch ( IOException e){
            System.out.println("non trovato");
        }
    }

}
*/
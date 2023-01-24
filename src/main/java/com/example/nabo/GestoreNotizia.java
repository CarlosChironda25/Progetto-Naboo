package com.example.nabo;
/*
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
 */
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rometools.rome.feed.synd.*;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import org.xml.sax.InputSource;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class GestoreNotizia {
    private ArrayList<Notizia> listaNotizie;
    public ArrayList<String> sources;
    public static String Path = "C:\\Users\\feder\\IdeaProjects\\Progetto-Naboo\\src\\main\\resources\\com\\example\\nabo\\DataBase\\Info-Notizie.json";

    private static GestoreNotizia instance = null;
    GestoreNotizia(){
        this.sources = new ArrayList<>();
        listaNotizie = new ArrayList<>();
    }

    public static GestoreNotizia getInstance(){
        if(instance == null){
            instance = new GestoreNotizia();
        }
        return instance;
    }

    public ArrayList<String> getSources(){return sources;}

    private boolean firstTime = true;
    public void caricaNotizie() throws IOException {
        listaNotizie.clear();


        if(firstTime) {
            sources.add("http://xml2.corriereobjects.it/rss/homepage.xml");
            sources.add("https://www.repubblica.it/rss/homepage/rss2.0.xml");
            sources.add("https://www.ilfattoquotidiano.it/feed/");
            firstTime = false;
        }

        XmlReader reader = null;
        try {
            //Istanziamo uno stream reader dall'url della nostra sorgente feed
            for (int j = 0; j < sources.size(); j++) {
                URL url = new URL(sources.get(j));
                reader = new XmlReader(url);

                SyndFeed feed = new SyndFeedInput().build(reader);
                for (Iterator<SyndEntry> i = feed.getEntries().iterator(); i.hasNext(); ) {
                    //Iteriamo tutte le voci presenti nel nostro feed
                    SyndEntry entry = i.next();
                    try {
                        Notizia notizia = new Notizia((Date) entry.getPublishedDate(), entry.getTitle(), entry.getLink(), entry.getDescription().getValue(), entry.getAuthor(), entry.getSource(), entry.getCategories().get(0).getName());
                        listaNotizie.add(notizia);
                    } catch (Exception e){}

                }
            }
        } catch (FeedException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {

            if (reader != null)
                reader.close();
        }

        FileWriter fileWriter = new FileWriter("C:\\Users\\feder\\IdeaProjects\\Progetto-Naboo\\src\\main\\resources\\com\\example\\nabo\\DataBase\\Info-Notizie.json");
        fileWriter.write( "[");
        Gson gson4 = new GsonBuilder().setPrettyPrinting().create();

        for (int i = 0; i < listaNotizie.size(); i++) {
            String jsonString = gson4.toJson(listaNotizie.get(i));
            fileWriter.write(jsonString + ",");
        }

        fileWriter.write(" ]");
        fileWriter.close();

    }

}

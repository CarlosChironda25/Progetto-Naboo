package com.example.nabo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rometools.rome.feed.synd.*;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import org.xml.sax.InputSource;

import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class GestoreNotizia {
       private ArrayList<Notizia>notizias;

       public  GestoreNotizia (String link) throws MalformedURLException {
           notizias =new ArrayList<>();
           URL feedUrl = new URL(link);

           SyndFeedInput input = new SyndFeedInput();

           try {
               SyndFeed feed = input.build(new InputSource(feedUrl.openStream()));

               List<SyndEntry> entries = feed.getEntries();

               Iterator<SyndEntry> itEntries = entries.iterator();
               while (itEntries.hasNext()) {

                   SyndEntry entry = itEntries.next();
                   //System.out.println(entry.getCategories().get(0));
                   //Date tempo, String title, String link,SyndContent discrizione, String autore, SyndFeed fonte
                   Notizia notizia = new Notizia(entry.getTitle(), entry.getDescription().getValue() , entry.getLink(), entry.getAuthor() , entry.getCategories().get(0).getName() , (Date) entry.getPublishedDate(), entry.getSource());
                   notizias.add(notizia);


               }
           } catch (IllegalArgumentException | FeedException | IOException e) {
               // Errore lettura feed
               e.printStackTrace();
           }


       }

       public ArrayList<Notizia> getNotizia(){
              return notizias;
       }

       public static void main(String[] args) throws IOException {
           FileWriter fileWriter = new FileWriter("Progetto-Naboo\\src\\main\\resources\\com\\example\\nabo\\Info-Notizie.json");
           fileWriter.write( "[");
           String source = "http://xml2.corriereobjects.it/rss/homepage.xml";
           GestoreNotizia gestoreNotizia= new GestoreNotizia( source);
           //System.out.println( gestoreNotizia.getNotizia());
           for( Notizia i : gestoreNotizia.getNotizia()) {
                Gson gson4 = new GsonBuilder().setPrettyPrinting().create();
                String jsonString = gson4.toJson(i);
                fileWriter.write(jsonString + ",");

           }

           fileWriter.write( "{}  ]");
           fileWriter.close();

       }

}

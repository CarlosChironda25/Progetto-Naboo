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
    private ArrayList<String> sources;
    public static String Path = "C:\\Users\\mitug\\OneDrive\\Desktop\\Nuova cartella\\Progetto-Naboo\\src\\main\\resources\\com\\example\\nabo\\DataBase\\eliminanoti.json";
    public  GestoreNotizia () throws MalformedURLException {
        this.sources = new ArrayList<>();
        listaNotizie = new ArrayList<>();
        //caricaNotizie();
    }

    public void importFromFile() throws IOException{
        //mandera' un file TXT così composto:
           /*
           "link1",
           "link2",
           "link3"
            */

           /*
           l'amministratore dall'interfaccia carica il file TXT preme un pulsante di OK
           viene attivato importFromFile()
           il GestoreNotizie legge questo file TXT, crea un ArrayList di stringhe
           composto dagli elementi del file e poi scrive quell'ArrayList dentro sources
            */
        BufferedReader input = null;
        try{
            String link;
            input = new BufferedReader(new FileReader("C:\\Users\\mitug\\OneDrive\\Desktop\\Nuova cartella\\Progetto-Naboo\\src\\main\\resources\\com\\example\\nabo\\links.txt"));
            while((link = input.readLine()) != null){
                sources.add(link);
            }
        }finally{
            if(input != null)
                input.close();
        }
    }

    public void caricaNotizie() throws IOException {
        sources.clear();

        sources.add("http://xml2.corriereobjects.it/rss/homepage.xml");
        sources.add("https://www.repubblica.it/rss/homepage/rss2.0.xml");
        sources.add("https://www.ilfattoquotidiano.it/feed/");

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

        FileWriter fileWriter = new FileWriter("C:\\Users\\mitug\\OneDrive\\Desktop\\Nuova cartella\\Progetto-Naboo\\src\\main\\resources\\com\\example\\nabo\\DataBase\\eliminanoti.json");
        fileWriter.write( "[");
        Gson gson4 = new GsonBuilder().setPrettyPrinting().create();

        for (int i = 0; i < listaNotizie.size(); i++) {
            String jsonString = gson4.toJson(listaNotizie.get(i));
            fileWriter.write(jsonString + ",");
        }

        fileWriter.write(" ]");
        fileWriter.close();

    }

        /*//questo metodo scorre le fonti RSS, prende le notizie e le scrive nel file JSON
        for(int i = 0; i< sources.size(); i++){
            URL feedUrl = new URL(sources.get(i));
            System.out.println(sources.size());
            SyndFeedInput input = new SyndFeedInput();

            try {
                SyndFeed feed = input.build(new InputSource(feedUrl.openStream()));

                List<SyndEntry> entries = feed.getEntries();

                Iterator<SyndEntry> itEntries = entries.iterator();
                while (itEntries.hasNext()) {
                    System.out.println("sto prendendo notizie dalla fonte:"+i);

                    SyndEntry entry = itEntries.next();

                    //System.out.println(entry.getCategories().get(0));
                    //Date tempo, String title, String link,SyndContent discrizione, String autore, SyndFeed fonte
                    try {
                        Notizia notizia = new Notizia((Date) entry.getPublishedDate(), entry.getTitle(), entry.getLink(), entry.getDescription().getValue(), entry.getAuthor(), entry.getSource(), entry.getCategories().get(0).getName());
                        listaNotizie.add(notizia);
                    } catch (Exception e){}

                }
            } catch (IllegalArgumentException | FeedException | IOException e) {
                // Errore lettura feed
                e.printStackTrace();
            }
        }
    }*/



    /*public static void main(String[] args) throws IOException {
        FileWriter fileWriter = new FileWriter("C:\\Users\\mitug\\OneDrive\\Desktop\\Nuova cartella\\Progetto-Naboo\\src\\main\\resources\\com\\example\\nabo\\DataBase\\eliminanoti.json");
        fileWriter.write( "[");
        ArrayList<String> sources = new ArrayList<String> ();
        sources.add("http://xml2.corriereobjects.it/rss/homepage.xml");
        sources.add("https://www.ilfattoquotidiano.it/feed/");
        sources.add("https://www.repubblica.it/rss/homepage/rss2.0.xml");
        //sources.add("http://xml2.corriereobjects.it/rss/homepage.xml");
        //sources.add("http://xml2.corriereobjects.it/rss/homepage.xml");
        //sources.add("http://xml2.corriereobjects.it/rss/homepage.xml");
        GestoreNotizia gestoreNotizia = new GestoreNotizia(sources);
        //System.out.println( gestoreNotizia.getNotizia());
        for( Notizia i : gestoreNotizia.getNotizia()) {
            Gson gson4 = new GsonBuilder().setPrettyPrinting().create();
            String jsonString = gson4.toJson(i);
            fileWriter.write(jsonString + ",");

        }

        fileWriter.write( " ]");
        fileWriter.close();

    }

    private ArrayList<Notizia> getNotizia() {
        return listaNotizie;
    }*/

}

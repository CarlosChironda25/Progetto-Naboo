package com.example.nabo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.rometools.rome.feed.synd.*;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import org.xml.sax.InputSource;

import java.io.BufferedReader;
import java.io.FileReader;
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
    private ArrayList<String> sources;

    public  GestoreNotizia (ArrayList<String> link) throws MalformedURLException {
        this.sources = link;
        notizias =new ArrayList<>();
        caricaNotizie();
    }

    public ArrayList<Notizia> getNotizia(){
        return notizias;
    }

    public void addSource(String newSource){
        sources.add(newSource);
    }

    public void getSource(){
        System.out.println(sources);
    }

    public void deleteSource(int toDeleteSource){
        sources.remove(toDeleteSource);
    }

    public void deleteUser(String toDeleteUser) throws IOException {
        //carichiamo il file JSON
        FileWriter fileWriter = new FileWriter("C:\\Users\\feder\\OneDrive\\Desktop\\Progetto-Naboo-main (1)\\Progetto-Naboo-main\\src\\main\\resources\\com\\example\\nabo\\Dati.json");
        //troviamo l'utente che si chiama toDeleteUser
        //lo cancelliamo
    }

    public void addUser(String newUsername, String newPassword, String newMail) throws IOException {
        FileWriter fileWriter = new FileWriter("C:\\Users\\feder\\OneDrive\\Desktop\\Progetto-Naboo-main (1)\\Progetto-Naboo-main\\src\\main\\resources\\com\\example\\nabo\\Dati.json");
        String newUser =
                "{" +
                        "Username:"+newUsername+","+
                        "Password:"+newPassword+","+
                        "Mail:"+newMail+
                        "}";

        fileWriter.write(newUser + ",");
        fileWriter.close();
    }

    public void updateUser(String newUsername, String newPassword, String newMail){
        try {
            String jsonMessage = "{\" + username + "\" + password + "\" + mail + "\}";
        JSONParser parser = new JSONParser();//carichiamo il file JSON
        Object obj = parser.parse();

        JsonObject jsonObject = (JSONObject) obj;
        jsonObject.put("username", "newUsername");
        jsonObject.put("password", "newPassword");
        jsonObject.put("mail", "newMail");
    } catch(Exception e) {
        System.out.println(e);
    }
}


    public void deleteNotizia(String toDeleteLink) throws IOException {
        //caricare il file JSON Info-Notizie.json
        FileWriter fileWriter = new FileWriter("C:\\Users\\feder\\IdeaProjects\\Progetto-Naboo\\src\\main\\resources\\com\\example\\nabo\\Info-Notizie.json");
        //cancellare la notizia giusta
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
            input = new BufferedReader(new FileReader("C:\\Users\\feder\\IdeaProjects\\Progetto-Naboo\\src\\main\\resources\\com\\example\\nabo\\Links.txt"));
            while((link = input.readLine()) != null){
                sources.add(link);
            }
        }finally{
            if(input != null)
                input.close();
        }
    }

    public void caricaNotizie() throws MalformedURLException {
        //questo metodo scorre le fonti RSS, prende le notizie e le scrive nel file JSON
        for(int i = 0; i< sources.size(); i++){
            URL feedUrl = new URL(sources.get(i));

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
                    Notizia notizia = new Notizia(entry.getTitle(), entry.getDescription().getValue() , entry.getLink(), entry.getAuthor() , entry.getCategories().get(0).getName() , (Date) entry.getPublishedDate(), entry.getSource());
                    notizias.add(notizia);


                }
            } catch (IllegalArgumentException | FeedException | IOException e) {
                // Errore lettura feed
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        FileWriter fileWriter = new FileWriter("C:\\Users\\feder\\OneDrive\\Desktop\\Progetto-Naboo-main (1)\\Progetto-Naboo-main\\src\\main\\resources\\com\\example\\nabo\\Info-Notizie.json");
        fileWriter.write( "[");
        ArrayList<String> sources = new ArrayList<String> ();
        sources.add("http://xml2.corriereobjects.it/rss/homepage.xml");
        sources.add("http://xml2.corriereobjects.it/rss/homepage.xml");
        sources.add("http://xml2.corriereobjects.it/rss/homepage.xml");
        sources.add("http://xml2.corriereobjects.it/rss/homepage.xml");
        GestoreNotizia gestoreNotizia= new GestoreNotizia(sources);
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

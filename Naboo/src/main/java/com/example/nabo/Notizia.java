package com.example.nabo;

import com.rometools.rome.feed.synd.SyndContent;
import com.rometools.rome.feed.synd.SyndFeed;

import java.security.Timestamp;
import java.util.Arrays;
import java.util.Date;

public class Notizia {

      public Date tempo;
      public   String title;
      public String link;
      //public SyndContent Discrizione[];
      public  String Autore;
      public   SyndFeed Fonte;

    public Notizia(Date tempo, String title, String link,
                   SyndContent discrizione[]
            , String autore, SyndFeed fonte) {
        this.tempo = tempo;
        this.title = title;
        this.link = link;
        //Discrizione = discrizione;
        Autore = autore;
        Fonte = fonte;
    }


   // public void setDiscrizione(SyndContent[] discrizione) {Discrizione = discrizione;}

    public String getAutore() {
        return Autore;
    }

    public void setAutore(String autore) {
        Autore = autore;
    }

    public SyndFeed getFonte(SyndFeed source) {
        return Fonte;
    }

    public void setFonte(SyndFeed fonte) {
        Fonte = fonte;
    }

    public Notizia(){

      }

    public String getLink() {
        return link;
    }

    public Date getTempo() {
        return tempo;
    }

    public String getTitle() {
        return title;
    }

    public void setTempo(Date tempo) {
        this.tempo = tempo;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    /*public String toString() {
        return "Notizia{" +
                "tempo=" + tempo +
                ", title='" + title + '\'' +
                ", link='" + link + '\'' +
             //   ", Discrizione='" + Arrays.toString(Discrizione) + '\'' +
                ", Autore='" + Autore + '\'' +
                ", Fonte='" + Fonte + '\'' +
                '}';
    }*/

    public String toString(){
        return title +"\n\n"+link;
    }
}

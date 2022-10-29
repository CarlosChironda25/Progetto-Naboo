package com.example.nabo;

import com.rometools.rome.feed.synd.SyndCategory;
import com.rometools.rome.feed.synd.SyndCategoryImpl;
import com.rometools.rome.feed.synd.SyndContent;
import com.rometools.rome.feed.synd.SyndFeed;

import java.util.Date;
import java.util.List;

public class Notizia {

      public Date data;
      public String title;
      public String link;
      public SyndContent[] Descrizione;
      public String Autore;
      public SyndFeed Fonte;
      public String category;


    public Notizia(String title, SyndContent Descrizione[], String link, String autore
                   , String category, Date data
                   , SyndFeed fonte) {
        this.data = data;
        this.title = title;
        this.link = link;
        this.category = category;
        //Descrizione = Descrizione;
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
      

    public String getCategory() {
            return category;
    }
    public String getLink() {
        return link;
    }

    public Date getData() {
        return data;
    }

    public String getTitle() {
        return title;
    }

    public void setData(Date data) {
        this.data = data;
    }

    //public void setCategory(String category) {category = category;}

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
        return title +"\n\n"+
                category +"\n\n"+
                link;

    }
}

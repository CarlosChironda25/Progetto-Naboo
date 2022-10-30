package com.example.nabo;

import com.rometools.rome.feed.synd.SyndCategory;
import com.rometools.rome.feed.synd.SyndCategoryImpl;
import com.rometools.rome.feed.synd.SyndContent;
import com.rometools.rome.feed.synd.SyndFeed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class Notizia {

      public Date data;
      public String title;
      public String link;
      public String descrizione;
      public String autore;
      public SyndFeed Fonte;
      public String category;


    public Notizia(String title, String descrizione, String link, String autore
                   , String category, Date data
                   , SyndFeed fonte) {
        this.data = data;
        this.title = title;
        this.link = link;
        this.category = category;
        this.descrizione = descrizione;
        this.autore = autore;
        Fonte = fonte;
    }


    public void setDiscrizione(String descrizione) {this.descrizione = descrizione;}

    public String getAutore() {
        return autore;
    }

    public void setAutore(String autore) {
        autore = autore;
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

    public void setCategory(String category) {this.category = category;}

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return title +
                "\n\n"+ data +
                //"\n" + descrizione +
                "\n\nCategory : " + category +
                "\n" + autore +
                //"\n" + Fonte +
                "\n\n" + link;
    }

    /*public String toString(){
        return title +"\n\n"+
                "Category : " + category +"\n\n"+
                link;

    }*/
}

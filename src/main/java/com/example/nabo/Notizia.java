package com.example.nabo;

import com.rometools.rome.feed.synd.SyndFeed;

import java.util.Date;

public class Notizia {

      public Date Data;
      public String Title;
      public String Link;
      public String Descrizione;
      public String Autore;
      public SyndFeed Fonte;
      public String Category;


    public Notizia(Date data, String title, String link, String descrizione, String autore, SyndFeed fonte, String category) {
        this.Data = data;
        this.Title = title;
        this.Link = link;
        this.Descrizione = descrizione;
        this.Autore = autore;
        Fonte = fonte;
        this.Category = category;
    }

    public void setDiscrizione(String descrizione) {this.Descrizione = descrizione;}

    public String getAutore() {
        return Autore;
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
            return Category;
    }
    public String getLink() {
        return Link;
    }

    public Date getData() {
        return Data;
    }

    public String getTitle() {
        return Title;
    }

    public void setData(Date data) {
        this.Data = data;
    }

    public void setCategory(String category) {this.Category = category;}

    public void setTitle(String title) {
        this.Title = title;
    }

    public void setLink(String link) {
        this.Link = link;
    }

    @Override
    public String toString() {

        return Title + "\n\n" +
                //descrizione + "\n\n" +
                Data + "\n\n" +
                "Category : " + Category + "\n" +
                Autore + "\n\n" +
                Link;
                //+ "\n\n" +
                //Fonte + "\n\n" +

    }
}

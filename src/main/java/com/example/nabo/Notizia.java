package com.example.nabo;

import com.rometools.rome.feed.synd.SyndFeed;

import java.util.Date;

public class Notizia {

    private Date Data;
    private String Title;
    private String Link;
    private String Descrizione;
    private String Autore;
    private SyndFeed Fonte;
    private String Category;

    public Notizia(){ }

    public Notizia(Date data, String title, String link, String descrizione, String autore, SyndFeed fonte, String category) {
        this.Data = data;
        this.Title = title;
        this.Link = link;
        this.Descrizione = descrizione;
        this.Autore = autore;
        Fonte = fonte;
        this.Category = category;
    }

    public String getAutore() {
        return Autore;
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

    public String getDescrizione() {
        return Descrizione;
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

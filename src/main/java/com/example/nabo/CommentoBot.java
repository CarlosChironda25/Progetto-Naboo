package com.example.nabo;

public class CommentoBot {

    private String Titolo;
    private String Testo;
    private String Commentatore;

    CommentoBot(){}

    CommentoBot(String titolo, String username, String testo){
        this.Titolo = titolo;
        this.Commentatore = username;
        this.Testo = testo;
    }

    public String getTitolo() {
        return Titolo;
    }

    public String getTesto() {
        return Testo;
    }

    public String getCommentatore() {
        return Commentatore;
    }

    @Override
    public String toString() {
        return "CommentoBot{" +
                "Titolo='" + this.Titolo + '\'' +
                "Commentatore='" + this.Commentatore + '\'' +
                ", Testo='" + this.Testo + '\'' +
                '}';
    }


}


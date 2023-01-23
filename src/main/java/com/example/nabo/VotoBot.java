package com.example.nabo;

public class VotoBot {

    private String Titolo;
    private String Commentatore;
    private double Voto;

    VotoBot(String titolo, String commentatore, double voto){
        this.Titolo = titolo;
        this.Commentatore = commentatore;
        this.Voto = voto;
    }

    public String getTitolo() {
        return Titolo;
    }

    public double getVoto() {
        return Voto;
    }
    public String getCommentatore(){ return Commentatore;}


    @Override
    public String toString() {
        return "VotoBot{" +
                "Titolo='" + this.Titolo + '\'' +
                "Commentatore='" + this.Commentatore + '\'' +
                ", Voto='" + this.Voto + '\'' +
                '}';
    }

}




package com.example.nabo;

public class VotoBot {

    private String Titolo;
    private double Voto;

    VotoBot(String titolo, double voto){
        this.Titolo = titolo;
        this.Voto = voto;
    }

    public String getTitolo() {
        return Titolo;
    }

    public double getVoto() {
        return Voto;
    }

    @Override
    public String toString() {
        return "VotoBot{" +
                "Titolo='" + this.Titolo + '\'' +
                ", Voto='" + this.Voto + '\'' +
                '}';
    }


}


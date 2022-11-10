

package com.example.nabo;

public class Voti_Commenti {
    public int Voto;
    public String Commento;

    public Voti_Commenti(int voto, String commento) {
        this.Voto = voto;
        this.Commento = commento;
    }

    public int getVoto() {
        return Voto;
    }

    public String getCommento() {
        return Commento;
    }

    public void setVoto(int voto) {
        Voto = voto;
    }

    public void setCommento(String commento) {
        Commento = commento;
    }

    @Override
    public String toString() {
        return "Voti_Commenti{" +
                "Voto=" + getVoto() +
                ", Commento='" + getCommento() + '\'' +
                '}';
    }
}



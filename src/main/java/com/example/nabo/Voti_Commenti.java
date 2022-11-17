package com.example.nabo;


import java.util.ArrayList;

public class Voti_Commenti {
    public ArrayList<Integer> Voti= new ArrayList<>();
    public   String commento;



    public Voti_Commenti(int voto, String commento){
        this.commento = commento;
        if(voto <= 10){
            Voti.add(voto);
        }
    }

    public void setVoti(ArrayList<Integer> voti) {
        Voti = voti;
    }

    public void setCommento(String commento) {
        this.commento = commento;
    }

    public ArrayList<Integer> getVoti() {
        return Voti;
    }

    public String getCommento() {
        return commento;
    }

    @Override
    public String toString() {
        return "Commento_voti{" +
                "Voti=" + Voti +
                ", commento='" + commento + '\'' +
                '}';
    }
}

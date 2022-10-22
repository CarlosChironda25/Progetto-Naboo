package com.example.nabo;

public class TipoLettore {
      private  String  Commeta, Vota, ComentaEvota;

      public String getComentaEvota() {
            return ComentaEvota;
      }

      public String getVota() {
            return Vota;
      }

      public String getCommeta() {
            return Commeta;
      }

      public void setCommeta(String commeta) {
            Commeta = commeta;
      }

      public void setVota(String vota) {
            Vota = vota;
      }

      public void setComentaEvota(String comentaEvota) {
            ComentaEvota = comentaEvota;
      }

      @Override
      public String toString() {
            return "TipoLettore{" +
                    "Commeta='" + Commeta + '\'' +
                    ", Vota='" + Vota + '\'' +
                    ", ComentaEvota='" + ComentaEvota + '\'' +
                    '}';
      }
}

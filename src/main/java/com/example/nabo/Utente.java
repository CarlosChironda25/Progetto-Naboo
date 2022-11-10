package com.example.nabo;

import java.util.ArrayList;

public class Utente {
public String  Username;
public   String Password;
    public  String Mail;
    public   String  Commeta, Vota;


    public Utente(String username, String password, String mail, String commeta, String vota, boolean admin) {
        Username = username;
        Password = password;
        Mail = mail;
        Commeta = commeta;
        Vota = vota;
        Admin = admin;
    }
    public String getVota() {return Vota;}
    public String getCommeta() {return Commeta;}
    public void setCommeta(String  commeta) {Commeta = commeta;}
    public void setVota(String vota) {Vota = vota;}


    public String getMail() {
        return Mail;
    }

    public void setMail(String mail) {
        Mail = mail;
    }

    public boolean isAdmin() {
        return Admin;
    }

    public void setAdmin(boolean admin) {
        Admin = admin;
    }

    public  boolean Admin;


    public String getUsername() {
        return Username;
    }

    public String getPassword() {
        return Password;
    }


    public void setUsername(String username) {
        Username = username;
    }

    public void setPassword(String password) {
        Password = password;
    }



    @Override
    public String toString() {
        return "Utente{" +
                "Username='" + Username + '\'' +
                ", Password='" + Password + '\'' +
                ", Mail='" + Mail + '\'' +
                ", Commeta='" + Commeta + '\'' +
                ", Vota='" + Vota + '\'' +
                ", Admin=" + Admin +
                '}';
    }
}

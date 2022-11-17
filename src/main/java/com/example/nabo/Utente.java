package com.example.nabo;
import java.util.ArrayList;

public class Utente {
    public String Username;
    public String Password;
    public String Mail;


    public Utente(String username, String password, String mail) {
        Username = username;
        Password = password;
        Mail = mail;

    }


    public String getMail() {
        return Mail;
    }

    public void setMail(String mail) {
        Mail = mail;
    }


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
                "Username='" + this.Username + '\'' +
                ", Password='" + this.Password+ '\'' +
                ", Mail='" + this.Mail + '\'' +
                '}';
    }


}






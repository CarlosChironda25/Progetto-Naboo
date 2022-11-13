package com.example.nabo;

import org.json.simple.JSONObject;
import java.io.FileWriter;
import java.io.IOException;

public class Utente {
    public String  Username;
    public   String Password;
    public  String Mail;



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

    public void createUserFile(){
        //Creating a JSONObject object
        JSONObject jsonobject = new JSONObject();
        //Inserting key-value pairs into the json object
        jsonobject.put("Username", "Prova1");
        jsonobject.put("Password", "Prova11");
        jsonobject.put("Mail", "home@home.it");
        try {
            FileWriter file = new FileWriter("dati.json");
            file.write(jsonobject.toJSONString());
            file.close();
        } catch (IOException e) {

            e.printStackTrace();
        }
        System.out.println("JSON file created: "+jsonobject);
    }

    public void createAdminFile(){
        JSONObject jsonobject = new JSONObject();
        //Inserting key-value pairs into the json object
        jsonobject.put("Username", "Federica");
        jsonobject.put("Password", "FedeMistu");
        jsonobject.put("Mail", "home@home.it");
        try {
            FileWriter file = new FileWriter("admin.json");
            file.write(jsonobject.toJSONString());
            file.close();
        } catch (IOException e) {

            e.printStackTrace();
        }
        System.out.println("JSON file created: "+jsonobject);
    }
}




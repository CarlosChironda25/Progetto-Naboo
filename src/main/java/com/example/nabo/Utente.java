package com.example.nabo;
import java.util.ArrayList;

public class Utente {
    public String Username;
    public String Password;
    public boolean isAdmin;


    public Utente(String username, String password, boolean isAdmin) {
        Username = username;
        Password = password;
        this.isAdmin = isAdmin;

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

    public boolean getIsAdmin(){
        return isAdmin;
    }
    public void setIsAdmin(boolean isadmin){ isAdmin = isadmin; }


    @Override
    public String toString() {
        return "Utente{" +
                "Username='" + this.Username + '\'' +
                ", Password='" + this.Password+ '\'' +
                ", isAdmin='" + this.isAdmin + '\'' +
                '}';
    }


}






package com.example.nabo;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.Scanner;

public class CheckUtenti {

    //Lista<Utente> list;

    public static void main(String[] args){

        JsonParser parser = new JsonParser();

        try {
            Object obj = parser.parse(new FileReader("Dati.json"));
            JsonObject jsonObject = (JsonObject) obj;

            JsonArray coursesArray = (JsonArray) jsonObject.get("Name");
            Iterator<JsonElement> iterator = coursesArray.iterator();

            while(iterator.hasNext()){
                System.out.println("Dati " + iterator.next());
            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }




    /*private void copy(){

        Reference inputjson = null;
        JsonReader jsonR = null;

        try {
            jsonR = new JsonReader(new FileReader("Dati.json"));
        }catch (FileNotFoundException e){
            System.out.println("Errore nell'apertura del file " + "Dati.json");
            System.exit(0);
        }

        while()
    }


    private void copyFile() {

        URL sourceUrl = JsonManager.class.getClassLoader().getResource("Dati.json"); //Search Data.json in PATonTS.jar
        File destination = new File(PFolder + "/PATonTSData.json"); //Create the destination file

        try {
            FileUtils.copyURLToFile(sourceUrl, destination); //Copy file Data.json to PATonTSData.json
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
}

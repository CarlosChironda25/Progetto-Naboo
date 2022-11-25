package com.example.nabo;

import java.io.*;

public class Comment_Vote {

    String nomeFileCommenti = "C:\\Users\\mitug\\OneDrive\\Desktop\\Nuova cartella\\Progetto-Naboo\\src\\main\\resources\\com\\example\\nabo\\Commenti.txt";
    String nomeFileVoti = "C:\\Users\\mitug\\OneDrive\\Desktop\\Nuova cartella\\Progetto-Naboo\\src\\main\\resources\\com\\example\\nabo\\Voti.txt";


    public Comment_Vote(){
    }


    public void writeComment(String commento, Notizia notizia, String name) throws IOException {

        FileWriter outputStream = null;
        try {
            outputStream = new FileWriter(nomeFileCommenti,true);
        } catch (FileNotFoundException e) {
            System.out.println("Errore nell'apertura del file " + nomeFileCommenti);
            System.exit(0);
        }

        outputStream.write("\n"+ notizia.getTitle() + "\n" + name + " : " + commento + "\n");
        outputStream.close();

    }

    public void writeVote(double vote, Notizia notizia) throws IOException {

        FileWriter outputStream = null;
        try {
            outputStream = new FileWriter(nomeFileVoti,true);
        } catch (FileNotFoundException e) {
            System.out.println("Errore nell'apertura del file " + nomeFileVoti);
            System.exit(0);
        }

        outputStream.write("\n"+ notizia.getTitle() + "\n"+ vote + "\n");
        outputStream.close();

    }

    public String getInfo(Notizia notizia) throws IOException {

        BufferedReader inputStreamC = null;
        BufferedReader inputStreamV = null;

        try {
            inputStreamC = new BufferedReader(new FileReader(nomeFileCommenti));
            inputStreamV = new BufferedReader(new FileReader(nomeFileVoti));
        } catch (FileNotFoundException e) {
            System.out.println("Errore nell'apertura del file");
            System.exit(0);
        }

        String outputComment = null;
        double outputVote = 0;

        int contatore = 0;
        String line1 = inputStreamC.readLine();
        while(line1 != null) {
            line1 = inputStreamC.readLine();
            if(notizia.getTitle().equals(line1)) {
                contatore++;
            } else if(contatore == 1){
                if(outputComment == null) {
                    outputComment = "\n" + line1 + "\n";
                    contatore--;
                } else {
                    outputComment = outputComment + line1 + "\n";
                    contatore--;
                }
            }
        }

        int contatore2 = 0;
        int count = 0;
        String line2 = inputStreamV.readLine();

        while(line2 != null) {
            line2 = inputStreamV.readLine();

            if (notizia.getTitle().equals(line2)) {
                contatore2++;
            } else if (contatore2 == 1) {
                if (outputVote == 0) {
                    outputVote = Double.parseDouble(line2);
                    contatore2--;
                    count++;
                } else {
                    outputVote = (Double.parseDouble(line2)) + outputVote;
                    contatore2--;
                    count++;
                }
            }
        }

        outputVote = (Math.round((outputVote / count) * 10));
        outputVote = outputVote/10;

        inputStreamC.close();
        inputStreamV.close();


        if(outputComment == null && outputVote == 0)
            return "\n\nComments : \nNo comment. \n\nAverage votes : \nNo vote. ";
        else if(outputComment != null && outputVote == 0)
            return "\n\nComments : " + outputComment + "\nAverage votes : \nNo vote. ";
        else if(outputComment == null && outputVote!= 0)
            return "\n\nComments : \nNo comment. \n\nAverage votes : " + outputVote;
        else
            return "\n\nComments : " + outputComment + "\nAverage votes : " + outputVote;
    }
}




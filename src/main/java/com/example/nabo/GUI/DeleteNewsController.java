package com.example.nabo.GUI;

import com.example.nabo.Main;
import com.example.nabo.Notizia;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DeleteNewsController {
    @FXML
    public Label labelError;
    @FXML
    public Label labelTitle;
    @FXML
    public Label labelProperRemoval;
    @FXML
    public TextField inputTitle;
    @FXML
    public Button searchNews;
    @FXML
    public Button deleteNews;
    private static String path = "C:\\Users\\feder\\IdeaProjects\\Progetto-Naboo\\src\\main\\resources\\com\\example\\nabo\\DataBase\\Info-Notizie.json";
    public static List<Notizia> readFile(String path) throws FileNotFoundException {
        Gson gson = new Gson();
        JsonReader reader = new JsonReader(new FileReader(path));
        return gson.fromJson(reader, new TypeToken<List<Notizia>>(){}.getType());
    }

    public static void writeFile(List<Notizia> news, String path) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<Notizia> listNews = new ArrayList<>(news);
        String jsonString = gson.toJson(listNews);
        FileWriter fw = new FileWriter(path);
        fw.write(jsonString);
        fw.close();
    }
    /*
    Qui cerco se la notizia effettivamente esiste. Nel caso dovesse esistere viene fuori una label affermativa,
    in caso contrario viene fuori una label di errore.
     */
    @FXML
    public void search(ActionEvent event) throws FileNotFoundException {
        labelError.setText("");
        List<Notizia> news = readFile(path);
        boolean newsFound = false;
        for (Notizia notizia : news) {
            if (notizia.getTitle().equals(inputTitle.getText())) {
                newsFound = true;
                labelTitle.setText(notizia.getTitle());
                System.out.println("Questa notizia esiste");
            }
        }
        if(!newsFound){
            labelError.setText("La news che stai cercando non esiste");
            System.out.println("La notizia non esiste");
            labelTitle.setText("");
        }
    }

   /*
   Dopo avere cercato se il titolo della notizia esiste, posso eliminare.
    */
    @FXML
    public void deleteNews(ActionEvent event) throws IOException {
        List<Notizia> news = readFile(path);
        if(labelTitle.getText().isEmpty()){
            labelError.setText("Attenzione, prima devi cercare e selezionare il titolo della notizia");
        }else{
            labelError.setText("");
            for(int i = 0; i < news.size(); i++){
                if(news.get(i).getTitle().equals(inputTitle.getText())){
                    news.remove(i);
                    writeFile(news, path);
                }
            }
            labelProperRemoval.setText("Il titolo della notizia: " + inputTitle.getText() + " da te cercato, è stato rimosso correttamente");
            System.out.println("la notizia è stata rimossa");
            inputTitle.setText("");
            labelTitle.setText("");
        }
    }
    @FXML
    public void goBackHomePage(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("grafica/HomepageForm.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Homepage di Naboo");
        stage.setScene(scene);
        stage.show();
    }
}

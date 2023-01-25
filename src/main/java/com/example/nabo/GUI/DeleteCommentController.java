package com.example.nabo.GUI;

import com.example.nabo.CommentoBot;
import com.example.nabo.Main;
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

public class DeleteCommentController {
        @FXML
        private Button goBackHome;
        @FXML
        private Button searchComment;
        @FXML
        private Button deleteComment;
        @FXML
        private Label labelError;
        @FXML
        private Label labelAllRight;
        @FXML
        private TextField inputTesto;
        @FXML
        private TextField inputTitolo;
        @FXML
        private TextField inputCommentatore;
        private static String path = "C:\\Users\\feder\\IdeaProjects\\Progetto-Naboo\\src\\main\\resources\\com\\example\\nabo\\DataBase\\Commento.json";

       public static List<CommentoBot> readFile(String path) throws FileNotFoundException {
           Gson gson = new Gson();
           JsonReader reader = new JsonReader(new FileReader(path));
           return gson.fromJson(reader, new TypeToken<List<CommentoBot>>(){}.getType());
       }
       public static void writeFile(List<CommentoBot> commento, String path) throws IOException {
           Gson gson = new GsonBuilder().setPrettyPrinting().create();
           List<CommentoBot> listCommenti = new ArrayList<>(commento);
           String jsonString = gson.toJson(listCommenti);
           FileWriter fw = new FileWriter(path);
           fw.write(jsonString);
           fw.close();
       }

       @FXML
       public void search(ActionEvent event) throws FileNotFoundException {
           System.out.println("entrato in search");
           labelError.setText("");
           List<CommentoBot> commento = readFile(path);
           boolean commentoFound = false;
           for(CommentoBot value : commento){
               System.out.println("entrato in for");
               if(value.getTesto().equals(inputTesto.getText()) && value.getTitolo().equals(inputTitolo.getText()) && value.getCommentatore().equals(inputCommentatore.getText())){
                   commentoFound = true;
                   System.out.println("entrato in if");
                   labelAllRight.setText("il commento " + value.getTesto() + " da te cercato esiste");
                   System.out.println(value.getTesto());
               }else{
                   System.out.println("entrato in else");
               }
           }
           if(!commentoFound){
               System.out.println("commento non trovat");
               labelError.setText("commento non esistente");
               labelAllRight.setText("");
               inputTesto.setText("");
               inputTitolo.setText("");
               inputCommentatore.setText("");
           }
       }
       @FXML
       public void deleteComment(ActionEvent event) throws IOException {
           System.out.println("entrato in delete");
           List<CommentoBot> commento = readFile(path);
           if(inputTesto.getText().isEmpty()){
               labelError.setText("Attenzione campo vuoto");
           }else{
               labelError.setText("");
               for(CommentoBot value : commento){
                   System.out.println("for delete");
                   if(value.getTesto().equals(inputTesto.getText()) && value.getCommentatore().equals(inputCommentatore.getText()) && value.getTitolo().equals(inputTitolo.getText())){
                       System.out.println("if for delete");
                       commento.remove(value);
                       writeFile(commento, path);
                   }
               }
               labelAllRight.setText("il commento " + inputTesto.getText() + " da te cercato è stato rimosso correttamente");
               labelError.setText("");
               inputTesto.setText("");
               inputCommentatore.setText("");
               inputTitolo.setText("");
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

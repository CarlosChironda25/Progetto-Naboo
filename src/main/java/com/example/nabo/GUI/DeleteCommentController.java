package com.example.nabo.GUI;

import com.example.nabo.CommentoBot;
import com.example.nabo.Main;
import com.example.nabo.Utente;
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
        public Button goBackHome;
        @FXML
        public Button searchComment;
        @FXML
        public Button deleteComment;
        @FXML
        public Label labelError;
        @FXML
        public Label labelTesto;
        @FXML
        public Label labelProperRemoval;
        @FXML
        public TextField inputTesto;
        private static String path = "C:\\Users\\feder\\IdeaProjects\\Progetto-Naboo\\src\\main\\resources\\com\\example\\nabo\\DataBase\\Commento.json";
        public static List<CommentoBot> readFile(String path) throws FileNotFoundException {
            Gson gson = new Gson();
            JsonReader reader = new JsonReader(new FileReader(path));
            return gson.fromJson(reader, new TypeToken<List<Utente>>(){}.getType());
        }

        public static void writeFile(List<CommentoBot> commento, String path) throws IOException {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            List<CommentoBot> listComment = new ArrayList<>(commento);
            String jsonString = gson.toJson(listComment);
            FileWriter fw = new FileWriter(path);
            fw.write(jsonString);
            fw.close();
        }

        @FXML
        public void search(ActionEvent event) throws FileNotFoundException {
            labelError.setText("");
            List<CommentoBot> commento = readFile(path);
            boolean commentFound = false;
            for (CommentoBot value : commento) {
                if (value.getTesto().equals(inputTesto.getText())) {
                    commentFound = true;
                    labelTesto.setText(value.getTesto());
                }
            }
            if(!commentFound){
                labelError.setText("commento rilasciato non esistente");
                labelTesto.setText("");
            }
        }

        @FXML
        public void deleteComment(ActionEvent event) throws IOException {
            List<CommentoBot> commento = readFile(path);
            if(labelTesto.getText().isEmpty()){
                labelError.setText("Attenzione, sembra che tu non abbia cercato nessun utente");
            }else{
                labelError.setText("");
                for(int i = 0; i < commento.size(); i++){
                    if(commento.get(i).getTesto().equals(inputTesto.getText())){
                        commento.remove(i);
                        writeFile(commento, path);
                    }
                }
                labelProperRemoval.setText("il commento: " + inputTesto.getText() + "da te cercato, è stato rimosso correttamente");
                System.out.println("il commento è stato rimosso correttamente");
                inputTesto.setText("");
                labelTesto.setText("");
            }
        }
        @FXML
        public void goBackHomePage(ActionEvent event) throws IOException {
            Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("grafica/HomepageForm.fxml")));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
}

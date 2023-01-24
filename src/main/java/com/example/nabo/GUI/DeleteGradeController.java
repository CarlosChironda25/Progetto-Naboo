package com.example.nabo.GUI;

import com.example.nabo.Main;
import com.example.nabo.VotoBot;
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

public class DeleteGradeController {
    @FXML
    public Button goBackHome;
    @FXML
    public Button searchGrade;
    @FXML
    public Button deleteGrade;
    @FXML
    public TextField inputGrade;
    @FXML
    public TextField inputNotizia;
    @FXML
    public TextField inputCommentatore;
    @FXML
    public Label labelError;
    @FXML
    public Label labelAllRight;
    double grade;

    private static String path = "C:\\Users\\feder\\IdeaProjects\\Progetto-Naboo\\src\\main\\resources\\com\\example\\nabo\\DataBase\\Voto.json";

    public static List<VotoBot> readFile(String path) throws FileNotFoundException {
        Gson gson = new Gson();
        JsonReader reader = new JsonReader(new FileReader(path));
        return gson.fromJson(reader, new TypeToken<List<VotoBot>>(){}.getType());
    }
    public static void writeFile(List<VotoBot> voto, String path) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<VotoBot> listGrades = new ArrayList<>(voto);
        String jsonString = gson.toJson(listGrades);
        FileWriter fw = new FileWriter(path);
        fw.write(jsonString);
        fw.close();
    }
    @FXML
    public void search(ActionEvent event) throws FileNotFoundException {
        labelError.setText("");
        List<VotoBot> voto = readFile(path);
        boolean gradeFound = false;
        for (VotoBot value : voto) {
            grade = Double.parseDouble(inputGrade.getText());
            if (value.getVoto() == grade && value.getTitolo().equals(inputNotizia.getText()) && value.getCommentatore().equals(inputCommentatore.getText())) {
                gradeFound = true;
                labelAllRight.setText("il commento: " + value.getVoto() + " esiste");
            }

        }
        if (!gradeFound) {
            labelError.setText("voto rilasciato non esistente");
            labelAllRight.setText("");
            inputGrade.setText("");
            inputNotizia.setText("");
            inputCommentatore.setText("");
        }
    }
    @FXML
    public void deleteGrade(ActionEvent event) throws IOException {
        List<VotoBot> voto = readFile(path);
        if(inputGrade.getText().isEmpty() || inputNotizia.getText().isEmpty() || inputCommentatore.getText().isEmpty()){
            labelError.setText("Attenzione, tutti i campi sono obbligatori");
        }else{
            labelError.setText("");
            for(VotoBot value : voto ){
                grade = Double.parseDouble(inputGrade.getText());
                if(value.getVoto() == grade && value.getTitolo().equals(inputNotizia.getText()) && value.getCommentatore().equals(inputCommentatore.getText())){
                    voto.remove(value);
                    writeFile(voto, path);
                }
            }
            labelAllRight.setText("il voto: " + inputGrade.getText() + "da te cercato, è stato rimosso correttamente");
            labelError.setText("");
            inputGrade.setText("");
            inputNotizia.setText("");
            inputCommentatore.setText("");
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

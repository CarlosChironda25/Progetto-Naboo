package com.example.nabo.GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try{
            Parent root = FXMLLoader.load(getClass().getResource("C:\\Users\\feder\\IdeaProjects\\Progetto-Naboo\\src\\main\\resources\\com\\example\\nabo\\grafica\\Login.fxml"));
            Scene scene = new Scene(root);

            primaryStage.setTitle("Benvenuto in Naboo. Effettua il login");

            primaryStage.setScene(scene);
            primaryStage.show();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    /*
    void NuovoUtente(ActionEvent event) throws IOException {
        root =FXMLLoader.load(getClass().getResource("NuovoUtente.fxml"));
        stage= (Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
     */

    public static void main(String[] args) {
        launch();
    }
}
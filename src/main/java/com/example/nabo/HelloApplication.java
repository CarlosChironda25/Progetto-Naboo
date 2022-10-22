package com.example.nabo;


import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.util.Objects;

public class HelloApplication extends Application {


    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("NABOO");
        stage.setScene(scene);
        stage.show();

       //scene.getStylesheets().add( Objects.requireNonNull(getClass().getResource("GraficeColor.css")).toExternalForm());
        // String css= (this.getClass().getResource("GraficeColor.css")).toExternalForm();
          // scene.getStylesheets().add(css);


    }

    public static void main(String[] args) {
        launch(args);
    }
}
/*
echo "# Naboo" >> README.md
git init
git add README.md
git commit -m "first commit"
git branch -M main
git remote add origin https://github.com/CarlosChironda25/Naboo.git
git push -u origin main
////
git remote add origin https://github.com/CarlosChironda25/Naboo.git
git branch -M main
 ssh-keygen -t ed25519 -C "carlosvasco.chironda@studio.unibo.it"

 */
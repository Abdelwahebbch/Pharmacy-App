package com.pharmacy.app;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/PrimaryStage.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Login Now !");
        primaryStage.setResizable(false);
        //primaryStage.setFullScreen(true);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
        
    }

}
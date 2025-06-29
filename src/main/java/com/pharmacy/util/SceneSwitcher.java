package com.pharmacy.util;

import java.io.IOException;

import javafx.animation.FadeTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SceneSwitcher {

    public static void switchSceneWithFade(Stage stage, Scene newScene, double durationInSeconds) {
        Scene currentScene = stage.getScene();

        if (currentScene != null) {
            FadeTransition fadeOut = new FadeTransition(Duration.seconds(durationInSeconds), currentScene.getRoot());
            fadeOut.setFromValue(1.0);
            fadeOut.setToValue(0.0);

            fadeOut.setOnFinished(e -> {
                stage.setScene(newScene);

                FadeTransition fadeIn = new FadeTransition(Duration.seconds(durationInSeconds), newScene.getRoot());
                fadeIn.setFromValue(0.0);
                fadeIn.setToValue(1.0);
                fadeIn.play();
            });

            fadeOut.play();
        } else {
            stage.setScene(newScene);
        }
    }

    public static void setContent(AnchorPane targetPane, String fxmlPath) {
        try {
            Parent node = FXMLLoader.load(SceneSwitcher.class.getResource(fxmlPath));
            AnchorPane.setTopAnchor(node, 0.0);
            AnchorPane.setBottomAnchor(node, 0.0);
            AnchorPane.setLeftAnchor(node, 0.0);
            AnchorPane.setRightAnchor(node, 0.0);
            targetPane.getChildren().setAll(node);
        } catch (IOException e) {
            System.err.println("Erreur !!");
            System.err.println(e.getMessage());
            // e.printStackTrace();
        }
    }


}

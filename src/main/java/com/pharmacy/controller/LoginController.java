package com.pharmacy.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.pharmacy.util.DataBaseConnection;
import com.pharmacy.util.SceneSwitcher;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private PasswordField pwd;

    @FXML
    private TextField userName;
    @FXML
    private Label errorLabel;

    @FXML

    private void Btnlogin(MouseEvent event) throws Exception {
        String username = userName.getText();
        String pwrd = pwd.getText();

        if (authenticate(username, pwrd)) {
            errorLabel.setText("Connexion réussie !");
            errorLabel.setStyle("-fx-text-fill: green;");

            Parent root = FXMLLoader.load(getClass().getResource("/fxml/PrimaryStage.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
          //  SceneSwitcher.setContent(DashboardController.MainScene, "/fxml/Dashboard.fxml");
            stage.setScene(new Scene(root));
            stage.show();
        } else {
            errorLabel.setText("Identifiants incorrects !");
            errorLabel.setStyle("-fx-text-fill: red;");
        }

    }

    private boolean authenticate(String username, String pwrd) throws Exception {

        String query = "SELECT user_name FROM users WHERE user_name = ? AND user_password = ?";

        try (Connection connection = DataBaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, pwrd);
            ResultSet resultSet = preparedStatement.executeQuery();

            return resultSet.next();
        }
    }

    @FXML
    void register(MouseEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/register.fxml"));
        Scene newScene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        SceneSwitcher.switchSceneWithFade(stage, newScene, 0);
    }

}

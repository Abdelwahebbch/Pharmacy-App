package com.pharmacy.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.pharmacy.app.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController extends App {
    // protected static final String url = "jdbc:oracle:thin:@localhost:1521:FREE";
    // protected static final String user = "pharmacydatabase";
    // protected static final String password = "1234";

    @FXML
    private PasswordField pwd;

    @FXML
    private TextField userName;
    @FXML
    private Label errorLabel;

    @FXML

    private void Btnlogin() throws Exception {
        String username = userName.getText();
        String pwrd = pwd.getText();

        if (authenticate(username, pwrd)) {
            errorLabel.setText("Connexion réussie !");
            errorLabel.setStyle("-fx-text-fill: green;");
        } else {
            errorLabel.setText("Identifiants incorrects !");
            errorLabel.setStyle("-fx-text-fill: red;");
        }
    }

    private boolean authenticate(String username, String pwrd) throws Exception {

        String query = "SELECT user_name FROM users WHERE user_name = ? AND user_password = ?";

        try (Connection connection = DriverManager.getConnection(url, user, password);
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, pwrd);
            ResultSet resultSet = preparedStatement.executeQuery();

            return resultSet.next();
        }
    }
}

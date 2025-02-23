package com.pharmacy.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class RegisterController {

    @FXML
    private TextField email;

    @FXML
    private TextField lastname;

    @FXML
    private TextField name;

    @FXML
    private PasswordField password;

    @FXML
    private TextField phoneNb;

    @FXML
    private PasswordField secpass;

    @FXML
    void addUser(ActionEvent event) {

    }

    @FXML
    void login(MouseEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("/fxml/LoginScene.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(new Scene(root));
        stage.show();

    }

}

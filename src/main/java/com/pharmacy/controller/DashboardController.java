package com.pharmacy.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class DashboardController {

    @FXML
    private Button logoutButton;

    @FXML
    private void initialize() {
        logoutButton.setOnAction(event -> handleLogout());
    }

    private void handleLogout() {
        System.out.println("Logout clicked");
        // TODO: implement logout logic
    }
}

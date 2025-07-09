package com.pharmacy.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.pharmacy.util.SceneSwitcher;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class PrimaryStageController implements Initializable {

    @FXML
    private Button LogoutButton;

    @FXML
    private AnchorPane MainScene;

    @FXML
    private Button dashboardButton;

    @FXML
    private Button inventoryButton;
    @FXML
    private Button patientsButton;
    @FXML
    private Button prescriptionsButton;

    @FXML
    private Button settingsButton;

    @FXML
    private Button salesButton;

    @FXML
    void handleSalesButton(ActionEvent event) {
        SceneSwitcher.setContent(MainScene, "/fxml/SalesScene.fxml");
    }

    @FXML
    void handleDashboardButton() {
        SceneSwitcher.setContent(MainScene, "/fxml/Dashboard.fxml");
    }

    @FXML
    void handlePatientsButton(ActionEvent event) {
        SceneSwitcher.setContent(MainScene, "/fxml/PatientsScene.fxml");
    }

    @FXML
    void handleInventoryButton(ActionEvent event) throws IOException {

        SceneSwitcher.setContent(MainScene, "/fxml/InventoryX.fxml");
    }

    @FXML
    void handlePrescriptionsButton(ActionEvent event) throws IOException {

        SceneSwitcher.setContent(MainScene, "/fxml/Prescription.fxml");
    }

    @FXML
    void handleSettingsButton(ActionEvent event) throws IOException {
        SceneSwitcher.setContent(MainScene, "/fxml/Settings.fxml");
    }

    @FXML
    void handleLogoutButton(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SceneSwitcher.setContent(MainScene, "/fxml/SalesScene.fxml");
    }

}

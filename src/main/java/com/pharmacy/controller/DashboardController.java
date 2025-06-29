package com.pharmacy.controller;

import java.io.IOException;

import com.pharmacy.util.SceneSwitcher;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class DashboardController {
    @FXML
    public AnchorPane MainScene;

    @FXML
    private Button LogoutButton;

    @FXML
    private PieChart categoryPieChart;

    @FXML
    private Button dashboardButton;

    @FXML
    private Label expiringItemsLabel;

    @FXML
    private Button inventoryButton;

    @FXML
    private Label lowStockLabel;

    @FXML
    private TableView<?> lowStockTable;

    @FXML
    private Button prescriptionsButton;

    @FXML
    private TableView<?> recentSalesTable;

    @FXML
    private BarChart<?, ?> salesBarChart;

    @FXML
    private Button settingsButton;

    @FXML
    private Label totalPrescriptionsLabel;

    @FXML
    private Label totalSalesLabel;

    @FXML
    private LineChart<?, ?> trendsLineChart;

    @FXML
    void handleDashboardButton() {
        SceneSwitcher.setContent(MainScene, "/fxml/Dashboard.fxml");
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

}

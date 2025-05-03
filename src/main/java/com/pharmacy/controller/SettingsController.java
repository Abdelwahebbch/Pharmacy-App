package com.pharmacy.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class SettingsController implements Initializable {

    @FXML private ComboBox<String> themeComboBox;
    @FXML private ComboBox<String> languageComboBox;
    @FXML private TextField backupLocationField;
    @FXML private Button browseButton;
    @FXML private Spinner<Integer> autoSaveIntervalSpinner;
    @FXML private CheckBox enableNotificationsCheckbox;
    @FXML private CheckBox enableAutoBackupCheckbox;
    @FXML private VBox autoBackupSettingsVBox;
    @FXML private ComboBox<String> backupFrequencyComboBox;
    @FXML private Button saveSettingsButton;
    @FXML private Button resetSettingsButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialize theme options
        themeComboBox.setItems(FXCollections.observableArrayList(
            "Light", "Dark", "System Default"
        ));
        themeComboBox.setValue("Light");
        
        // Initialize language options
        languageComboBox.setItems(FXCollections.observableArrayList(
            "English", "Spanish", "French", "German", "Chinese"
        ));
        languageComboBox.setValue("English");
        
        // Initialize auto-save spinner
        SpinnerValueFactory<Integer> valueFactory = 
            new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 60, 5);
        autoSaveIntervalSpinner.setValueFactory(valueFactory);
        
        // Initialize backup frequency options
        backupFrequencyComboBox.setItems(FXCollections.observableArrayList(
            "Daily", "Weekly", "Monthly"
        ));
        backupFrequencyComboBox.setValue("Weekly");
        
        // Set default backup location
        backupLocationField.setText(System.getProperty("user.home") + "/PharmacyBackups");
        
        // Set default checkbox values
        enableNotificationsCheckbox.setSelected(true);
        enableAutoBackupCheckbox.setSelected(true);
        
        // Setup listeners
        setupListeners();
    }
    
    private void setupListeners() {
        // Toggle visibility of auto backup settings based on checkbox
        enableAutoBackupCheckbox.selectedProperty().addListener((obs, oldVal, newVal) -> {
            autoBackupSettingsVBox.setDisable(!newVal);
        });
    }
    
    @FXML
    private void handleBrowseButton() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Select Backup Location");
        
        // Set initial directory to current backup location if it exists
        String currentPath = backupLocationField.getText();
        File initialDirectory = new File(currentPath);
        if (initialDirectory.exists()) {
            directoryChooser.setInitialDirectory(initialDirectory);
        }
        
        // Show directory chooser dialog
        File selectedDirectory = directoryChooser.showDialog(browseButton.getScene().getWindow());
        if (selectedDirectory != null) {
            backupLocationField.setText(selectedDirectory.getAbsolutePath());
        }
    }
    
    @FXML
    private void handleSaveSettings() {
        // Here you would save settings to a configuration file or database
        // For this example, we'll just show a confirmation alert
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Settings Saved");
        alert.setHeaderText(null);
        alert.setContentText("Your settings have been saved successfully.");
        alert.showAndWait();
    }
    
    @FXML
    private void handleResetSettings() {
        // Reset to default values
        themeComboBox.setValue("Light");
        languageComboBox.setValue("English");
        backupLocationField.setText(System.getProperty("user.home") + "/PharmacyBackups");
        autoSaveIntervalSpinner.getValueFactory().setValue(5);
        enableNotificationsCheckbox.setSelected(true);
        enableAutoBackupCheckbox.setSelected(true);
        backupFrequencyComboBox.setValue("Weekly");
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Settings Reset");
        alert.setHeaderText(null);
        alert.setContentText("Settings have been reset to default values.");
        alert.showAndWait();
    }
    
    @FXML
    private void handleCreateBackupNow() {
        // Simulate backup creation
        ProgressIndicator progress = new ProgressIndicator();
        progress.setProgress(ProgressIndicator.INDETERMINATE_PROGRESS);
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Backup");
        alert.setHeaderText("Creating Backup");
        alert.setContentText("Creating backup at: " + backupLocationField.getText());
        alert.getDialogPane().setGraphic(progress);
        
        // Simulate backup process with a separate thread
        Thread backupThread = new Thread(() -> {
            try {
                // Simulate backup process
                Thread.sleep(2000);
                
                // Update UI on JavaFX thread
                javafx.application.Platform.runLater(() -> {
                    progress.setProgress(1.0);
                    alert.setHeaderText("Backup Complete");
                    alert.setContentText("Backup has been created successfully at: " + backupLocationField.getText());
                });
                
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        
        backupThread.setDaemon(true);
        backupThread.start();
        
        alert.showAndWait();
    }
}

package com.pharmacy.controller;
import com.pharmacy.Model.Medication;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Button;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;



public class InventoryController implements Initializable {

    @FXML private TableView<Medication> medicationTable;
    @FXML private TextField nameField;
    @FXML private TextField priceField;
    @FXML private TextField quantityField;
    @FXML private ComboBox<String> categoryComboBox;
    @FXML private DatePicker expiryDatePicker;
    @FXML private Button addButton;
    @FXML private Button updateButton;
    @FXML private Button deleteButton;
    @FXML private TextField searchField;

    private ObservableList<Medication> medicationList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialize category options
        categoryComboBox.setItems(FXCollections.observableArrayList(
            "Antibiotics", "Pain Relief", "Vitamins", "Cardiac", "Respiratory", "Gastrointestinal", "Others"
        ));
        
        // Add sample data
        medicationList.addAll(
            new Medication("Amoxicillin", "Antibiotics", 12.99, 150, LocalDate.now().plusMonths(12)),
            new Medication("Ibuprofen", "Pain Relief", 8.50, 200, LocalDate.now().plusMonths(18)),
            new Medication("Vitamin C", "Vitamins", 15.75, 80, LocalDate.now().plusMonths(24)),
            new Medication("Lisinopril", "Cardiac", 22.50, 45, LocalDate.now().plusMonths(9)),
            new Medication("Ventolin", "Respiratory", 35.99, 30, LocalDate.now().plusMonths(15))
        );
        
        // Set up the table
        medicationTable.setItems(medicationList);
    }
    
    @FXML
    private void handleAddMedication() {
        try {
            String name = nameField.getText();
            String category = categoryComboBox.getValue();
            double price = Double.parseDouble(priceField.getText());
            int quantity = Integer.parseInt(quantityField.getText());
            LocalDate expiryDate = expiryDatePicker.getValue();
            
            if (name.isEmpty() || category == null || expiryDate == null) {
                // Show error message
                return;
            }
            
            Medication newMedication = new Medication(name, category, price, quantity, expiryDate);
            medicationList.add(newMedication);
            
            // Clear fields
            clearFields();
            
        } catch (NumberFormatException e) {
            // Show error message for invalid number format
        }
    }
    
    @FXML
    private void handleUpdateMedication() {
        Medication selectedMedication = medicationTable.getSelectionModel().getSelectedItem();
        if (selectedMedication == null) {
            // Show error message
            return;
        }
        
        try {
            String name = nameField.getText();
            String category = categoryComboBox.getValue();
            double price = Double.parseDouble(priceField.getText());
            int quantity = Integer.parseInt(quantityField.getText());
            LocalDate expiryDate = expiryDatePicker.getValue();
            
            if (name.isEmpty() || category == null || expiryDate == null) {
                // Show error message
                return;
            }
            
            selectedMedication.setName(name);
            selectedMedication.setCategory(category);
            selectedMedication.setPrice(price);
            selectedMedication.setQuantity(quantity);
            selectedMedication.setExpiryDate(expiryDate);
            
            // Refresh table
            medicationTable.refresh();
            
            // Clear fields
            clearFields();
            
        } catch (NumberFormatException e) {
            // Show error message for invalid number format
        }
    }
    
    @FXML
    private void handleDeleteMedication() {
        Medication selectedMedication = medicationTable.getSelectionModel().getSelectedItem();
        if (selectedMedication == null) {
            // Show error message
            return;
        }
        
        medicationList.remove(selectedMedication);
        clearFields();
    }
    
    @FXML
    private void handleSearchMedication() {
        String searchTerm = searchField.getText().toLowerCase();
        
        if (searchTerm.isEmpty()) {
            medicationTable.setItems(medicationList);
            return;
        }
        
        ObservableList<Medication> filteredList = FXCollections.observableArrayList();
        for (Medication medication : medicationList) {
            if (medication.getName().toLowerCase().contains(searchTerm) || 
                medication.getCategory().toLowerCase().contains(searchTerm)) {
                filteredList.add(medication);
            }
        }
        
        medicationTable.setItems(filteredList);
    }
    
    @FXML
    private void handleMedicationSelection() {
        Medication selectedMedication = medicationTable.getSelectionModel().getSelectedItem();
        if (selectedMedication != null) {
            nameField.setText(selectedMedication.getName());
            categoryComboBox.setValue(selectedMedication.getCategory());
            priceField.setText(String.valueOf(selectedMedication.getPrice()));
            quantityField.setText(String.valueOf(selectedMedication.getQuantity()));
            expiryDatePicker.setValue(selectedMedication.getExpiryDate());
        }
    }
    
    private void clearFields() {
        nameField.clear();
        categoryComboBox.setValue(null);
        priceField.clear();
        quantityField.clear();
        expiryDatePicker.setValue(null);
    }
}

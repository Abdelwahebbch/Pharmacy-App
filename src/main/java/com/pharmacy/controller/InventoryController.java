package com.pharmacy.controller;

import com.pharmacy.DAO.MedicationDAO;
import com.pharmacy.Model.Medication;
import com.pharmacy.Validation.Validators;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.Button;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

public class InventoryController implements Initializable {

    @FXML
    private TableView<Medication> medicationTable;
    @FXML
    private TextField nameField;
    @FXML
    private TextField priceField;
    @FXML
    private TextField quantityField;
    @FXML
    private ComboBox<String> categoryComboBox;
    @FXML
    private DatePicker expiryDatePicker;
    @FXML
    private Button addButton;
    @FXML
    private Button updateButton;
    @FXML
    private Button deleteButton;
    @FXML
    private TextField searchField;
    @FXML
    private TableColumn<?, ?> name;
    @FXML
    private TableColumn<?, ?> price;
    @FXML
    private TableColumn<?, ?> quantity;
    @FXML
    private TableColumn<?, ?> category;
    @FXML
    private TableColumn<?, ?> exp;

    private ObservableList<Medication> medicationList = FXCollections.observableArrayList();
    private ObservableList<Medication> newMedicationsList = FXCollections.observableArrayList();
    private ObservableList<Medication> updatedMedicationsList = FXCollections.observableArrayList();
    private ObservableList<Medication> deletedMedicationsList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        category.setCellValueFactory(new PropertyValueFactory<>("category"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        exp.setCellValueFactory(new PropertyValueFactory<>("expiryDate"));
        MedicationDAO.LoadAllmedications(medicationList);
        categoryComboBox.setItems(FXCollections.observableArrayList(
                "Antibiotics", "Pain Relief", "Vitamins", "Cardiac", "Respiratory", "Gastrointestinal", "Others"));
        medicationTable.setItems(medicationList);
    }
    
    @FXML
    private void handleAddMedication() {
        try {
            String name = nameField.getText();
            String category = categoryComboBox.getValue();
            double price = Double.parseDouble(priceField.getText());
            int quantity = Integer.parseInt(quantityField.getText());
            Date expiryDate = java.sql.Date.valueOf(expiryDatePicker.getValue());
            
            if (name.isEmpty() || category == null || expiryDate == null || price < 0 || quantity < 0
            || expiryDate == null || !Validators.MedicationSearch(medicationList, name)) {
                System.err.println("Already exist ");
                // TODO : nzid alert
                return;
            }
            Medication newMedication = new Medication("None", name, category, price, quantity, expiryDate);
            newMedicationsList.add(newMedication);
            MedicationDAO.addMedications(newMedicationsList);
            MedicationDAO.LoadAllmedications(medicationList);
            medicationTable.setItems(medicationList);
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
            Date expiryDate = java.sql.Date.valueOf(expiryDatePicker.getValue());

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
            updatedMedicationsList
                    .add(new Medication(selectedMedication.getId(), name, category, price, quantity, expiryDate));
            MedicationDAO.updateMedications(updatedMedicationsList);
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

        deletedMedicationsList.add(selectedMedication);
        MedicationDAO.deleteMedications(deletedMedicationsList);
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
            expiryDatePicker.setValue(selectedMedication.getExpiryDate().toLocalDate());
        }
    }

    @FXML
    private void clearFields() {
        nameField.clear();
        categoryComboBox.setValue(null);
        priceField.clear();
        quantityField.clear();
        expiryDatePicker.setValue(null);
    }
}

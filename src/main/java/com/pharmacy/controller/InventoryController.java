package com.pharmacy.controller;

import com.pharmacy.DAO.MedicationDAO;
import com.pharmacy.Model.Medication;
import com.pharmacy.Validation.Validators;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class InventoryController implements Initializable {

    @FXML private TableView<Medication> medicationTable;
    @FXML private TextField nameField, priceField, quantityField, searchField;
    @FXML private ComboBox<String> categoryComboBox;
    @FXML private DatePicker expiryDatePicker;
    @FXML private Button addButton, updateButton, deleteButton;
    @FXML private TableColumn<Medication, String> name, category;
    @FXML private TableColumn<Medication, Double> price;
    @FXML private TableColumn<Medication, Integer> quantity;
    @FXML private TableColumn<Medication, Date> exp;

    private final ObservableList<Medication> medicationList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeColumns();
        loadMedications();
        categoryComboBox.setItems(FXCollections.observableArrayList(
                "Antibiotics", "Pain Relief", "Vitamins", "Cardiac", "Respiratory", "Gastrointestinal", "Others"
        ));
    }

    private void initializeColumns() {
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        category.setCellValueFactory(new PropertyValueFactory<>("category"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        exp.setCellValueFactory(new PropertyValueFactory<>("expiryDate"));
    }

    private void loadMedications() {
        medicationList.clear();
        MedicationDAO.LoadAllmedications(medicationList);
        medicationTable.setItems(medicationList);
    }

    @FXML
    private void handleAddMedication() {
        Medication med = buildMedicationFromFields();
        if (med == null) return;

        if (!Validators.MedicationSearch(medicationList, med.getName())) {
            System.err.println("Medication already exists.");
            // TODO: replace with Alert
            return;
        }

        MedicationDAO.addMedications(FXCollections.observableArrayList(med));
        loadMedications();
        clearFields();
    }

    @FXML
    private void handleUpdateMedication() {
        Medication selected = medicationTable.getSelectionModel().getSelectedItem();
        if (selected == null) return;

        Medication updated = buildMedicationFromFields();
        if (updated == null) return;

        updated.setId(selected.getId());

        MedicationDAO.updateMedications(FXCollections.observableArrayList(updated));
        loadMedications();
        clearFields();
    }

    @FXML
    private void handleDeleteMedication() {
        Medication selected = medicationTable.getSelectionModel().getSelectedItem();
        if (selected == null) return;

        MedicationDAO.deleteMedications(FXCollections.observableArrayList(selected));
        medicationList.remove(selected);
        clearFields();
    }

    @FXML
    private void handleSearchMedication() {
        String searchTerm = searchField.getText().trim().toLowerCase();

        if (searchTerm.isEmpty()) {
            medicationTable.setItems(medicationList);
            return;
        }

        ObservableList<Medication> filtered = FXCollections.observableArrayList();
        for (Medication med : medicationList) {
            if (med.getName().toLowerCase().contains(searchTerm)
                    || med.getCategory().toLowerCase().contains(searchTerm)) {
                filtered.add(med);
            }
        }
        medicationTable.setItems(filtered);
    }

    @FXML
    private void handleMedicationSelection() {
        Medication selected = medicationTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            nameField.setText(selected.getName());
            categoryComboBox.setValue(selected.getCategory());
            priceField.setText(String.valueOf(selected.getPrice()));
            quantityField.setText(String.valueOf(selected.getQuantity()));
            expiryDatePicker.setValue(selected.getExpiryDate().toLocalDate());
        }
    }

    @FXML
    private void clearFields() {
        nameField.clear();
        priceField.clear();
        quantityField.clear();
        categoryComboBox.setValue(null);
        expiryDatePicker.setValue(null);
    }

    private Medication buildMedicationFromFields() {
        try {
            String name = nameField.getText().trim();
            String category = categoryComboBox.getValue();
            LocalDate localDate = expiryDatePicker.getValue();
            double price = Double.parseDouble(priceField.getText().trim());
            int quantity = Integer.parseInt(quantityField.getText().trim());

            if (name.isEmpty() || category == null || localDate == null || price < 0 || quantity < 0) {
                System.err.println("Invalid fields");
                return null;
            }

            Date expiryDate = Date.valueOf(localDate);
            return new Medication( name, category, price, quantity, expiryDate);
        } catch (NumberFormatException e) {
            System.err.println("Invalid number format");
            return null;
        }
    }
}

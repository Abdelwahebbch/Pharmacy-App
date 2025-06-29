package com.pharmacy.controller;

import com.pharmacy.Model.Prescription;
import com.pharmacy.util.DataBaseConnection;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class PrescriptionController implements Initializable {

    @FXML
    private Button addButton;

    @FXML
    private Button clearButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button updateButton;

    @FXML
    private TextField patientNameField;

    @FXML
    private TextField doctorNameField;

    @FXML
    private TextArea medicationsArea;

    @FXML
    private ComboBox<String> patientComboBox;

    @FXML
    private ComboBox<String> statusComboBox;

    @FXML
    private DatePicker issueDatePicker;

    @FXML
    private DatePicker expiryDatePicker;

    @FXML
    private TextField searchField;

    @FXML
    private TableView<Prescription> prescriptionTable;

    @FXML
    private TableColumn<Prescription, Integer> idColumn;

    @FXML
    private TableColumn<Prescription, String> patientNameColumn;

    @FXML
    private TableColumn<Prescription, String> doctorNameColumn;

    @FXML
    private TableColumn<Prescription, Date> issueDateColumn;

    @FXML
    private TableColumn<Prescription, Date> expiryDateColumn;

    @FXML
    private TableColumn<Prescription, String> statusColumn;

    private ObservableList<Prescription> prescriptionList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Configurer les colonnes
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        patientNameColumn.setCellValueFactory(new PropertyValueFactory<>("patientName"));
        doctorNameColumn.setCellValueFactory(new PropertyValueFactory<>("doctorName"));
        issueDateColumn.setCellValueFactory(new PropertyValueFactory<>("issueDate"));
        expiryDateColumn.setCellValueFactory(new PropertyValueFactory<>("expiryDate"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        // Remplir combo box statut
        statusComboBox.setItems(FXCollections.observableArrayList("Malade", "Others"));
        issueDatePicker.setValue(LocalDate.now());

        // Exemple de requête pour remplir la table avec des prescriptions fictives
        String query = "SELECT * FROM prescriptions ";

        try (Connection conn = DataBaseConnection.getConnection();
                PreparedStatement stm = conn.prepareStatement(query);
                ResultSet res = stm.executeQuery()) {

            while (res.next()) {
                Prescription prescription = new Prescription(
                        res.getString("pres_id"),
                        res.getString("patient_name"),
                        res.getString("doctor_name"),
                        res.getDate("issue_date"),
                        res.getDate("med_exp"),
                        res.getString("Status"),
                        res.getString("medications"));
                prescriptionList.add(prescription);
            }

        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Erreur lors du chargement des données !");
            e.printStackTrace();
        }

        prescriptionTable.setItems(prescriptionList);
    }

    @FXML
    void handleAddPrescription(ActionEvent event) {
        String name = patientNameField.getText();
        String doctor = doctorNameField.getText();
        Date issueDate = java.sql.Date.valueOf(issueDatePicker.getValue());
        Date expiryDate = java.sql.Date.valueOf(expiryDatePicker.getValue());
        String Status = statusComboBox.getValue();
        String medications = medicationsArea.getText();
        if (name.isEmpty() || doctor == null || issueDate == null || Status == null || medications == null
                || expiryDate == null) {
            // Show error message
            return;
        }

        prescriptionList.add(new Prescription(medications, name, doctor, null, null, Status, medications));
    }

    @FXML
    void handleClearFields(ActionEvent event) {
        doctorNameField.clear();
        patientComboBox.setValue(null);
        issueDatePicker.setValue(null);
        expiryDatePicker.setValue(null);
        statusComboBox.setValue(null);
        medicationsArea.clear();
    }

    @FXML
    void handleCreateSale(ActionEvent event) {
        // À implémenter
    }

    @FXML
    void handleDeletePrescription(ActionEvent event) {
        // À implémenter
    }

    @FXML
    void handleNewPatient(ActionEvent event) {
        // À implémenter
    }

    @FXML
    void handlePrescriptionSelection(MouseEvent event) {
        Prescription selectedPrescription = prescriptionTable.getSelectionModel().getSelectedItem();
        if (selectedPrescription != null) {
            patientComboBox.setValue(selectedPrescription.getPatientName());
            doctorNameField.setText(selectedPrescription.getDoctorName());
            issueDatePicker.setValue(selectedPrescription.getIssueDate().toLocalDate());
            expiryDatePicker.setValue(selectedPrescription.getExpiryDate().toLocalDate());
            statusComboBox.setValue(selectedPrescription.getStatus());
            medicationsArea.setText(selectedPrescription.getMedications());
        }
    }

    @FXML
    void handlePrintPrescription(ActionEvent event) {
        // À implémenter
    }

    @FXML
    void handleSearchPrescription(ActionEvent event) {
        String searchTerm = searchField.getText().toLowerCase();

        if (searchTerm.isEmpty()) {
            prescriptionTable.setItems(prescriptionList);
            return;
        }

        ObservableList<Prescription> filteredList = FXCollections.observableArrayList();
        for (Prescription p : prescriptionList) {
            if (p.getDoctorName().toLowerCase().contains(searchTerm) ||
                    p.getPatientName().toLowerCase().contains(searchTerm)) {
                filteredList.add(p);
            }
        }

        prescriptionTable.setItems(filteredList);
    }

    @FXML
    void handleUpdatePrescription(ActionEvent event) {
        // À implémenter
    }
}

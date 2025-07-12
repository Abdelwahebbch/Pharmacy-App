package com.pharmacy.controller;

import com.pharmacy.DAO.PatientsDAO;
import com.pharmacy.DAO.PrescriptionDAO;
import com.pharmacy.Model.Patient;
import com.pharmacy.Model.Prescription;
import com.pharmacy.Validation.Validators;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.Optional;
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
    private TextField PhoneField;

    @FXML
    private TextField doctorNameField;

    @FXML
    private TextArea medicationsArea;

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
    private ObservableList<Prescription> newPrescriptionList = FXCollections.observableArrayList();
    private ObservableList<Prescription> updatedPrescriptionList = FXCollections.observableArrayList();
    private ObservableList<Prescription> deletedPrescriptionList = FXCollections.observableArrayList();
    private ObservableList<Patient> patientsList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Configurer les colonnes
        idColumn.setCellValueFactory(new PropertyValueFactory<>("patientPhone"));
        patientNameColumn.setCellValueFactory(new PropertyValueFactory<>("patientName"));
        doctorNameColumn.setCellValueFactory(new PropertyValueFactory<>("doctorName"));
        issueDateColumn.setCellValueFactory(new PropertyValueFactory<>("issueDate"));
        expiryDateColumn.setCellValueFactory(new PropertyValueFactory<>("expiryDate"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        // Default parameterss
        statusComboBox.setItems(FXCollections.observableArrayList("Malade", "Others"));
        issueDatePicker.setValue(LocalDate.now());

        PrescriptionDAO.loadPrescription(prescriptionList);
        prescriptionTable.setItems(prescriptionList);
        PatientsDAO.LoadAllPatients(patientsList);
    }

    @FXML
    void handleAddPrescription(ActionEvent event) {
        String name = patientNameField.getText();
        String phone = PhoneField.getText();
        String doctor = doctorNameField.getText();
        Date issueDate = java.sql.Date.valueOf(issueDatePicker.getValue());
        Date expiryDate = java.sql.Date.valueOf(expiryDatePicker.getValue());
        String Status = statusComboBox.getValue();
        String medications = medicationsArea.getText();
        if (name.isEmpty() || doctor == null || issueDate == null || Status == null || medications == null
                || expiryDate == null || !Validators.patientPrescriptionValid(patientsList, phone)) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText("There is no patient with this phone you want to create one ?");
            ButtonType yesButton = new ButtonType("Yes");
            ButtonType noButton = new ButtonType("No");

            alert.getButtonTypes().setAll(yesButton, noButton);
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == yesButton) {
                Alert alert2 = new Alert(AlertType.INFORMATION);
                //TODO : create a new patient (patirntDAO) createPatient
                alert2.setContentText("ok !");
                alert2.setHeaderText(null);
                alert2.showAndWait();
            } else {
                Alert alert3 = new Alert(AlertType.INFORMATION);
                alert3.setContentText("User clicked No or closed the dialog");
                alert3.setHeaderText(null);
                alert3.showAndWait();
            }
            return;
        }

        newPrescriptionList.add(new Prescription(phone, name, doctor, issueDate, expiryDate, Status, medications));
        prescriptionList.add(new Prescription(phone, name, doctor, issueDate, expiryDate, Status, medications));
        PrescriptionDAO.AddPrescription(newPrescriptionList);
        prescriptionTable.setItems(prescriptionList);
    }

    @FXML
    void handleClearFields() {
        doctorNameField.clear();
        patientNameField.clear();
        PhoneField.clear();
        issueDatePicker.setValue(null);
        expiryDatePicker.setValue(null);
        statusComboBox.setValue(null);
        medicationsArea.clear();
    }

    @FXML
    void handlePrescriptionSelection() {
        Prescription selectedPrescription = prescriptionTable.getSelectionModel().getSelectedItem();
        if (selectedPrescription != null) {
            patientNameField.setText(selectedPrescription.getPatientName());
            doctorNameField.setText(selectedPrescription.getDoctorName());
            issueDatePicker.setValue(selectedPrescription.getIssueDate().toLocalDate());
            expiryDatePicker.setValue(selectedPrescription.getExpiryDate().toLocalDate());
            statusComboBox.setValue(selectedPrescription.getStatus());
            medicationsArea.setText(selectedPrescription.getMedications());
            PhoneField.setText(selectedPrescription.getPatientPhone());
            ;
        }
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
        Prescription selectedPrescription = prescriptionTable.getSelectionModel().getSelectedItem();
        if (selectedPrescription == null) {
            // Show error message
            return;
        }

        try {
            String patient = patientNameField.getText();
            String doctor = doctorNameField.getText();
            String phone = PhoneField.getText();
            Date issue_date = java.sql.Date.valueOf(issueDatePicker.getValue());
            Date expiry_date = java.sql.Date.valueOf(expiryDatePicker.getValue());
            String status = statusComboBox.getValue();
            String medications = medicationsArea.getText();

            if (patient.isEmpty() || doctor.isEmpty() || phone.isEmpty() || status.isEmpty() || medications.isEmpty()
                    || expiry_date == null || issue_date == null
                    || !Validators.patientPrescriptionValid(patientsList, phone)) {
                // Show error message
                return;
            }

            selectedPrescription.setPatientName(patient);
            selectedPrescription.setDoctorName(doctor);
            selectedPrescription.setPatientPhone(phone);
            selectedPrescription.setIssueDate(issue_date);
            selectedPrescription.setExpiryDate(expiry_date);
            selectedPrescription.setStatus(status);
            selectedPrescription.setMedications(medications);

            // Refresh table
            prescriptionTable.refresh();
            updatedPrescriptionList
                    .add(new Prescription(phone, patient, doctor, issue_date, expiry_date, status, medications));
            PrescriptionDAO.updatePrescription(updatedPrescriptionList);
            // Clear fields
            handleClearFields();

        } catch (NumberFormatException e) {
            // Show error message for invalid number format
        }
    }

    @FXML
    void handleDeletePrescription(ActionEvent event) {
        Prescription selectedPrescription = prescriptionTable.getSelectionModel().getSelectedItem();
        if (selectedPrescription == null) {
            // Show error message
            return;
        }
        deletedPrescriptionList.add(selectedPrescription);
        PrescriptionDAO.deletePrescription(deletedPrescriptionList);
        prescriptionList.remove(selectedPrescription);
        prescriptionTable.refresh();
        handleClearFields();
    }

    @FXML
    void handleCreateSale(ActionEvent event) {
        // À implémenter
    }

    @FXML
    void handlePrintPrescription(ActionEvent event) {
        // À implémenter
    }
}

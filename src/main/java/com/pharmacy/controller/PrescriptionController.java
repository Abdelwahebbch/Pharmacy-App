package com.pharmacy.controller;

import com.pharmacy.DAO.PatientsDAO;
import com.pharmacy.DAO.PrescriptionDAO;
import com.pharmacy.Model.Patient;
import com.pharmacy.Model.Prescription;
import com.pharmacy.Validation.Validators;
//import com.pharmacy.util.PdfGenerator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

public class PrescriptionController implements Initializable {

    @FXML
    private Button addButton, clearButton, deleteButton, updateButton;
    @FXML
    private TextField patientNameField, PhoneField, doctorNameField, searchField;
    @FXML
    private TextArea medicationsArea;
    @FXML
    private ComboBox<String> statusComboBox;
    @FXML
    private DatePicker issueDatePicker, expiryDatePicker;
    @FXML
    private TableView<Prescription> prescriptionTable;
    @FXML
    private TableColumn<Prescription, Integer> idColumn;
    @FXML
    private TableColumn<Prescription, String> patientNameColumn, doctorNameColumn, statusColumn;
    @FXML
    private TableColumn<Prescription, Date> issueDateColumn, expiryDateColumn;

    private final ObservableList<Prescription> prescriptionList = FXCollections.observableArrayList();
    private final ObservableList<Prescription> newPrescriptionList = FXCollections.observableArrayList();
    private final ObservableList<Prescription> updatedPrescriptionList = FXCollections.observableArrayList();
    private final ObservableList<Prescription> deletedPrescriptionList = FXCollections.observableArrayList();
    private final ObservableList<Patient> patientsList = FXCollections.observableArrayList();

    private static final ObservableList<String> STATUS_OPTIONS = FXCollections.observableArrayList("Malade", "Others");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("patientPhone"));
        patientNameColumn.setCellValueFactory(new PropertyValueFactory<>("patientName"));
        doctorNameColumn.setCellValueFactory(new PropertyValueFactory<>("doctorName"));
        issueDateColumn.setCellValueFactory(new PropertyValueFactory<>("issueDate"));
        expiryDateColumn.setCellValueFactory(new PropertyValueFactory<>("expiryDate"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        statusComboBox.setItems(STATUS_OPTIONS);
        issueDatePicker.setValue(LocalDate.now());

        PrescriptionDAO.loadPrescription(prescriptionList);
        prescriptionTable.setItems(prescriptionList);
        PatientsDAO.LoadAllPatients(patientsList);
    }

    @FXML
    void handleAddPrescription(ActionEvent event) {
        if (!isFormValid())
            return;

        Prescription p = buildPrescriptionFromForm();
        newPrescriptionList.add(p);
        prescriptionList.add(p);
        PrescriptionDAO.addPrescriptions(newPrescriptionList);
        prescriptionTable.setItems(prescriptionList);
        handleClearFields();
    }

    @FXML
    void handleUpdatePrescription(ActionEvent event) {
        Prescription selected = prescriptionTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showError("Aucune ligne sélectionnée.");
            return;
        }

        if (!isFormValid())
            return;

        Prescription updated = buildPrescriptionFromForm();
        updated.setId(selected.getId()); // conserve l'ID existant
        updatedPrescriptionList.add(updated);
        PrescriptionDAO.updatePrescriptions(updatedPrescriptionList);

        prescriptionList.set(prescriptionList.indexOf(selected), updated);
        prescriptionTable.refresh();
        handleClearFields();
    }

    @FXML
    void handleDeletePrescription(ActionEvent event) {
        Prescription selected = prescriptionTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showError("Sélectionnez une prescription à supprimer.");
            return;
        }

        deletedPrescriptionList.add(selected);
        PrescriptionDAO.deletePrescriptions(deletedPrescriptionList);
        prescriptionList.remove(selected);
        prescriptionTable.refresh();
        handleClearFields();
    }

    @FXML
    void handleClearFields() {
        patientNameField.clear();
        PhoneField.clear();
        doctorNameField.clear();
        medicationsArea.clear();
        statusComboBox.setValue(null);
        issueDatePicker.setValue(null);
        expiryDatePicker.setValue(null);
    }

    @FXML
    void handlePrescriptionSelection() {
        Prescription selected = prescriptionTable.getSelectionModel().getSelectedItem();
        if (selected == null)
            return;

        patientNameField.setText(selected.getPatientName());
        PhoneField.setText(selected.getPatientPhone());
        doctorNameField.setText(selected.getDoctorName());
        medicationsArea.setText(selected.getMedications());
        issueDatePicker.setValue(selected.getIssueDate().toLocalDate());
        expiryDatePicker.setValue(selected.getExpiryDate().toLocalDate());
        statusComboBox.setValue(selected.getStatus());
    }

    @FXML
    void handleSearchPrescription(ActionEvent event) {
        String term = searchField.getText().toLowerCase();

        if (term.isEmpty()) {
            prescriptionTable.setItems(prescriptionList);
            return;
        }

        ObservableList<Prescription> filtered = FXCollections.observableArrayList();
        for (Prescription p : prescriptionList) {
            if (p.getDoctorName().toLowerCase().contains(term) || p.getPatientName().toLowerCase().contains(term)) {
                filtered.add(p);
            }
        }

        prescriptionTable.setItems(filtered);
    }

    private Prescription buildPrescriptionFromForm() {
        return new Prescription(
                PhoneField.getText(),
                patientNameField.getText(),
                doctorNameField.getText(),
                Date.valueOf(issueDatePicker.getValue()),
                Date.valueOf(expiryDatePicker.getValue()),
                statusComboBox.getValue(),
                medicationsArea.getText());
    }

    private boolean isFormValid() {
        String name = patientNameField.getText();
        String phone = PhoneField.getText();
        String doctor = doctorNameField.getText();
        LocalDate issue = issueDatePicker.getValue();
        LocalDate exp = expiryDatePicker.getValue();
        String status = statusComboBox.getValue();
        String meds = medicationsArea.getText();

        if (name.isEmpty() || phone.isEmpty() || doctor.isEmpty() || meds.isEmpty() || issue == null || exp == null
                || status == null) {
            showError("Veuillez remplir tous les champs.");
            return false;
        }

        if (!Validators.patientPrescriptionValid(patientsList, phone)) {
            return showConfirmationToCreatePatient(phone, name);
        }

        return true;
    }

    private boolean showConfirmationToCreatePatient(String phone, String name) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Patient introuvable");
        alert.setContentText("Aucun patient avec ce numéro : " + phone + "\nVoulez-vous en créer un ?");

        ButtonType yesBtn = new ButtonType("Oui");
        ButtonType noBtn = new ButtonType("Non");
        alert.getButtonTypes().setAll(yesBtn, noBtn);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == yesBtn) {
            PatientsDAO.createPatient(phone, name); // Ajout direct
            showInfo("Patient ajouté", "Le patient a été créé.");
            PatientsDAO.LoadAllPatients(patientsList);
            return true;
        } else {
            showInfo("Info", "Aucune action effectuée.");
            return false;
        }
    }

    // === Alert Helpers ===
    private void showError(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showInfo(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    void handleCreateSale(ActionEvent event) {
        // À implémenter
    }

    @FXML
    void handlePrintPrescription(ActionEvent event) {
        // Prescription selected = prescriptionTable.getSelectionModel().getSelectedItem();
        // if (selected != null) {
        //     String path = "prescriptions/" + selected.getPatientName() + "_" + selected.getIssueDate() + ".pdf";
        //     PdfGenerator.generatePrescriptionPDF(selected);
        //     showInfo("PDF généré", "Ordonnance enregistrée sous :\n" + path);
        // }
    }
}

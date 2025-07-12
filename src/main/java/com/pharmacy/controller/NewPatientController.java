package com.pharmacy.controller;

import com.pharmacy.DAO.PatientsDAO;
import com.pharmacy.Model.Patient;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NewPatientController {

    @FXML
    private TextField PhoneField;

    @FXML
    private TextField doctorNameField;

    @FXML
    private DatePicker birthdayDatePicker;

    @FXML
    private TextArea notesArea;

    @FXML
    private TextField patientNameField;

    @FXML
    void handleAddPatientButton(ActionEvent event) {
        Patient p = buildPatientFromForm();
        PatientsDAO.createPatient(p);
        showInfo("Sccess !", "The new patient named \" " + p.getName() + " \" was created successfully");
    }

    private Patient buildPatientFromForm() {

        return new Patient(patientNameField.getText(), PhoneField.getText(), doctorNameField.getText(),
                java.sql.Date.valueOf(birthdayDatePicker.getValue()), notesArea.getText());
    }

    @FXML
    void handleClearFields(ActionEvent event) {
        patientNameField.clear();
        doctorNameField.clear();
        PhoneField.clear();
        notesArea.clear();
        birthdayDatePicker.setValue(null);
    }

    @FXML
    void handleCancelButton(ActionEvent event) {
        Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
        s.close();
    }

    private void showInfo(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}

package com.pharmacy.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class NewPatientController {

    @FXML
    private TextField PhoneField;

    @FXML
    private Button addButton;

    @FXML
    private Button clearButton;

    @FXML
    private Button deleteButton;

    @FXML
    private TextField doctorNameField;

    @FXML
    private DatePicker expiryDatePicker;

    @FXML
    private DatePicker issueDatePicker;

    @FXML
    private TextArea medicationsArea;

    @FXML
    private TextField patientNameField;

    @FXML
    private ComboBox<?> statusComboBox;

    @FXML
    void handleAddPrescription(ActionEvent event) {

    }

    @FXML
    void handleClearFields(ActionEvent event) {

    }

    @FXML
    void handleDeletePrescription(ActionEvent event) {

    }

}

package com.pharmacy.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class PatientController {

    @FXML
    private TextField PhoneField;

    @FXML
    private Button addButton;

    @FXML
    private TableColumn<?, ?> birthdayColumn;

    @FXML
    private DatePicker birthdayDatePicker;

    @FXML
    private Button clearButton;

    @FXML
    private Button deleteButton;

    @FXML
    private TableColumn<?, ?> doctorNameColumn;

    @FXML
    private TextField doctorNameField;

    @FXML
    private TextArea notesArea;

    @FXML
    private TableColumn<?, ?> patientNameColumn;

    @FXML
    private TextField patientNameField;

    @FXML
    private TableView<?> patientsTable;

    @FXML
    private TableColumn<?, ?> phoneColumn;

    @FXML
    private TextField searchField;

    @FXML
    private Button updateButton;

    @FXML
    void handleAddPatient(ActionEvent event) {

    }

    @FXML
    void handleClearFields(ActionEvent event) {

    }

    @FXML
    void handleDeletePatient(ActionEvent event) {

    }

    @FXML
    void handlePrescriptionSelection(MouseEvent event) {

    }

    @FXML
    void handleSearchPatient(ActionEvent event) {

    }

    @FXML
    void handleUpdatePatient(ActionEvent event) {

    }

}

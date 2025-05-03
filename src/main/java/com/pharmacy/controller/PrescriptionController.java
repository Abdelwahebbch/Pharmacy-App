package com.pharmacy.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class PrescriptionController {

    @FXML
    private Button addButton;

    @FXML
    private Button clearButton;

    @FXML
    private Button deleteButton;

    @FXML
    private TableColumn<?, ?> doctorNameColumn;

    @FXML
    private TextField doctorNameField;

    @FXML
    private TableColumn<?, ?> expiryDateColumn;

    @FXML
    private DatePicker expiryDatePicker;

    @FXML
    private TableColumn<?, ?> idColumn;

    @FXML
    private TableColumn<?, ?> issueDateColumn;

    @FXML
    private DatePicker issueDatePicker;

    @FXML
    private TextArea medicationsArea;

    @FXML
    private ComboBox<?> patientComboBox;

    @FXML
    private TableColumn<?, ?> patientNameColumn;

    @FXML
    private TableView<?> prescriptionTable;

    @FXML
    private TextField searchField;

    @FXML
    private TableColumn<?, ?> statusColumn;

    @FXML
    private ComboBox<?> statusComboBox;

    @FXML
    private Button updateButton;

    @FXML
    void handleAddPrescription(ActionEvent event) {

    }

    @FXML
    void handleClearFields(ActionEvent event) {

    }

    @FXML
    void handleCreateSale(ActionEvent event) {

    }

    @FXML
    void handleDeletePrescription(ActionEvent event) {

    }

    @FXML
    void handleNewPatient(ActionEvent event) {

    }

    @FXML
    void handlePrescriptionSelection(MouseEvent event) {

    }

    @FXML
    void handlePrintPrescription(ActionEvent event) {

    }

    @FXML
    void handleSearchPrescription(ActionEvent event) {

    }

    @FXML
    void handleUpdatePrescription(ActionEvent event) {

    }

}

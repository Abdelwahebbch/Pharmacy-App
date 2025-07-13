package com.pharmacy.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.pharmacy.DAO.PatientsDAO;
import com.pharmacy.Model.Patient;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class PatientController implements Initializable {

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
    private TableView<Patient> patientsTable;

    @FXML
    private TableColumn<?, ?> phoneColumn;

    @FXML
    private TextField searchField;

    @FXML
    private Button updateButton;

    private ObservableList<Patient> patientList = FXCollections.observableArrayList();

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
        String p = searchField.getText();
        if (p.isEmpty()) {
            patientsTable.setItems(patientList);
            return;
        }
        ObservableList<Patient> filtredList = FXCollections.observableArrayList();
        for (Patient i : patientList) {
            if (i.getName().toLowerCase().contains(p) || i.getPhone().toLowerCase().contains(p)
                    || String.valueOf(i.getBirthday()).contains(p)) {
                filtredList.add(i);
            }
        }
        patientsTable.setItems(filtredList);
    }

    @FXML
    void handleUpdatePatient(ActionEvent event) {

    }

    private void initializeColumns() {
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        patientNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        birthdayColumn.setCellValueFactory(new PropertyValueFactory<>("birthday"));
        doctorNameColumn.setCellValueFactory(new PropertyValueFactory<>("familyDoctor"));
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        initializeColumns();
        PatientsDAO.LoadAllPatients(patientList);
        patientsTable.setItems(patientList);

    }

    private Patient buildPatientFromForm() {

        return new Patient(patientNameField.getText(), PhoneField.getText(), doctorNameField.getText(),
                java.sql.Date.valueOf(birthdayDatePicker.getValue()), notesArea.getText());
    }
}

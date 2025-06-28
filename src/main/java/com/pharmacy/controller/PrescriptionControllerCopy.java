// package com.pharmacy.controller;

// import javafx.fxml.FXML;
// import javafx.fxml.Initializable;
// import javafx.scene.control.TableView;
// import javafx.scene.control.TextField;
// import javafx.scene.control.TextArea;
// import javafx.scene.control.DatePicker;
// import javafx.scene.control.Button;
// import javafx.collections.FXCollections;
// import javafx.collections.ObservableList;

// import java.net.URL;
// import java.time.LocalDate;
// import java.util.ResourceBundle;

// import com.pharmacy.Model.Prescription;

// public class PrescriptionControllerCopy implements Initializable {

//     @FXML
//     private TableView<Prescription> prescriptionTable;
//     @FXML
//     private TextField patientNameField;
//     @FXML
//     private TextField doctorNameField;
//     @FXML
//     private TextArea medicationsArea;
//     @FXML
//     private DatePicker issueDatePicker;
//     @FXML
//     private DatePicker expiryDatePicker;
//     @FXML
//     private Button addButton;
//     @FXML
//     private Button updateButton;
//     @FXML
//     private Button deleteButton;
//     @FXML
//     private TextField searchField;

//     private ObservableList<Prescription> prescriptionList = FXCollections.observableArrayList();

//     @Override
//     public void initialize(URL location, ResourceBundle resources) {
//         // Add sample data
//         prescriptionList.addAll(
//                 new Prescription("John Smith", "Dr. Emily Johnson",
//                         "Amoxicillin 500mg - 3 times daily\nIbuprofen 400mg - as needed",
//                         LocalDate.now().minusDays(5), LocalDate.now().plusMonths(1)),
//                 new Prescription("Sarah Williams", "Dr. Michael Chen",
//                         "Lisinopril 10mg - once daily\nAspirin 81mg - once daily",
//                         LocalDate.now().minusDays(10), LocalDate.now().plusMonths(3)),
//                 new Prescription("Robert Brown", "Dr. Lisa Anderson",
//                         "Ventolin Inhaler - as needed\nPrednisone 20mg - daily for 5 days",
//                         LocalDate.now().minusDays(2), LocalDate.now().plusDays(14)));

//         // Set up the table
//         prescriptionTable.setItems(prescriptionList);
//     }

//     @FXML
//     private void handleAddPrescription() {
//         try {
//             String patientName = patientNameField.getText();
//             String doctorName = doctorNameField.getText();
//             String medications = medicationsArea.getText();
//             LocalDate issueDate = issueDatePicker.getValue();
//             LocalDate expiryDate = expiryDatePicker.getValue();

//             if (patientName.isEmpty() || doctorName.isEmpty() || medications.isEmpty() ||
//                     issueDate == null || expiryDate == null) {
//                 // Show error message
//                 return;
//             }

//             Prescription newPrescription = new Prescription(patientName, doctorName, medications, issueDate,
//                     expiryDate);
//             prescriptionList.add(newPrescription);

//             // Clear fields
//             clearFields();

//         } catch (Exception e) {
//             // Show error message
//         }
//     }

//     @FXML
//     private void handleUpdatePrescription() {
//         Prescription selectedPrescription = prescriptionTable.getSelectionModel().getSelectedItem();
//         if (selectedPrescription == null) {
//             // Show error message
//             return;
//         }

//         try {
//             String patientName = patientNameField.getText();
//             String doctorName = doctorNameField.getText();
//             String medications = medicationsArea.getText();
//             LocalDate issueDate = issueDatePicker.getValue();
//             LocalDate expiryDate = expiryDatePicker.getValue();

//             if (patientName.isEmpty() || doctorName.isEmpty() || medications.isEmpty() ||
//                     issueDate == null || expiryDate == null) {
//                 // Show error message
//                 return;
//             }

//             selectedPrescription.setPatientName(patientName);
//             selectedPrescription.setDoctorName(doctorName);
//             selectedPrescription.setMedications(medications);
//             selectedPrescription.setIssueDate(issueDate);
//             selectedPrescription.setExpiryDate(expiryDate);

//             // Refresh table
//             prescriptionTable.refresh();

//             // Clear fields
//             clearFields();

//         } catch (Exception e) {
//             // Show error message
//         }
//     }

//     @FXML
//     private void handleDeletePrescription() {
//         Prescription selectedPrescription = prescriptionTable.getSelectionModel().getSelectedItem();
//         if (selectedPrescription == null) {
//             // Show error message
//             return;
//         }

//         prescriptionList.remove(selectedPrescription);
//         clearFields();
//     }

//     @FXML
//     private void handleSearchPrescription() {
//         String searchTerm = searchField.getText().toLowerCase();

//         if (searchTerm.isEmpty()) {
//             prescriptionTable.setItems(prescriptionList);
//             return;
//         }

//         ObservableList<Prescription> filteredList = FXCollections.observableArrayList();
//         for (Prescription prescription : prescriptionList) {
//             if (prescription.getPatientName().toLowerCase().contains(searchTerm) ||
//                     prescription.getDoctorName().toLowerCase().contains(searchTerm)) {
//                 filteredList.add(prescription);
//             }
//         }

//         prescriptionTable.setItems(filteredList);
//     }

//     @FXML
//     private void handlePrescriptionSelection() {
//         Prescription selectedPrescription = prescriptionTable.getSelectionModel().getSelectedItem();
//         if (selectedPrescription != null) {
//             patientNameField.setText(selectedPrescription.getPatientName());
//             doctorNameField.setText(selectedPrescription.getDoctorName());
//             medicationsArea.setText(selectedPrescription.getMedications());
//             issueDatePicker.setValue(selectedPrescription.getIssueDate());
//             expiryDatePicker.setValue(selectedPrescription.getExpiryDate());
//         }
//     }

//     private void clearFields() {
//         patientNameField.clear();
//         doctorNameField.clear();
//         medicationsArea.clear();
//         issueDatePicker.setValue(null);
//         expiryDatePicker.setValue(null);
//     }
// }

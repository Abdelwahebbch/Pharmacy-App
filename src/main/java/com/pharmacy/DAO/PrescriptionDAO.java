package com.pharmacy.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.pharmacy.Model.Prescription;
import com.pharmacy.util.DataBaseConnection;

import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class PrescriptionDAO {

    public static void loadPrescription(ObservableList<Prescription> prescriptionList) {
        String query = "SELECT * FROM prescriptions";

        try (Connection conn = DataBaseConnection.getConnection();
                PreparedStatement stm = conn.prepareStatement(query);
                ResultSet res = stm.executeQuery()) {

            prescriptionList.clear();
            while (res.next()) {
                prescriptionList.add(new Prescription(
                        res.getString("pres_id"),
                        res.getString("patient_id"),
                        res.getString("patient_name"),
                        res.getString("doctor_name"),
                        res.getDate("issue_date"),
                        res.getDate("med_exp"),
                        res.getString("status"),
                        res.getString("medications")));
            }

        } catch (ClassNotFoundException | SQLException e) {
            showError("Erreur lors du chargement des prescriptions", e.getMessage());
        }
    }

    public static void addPrescriptions(ObservableList<Prescription> prescriptions) {
        String query = "INSERT INTO prescriptions (patient_id, patient_name, doctor_name, issue_date, " +
                "med_exp, status, medications)VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DataBaseConnection.getConnection();
                PreparedStatement stm = conn.prepareStatement(query)) {

            for (Prescription p : prescriptions) {
                stm.setString(1, p.getPatientPhone());
                stm.setString(2, p.getPatientName());
                stm.setString(3, p.getDoctorName());
                stm.setDate(4, p.getIssueDate());
                stm.setDate(5, p.getExpiryDate());
                stm.setString(6, p.getStatus());
                stm.setString(7, p.getMedications());

                int res = stm.executeUpdate();
                if (res > 0) {
                    showInfo("Ajout réussi", "Prescription de \" " + p.getPatientName() + " \" ajoutée avec succès.");
                } else {
                    showError("Échec de l'ajout",
                            "La prescription de \" " + p.getPatientName() + " \" n'a pas été ajoutée.");
                }
            }

            prescriptions.clear();

        } catch (ClassNotFoundException | SQLException e) {
            showError("Erreur lors de l'ajout des prescriptions", e.getMessage());
        }
    }

    public static void deletePrescriptions(ObservableList<Prescription> prescriptions) {
        String query = "DELETE FROM prescriptions WHERE patient_id = ?";

        try (Connection conn = DataBaseConnection.getConnection();
                PreparedStatement stm = conn.prepareStatement(query)) {

            for (Prescription p : prescriptions) {
                stm.setString(1, p.getPatientPhone());
                int rows = stm.executeUpdate();

                if (rows > 0) {
                    showInfo("Suppression réussie", "Prescription de " + p.getPatientName() + " supprimée.");
                } else {
                    showInfo("Aucune suppression", "Aucune prescription trouvée pour " + p.getPatientName());
                }
            }

        } catch (ClassNotFoundException | SQLException e) {
            showError("Erreur lors de la suppression", e.getMessage());
        } finally {
            prescriptions.clear();
        }
    }

    public static void updatePrescriptions(ObservableList<Prescription> prescriptions) {
        String query = "UPDATE prescriptions SET patient_id = ?,patient_name = ?,doctor_name = ?,issue_date = ?, med_exp = ?, status = ?,"
                +
                " medications = ? WHERE pres_id = ? ";

        try (Connection conn = DataBaseConnection.getConnection();
                PreparedStatement stm = conn.prepareStatement(query)) {

            for (Prescription p : prescriptions) {
                stm.setString(1, p.getPatientPhone());
                stm.setString(2, p.getPatientName());
                stm.setString(3, p.getDoctorName());
                stm.setDate(4, p.getIssueDate());
                stm.setDate(5, p.getExpiryDate());
                stm.setString(6, p.getStatus());
                stm.setString(7, p.getMedications());
                stm.setString(8, p.getId());

                int rows = stm.executeUpdate();
                if (rows > 0) {
                    showInfo("Mise à jour réussie", "Prescription de " + p.getPatientName() + " mise à jour.");
                } else {
                    showInfo("Aucune mise à jour",
                            "Aucune prescription mise à jour pour  \" " + p.getPatientName() + " \"");
                }
            }

        } catch (ClassNotFoundException | SQLException e) {
            showError("Erreur lors de la mise à jour", e.getMessage());
        } finally {
            prescriptions.clear();
        }
    }

    // === Méthodes d'affichage des alertes ===
    private static void showInfo(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private static void showError(String title, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

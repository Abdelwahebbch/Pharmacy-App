package com.pharmacy.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.pharmacy.Model.Medication;
import com.pharmacy.Model.Prescription;
import com.pharmacy.util.DataBaseConnection;

import javafx.collections.ObservableList;

public class PrescriptionDAO {

    public static void loadPrescription(ObservableList<Prescription> prescriptionList) {
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
    }

    public static void AddPrescription(ObservableList<Prescription> prescriptionList) {
        String query = "INSERT INTO prescriptions (pres_id, patient_name, doctor_name, issue_date, med_exp, status, medications) "
                +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DataBaseConnection.getConnection();
                PreparedStatement stm = conn.prepareStatement(query)) {
            for (Prescription p : prescriptionList) {
                stm.setString(1, p.getId());
                stm.setString(2, p.getPatientName());
                stm.setString(3, p.getDoctorName());
                stm.setDate(4, p.getIssueDate());
                stm.setDate(5, p.getExpiryDate());
                stm.setString(6, p.getStatus());
                stm.setString(7, p.getMedications());
                int res = stm.executeUpdate();
                if (res != 0) {
                    System.out.println("The prescriptions " + p.getDoctorName() + " was added");
                } else {
                    System.err.println("no addeed !! ");
                }
            }
            prescriptionList.clear();
        } catch (ClassNotFoundException | SQLException e) {
            // TODO Auto-generated catch block
            System.err.println("VCC");
            e.printStackTrace();
        }
    }

    public static void deletePrescription(ObservableList<Prescription> prescriptionList) {
        String query = "delete from  prescriptions where pres_id = ? ";

        try (Connection conn = DataBaseConnection.getConnection();
                PreparedStatement stm = conn.prepareStatement(query)) {
            for (Prescription p : prescriptionList) {
                stm.setString(1, p.getId());

                int rowsAffected = stm.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Prescription with ID " + p.getId() + " deleted successfully.");
                } else {
                    System.out.println("No Prescription delete for ID " + p.getId());
                }
            }
        } catch (Exception e) {
            System.err.println("Error when deleting ");
            System.err.println(e.getMessage());

        }
        prescriptionList.clear();

    }

    public static void updatePrescription(ObservableList<Prescription> prescriptionList) {
        String query = "update prescriptions set pres_id = ? , patient_name = ? , doctor_name = ? , issue_date = ? , med_exp = ? ,status = ? ,medications = ?  where pres_id = ? ";

        try (Connection conn = DataBaseConnection.getConnection();
                PreparedStatement stm = conn.prepareStatement(query)) {
            for (Prescription p : prescriptionList) {

                stm.setString(1, p.getId());
                stm.setString(2, p.getPatientName());
                stm.setString(3, p.getDoctorName());
                stm.setDate(4, p.getIssueDate());
                stm.setDate(5, p.getExpiryDate());
                stm.setString(6, p.getStatus());
                stm.setString(7, p.getMedications());
                stm.setString(8, p.getId());

                int rowsAffected = stm.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("prescription with phone number << " + p.getId() + " >> updated successfully.");
                } else {
                    System.out.println("No prescription updated for phone number" + p.getId());
                }
            }
            prescriptionList.clear();
        } catch (Exception e) {
            System.err.println("Error when updating ");
            System.err.println(e.getMessage());

        }
    }

}

package com.pharmacy.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.pharmacy.Model.Patient;
import com.pharmacy.util.DataBaseConnection;

import javafx.collections.ObservableList;

public class PatientsDAO {

    public static void LoadAllPatients(ObservableList<Patient> patientList) {
        String query = "select * from patients";

        try (Connection conn = DataBaseConnection.getConnection();
                PreparedStatement stm = conn.prepareStatement(query);
                ResultSet res = stm.executeQuery()) {
            while (res.next()) {
                patientList.add(new Patient(res.getString("patient_phone"), res.getString("patient_name"),
                        res.getDate("patient_date"), res.getString("patient_note")));
            }

        } catch (ClassNotFoundException | SQLException e) {
            // TODO Auto-generated catch block
            System.err.println("Error PatientDAO");
            e.printStackTrace();
        }

    }

    public static void createPatient(Patient p) {
        String query = "INSERT INTO patients (patient_phone, patient_name,patient_date,patient_note, family_doc) VALUES (?, ?, ?, ?,?)";
        try (Connection conn = DataBaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, p.getPhone());
            stmt.setString(2, p.getName());
            stmt.setDate(3, p.getBirthday());
            stmt.setString(4, p.getNote());
            stmt.setString(5, p.getFamilyDoctor());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void createPatient(String phone, String name) {
        String query = "INSERT INTO patients (patient_phone, patient_name) VALUES (?, ?)";
        try (Connection conn = DataBaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, phone);
            stmt.setString(2, name);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

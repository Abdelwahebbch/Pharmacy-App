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

}

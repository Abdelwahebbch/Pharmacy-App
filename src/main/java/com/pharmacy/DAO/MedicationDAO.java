package com.pharmacy.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.pharmacy.Model.Medication;
import com.pharmacy.Model.Prescription;
import com.pharmacy.util.DataBaseConnection;

import javafx.collections.ObservableList;

public class MedicationDAO {

    public static void LoadAllMedecins(ObservableList<Medication> medicationList) {
        String query = "select * from medecins ";
        // Initialize category options

        try (Connection conn = DataBaseConnection.getConnection();
                PreparedStatement stm = conn.prepareStatement(query);
                ResultSet res = stm.executeQuery();) {
            // Add All the data
            while (res.next()) {
                medicationList.add(
                        new Medication(res.getString("med_id"), res.getString("med_name"), res.getString("med_categ"),
                                res.getDouble("med_price"), res.getInt("med_quantity"), res.getDate("med_exp")));
            }

        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Error !! ");
            e.printStackTrace();
        }
    }

    public static void updateMedications(ObservableList<Medication> medicationList) {
        String query = "update medecins set med_name = ? , med_categ = ? , med_price = ? , med_quantity = ? , med_exp= ? where med_id = ? ";

        try (Connection conn = DataBaseConnection.getConnection();
                PreparedStatement stm = conn.prepareStatement(query)) {
            for (Medication m : medicationList) {

                stm.setString(1, m.getName());
                stm.setString(2, m.getCategory());
                stm.setDouble(3, m.getPrice());
                stm.setInt(4, m.getQuantity());
                stm.setDate(5, m.getExpiryDate());
                stm.setString(6, m.getId());

                int rowsAffected = stm.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Medication with ID " + m.getId() + " updated successfully.");
                } else {
                    System.out.println("No medication updated for ID " + m.getId());
                }
            }
        } catch (Exception e) {
            System.err.println("Error when updating ");
            System.err.println(e.getMessage());

        }

    }

    public static void deleteMedications(ObservableList<Medication> medicationList) {

        String query = "delete from  medecins where med_id = ? ";

        try (Connection conn = DataBaseConnection.getConnection();
                PreparedStatement stm = conn.prepareStatement(query)) {
            for (Medication m : medicationList) {
                stm.setString(1, m.getId());

                int rowsAffected = stm.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Medication with ID " + m.getId() + " deleted successfully.");
                } else {
                    System.out.println("No medication delete for ID " + m.getId());
                }
            }
        } catch (Exception e) {
            System.err.println("Error when deleting ");
            System.err.println(e.getMessage());

        }

    }

    // TODO
    public static void addMedications(ObservableList<Medication> medicationList) {
        String query = "INSERT INTO medecins (med_id, med_name, med_categ, med_price, med_quantity, med_exp) "
                +
                "VALUES ('MED_NUM_'||MED_IS_SEQ.NEXTVAL, ?, ?, ?, ?, ?)";

        try (Connection conn = DataBaseConnection.getConnection();
                PreparedStatement stm = conn.prepareStatement(query)) {
            for (Medication m : medicationList) {
                stm.setString(1, m.getName());
                stm.setString(2, m.getCategory());
                stm.setDouble(3, m.getPrice());
                stm.setInt(4, m.getQuantity());
                stm.setDate(5, m.getExpiryDate());

                int res = stm.executeUpdate();
                if (res != 0) {
                    System.out.println("The Medication " + m.getName() + " was added");
                } else {
                    System.err.println("no addeed !! ");
                }
            }
            medicationList.clear();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

}

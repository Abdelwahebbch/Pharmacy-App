package com.pharmacy.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.pharmacy.Model.User;
import com.pharmacy.util.DataBaseConnection;

public class UserDAO {

    public static User createUser(int cin, String name, String lastName, String email, String phone, String password,
            double salary) {
        User u = new User(cin, name, lastName, email, phone, password, salary);

        String query = "insert into users values(?,?,?,?,?,?,?)";

        try (Connection conn = DataBaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, u.getCin());
            stmt.setString(2, u.getName());
            stmt.setString(3, u.getLastName());
            stmt.setInt(4, u.getPassword());
            stmt.setString(5, u.getEmail());
            stmt.setString(6, u.getPhone());
            stmt.setDouble(7, u.getSalary());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                return null;
            }
        } catch (SQLException e) {
            System.err.println("Error when creating the new user ");
        } catch (ClassNotFoundException e1) {
            System.err.println("DataBaseConnection Not FOUND !");
        }
        return u;
    }

    

}

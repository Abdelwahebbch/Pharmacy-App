package com.pharmacy.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:FREE";
    private static final String USER = "c##pharmacy";
    private static final String PASSWORD = "0000";

    public static Connection connection;

    public static Connection getConnection() throws SQLException, ClassNotFoundException {

        if (connection == null || connection.isClosed()) {
            try {

                Class.forName("oracle.jdbc.driver.OracleDriver");
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (SQLException x) {
                System.err.println("Connection failed !  ");
            } catch (ClassNotFoundException x) {
                System.err.println("Oracle JDBC Driver not found ");
            }
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {

                connection.close();
                System.out.println("Database connection closed");
            } catch (SQLException x) {
                System.err.println("Error when closing Connection ");
            }
        }
    }
}

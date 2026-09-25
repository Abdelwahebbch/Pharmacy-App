package com.pharmacy.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {

 private static final String url = "jdbc:postgresql://db.sqjciedukstpzofrsqva.supabase.co:5432/postgres?user=postgres&password=[YOUR-PASSWORD]";
    //private static final String url = "jdbc:oracle:thin:@localhost:1521:FREE";
    private static final String password = "Abcd1234*DB@FREE";
    private static final String user = "postgres";
    public static Connection connection;

    public static synchronized Connection getConnection() throws SQLException, ClassNotFoundException {

        if (connection == null || connection.isClosed()) {
            try {

                Class.forName("oracle.jdbc.driver.OracleDriver");
                connection = DriverManager.getConnection(url, user, password);
                System.out.println("Database connection established successfully");
            } catch (SQLException x) {
                System.err.println("Connection failed !  ");
            }
        }
        return connection;
    }

    public static synchronized void closeConnection() {
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

// private static final String URL =
// "jdbc:postgresql://db.kithxgxyqorzhusqpaxn.supabase.co:5432/postgres?user=postgres&password=Abcd1234*@*";
// private static final String USER = "postgres";
// private static final String PASSWORD = "Abcd1234*@*";

// public static Connection connection;

// public static synchronized Connection getConnection() throws SQLException,
// ClassNotFoundException {

// if (connection == null || connection.isClosed()) {
// try {

// Class.forName("oracle.jdbc.driver.OracleDriver");
// connection = DriverManager.getConnection(URL, USER, PASSWORD);
// System.out.println("Database connection established successfully");
// } catch (SQLException x) {
// System.err.println("Connection failed ! ");
// }
// }
// return connection;
// }

// public static synchronized void closeConnection() {
// if (connection != null) {
// try {
// connection.close();
// System.out.println("Database connection closed");
// } catch (SQLException x) {
// System.err.println("Error when closing Connection ");
// }
// }
// }
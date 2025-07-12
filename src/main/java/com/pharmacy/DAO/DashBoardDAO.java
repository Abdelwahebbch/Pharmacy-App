package com.pharmacy.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Year;
import java.util.concurrent.atomic.AtomicInteger;

import com.google.common.util.concurrent.AtomicDouble;
import com.pharmacy.Model.DashboardStats;
import com.pharmacy.Model.Medication;
import com.pharmacy.Model.Sale;
import com.pharmacy.controller.DashboardController;
import com.pharmacy.util.DataBaseConnection;

import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;

public class DashBoardDAO extends DashboardController {

    public static DashboardStats initializeDashboard(
            ObservableList<Medication> lowMedList,
            ObservableList<Sale> recentSaleList,
            ObservableList<PieChart.Data> pieData) {

        String queryLowStock = "SELECT COUNT(*) FROM low_stock_log";
        String queryPrescriptions = "SELECT COUNT(*) FROM prescriptions";
        String queryExpiring = "SELECT COUNT(*) FROM medications WHERE med_exp <= SYSDATE + 30";
        String queryLowMedications = "SELECT * FROM medications WHERE med_id IN (SELECT med_id FROM low_stock_log)";
        String querySales = "SELECT * FROM sales";
        String querySalesByCategory = "SELECT sale_categ, COUNT(*) AS total FROM sales GROUP BY sale_categ";

        int lowStock = 0;
        int totalPrescriptions = 0;
        int expItems = 0;

        int x = 0;

        try (Connection conn = DataBaseConnection.getConnection();
                PreparedStatement stmLowStock = conn.prepareStatement(queryLowStock);
                PreparedStatement stmPrescriptions = conn.prepareStatement(queryPrescriptions);
                PreparedStatement stmExpiring = conn.prepareStatement(queryExpiring);
                PreparedStatement stmLowMeds = conn.prepareStatement(queryLowMedications);
                PreparedStatement stmSales = conn.prepareStatement(querySales);
                PreparedStatement stmSalesByCategory = conn.prepareStatement(querySalesByCategory);
                ResultSet rsLowStock = stmLowStock.executeQuery();
                ResultSet rsPrescriptions = stmPrescriptions.executeQuery();
                ResultSet rsExpiring = stmExpiring.executeQuery();
                ResultSet rsLowMeds = stmLowMeds.executeQuery();
                ResultSet rsSales = stmSales.executeQuery();
                ResultSet rsSalesByCategory = stmSalesByCategory.executeQuery()) {

            if (rsLowStock.next()) {
                lowStock = rsLowStock.getInt(1);
            }

            if (rsPrescriptions.next()) {
                totalPrescriptions = rsPrescriptions.getInt(1);
            }

            if (rsExpiring.next()) {
                expItems = rsExpiring.getInt(1);
            }

            while (rsLowMeds.next()) {
                Medication med = new Medication(
                        rsLowMeds.getString(1),
                        rsLowMeds.getString(2),
                        rsLowMeds.getString(3),
                        rsLowMeds.getDouble(4),
                        rsLowMeds.getInt(5),
                        rsLowMeds.getDate(6));
                lowMedList.add(med);
            }

            while (rsSales.next() && x < 10) {
                Sale sale = new Sale(
                        rsSales.getString(1),
                        rsSales.getString(2),
                        rsSales.getString(6),
                        rsSales.getInt(3),
                        rsSales.getDouble(4),
                        rsSales.getDate(5));
                recentSaleList.add(sale);
                x++;
            }

            while (rsSalesByCategory.next()) {
                String category = rsSalesByCategory.getString("sale_categ");
                int total = rsSalesByCategory.getInt("total");
                pieData.add(new PieChart.Data(category, total));
            }

        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Erreur dans le calcul du dashboard:");
            e.printStackTrace();
        }

        return new DashboardStats(lowStock, totalPrescriptions, expItems);
    }

    public static double initialBarCharts(XYChart.Series<String, Number> series) {

        int year = Year.now().getValue();
        int month = LocalDate.now().getMonthValue();
        double currentMonthSales = 0.0;

        String[] T = { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October",
                "November", "December" };

        String query = "SELECT EXTRACT(MONTH FROM sale_date) AS sale_month, SUM(sale_price) AS total_sales FROM sales WHERE EXTRACT(YEAR FROM sale_date) = ? GROUP BY EXTRACT(MONTH FROM sale_date) ORDER BY sale_month ";

        try (Connection conn = DataBaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, year);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                series.getData().add(new XYChart.Data<>(T[rs.getInt("sale_month") - 1], rs.getDouble("total_sales")));
                if (rs.getInt("sale_month") == month) {
                    currentMonthSales = rs.getDouble("total_sales");

                }

            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return currentMonthSales;
    }
}

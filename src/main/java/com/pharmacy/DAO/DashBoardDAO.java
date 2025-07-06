package com.pharmacy.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Year;
import java.util.concurrent.atomic.AtomicInteger;

import com.google.common.util.concurrent.AtomicDouble;
import com.pharmacy.Model.Medication;
import com.pharmacy.Model.Sale;
import com.pharmacy.controller.DashboardController;
import com.pharmacy.util.DataBaseConnection;

import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;

public class DashBoardDAO extends DashboardController {

    public static void initializeDachboard(ObservableList<Medication> lowMedList, ObservableList<Sale> recentSaleList,
            ObservableList<PieChart.Data> pieData, AtomicInteger lowStock, AtomicInteger totalPrescriptions,
            AtomicInteger expItems) {
        String query = "select count(*) from low_stock_log ";
        String query1 = "select count(*) from prescriptions ";
        String query2 = "SELECT COUNT(*) FROM medecins WHERE med_exp <= SYSDATE + 30";
        String query3 = "select * from medecins where med_id in (select med_id from low_stock_log)";
        String query4 = "select * from sales ";
        String query5 = "SELECT sale_categ, COUNT(*) AS total FROM sales GROUP BY sale_categ";
        int x = 0;

        try (Connection conn = DataBaseConnection.getConnection();
                PreparedStatement stm = conn.prepareStatement(query);
                PreparedStatement stm1 = conn.prepareStatement(query1);
                PreparedStatement stm2 = conn.prepareStatement(query2);
                PreparedStatement stm3 = conn.prepareStatement(query3);
                PreparedStatement stm4 = conn.prepareStatement(query4);
                PreparedStatement stm5 = conn.prepareStatement(query5);
                ResultSet rs = stm.executeQuery();
                ResultSet rs1 = stm1.executeQuery();
                ResultSet rs2 = stm2.executeQuery();
                ResultSet rs3 = stm3.executeQuery();
                ResultSet rs4 = stm4.executeQuery();
                ResultSet rs5 = stm5.executeQuery()) {
            if (rs.next()) {
                lowStock.set(rs.getInt(1));

            }
            if (rs1.next()) {
                totalPrescriptions.set(rs1.getInt(1));

            }
            if (rs2.next()) {
                expItems.set(rs2.getInt(1));

            }
            while (rs3.next()) {
                lowMedList.add(new Medication(rs3.getString(1), rs3.getString(2), rs3.getString(3), rs3.getDouble(4),
                        rs3.getInt(5), rs3.getDate(6)));

            }

            while (rs4.next() && x < 10) {
                recentSaleList.add(
                        new Sale(rs4.getString(1), rs4.getString(2), rs4.getString(6), rs4.getInt(3), rs4.getDouble(4),
                                rs4.getDate(5)));
                x += 1;
            }

            while (rs5.next()) {
                String category = rs5.getString("sale_categ");
                int total = rs5.getInt("total");
                pieData.add(new PieChart.Data(category, total));
            }

        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Erreur dans le calcul de lowItems");

            e.printStackTrace();
        }
    }

    public static void initialBarCharts(XYChart.Series<String, Number> series, AtomicDouble x) {

        int year = Year.now().getValue();
        int month = LocalDate.now().getMonthValue();

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
                    x.set(rs.getDouble("total_sales")); 

                }

            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}

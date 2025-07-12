package com.pharmacy.controller;

import java.net.URL;
import java.util.ResourceBundle;
import com.pharmacy.DAO.DashBoardDAO;
import com.pharmacy.Model.DashboardStats;
import com.pharmacy.Model.Medication;
import com.pharmacy.Model.Sale;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class DashboardController implements Initializable {

    // Data Lists
    private final ObservableList<Medication> lowMedList = FXCollections.observableArrayList();
    private final ObservableList<Sale> recentSaleList = FXCollections.observableArrayList();
    private final ObservableList<PieChart.Data> pieData = FXCollections.observableArrayList();
    private final XYChart.Series<String, Number> series = new XYChart.Series<>();

    // FXML components
    @FXML private TableColumn<Medication, String> lowMedicationColumn;
    @FXML private TableColumn<Medication, String> categoryColumn;
    @FXML private TableColumn<Medication, Integer> lowQuantityColumn;
    @FXML private TableView<Medication> lowStockTable;

    @FXML private TableColumn<Sale, String> recentMedicationColumn;
    @FXML private TableColumn<Sale, Integer> recentQuantityColumn;
    @FXML private TableColumn<Sale, Double> priceColumn;
    @FXML private TableColumn<Sale, String> dateColumn;
    @FXML private TableView<Sale> recentSalesTable;

    @FXML private PieChart categoryPieChart;
    @FXML private BarChart<String, Number> salesBarChart;
    @FXML private LineChart<?, ?> trendsLineChart;

    @FXML private Label lowStockLabel;
    @FXML private Label totalPrescriptionsLabel;
    @FXML private Label expiringItemsLabel;
    @FXML private Label totalSalesLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeColumns();
        loadDashboardData();
        updateUI();
    }

    private void initializeColumns() {
        // Low stock columns
        lowMedicationColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        lowQuantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        // Recent sales columns
        recentMedicationColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        recentQuantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
    }

    private void loadDashboardData() {
        DashboardStats stats = DashBoardDAO.initializeDashboard(
                lowMedList, recentSaleList, pieData);

        double currentMonthSales = DashBoardDAO.initialBarCharts(series);
        lowStockLabel.setText(String.valueOf(stats.lowStock));
        expiringItemsLabel.setText(String.valueOf(stats.expItems));
        totalPrescriptionsLabel.setText(String.valueOf(stats.totalPrescriptions));
        totalSalesLabel.setText(String.format("%.2f TND", currentMonthSales));
    }

    private void updateUI() {
        // lowStockLabel.setText(String.valueOf(lowStock.get()));
        // totalPrescriptionsLabel.setText(String.valueOf(totalPrescriptions.get()));
        // expiringItemsLabel.setText(String.valueOf(expItems.get()));
        // totalSalesLabel.setText( totalMonthlySales.get()));

        categoryPieChart.setData(pieData);
        lowStockTable.setItems(lowMedList);
        recentSalesTable.setItems(recentSaleList);

        series.setName("Sales");
        salesBarChart.getData().clear();
        salesBarChart.getData().add(series);
    }
}

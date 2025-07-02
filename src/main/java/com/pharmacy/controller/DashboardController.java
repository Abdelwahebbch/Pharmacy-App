package com.pharmacy.controller;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;

import com.google.common.util.concurrent.AtomicDouble;
import com.pharmacy.DAO.DashBoardDAO;
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
    private ObservableList<Medication> lowMedList = FXCollections.observableArrayList();
    private ObservableList<Sale> recentSaleList = FXCollections.observableArrayList();
    private ObservableList<PieChart.Data> pieData = FXCollections.observableArrayList();
    private XYChart.Series<String, Number> series = new XYChart.Series<>();

    private AtomicInteger lowStock = new AtomicInteger();
    private AtomicInteger totalPrescriptions = new AtomicInteger();
    private AtomicInteger expItems = new AtomicInteger();
    private AtomicDouble totalMonthlySales = new AtomicDouble();

    @FXML
    private TableColumn<?, ?> categoryColumn;

    @FXML
    private PieChart categoryPieChart;

    @FXML
    private TableColumn<?, ?> dateColumn;

    @FXML
    private Label expiringItemsLabel;

    @FXML
    private TableColumn<?, ?> lowMedicationColumn;

    @FXML
    private TableColumn<?, ?> lowQuantityColumn;

    @FXML
    private Label lowStockLabel;

    @FXML
    private TableView<Medication> lowStockTable;

    @FXML
    private TableColumn<?, ?> priceColumn;

    @FXML
    private TableColumn<?, ?> recentMedicationColumn;

    @FXML
    private TableColumn<?, ?> recentQuantityColumn;

    @FXML
    private TableView<Sale> recentSalesTable;

    @FXML
    private BarChart<String, Number> salesBarChart;

    @FXML
    private Label totalPrescriptionsLabel;

    @FXML
    private Label totalSalesLabel;

    @FXML
    private LineChart<?, ?> trendsLineChart;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        lowMedicationColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        lowQuantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        recentMedicationColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        recentQuantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        DashBoardDAO.initializeDachboard(lowMedList, recentSaleList,
                pieData, this.lowStock, this.totalPrescriptions, this.expItems);

        lowStockLabel.setText(String.valueOf(lowStock));
        totalPrescriptionsLabel.setText(String.valueOf(totalPrescriptions));
        expiringItemsLabel.setText(String.valueOf(expItems));
        categoryPieChart.setData(pieData);
        recentSalesTable.setItems(recentSaleList);
        lowStockTable.setItems(lowMedList);

        salesBarChart.getData().clear();
        series.setName("Sales");
        DashBoardDAO.initialBarCharts(series,totalMonthlySales);
        totalSalesLabel.setText(totalMonthlySales+" TND");
        salesBarChart.getData().add(series);
    }

}

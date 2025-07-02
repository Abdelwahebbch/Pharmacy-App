package com.pharmacy.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.pharmacy.DAO.MedicationDAO;
import com.pharmacy.Model.Medication;
import com.pharmacy.Model.Sale;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class SalesController implements Initializable {
    @FXML
    private TableColumn<?, ?> cartProductColumn;

    @FXML
    private TableColumn<?, ?> cartQuantityColumn;

    @FXML
    private TableView<Sale> cartTable;

    @FXML
    private TableColumn<?, ?> cartTotalColumn;

    @FXML
    private TableColumn<?, ?> cartUnitPriceColumn;

    @FXML
    private Label customerNameLabel;

    @FXML
    private Label customerPhoneLabel;

    @FXML
    private TextField discountField;

    @FXML
    private ComboBox<?> paymentMethodComboBox;

    @FXML
    private TableColumn<?, ?> productCategoryColumn;

    @FXML
    private TableColumn<?, ?> productCodeColumn;

    @FXML
    private TableColumn<?, ?> productNameColumn;

    @FXML
    private TableColumn<?, ?> productPriceColumn;

    @FXML
    private TableColumn<?, ?> productStockColumn;

    @FXML
    private TableView<Medication> productsTable;

    @FXML
    private TextField quantityField;

    @FXML
    private TextField searchProductField;

    @FXML
    private Label subtotalLabel;

    @FXML
    private Label taxLabel;

    @FXML
    private Label totalLabel;
    
    private ObservableList<Medication> MedicationList = FXCollections.observableArrayList();
    private ObservableList<Sale> productInCart = FXCollections.observableArrayList();

    @FXML
    void handleAddToCart(ActionEvent event) {
        Medication selectedProduct = productsTable.getSelectionModel().getSelectedItem();

        if (selectedProduct != null) {
            int q = Integer.parseInt(quantityField.getText());
            productInCart.add(new Sale(selectedProduct.getId(), selectedProduct.getName(),
                    selectedProduct.getCategory(), q, selectedProduct.getPrice(), selectedProduct.getPrice() * q,
                    selectedProduct.getExpiryDate()));
        }

        cartTable.setItems(productInCart);
    }

    @FXML
    void handleApplyDiscount(ActionEvent event) {

    }

    @FXML
    void handleClearCart(ActionEvent event) {
        cartTable.setItems(null);
    }

    @FXML
    void handleNewCustomer(ActionEvent event) {

    }

    @FXML
    void handleProcessPayment(ActionEvent event) {

    }

    @FXML
    void handleRemoveItem(ActionEvent event) {
        Sale selectedProduct = cartTable.getSelectionModel().getSelectedItem();
        productInCart.remove(selectedProduct);
        cartTable.refresh();
    }

    @FXML
    void handleSearchProduct(ActionEvent event) {
        String searchTerm = searchProductField.getText().toLowerCase();

        if (searchTerm.isEmpty()) {
            productsTable.setItems(MedicationList);
            return;
        }

        ObservableList<Medication> filteredList = FXCollections.observableArrayList();
        for (Medication p : MedicationList) {
            if (p.getName().toLowerCase().contains(searchTerm) ||
                    p.getId().toLowerCase().contains(searchTerm)) {
                filteredList.add(p);
            }
        }

        productsTable.setItems(filteredList);
    }

    @FXML
    void handleViewPrescription(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        productCodeColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        productCategoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        productPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        productStockColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        cartProductColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        cartQuantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        cartUnitPriceColumn.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        cartTotalColumn.setCellValueFactory(new PropertyValueFactory<>("total"));
        MedicationDAO.LoadAllMedecins(MedicationList);
        productsTable.setItems(MedicationList);

    }

}

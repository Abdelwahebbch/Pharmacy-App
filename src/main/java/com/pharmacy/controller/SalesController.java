package com.pharmacy.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.pharmacy.DAO.MedicationDAO;
import com.pharmacy.DAO.PrescriptionDAO;
import com.pharmacy.Model.Medication;
import com.pharmacy.Model.Prescription;
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
    private TableColumn<?, ?> cartTotalColumn;

    @FXML
    private TableColumn<?, ?> cartUnitPriceColumn;

    @FXML
    private ComboBox<Prescription> clientPrescriptionsComboBox;

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
    private TextField quantityField;

    @FXML
    private TextField searchField;

    @FXML
    private TextField searchProductField;

    @FXML
    private ComboBox<String> searchResult;

    @FXML
    private Label subtotalLabel;

    @FXML
    private Label taxLabel;

    @FXML
    private Label totalLabel;

    @FXML
    private TableView<Medication> productsTable;
    @FXML
    private TableView<Sale> cartTable;

    private ObservableList<Medication> MedicationList = FXCollections.observableArrayList();
    private ObservableList<Prescription> prescriptionList = FXCollections.observableArrayList();
   // private ObservableList<Prescription> clientPrescriptionList = FXCollections.observableArrayList();
    private ObservableList<Sale> productInCart = FXCollections.observableArrayList();

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
        PrescriptionDAO.loadPrescription(prescriptionList);
        updateSubtotal();
    }

    private void updateSubtotal() {
        double subtotal = 0;
        for (Sale sale : productInCart) {
            subtotal += sale.getTotal();
        }
        subtotalLabel.setText(String.format("%.2f", subtotal));

        if (!discountField.getText().isEmpty()) {
            handleApplyDiscount(null);
        } else {
            totalLabel.setText(String.format("%.2f", subtotal));
        }
    }

    @FXML
    void handleAddToCart(ActionEvent event) {
        Medication selectedProduct = productsTable.getSelectionModel().getSelectedItem();
        int q = Integer.parseInt(quantityField.getText());

        if (selectedProduct == null || quantityField.getText().isEmpty()) {
            return;
        }

        boolean found = false;

        for (Sale saleItem : productInCart) {
            if (saleItem.getId().equals(selectedProduct.getId())) {
                // Update existing Sale item
                int newQuantity = saleItem.getQuantity() + q;
                saleItem.setQuantity(newQuantity);
                saleItem.setTotal(saleItem.getUnitPrice() * newQuantity);
                found = true;
                break;
            }
        }

        if (!found) {
            productInCart.add(new Sale(
                    selectedProduct.getId(),
                    selectedProduct.getName(),
                    selectedProduct.getCategory(),
                    q,
                    selectedProduct.getPrice(),
                    selectedProduct.getPrice() * q,
                    selectedProduct.getExpiryDate()));
        }

        cartTable.setItems(productInCart); // Needed to show updated quantity/total
        cartTable.refresh();
        updateSubtotal(); // Instant total update
    }

    @FXML
    void handleApplyDiscount(ActionEvent event) {
        double T = Double.parseDouble(subtotalLabel.getText().replace(",", "."));
        double d = Double.parseDouble(discountField.getText());
        if (d <= 100) {
            totalLabel.setText(String.format("%.2f", T - (d * T / 100)));
        } else {
            System.err.println("> 100 ! ");
        }

    }

    @FXML
    void handleApplyTax(ActionEvent event) {

    }

    @FXML
    void handleClearCart(ActionEvent event) {
        productInCart.clear();
        cartTable.setItems(productInCart);
        updateSubtotal();
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
        if (selectedProduct != null) {
            productInCart.remove(selectedProduct);
            updateSubtotal();
        }
        cartTable.refresh();
    }

    @FXML
    void handleSearchCustomer(ActionEvent event) {
        String searchTerm = searchField.getText().toLowerCase();

        if (searchTerm.isEmpty()) {
            searchResult.setItems(null);
            return;
        }

        ObservableList<String> filteredList = FXCollections.observableArrayList();
        for (Prescription p : prescriptionList) {
            if (p.getId().toLowerCase().contains(searchTerm) ||
                    p.getPatientName().toLowerCase().contains(searchTerm)) {
                filteredList.add(p.getPatientName() + " / " + p.getId());
            }
        }
        searchResult.setItems(filteredList);
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

    @FXML
    void handleResultSelection() {
        String selectedClient = searchResult.getSelectionModel().getSelectedItem();
        if (selectedClient == null || selectedClient.trim().isEmpty()) {
            System.out.println("Selected client is null or empty.");
            return;  
        }

        String[] parts = selectedClient.split("/");
        if (parts.length != 2) {
            System.out.println("Invalid format for selected client: " + selectedClient);
            return; 
        }

        ObservableList<Prescription> secFilteredList = FXCollections.observableArrayList();
        if (selectedClient != null && parts != null) {
            customerNameLabel.setText(parts[0]);
            customerPhoneLabel.setText(parts[1]);
            for (Prescription p : prescriptionList) {
                if (p.getId().toLowerCase().contains(parts[1].trim()) ||
                        p.getPatientName().toLowerCase().contains(parts[0].trim())) {
                    secFilteredList.add(p);
                }
            }
        }
        clientPrescriptionsComboBox.setItems(secFilteredList);
    }

}

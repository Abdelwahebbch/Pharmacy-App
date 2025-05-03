package com.pharmacy.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert.AlertType;

import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

import com.pharmacy.Model.User;

public class UserManagementController implements Initializable {

    @FXML private TableView<User> userTable;
    @FXML private TableColumn<User, Integer> idColumn;
    @FXML private TableColumn<User, String> usernameColumn;
    @FXML private TableColumn<User, String> fullNameColumn;
    @FXML private TableColumn<User, String> emailColumn;
    @FXML private TableColumn<User, String> roleColumn;
    @FXML private TableColumn<User, Boolean> activeColumn;
    @FXML private TableColumn<User, LocalDate> lastLoginColumn;
    
    @FXML private TextField usernameField;
    @FXML private TextField fullNameField;
    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private PasswordField confirmPasswordField;
    @FXML private ComboBox<String> roleComboBox;
    @FXML private CheckBox activeCheckbox;
    
    @FXML private Button addButton;
    @FXML private Button updateButton;
    @FXML private Button deleteButton;
    @FXML private Button clearButton;
    @FXML private TextField searchField;
    
    private ObservableList<User> userList = FXCollections.observableArrayList();
    private int nextUserId = 1;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialize table columns
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        fullNameColumn.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));
        activeColumn.setCellValueFactory(new PropertyValueFactory<>("active"));
        lastLoginColumn.setCellValueFactory(new PropertyValueFactory<>("lastLogin"));
        
        // Format boolean column to show "Yes" or "No" instead of true/false
        activeColumn.setCellFactory(column -> new TableCell<User, Boolean>() {
            @Override
            protected void updateItem(Boolean item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item ? "Yes" : "No");
                }
            }
        });
        
        // Initialize role options
        roleComboBox.setItems(FXCollections.observableArrayList(
            "Administrator", "Pharmacist", "Technician", "Cashier", "Inventory Manager"
        ));
        
        // Add sample data
        userList.addAll(
            new User(nextUserId++, "admin", "System Administrator", "admin@pharmacy.com", "Administrator", true, LocalDate.now().minusDays(1)),
            new User(nextUserId++, "jsmith", "John Smith", "jsmith@pharmacy.com", "Pharmacist", true, LocalDate.now().minusDays(2)),
            new User(nextUserId++, "mwilliams", "Mary Williams", "mwilliams@pharmacy.com", "Technician", true, LocalDate.now().minusDays(3)),
            new User(nextUserId++, "rjohnson", "Robert Johnson", "rjohnson@pharmacy.com", "Cashier", false, LocalDate.now().minusMonths(1))
        );
        
        // Set up the table
        userTable.setItems(userList);
        
        // Set up table selection listener
        userTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                handleUserSelection(newSelection);
            }
        });
    }
    
    private void handleUserSelection(User user) {
        usernameField.setText(user.getUsername());
        fullNameField.setText(user.getFullName());
        emailField.setText(user.getEmail());
        passwordField.clear();
        confirmPasswordField.clear();
        roleComboBox.setValue(user.getRole());
        activeCheckbox.setSelected(user.isActive());
        
        // Enable update and delete buttons
        updateButton.setDisable(false);
        deleteButton.setDisable(false);
    }
    
    @FXML
    private void handleAddUser() {
        if (!validateInput()) {
            return;
        }
        
        // Check if username already exists
        for (User user : userList) {
            if (user.getUsername().equals(usernameField.getText())) {
                showAlert(AlertType.ERROR, "Error", "Username already exists", 
                          "The username '" + usernameField.getText() + "' is already taken. Please choose a different username.");
                return;
            }
        }
        
        // Create new user
        User newUser = new User(
            nextUserId++,
            usernameField.getText(),
            fullNameField.getText(),
            emailField.getText(),
            roleComboBox.getValue(),
            activeCheckbox.isSelected(),
            null // New user has no last login
        );
        
        userList.add(newUser);
        clearFields();
        
        showAlert(AlertType.INFORMATION, "Success", "User Added", 
                  "User '" + newUser.getUsername() + "' has been added successfully.");
    }
    
    @FXML
    private void handleUpdateUser() {
        User selectedUser = userTable.getSelectionModel().getSelectedItem();
        if (selectedUser == null) {
            showAlert(AlertType.WARNING, "Warning", "No User Selected", 
                      "Please select a user to update.");
            return;
        }
        
        if (!validateInput()) {
            return;
        }
        
        // Check if username already exists (excluding the selected user)
        for (User user : userList) {
            if (user.getUsername().equals(usernameField.getText()) && user.getId() != selectedUser.getId()) {
                showAlert(AlertType.ERROR, "Error", "Username already exists", 
                          "The username '" + usernameField.getText() + "' is already taken. Please choose a different username.");
                return;
            }
        }
        
        // Update user
        selectedUser.setUsername(usernameField.getText());
        selectedUser.setFullName(fullNameField.getText());
        selectedUser.setEmail(emailField.getText());
        selectedUser.setRole(roleComboBox.getValue());
        selectedUser.setActive(activeCheckbox.isSelected());
        
        // Refresh table
        userTable.refresh();
        clearFields();
        
        showAlert(AlertType.INFORMATION, "Success", "User Updated", 
                  "User '" + selectedUser.getUsername() + "' has been updated successfully.");
    }
    
    @FXML
    private void handleDeleteUser() {
        User selectedUser = userTable.getSelectionModel().getSelectedItem();
        if (selectedUser == null) {
            showAlert(AlertType.WARNING, "Warning", "No User Selected", 
                      "Please select a user to delete.");
            return;
        }
        
        // Confirm deletion
        Alert confirmAlert = new Alert(AlertType.CONFIRMATION);
        confirmAlert.setTitle("Confirm Deletion");
        confirmAlert.setHeaderText("Delete User");
        confirmAlert.setContentText("Are you sure you want to delete user '" + selectedUser.getUsername() + "'?");
        
        Optional<ButtonType> result = confirmAlert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            userList.remove(selectedUser);
            clearFields();
            
            showAlert(AlertType.INFORMATION, "Success", "User Deleted", 
                      "User has been deleted successfully.");
        }
    }
    
    @FXML
    private void handleClearFields() {
        clearFields();
    }
    
    @FXML
    private void handleSearchUser() {
        String searchTerm = searchField.getText().toLowerCase();
        
        if (searchTerm.isEmpty()) {
            userTable.setItems(userList);
            return;
        }
        
        ObservableList<User> filteredList = FXCollections.observableArrayList();
        for (User user : userList) {
            if (user.getUsername().toLowerCase().contains(searchTerm) || 
                user.getFullName().toLowerCase().contains(searchTerm) ||
                user.getEmail().toLowerCase().contains(searchTerm) ||
                user.getRole().toLowerCase().contains(searchTerm)) {
                filteredList.add(user);
            }
        }
        
        userTable.setItems(filteredList);
    }
    
    private boolean validateInput() {
        StringBuilder errorMessage = new StringBuilder();
        
        if (usernameField.getText().isEmpty()) {
            errorMessage.append("Username is required.\n");
        }
        
        if (fullNameField.getText().isEmpty()) {
            errorMessage.append("Full Name is required.\n");
        }
        
        if (emailField.getText().isEmpty()) {
            errorMessage.append("Email is required.\n");
        } else if (!isValidEmail(emailField.getText())) {
            errorMessage.append("Email format is invalid.\n");
        }
        
        if (passwordField.getText().isEmpty() && addButton.isDisabled()) {
            // For new users, password is required
            errorMessage.append("Password is required.\n");
        }
        
        if (!passwordField.getText().isEmpty() && !passwordField.getText().equals(confirmPasswordField.getText())) {
            errorMessage.append("Passwords do not match.\n");
        }
        
        if (roleComboBox.getValue() == null) {
            errorMessage.append("Role is required.\n");
        }
        
        if (errorMessage.length() > 0) {
            showAlert(AlertType.ERROR, "Validation Error", "Please correct the following errors:", 
                      errorMessage.toString());
            return false;
        }
        
        return true;
    }
    
    private boolean isValidEmail(String email) {
        // Simple email validation
        return email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");
    }
    
    private void clearFields() {
        usernameField.clear();
        fullNameField.clear();
        emailField.clear();
        passwordField.clear();
        confirmPasswordField.clear();
        roleComboBox.setValue(null);
        activeCheckbox.setSelected(true);
        
        // Disable update and delete buttons
        updateButton.setDisable(true);
        deleteButton.setDisable(true);
        
        // Enable add button
        addButton.setDisable(false);
        
        // Clear selection
        userTable.getSelectionModel().clearSelection();
    }
    
    private void showAlert(AlertType alertType, String title, String header, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}

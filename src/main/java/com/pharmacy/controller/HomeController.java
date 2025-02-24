package com.pharmacy.controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.image.ImageView;

public class HomeController {

    @FXML
    private DatePicker checkInDate;

    @FXML
    private DatePicker checkOutDate;

    @FXML
    private ComboBox<?> guestsCombo;

    @FXML
    private ImageView hostImage;

    @FXML
    private ImageView image1;

    @FXML
    private ImageView image2;

    @FXML
    private ImageView mainImage;

    @FXML
    private Button reserveButton;

}

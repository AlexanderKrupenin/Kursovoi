package com.example.first_krurs;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class Guest_controller {

    @FXML
    private Button guest_back_guest;

    @FXML
    private Button quote_list_guest;

    @FXML
    private Label text_1_guest;

    @FXML
    private Label text_3_guest;

    @FXML
    public void initialize() {
        guest_back_guest.setOnAction(actionEvent -> {
            guest_back_guest.getScene().getWindow().hide();
            Main.guest_window.show();
        });
    }
}


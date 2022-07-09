package com.example.first_krurs;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class Success_controller {

    @FXML
    private Button s_back_s;

    @FXML
    private Label text_1_s;

    @FXML
    private Label text_3_s;

    @FXML
    void initialize() {
        s_back_s.setOnAction(actionEvent -> {
            s_back_s.getScene().getWindow().hide();
            Main.reg_window.show();
        });
    }

}
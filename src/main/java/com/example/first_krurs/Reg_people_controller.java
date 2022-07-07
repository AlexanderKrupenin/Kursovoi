package com.example.first_krurs;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Reg_people_controller {

    @FXML
    private TextField first_name_reg;

    @FXML
    private TextField login_name_reg;

    @FXML
    private PasswordField password_reg;

    @FXML
    private Button reg_back_reg;

    @FXML
    private Button reg_button_reg;

    @FXML
    private TextField second_name_reg;

    @FXML
    private TextField surname_reg;

    @FXML
    private Label text_1_reg;

    @FXML
    private Label text_3_reg;

    @FXML
    public void initialize() {
        reg_back_reg.setOnAction(actionEvent -> {
            reg_back_reg.getScene().getWindow().hide();
            Main.reg_window.setResizable(false);
            Main.reg_window.show();
        });
        Main.reg_window.setResizable(false);
    }
}
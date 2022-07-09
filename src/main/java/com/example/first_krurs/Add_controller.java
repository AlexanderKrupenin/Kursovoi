package com.example.first_krurs;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Add_controller {

    @FXML
    private Button add_back_add;

    @FXML
    private TextField first_name_add;

    @FXML
    private TextField new_quote_add_quote;

    @FXML
    private Button quote_add_add;

    @FXML
    private TextField second_name_add;

    @FXML
    private TextField surname_add;

    @FXML
    private Label text_1_quote;

    @FXML
    private Label text_3_quote;

    @FXML
    void initialize() {
        add_back_add.setOnAction(actionEvent -> {
            add_back_add.getScene().getWindow().hide();
            Main.admin_window.show();
        });
    }

}

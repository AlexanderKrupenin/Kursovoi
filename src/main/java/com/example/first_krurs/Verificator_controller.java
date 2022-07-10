package com.example.first_krurs;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class Verificator_controller {

    @FXML
    private Button fio_red_ver;

    @FXML
    private Button group_red_ver;

    @FXML
    private Button quote_add_ver;

    @FXML
    private Button quote_list_ver;

    @FXML
    private Label text_1_ver;

    @FXML
    private Label text_3_ver;

    @FXML
    private Button ver_back_ver;

    @FXML
    void initialize() {
        ver_back_ver.setOnAction(actionEvent -> {
            ver_back_ver.getScene().getWindow().hide();
            Main.guest_window.show();
        });
    }

}

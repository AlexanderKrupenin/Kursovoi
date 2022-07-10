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
import javafx.stage.Stage;

public class Admin_controller {


    @FXML
    private Button admin_back_admin;

    @FXML
    private Button fio_red_admin;

    @FXML
    private Button quote_add_admin;

    @FXML
    private Button quote_list_admin;

    @FXML
    private Label text_1_admin;

    @FXML
    private Label text_3_admin;

    @FXML
    public void initialize() {
        quote_add_admin.setOnAction(actionEvent -> {
            quote_add_admin.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("add.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            //stage.setResizable(false);
            stage.showAndWait();
            //stage.close();
        });

        admin_back_admin.setOnAction(actionEvent -> {
            admin_back_admin.getScene().getWindow().hide();
            Main.guest_window.show();
        });
    }

}
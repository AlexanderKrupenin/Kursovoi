package com.example.first_krurs;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.sql.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;

public class Controller {

    @FXML
    private Button autfit_button_view;

    @FXML
    private Label error;

    @FXML
    private Button guest_button_view;

    @FXML
    private TextField login_field;

    @FXML
    private PasswordField password_field;

    @FXML
    private Button reg_button_view;

    @FXML
    private Label text_1_view;

    @FXML
    private Label text_2_view;

    @FXML
    private Label text_3_view;

    @FXML
    private Label text_4_view;

    @FXML
    public void initialize() {
        reg_button_view.setOnAction(actionEvent -> {
            reg_button_view.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("reg_people.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.showAndWait();
        });

        guest_button_view.setOnAction(actionEvent -> {
            guest_button_view.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("guest.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.showAndWait();
        });

        autfit_button_view.setOnAction(actionEvent -> {
            FXMLLoader loader = new FXMLLoader();
            String login = login_field.getText().trim();
            String password = password_field.getText().trim();

            try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://std-mysql.ist.mospolytech.ru:3306/std_2008_kursovoi",
                    "std_2008_kursovoi", "12345678");
            Statement statement = connection.createStatement();
                String log;
                String pass;
                ResultSet reg;
                try {
                    reg = statement.executeQuery("SELECT login_name,password FROM People");

                    while(reg.next()) {
                        int i = 1;
                        log = reg.getString(1);
                        pass = reg.getString(2);
                        if (log.equals(login) && pass.equals(password))
                        {
                            System.out.println("Успешно");
                            break;
                        }
                        else error.setVisible(true);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                //ResultSet result = statement.executeQuery(query);
                connection.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        });
    }
}

            /*loader.setLocation(getClass().getResource("admin.fxml"));
            try {
                loader.load();

            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.showAndWait();*/


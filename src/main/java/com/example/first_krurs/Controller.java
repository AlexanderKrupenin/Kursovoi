package com.example.first_krurs;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

            String newPassword = null;
            try {
                MessageDigest md5 = MessageDigest.getInstance("MD5");
                byte[] bytes = md5.digest(password.getBytes());
                StringBuilder sb = new StringBuilder();
                StringBuilder builder = new StringBuilder();
                for (byte password_hash : bytes) {
                    builder.append(String.format("%02X", password_hash));
                    //last_password_hash = password_hash;
                }
                System.out.print(builder.toString() + "          ");
                newPassword = builder.toString();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection connection = DriverManager.getConnection(
                        "jdbc:mysql://std-mysql.ist.mospolytech.ru:3306/std_2008_kursovoi",
                        "std_2008_kursovoi", "12345678");
                Statement statement = connection.createStatement();
                String log;
                String pass;
                String root;
                ResultSet reg;
                try {
                    reg = statement.executeQuery("SELECT login_name,password,user,id,group_people FROM People");

                    while (reg.next()) {
                        int i = 1;
                        log = reg.getString(1);
                        pass = reg.getString(2);
                        root = reg.getString(3);
                        SaveLogin.ID =reg.getString(4);
                        SaveLogin.Group = reg.getString(5);
                        if (log.equals(login) && pass.equals(newPassword)) {
                            SaveLogin saveLogin = new SaveLogin(login);

                            System.out.println("Успешно");
                            if (root.equals("student"))
                                ChangeScene("student.fxml");
                            else if (root.equals("admin"))
                                ChangeScene("admin.fxml");
                            else if (root.equals("ver"))
                                ChangeScene("verificator.fxml");
                            break;
                        } else error.setVisible(true);
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

    public void ChangeScene(String str) {
        guest_button_view.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(str));
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
        stage.close();
    }
}
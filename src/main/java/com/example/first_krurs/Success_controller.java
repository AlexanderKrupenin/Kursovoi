package com.example.first_krurs;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

public class Success_controller {

    @FXML
    private CheckBox check_edit;

    @FXML
    private CheckBox check_read;

    @FXML
    private CheckBox check_write;

    @FXML
    private Button s_back_s;

    @FXML
    private Button save_button;

    @FXML
    private Label text_1_s;

    String edit = "null";
    String read = "null";
    String write = "null";
    @FXML
    void initialize() {
        s_back_s.setOnAction(actionEvent -> {
            s_back_s.getScene().getWindow().hide();
            Main.reg_window.show();
        });


        save_button.setOnAction(e -> {
            if (check_edit.isSelected()) edit = "+";
            else edit = "null";
            if (check_read.isSelected()) read = "+";
            else read = "null";
            if (check_write.isSelected()) write = "+";
            else write = "null";
        CheckEdit();
        });

    }

    public void CheckEdit()
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://std-mysql.ist.mospolytech.ru:3306/std_2008_kursovoi",
                    "std_2008_kursovoi", "12345678");

            ResultSet GetInfo;
            Statement statement = connection.createStatement();

            int count = statement.executeUpdate("update People\n" +
                    "set\n" +
                    "edit = '" + edit + "',\n" +
                    "write_people = '" + write + "\n'," +
                    "read_people = '" + read + "'\n" +
                    "where id = '" + SaveLogin.SaveID + "'");
            SaveLogin.EditPerm = edit;
            SaveLogin.ReadPerm = read;
            SaveLogin.WritePerm = write;

            //ResultSet result = statement.executeQuery(query);
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
package com.example.first_krurs;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

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
    private Label text_error_reg;

    @FXML
    public void initialize() {
        reg_back_reg.setOnAction(actionEvent -> {
            reg_back_reg.getScene().getWindow().hide();
            Main.reg_window.show();
        });

        reg_button_reg.setOnAction(actionEvent -> {
            String surname = surname_reg.getText().trim();
            String first_name = first_name_reg.getText().trim();
            String second_name = second_name_reg.getText().trim();
            String login_name = login_name_reg.getText().trim();
            String password = password_reg.getText().trim();

            if(!first_name.equals("") && ! login_name.equals("") && ! password.equals("") && ! second_name.equals("")
                    && ! surname.equals("")){
                System.out.println("Успешно");
                people(surname, first_name, second_name, login_name, password);
            }

            else {
                System.out.println("Заполните поля");
            }
        });
        }
        public static void people(String surname, String first_name, String second_name, String login_name, String password){
        String surname_registr_people = surname;
        String first_name_registr_people = first_name;
        String second_name_registr_people = second_name;
        String login_name_registr_people = login_name;
        String password_registr_people = password;

        String table = "People";
        int id = VPN.last_id + 1;
        //System.out.println(surname_registr_people);
        VPN.registr_bd_people(surname_registr_people, first_name_registr_people,second_name_registr_people, login_name_registr_people, password_registr_people, table,id);
        }
        }
package com.example.first_krurs;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class Student_controller {

    @FXML
    private Button fio_red_student;

    @FXML
    private TextField first_name_q_student;

    @FXML
    private TableColumn<?, ?> first_name_user;

    @FXML
    private TableColumn<?, ?> gata_user;

    @FXML
    private TableColumn<?, ?> id_studen;

    @FXML
    private TableColumn<?, ?> id_student;

    @FXML
    private TableColumn<?, ?> id_user;

    @FXML
    private TableColumn<?, ?> qoute_surname_user;

    @FXML
    private Button quote_add_student;

    @FXML
    private Button quote_list_student;

    @FXML
    private TextField quote_q_student;

    @FXML
    private Button quote_red_student;

    @FXML
    private TableColumn<?, ?> quote_user;

    @FXML
    private TextField second_name_q_student;

    @FXML
    private TableColumn<?, ?> second_name_user;

    @FXML
    private Button student_back_student;

    @FXML
    private TextField subject_q_student;

    @FXML
    private TableColumn<?, ?> subject_user;

    @FXML
    private TextField sur_q_student;

    @FXML
    private TableView<?> table_user;

    @FXML
    private Label text_1_student;

    @FXML
    private Label text_3_student;

    @FXML
    private Label text_4_student;

    @FXML
    void initialize() {
        student_back_student.setOnAction(actionEvent -> {
            student_back_student.getScene().getWindow().hide();
            Main.guest_window.show();
        });
    }

}

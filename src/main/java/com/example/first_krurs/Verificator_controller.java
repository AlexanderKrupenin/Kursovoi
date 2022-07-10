package com.example.first_krurs;

import java.sql.*;
import java.util.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class Verificator_controller {
    private ObservableList<GetUserClassVer> usersData = FXCollections.observableArrayList();
    @FXML
    private TableColumn<GetUserClassVer,String> data_ver;

    @FXML
    private Button fio_red_ver;

    @FXML
    private TextField first_name_q_ver;

    @FXML
    private TableColumn<GetUserClassVer, String> first_name_ver;

    @FXML
    private Button group_red_ver;

    @FXML
    private TableColumn<GetUserClassVer, String> id_ver;

    @FXML
    private TableColumn<GetUserClassVer, String> qoute_surname_ver;

    @FXML
    private Button quote_add_ver;

    @FXML
    private TextField quote_q_ver;

    @FXML
    private TableColumn<GetUserClassVer, String> quote_ver;

    @FXML
    private TextField second_name_q_ver;

    @FXML
    private TableColumn<GetUserClassVer, String> second_name_ver;

    @FXML
    private TextField subject_q_ver;

    @FXML
    private TableColumn<GetUserClassVer, String> subject_ver;

    @FXML
    private TextField sur_q_ver;

    @FXML
    private TableView<GetUserClassVer> table_ver;

    @FXML
    private Label text_1_ver;

    @FXML
    private Label text_3_ver;

    @FXML
    private Button ver_back_ver;

    @FXML
    public void initialize() {
        ver_back_ver.setOnAction(actionEvent -> {
            ver_back_ver.getScene().getWindow().hide();
            Main.ver_window.show();
        });
        quote_add_ver.setOnAction(actionEvent -> {
            quote_add_ver.getScene().getWindow().hide();
        });

    }
    public class GetUserClassVer  {
        private String first_name;
        private String date;
        private String id;
        private String surname;
        private String quote;
        private String second_name;
        private String subject;
        private String PeopleID;

        public GetUserClassVer(String id, String surname, String first_name, String second_name, String subject, String date, String quote, String PeopleID) {
            this.first_name = first_name;
            this.date = date;
            this.id = id;
            this.surname = surname;
            this.quote = quote;
            this.second_name = second_name;
            this.subject = subject;
            this.PeopleID = PeopleID;
        }

        public void setFirst_name(String first_name) {
            this.first_name = first_name;
        }


        public void setSecond_name(String second_name) {
            this.second_name = second_name;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setQuote(String quote) {
            this.quote = quote;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public void setSurname(String surname) {
            this.surname = surname;
        }
        public  void setPeopleID(String PeopleID){
            this.PeopleID = PeopleID;
        }

        public GetUserClassVer() {
        }

        public String getFirst_name() {
            return first_name;
        }

        public String getDate() {
            return date;
        }

        public String getId() {
            return id;
        }

        public String getSurname() {
            return surname;
        }

        public String getQuote() {
            return quote;
        }

        public String getSecond_name() {
            return second_name;
        }

        public String getSubject() {
            return subject;
        }
        public  String getPeopleID(){
            return  PeopleID;
        }
    }
}

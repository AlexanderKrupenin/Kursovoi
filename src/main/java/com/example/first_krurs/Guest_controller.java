package com.example.first_krurs;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;

import javax.xml.transform.Result;
import java.io.FileReader;
import java.sql.*;
import java.util.PropertyPermission;
import java.util.Set;

public class Guest_controller {
    private ObservableList<User> usersData = FXCollections.observableArrayList();
    @FXML
    private TableColumn<User, String> first_name_guest;

    @FXML
    private TableColumn<User, String> gata_guest;

    @FXML
    private Button guest_back_guest;

    @FXML
    private TableColumn<User, String> id_guest;

    @FXML
    private TableColumn<User, String> qoute_surname_guest;

    @FXML
    private TableColumn<User, String> quote_guest;

    @FXML
    private TableColumn<User, String> second_name_guest;

    @FXML
    private TableColumn<User, String> subject_guest;

    @FXML
    private TableView<User> table_guest;

    @FXML
    public void initialize() {
        guest_back_guest.setOnAction(actionEvent -> {
            guest_back_guest.getScene().getWindow().hide();
            Main.guest_window.show();
        });
        GetQuoteFromDataBase();


    }

    public void SetQuoteTo(){
        first_name_guest.setCellValueFactory(new PropertyValueFactory<User,String>("first_name"));
        gata_guest.setCellValueFactory(new PropertyValueFactory<User,String>("date"));
        id_guest.setCellValueFactory(new PropertyValueFactory<User,String>("id"));
        qoute_surname_guest.setCellValueFactory(new PropertyValueFactory<User,String>("surname"));
        quote_guest.setCellValueFactory(new PropertyValueFactory<User,String>("quote"));
        second_name_guest.setCellValueFactory(new PropertyValueFactory<User,String>("second_name"));
        subject_guest.setCellValueFactory(new PropertyValueFactory<User,String>("subject"));
        table_guest.setItems(usersData);
    }

    public void GetQuoteFromDataBase()
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://std-mysql.ist.mospolytech.ru:3306/std_2008_kursovoi",
                    "std_2008_kursovoi", "12345678");

            ResultSet GetInfo;
            Statement statement = connection.createStatement();

            try{
                GetInfo = statement.executeQuery("SELECT * FROM quote");
                while (GetInfo.next()){
                    usersData.add(new User(/*Айди*/GetInfo.getString(1),/*Фамилия*/GetInfo.getString(2),
                            /*Имя*/GetInfo.getString(3),/*Отчество*/GetInfo.getString(4),
                            /*Предмет*/GetInfo.getString(5), /*Дата*/GetInfo.getString(6),
                            /*Цитата*/GetInfo.getString(7)));
                    SetQuoteTo();
                }

            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }


            //ResultSet result = statement.executeQuery(query);
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public class User {
        private String first_name;
        private String date;
        private String id;
        private String surname;
        private String quote;
        private String second_name;
        private String subject;
        public User(String id, String surname,String first_name, String second_name, String subject, String date, String quote)
        {
            this.first_name = first_name;
            this.date = date;
            this.id = id;
            this.surname = surname;
            this.quote = quote;
            this.second_name = second_name;
            this.subject = subject;
        }
        public void setFirst_name(String first_name){
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

        public User(){}
        public String getFirst_name(){
            return first_name;
        }
        public String getDate(){
            return  date;
        }
        public String getId(){
            return id;
        }
        public String getSurname(){
            return  surname;
        }
        public String getQuote(){
            return  quote;
        }
        public String getSecond_name(){
            return  second_name;
        }
        public String getSubject(){
            return  subject;
        }
    }
}
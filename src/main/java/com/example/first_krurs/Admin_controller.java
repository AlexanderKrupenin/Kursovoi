package com.example.first_krurs;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class Admin_controller {

    private ObservableList<GetUserClass> usersdata = FXCollections.observableArrayList();
    @FXML
    private Button admin_back_admin;

    @FXML
    private Button fio_red_admin;

    @FXML
    private TableColumn<GetUserClass, String> first_name_admin;

    @FXML
    private TextField first_name_q;

    @FXML
    private TableColumn<GetUserClass, String> gata_admin;

    @FXML
    private TableColumn<GetUserClass, String> id_admin;

    @FXML
    private TableColumn<GetUserClass, String> id_user;

    @FXML
    private TableColumn<GetUserClass, String> qoute_surname_admin;

    @FXML
    private Button quote_add_admin;

    @FXML
    private TableColumn<GetUserClass, String> quote_admin;

    @FXML
    private TextField quote_q;

    @FXML
    private TableColumn<GetUserClass, String> second_name_admin;

    @FXML
    private TextField second_name_q;

    @FXML
    private TableColumn<GetUserClass, String> subject_admin;

    @FXML
    private TextField subject_q;

    @FXML
    private TextField sur_q;

    @FXML
    private TableView<GetUserClass> table_admin;

    @FXML
    private Label text_1_admin;

    @FXML
    private Label text_3_admin;

    @FXML
    public void initialize() {
        quote_add_admin.setOnAction(actionEvent -> {
            //quote_add_admin.getScene().getWindow().hide();
            AddInfoToDataBase();
        });

        admin_back_admin.setOnAction(actionEvent -> {
            admin_back_admin.getScene().getWindow().hide();
            Main.guest_window.show();
        });
        GetQuoteFromDataBase();
    }

    public void SetQuoteTo(){
        first_name_admin.setCellValueFactory(new PropertyValueFactory<GetUserClass,String>("first_name"));
        gata_admin.setCellValueFactory(new PropertyValueFactory<GetUserClass,String>("date"));
        id_admin.setCellValueFactory(new PropertyValueFactory<GetUserClass,String>("id"));
        qoute_surname_admin.setCellValueFactory(new PropertyValueFactory<GetUserClass,String>("surname"));
        quote_admin.setCellValueFactory(new PropertyValueFactory<GetUserClass,String>("quote"));
        second_name_admin.setCellValueFactory(new PropertyValueFactory<GetUserClass,String>("second_name"));
        subject_admin.setCellValueFactory(new PropertyValueFactory<GetUserClass,String>("subject"));
        id_user.setCellValueFactory(new PropertyValueFactory<GetUserClass,String>("PeopleID"));
        table_admin.setItems(usersdata);
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
                    usersdata.add(new GetUserClass(/*Айди*/GetInfo.getString(1),/*Фамилия*/GetInfo.getString(2),
                            /*Имя*/GetInfo.getString(3),/*Отчество*/GetInfo.getString(4),
                            /*Предмет*/GetInfo.getString(5), /*Дата*/GetInfo.getString(6),
                            /*Цитата*/GetInfo.getString(7), GetInfo.getString(8)));
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

    private void AddInfoToDataBase()
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://std-mysql.ist.mospolytech.ru:3306/std_2008_kursovoi",
                    "std_2008_kursovoi", "12345678");

            ResultSet SetInfo;
            ResultSet GetID;
            PreparedStatement reg = null;
            Statement statement = connection.createStatement();
            ResultSet count;
            ResultSet id_save;
            int PeopleID = 0;
            int id_registr_people = 0;
            int check = 0;
            try {
                count = statement.executeQuery("SELECT COUNT(*) FROM quote");
                while (count.next())
                    check = count.getInt(1);
                if (check != 0) {
                    id_save = statement.executeQuery("SELECT MAX(quote_id) FROM quote");
                    while (id_save.next()) {
                        id_registr_people = id_save.getInt(1) + 1;
                    }
                } else id_registr_people = 1;
                String savelogin = SaveLogin.login;
                GetID = statement.executeQuery("SELECT id FROM People WHERE login_name = '" + savelogin + "'");
                while (GetID.next()){
                    PeopleID = GetID.getInt(1);
                }
                reg = connection.prepareStatement("INSERT INTO "+ BD.QUOTE_TABLE +"(" + BD.QUOTE_ID+ "," + BD.QUOTE_SURNAME
                       +","+ BD.QUOTE_FIRST_NAME+ "," + BD.QUOTE_SECOND_NAME + ","+
                        BD.QUOTE_SUBJECT + ","+ BD.QUOTE_DATA + "," + BD.QUOTE_TEXT + "," +
                        BD.QUOTE_PEOPLE + ")" + "VALUES(?,?,?,?,?,?,?,?)");

                Date d = new Date();

                reg.setInt(1, id_registr_people);
                reg.setString(2, sur_q.getText());
                reg.setString(3, first_name_q.getText());
                reg.setString(4, second_name_q.getText());
                reg.setString(5, subject_q.getText());
                reg.setString(6, d.toString());
                reg.setString(7, quote_q.getText());
                reg.setInt(8,PeopleID);
                reg.executeUpdate();


GetQuoteFromDataBase();
            }
            catch (Exception e){
                e.printStackTrace();
            }

            //ResultSet result = statement.executeQuery(query);
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    public class GetUserClass  {
        private String first_name;
        private String date;
        private String id;
        private String surname;
        private String quote;
        private String second_name;
        private String subject;
        private String PeopleID;

        public GetUserClass(String id, String surname, String first_name, String second_name, String subject, String date, String quote, String PeopleID) {
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

        public GetUserClass() {
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
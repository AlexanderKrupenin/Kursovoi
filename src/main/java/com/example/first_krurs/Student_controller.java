package com.example.first_krurs;

import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class Student_controller {
    private ObservableList<GetUserClassStudent> usersdata = FXCollections.observableArrayList();
    @FXML
    private Button fio_red_student;

    @FXML
    private TextField first_name_q_student;

    @FXML
    private TableColumn<GetUserClassStudent, String> first_name_user;

    @FXML
    private TableColumn<GetUserClassStudent, String> gata_user;

    @FXML
    private TableColumn<GetUserClassStudent, String> id_student;

    @FXML
    private TableColumn<GetUserClassStudent, String> id_user_lastcol;

    @FXML
    private TableColumn<GetUserClassStudent, String> qoute_surname_user;

    @FXML
    private Button quote_add_student;

    @FXML
    private Button quote_list_student;

    @FXML
    private TextField quote_q_student;

    @FXML
    private Button quote_red_student;

    @FXML
    private TableColumn<GetUserClassStudent, String> quote_user;

    @FXML
    private TextField second_name_q_student;

    @FXML
    private TableColumn<GetUserClassStudent, String> second_name_user;

    @FXML
    private Button student_back_student;

    @FXML
    private TextField subject_q_student;

    @FXML
    private TableColumn<GetUserClassStudent, String> subject_user;

    @FXML
    private TextField sur_q_student;

    @FXML
    private TableView<GetUserClassStudent> table_user;

    @FXML
    private Label text_1_student;

    @FXML
    private Label text_3_student;

    @FXML
    private Label text_4_student;

    @FXML
    private TextField make_first_name_student;

    @FXML
    private TextField make_new_login_student;

    @FXML
    private TextField make_new_password_student;

    @FXML
    private TextField make_new_second_name_student;

    @FXML
    private TextField make_new_surname_student;

    @FXML
    private Label text_7_student;

    @FXML
    private Label count_first_metod_student;

    @FXML
    private TextField qoute_new_id_student;

    @FXML
    private TextField qoute_new_first_name_student;

    @FXML
    private TextField qoute_new_second_name_student;

    @FXML
    private TextField qoute_new_subject_student;

    @FXML
    private TextField qoute_new_surname_student;

    @FXML
    private TextField qoute_new_text_student;

    @FXML
    private Button qoute_new_button_student;

    @FXML
    private Label text_10_ver;

    @FXML
    private Label text_11_ver;

    @FXML
    private Label count_label_button_student;

    @FXML
    private Button count_button_student;

    @FXML
    void initialize() {
        student_back_student.setOnAction(actionEvent -> {
            student_back_student.getScene().getWindow().hide();
            Main.guest_window.show();
        });
        Count();
        count_button_student.setOnAction(actionEvent -> {
            CountBtn();
        });
        fio_red_student.setOnAction(actionEvent -> {
            ChangeInfoAdmin();
        });

        quote_add_student.setOnAction(actionEvent -> {
            AddInfoToDataBase();
        });
        GetQuoteFromDataBase();
        EditInfoAdmin();

        qoute_new_button_student.setOnAction(actionEvent -> {
            ChangeUserQuote();
        });
    }

    public void ChangeUserQuote()
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://std-mysql.ist.mospolytech.ru:3306/std_2008_kursovoi",
                    "std_2008_kursovoi", "12345678");

            Statement statement = connection.createStatement();
            ResultSet idshka;
            String newID = "";
            idshka = statement.executeQuery("SELECT id_people from quote\nWhere quote_id = '"+qoute_new_id_student.getText()+ "'");
            while (idshka.next())
            {
                newID = idshka.getString(1);
            }
            if (SaveLogin.ID.equals(newID)) {
                try {
                    int count = statement.executeUpdate("update quote\n" +
                            "set\n" +
                            "quote_surname = '" + qoute_new_surname_student.getText() + "',\n" +
                            "quote_first_name = '" + qoute_new_first_name_student.getText() + "',\n" +
                            "quote_second_name = '" + qoute_new_second_name_student.getText() + "',\n" +
                            "quote_subject = '" + qoute_new_subject_student.getText() + "',\n" +
                            "quote_text = '" + qoute_new_text_student.getText() + "'\n" +
                            "where quote_id = '" + qoute_new_id_student.getText() + "'");
                    System.out.println("Строк изменено " + count);
                    table_user.getItems().clear();
                    GetQuoteFromDataBase();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
            else{System.out.println("Ошибка");}
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void Count()
    {
        count_first_metod_student.setVisible(true);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://std-mysql.ist.mospolytech.ru:3306/std_2008_kursovoi",
                    "std_2008_kursovoi", "12345678");

            ResultSet GetInfo;
            Statement statement = connection.createStatement();
            GetInfo = statement.executeQuery("Select Count(*) from quote\nWhere id_people = '" + SaveLogin.ID + "'");
            while (GetInfo.next())
                count_first_metod_student.setText(GetInfo.getString(1));

            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void CountBtn()
    {
        count_label_button_student.setVisible(true);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://std-mysql.ist.mospolytech.ru:3306/std_2008_kursovoi",
                    "std_2008_kursovoi", "12345678");

            ResultSet GetInfo;
            Statement statement = connection.createStatement();
            GetInfo = statement.executeQuery("Select Count(*) from quote\nWhere id_people = '" + SaveLogin.ID + "'");
            while (GetInfo.next())
                count_label_button_student.setText(GetInfo.getString(1));

            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void ChangeInfoAdmin()
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://std-mysql.ist.mospolytech.ru:3306/std_2008_kursovoi",
                    "std_2008_kursovoi", "12345678");

            ResultSet GetInfo;
            Statement statement = connection.createStatement();

            try{

                if (!make_new_surname_student.getText().equals("") && !make_new_second_name_student.getText().equals("")&&
                        !make_new_password_student.getText().equals("") && !make_new_login_student.getText().equals("") &&
                        !make_first_name_student.getText().equals("")) {

                    String newPassword = null;
                    try {
                        MessageDigest md5 = MessageDigest.getInstance("MD5");
                        byte[] bytes = md5.digest(make_new_password_student.getText().getBytes());
                        StringBuilder sb = new StringBuilder();
                        StringBuilder builder = new StringBuilder();
                        for (byte password_hash : bytes) {
                            builder.append(String.format("%02X", password_hash));
                            //last_password_hash = password_hash;
                        }
                        newPassword = builder.toString();
                    } catch (NoSuchAlgorithmException e) {
                        e.printStackTrace();
                    }

                    int count = statement.executeUpdate("update People\n" +
                            "set\n" +
                            "surname = '" + make_new_surname_student.getText() + "',\n" +
                            "first_name = '" + make_first_name_student.getText() + "',\n" +
                            "second_name = '" + make_new_second_name_student.getText() + "',\n" +
                            "login_name = '" + make_new_login_student.getText() + "',\n" +
                            "password = '" + newPassword + "'\n" +
                            "where id = '" + SaveLogin.ID + "'");
                    System.out.println("Строк изменено " + count);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void EditInfoAdmin()
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://std-mysql.ist.mospolytech.ru:3306/std_2008_kursovoi",
                    "std_2008_kursovoi", "12345678");

            ResultSet GetInfo;
            Statement statement = connection.createStatement();

            try{


                GetInfo = statement.executeQuery("select * from People\n where login_name = '" +SaveLogin.login +"'");
                while (GetInfo.next()){
                    make_first_name_student.setText(GetInfo.getString(2));
                    make_new_login_student.setText(GetInfo.getString(4));
                    make_new_second_name_student.setText(GetInfo.getString(3));
                    make_new_surname_student.setText(GetInfo.getString(1));
                }

            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }


            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void SetQuoteTo(){
        first_name_user.setCellValueFactory(new PropertyValueFactory<GetUserClassStudent,String>("first_name"));
        gata_user.setCellValueFactory(new PropertyValueFactory<GetUserClassStudent,String>("date"));
        id_student.setCellValueFactory(new PropertyValueFactory<GetUserClassStudent,String>("id"));
        qoute_surname_user.setCellValueFactory(new PropertyValueFactory<GetUserClassStudent,String>("surname"));
        quote_user.setCellValueFactory(new PropertyValueFactory<GetUserClassStudent,String>("quote"));
        second_name_user.setCellValueFactory(new PropertyValueFactory<GetUserClassStudent,String>("second_name"));
        subject_user.setCellValueFactory(new PropertyValueFactory<GetUserClassStudent,String>("subject"));
        id_user_lastcol.setCellValueFactory(new PropertyValueFactory<GetUserClassStudent,String>("PeopleID"));
        table_user.setItems(usersdata);
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
                    usersdata.add(new GetUserClassStudent(/*Айди*/GetInfo.getString(1),/*Фамилия*/GetInfo.getString(2),
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
                java.util.Date d = new Date();

                reg.setInt(1, id_registr_people);
                reg.setString(2, sur_q_student.getText());
                reg.setString(3, first_name_q_student.getText());
                reg.setString(4, second_name_q_student.getText());
                reg.setString(5, subject_q_student.getText());
                reg.setString(6, d.toString());
                reg.setString(7, quote_q_student.getText());
                reg.setInt(8,PeopleID);
                reg.executeUpdate();

                table_user.getItems().clear();
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

    public class GetUserClassStudent  {
        private String first_name;
        private String date;
        private String id;
        private String surname;
        private String quote;
        private String second_name;
        private String subject;
        private String PeopleID;

        public GetUserClassStudent(String id, String surname, String first_name, String second_name, String subject, String date, String quote, String PeopleID) {
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

        public GetUserClassStudent() {
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
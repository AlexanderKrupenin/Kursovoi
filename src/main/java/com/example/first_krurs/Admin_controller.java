package com.example.first_krurs;

import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Admin_controller {

    private ObservableList<GetUserClass> usersdata = FXCollections.observableArrayList();
    private ObservableList<GetInfoPeople> userPeople = FXCollections.observableArrayList();

    @FXML
    private TableColumn<GetInfoPeople, String> colum_first_name;

    @FXML
    private TableColumn<GetInfoPeople, String> colum_group;

    @FXML
    private TableColumn<GetInfoPeople, String> colum_id;

    @FXML
    private TableColumn<GetInfoPeople, String> colum_login;

    @FXML
    private TableColumn<GetInfoPeople, String> colum_password;

    @FXML
    private TableColumn<GetInfoPeople, String> colum_second_name;

    @FXML
    private TableColumn<GetInfoPeople, String> colum_surname;

    @FXML
    private TableColumn<GetInfoPeople, String> colum_user;

    @FXML
    private TableView<GetInfoPeople> table_people;

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
    private TextField make_first_name_admin;

    @FXML
    private TextField make_new_first_name_user;

    @FXML
    private TextField make_new_id_user;

    @FXML
    private TextField make_new_login_admin;

    @FXML
    private TextField make_new_login_user;

    @FXML
    private TextField make_new_password_admin;

    @FXML
    private TextField make_new_password_user;

    @FXML
    private TextField make_new_second_name_admin;

    @FXML
    private TextField make_new_second_name_user;

    @FXML
    private TextField make_new_surname_admin;

    @FXML
    private TextField make_new_surname_user;

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
    private Label text_3_admin1;

    @FXML
    private Label text_4_admin;

    @FXML
    private Label text_5_admin;

    @FXML
    private Label text_6_admin;

    @FXML
    private Label text_7_admin;

    @FXML
    private Label text_10_admin;

    @FXML
    private Label text_11_admin;

    @FXML
    private Label count_first_metod_admin;

    @FXML
    private Button new_fio_red_user;

    @FXML
    private TextField qoute_new_id;

    @FXML
    private TextField qoute_new_first_name;

    @FXML
    private TextField qoute_new_second_name;

    @FXML
    private TextField qoute_new_subject;

    @FXML
    private TextField qoute_new_surname;

    @FXML
    private TextField qoute_new_text;

    @FXML
    private Button qoute_new_button;

    @FXML
    private Label count_label_button_admin;

    @FXML
    private Button count_button_admin;

    @FXML
    public void initialize() {

        qoute_new_button.setOnAction(actionEvent -> {
            ChangeQuote();
        });

        make_new_first_name_user.setVisible(false);
        make_new_login_user.setVisible(false);
        make_new_password_user.setVisible(false);
        make_new_second_name_user.setVisible(false);
        make_new_surname_user.setVisible(false);
        qoute_new_first_name.setVisible(false);
        qoute_new_subject.setVisible(false);
        qoute_new_second_name.setVisible(false);
        qoute_new_surname.setVisible(false);
        qoute_new_text.setVisible(false);

        table_people.setOnMouseClicked((MouseEvent event) ->{
            table_people.getScene().getWindow().hide();
            SaveLogin.SaveID = table_people.getSelectionModel().getSelectedItem().getId();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("success.fxml"));
            System.out.println("Вы выбрали " + table_people.getSelectionModel().getSelectedItem().getFirst_name());
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
        });

        qoute_new_id.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode().equals(KeyCode.ENTER)){
                    showQuote();
                }

            }
        });
        count_button_admin.setOnAction(actionEvent -> {
            CountBtn();
        });
        fio_red_admin.setOnAction(actionEvent -> {
            ChangeInfoAdmin();
        });
        make_new_id_user.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode().equals(KeyCode.ENTER)){
                    EditAll();
                }
            }
        });
        Count();
        GetPeopleFromDataBase();
        EditInfoAdmin();
        quote_add_admin.setOnAction(actionEvent -> {
            //quote_add_admin.getScene().getWindow().hide();
            AddInfoToDataBase();
        });

        admin_back_admin.setOnAction(actionEvent -> {
            admin_back_admin.getScene().getWindow().hide();
            Main.guest_window.show();
        });
        new_fio_red_user.setOnAction(actionEvent -> {
            EditInfoPeople();
        });
        GetQuoteFromDataBase();
    }

    public void ChangeQuote()
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://std-mysql.ist.mospolytech.ru:3306/std_2008_kursovoi",
                    "std_2008_kursovoi", "12345678");

            Statement statement = connection.createStatement();

            try{
                int count = statement.executeUpdate("update quote\n" +
                        "set\n" +
                        "quote_surname = '" + qoute_new_surname.getText() + "',\n" +
                        "quote_first_name = '" + qoute_new_first_name.getText() + "',\n" +
                        "quote_second_name = '" + qoute_new_second_name.getText() + "',\n" +
                        "quote_subject = '" + qoute_new_subject.getText() + "',\n" +
                        "quote_text = '" + qoute_new_text.getText() + "'\n" +
                        "where quote_id = '" + qoute_new_id.getText() + "'");
                System.out.println("Строк изменено " + count);
                table_admin.getItems().clear();
                GetQuoteFromDataBase();
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

    public void CountBtn()
    {
        count_label_button_admin.setVisible(true);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://std-mysql.ist.mospolytech.ru:3306/std_2008_kursovoi",
                    "std_2008_kursovoi", "12345678");

            ResultSet GetInfo;
            Statement statement = connection.createStatement();
            GetInfo = statement.executeQuery("Select Count(*) from quote\nWhere id_people = '" + SaveLogin.ID + "'");
            while (GetInfo.next())
                count_label_button_admin.setText(GetInfo.getString(1));

            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void Count()
    {
        count_first_metod_admin.setVisible(true);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://std-mysql.ist.mospolytech.ru:3306/std_2008_kursovoi",
                    "std_2008_kursovoi", "12345678");

            ResultSet GetInfo;
            Statement statement = connection.createStatement();
            GetInfo = statement.executeQuery("Select Count(*) from quote\nWhere id_people = '" + SaveLogin.ID + "'");
            while (GetInfo.next())
                count_first_metod_admin.setText(GetInfo.getString(1));

            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void ChangeUserQuote()
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://std-mysql.ist.mospolytech.ru:3306/std_2008_kursovoi",
                    "std_2008_kursovoi", "12345678");

            Statement statement = connection.createStatement();

            if (SaveLogin.ID.equals(""))
            try{
                int count = statement.executeUpdate("update quote\n" +
                        "set\n" +
                        "quote_surname = '" + qoute_new_surname.getText() + "',\n" +
                        "quote_first_name = '" + qoute_new_first_name.getText() + "',\n" +
                        "quote_second_name = '" + qoute_new_second_name.getText() + "',\n" +
                        "quote_subject = '" + qoute_new_subject.getText() + "',\n" +
                        "quote_text = '" + qoute_new_text.getText() + "'\n" +
                        "where quote_id = '" + qoute_new_id.getText() + "'");
                System.out.println("Строк изменено " + count);
                table_admin.getItems().clear();
                GetQuoteFromDataBase();
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


    public void EditAll() {
        make_new_first_name_user.setVisible(true);
        make_new_login_user.setVisible(true);
        make_new_password_user.setVisible(true);
        make_new_second_name_user.setVisible(true);
        make_new_surname_user.setVisible(true);
    }
    public void showQuote()
    {
        qoute_new_first_name.setVisible(true);
        qoute_new_subject.setVisible(true);
        qoute_new_second_name.setVisible(true);
        qoute_new_surname.setVisible(true);
        qoute_new_text.setVisible(true);
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

    public void SetPeopleTo(){
        colum_surname.setCellValueFactory(new PropertyValueFactory<GetInfoPeople,String>("surname"));
        colum_first_name.setCellValueFactory(new PropertyValueFactory<GetInfoPeople,String>("first_name"));
        colum_second_name.setCellValueFactory(new PropertyValueFactory<GetInfoPeople,String>("second_name"));
        colum_login.setCellValueFactory(new PropertyValueFactory<GetInfoPeople,String>("login_name"));
        colum_password.setCellValueFactory(new PropertyValueFactory<GetInfoPeople,String>("password"));
        colum_id.setCellValueFactory(new PropertyValueFactory<GetInfoPeople,String>("id"));
        colum_user.setCellValueFactory(new PropertyValueFactory<GetInfoPeople,String>("user"));
        colum_group.setCellValueFactory(new PropertyValueFactory<GetInfoPeople,String>("group"));
        table_people.setItems(userPeople);
    }

    public void GetPeopleFromDataBase() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://std-mysql.ist.mospolytech.ru:3306/std_2008_kursovoi",
                    "std_2008_kursovoi", "12345678");

            ResultSet GetInfo;
            Statement statement = connection.createStatement();

            try {
                GetInfo = statement.executeQuery("SELECT * FROM People");
                while (GetInfo.next()) {
                    userPeople.add(new GetInfoPeople(GetInfo.getString(1),GetInfo.getString(2),GetInfo.getString(3), GetInfo.getString(4),GetInfo.getString(5),GetInfo.getString(6),GetInfo.getString(7), GetInfo.getString(8)));
                    SetPeopleTo();

                }

            } catch (SQLException e) {
                e.printStackTrace();
            }


            //ResultSet result = statement.executeQuery(query);
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
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

                if (!make_new_surname_admin.getText().equals("") && !make_new_second_name_admin.getText().equals("")&&
                !make_new_password_admin.getText().equals("") && !make_new_login_admin.getText().equals("") &&
                !make_first_name_admin.getText().equals("")) {

                    String newPassword = null;
                    try {
                        MessageDigest md5 = MessageDigest.getInstance("MD5");
                        byte[] bytes = md5.digest(make_new_password_admin.getText().getBytes());
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
                            "surname = '" + make_new_surname_admin.getText() + "',\n" +
                            "first_name = '" + make_first_name_admin.getText() + "',\n" +
                            "second_name = '" + make_new_second_name_admin.getText() + "',\n" +
                            "login_name = '" + make_new_login_admin.getText() + "',\n" +
                            "password = '" + newPassword + "'\n" +
                            "where id = '" + SaveLogin.ID + "'");
                    System.out.println("Строк изменено " + count);
                    table_admin.getItems().clear();
                    GetPeopleFromDataBase();
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

    public void EditInfoPeople()
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://std-mysql.ist.mospolytech.ru:3306/std_2008_kursovoi",
                    "std_2008_kursovoi", "12345678");
            
            Statement statement = connection.createStatement();

            try{
                String newPassword = null;
                try {
                    MessageDigest md5 = MessageDigest.getInstance("MD5");
                    byte[] bytes = md5.digest(make_new_password_user.getText().getBytes());
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
                        "surname = '" + make_new_surname_user.getText() + "',\n" +
                        "first_name = '" + make_new_first_name_user.getText() + "',\n" +
                        "second_name = '" + make_new_second_name_user.getText() + "',\n" +
                        "login_name = '" + make_new_login_user.getText() + "',\n" +
                        "password = '" + newPassword + "'\n" +
                        "where id = '" + make_new_id_user.getText() + "'");
                System.out.println("Строк изменено " + count);
                table_admin.getItems().clear();
                GetPeopleFromDataBase();
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
                    make_first_name_admin.setText(GetInfo.getString(2));
                    make_new_login_admin.setText(GetInfo.getString(4));
                    make_new_second_name_admin.setText(GetInfo.getString(3));
                    make_new_surname_admin.setText(GetInfo.getString(1));
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
                        BD.QUOTE_PEOPLE + ", quote_group)" + "VALUES(?,?,?,?,?,?,?,?,?)");

                Date d = new Date();

                reg.setInt(1, id_registr_people);
                reg.setString(2, sur_q.getText());
                reg.setString(3, first_name_q.getText());
                reg.setString(4, second_name_q.getText());
                reg.setString(5, subject_q.getText());
                reg.setString(6, d.toString());
                reg.setString(7, quote_q.getText());
                reg.setInt(8,PeopleID);
                reg.setString(9,SaveLogin.Group);
                reg.executeUpdate();

                table_admin.getItems().clear();

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

    public class GetInfoPeople {
        private String surname;
        private String first_name;
        private String second_name;
        private String login_name;
        private String password;
        private String id;
        private String user;
        private String group;


        public GetInfoPeople(String surname, String first_name, String second_name, String login_name, String password, String id, String user, String group) {
            this.surname = surname;
            this.first_name = first_name;
            this.second_name = second_name;
            this.login_name = login_name;
            this.password = password;
            this.id = id;
            this.user = user;
            this.group = group;
        }

        public void setFirst_name(String first_name) {
            this.first_name = first_name;
        }


        public void setSecond_name(String second_name) {
            this.second_name = second_name;
        }

        public void setLogin_name(String login_name) {
            this.login_name = login_name;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setSurname(String surname) {
            this.surname = surname;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public  void setUser(String user){
            this.user = user;
        }

        public  void setGroup(String group) {this.group = group;}

        public GetInfoPeople() {
        }

        public String getFirst_name() {
            return first_name;
        }

        public String getGroup() {
            return group;
        }

        public String getId() {
            return id;
        }
        public  String getLogin_name(){return login_name;}

        public String getSurname() {
            return surname;
        }

        public String getUser() {
            return user;
        }

        public String getSecond_name() {
            return second_name;
        }

        public String getPassword() {
            return password;
        }

    }
}
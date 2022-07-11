package com.example.first_krurs;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class Verificator_controller {
    private ObservableList<GetUserClassVer> usersData = FXCollections.observableArrayList();
    private ObservableList<GetUserClassVerAll> usersdata = FXCollections.observableArrayList();
    @FXML
    private TableColumn<GetUserClassVer,String> data_ver;

    @FXML
    private Button fio_red_ver;

    @FXML
    private TableColumn<GetUserClassVer, String> id_ver_last_colom;

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
    private TextField make_first_name_ver;

    @FXML
    private TextField make_new_first_name_user_ver;

    @FXML
    private TextField make_new_id_user_ver;

    @FXML
    private TextField make_new_login_user_ver;

    @FXML
    private TextField make_new_login_ver;

    @FXML
    private TextField make_new_password_user_ver;

    @FXML
    private TextField make_new_password_ver;

    @FXML
    private TextField make_new_second_name_user_ver;

    @FXML
    private TextField make_new_second_name_ver;

    @FXML
    private TextField make_new_surname_user_ver;

    @FXML
    private TextField make_new_surname_ver;

    @FXML
    private Label text_7_ver;

    @FXML
    private Label count_first_metod_ver;

    @FXML
    private TextField qoute_new_id_ver;

    @FXML
    private TextField qoute_new_first_name_ver;

    @FXML
    private TextField qoute_new_second_name_ver;

    @FXML
    private TextField qoute_new_subject_ver;

    @FXML
    private TextField qoute_new_surname_ver;

    @FXML
    private TextField qoute_new_text_ver;

    @FXML
    private Button qoute_new_button_ver;

    @FXML
    private Label text_10_ver;

    @FXML
    private Label text_11_ver;

    @FXML
    private Label count_label_button;

    @FXML
    private Button count_button;

    @FXML
    private TableColumn<GetUserClassVerAll, String> data_ver_all;

    @FXML
    private TableColumn<GetUserClassVerAll, String> first_name_ver_all;

    @FXML
    private TableColumn<GetUserClassVerAll, String> id_ver_all;

    @FXML
    private TableColumn<GetUserClassVerAll, String> id_ver_last_colom_all;

    @FXML
    private TableColumn<GetUserClassVerAll, String> qoute_surname_ver_all;

    @FXML
    private TableColumn<GetUserClassVerAll, String> quote_ver_all;

    @FXML
    private TableColumn<GetUserClassVerAll, String> second_name_ver_all;

    @FXML
    private TableColumn<GetUserClassVerAll, String> subject_ver_all;

    @FXML
    private TableView<GetUserClassVerAll> table_people;

    @FXML
    public void initialize() {
        ver_back_ver.setOnAction(actionEvent -> {
            ver_back_ver.getScene().getWindow().hide();
            Main.ver_window.show();

        });

        count_button.setOnAction(actionEvent -> {
            CountBtn();
        });
        quote_add_ver.setOnAction(actionEvent -> {
            AddInfoToDataBase();
            //quote_add_ver.getScene().getWindow().hide();
        });
        fio_red_ver.setOnAction(actionEvent -> {
            ChangeInfoAdmin();
        });
        GetQuoteFromDataBase();
        EditInfoAdmin();
        qoute_new_button_ver.setOnAction(actionEvent -> {
            ChangeInfoGroupAndYou();
        });
        Count();

    }

    public void GetDataOnlyGroup()
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://std-mysql.ist.mospolytech.ru:3306/std_2008_kursovoi",
                    "std_2008_kursovoi", "12345678");

            ResultSet GetInfo;
            int i = 0;
            Statement statement = connection.createStatement();
            String group= "";
            ResultSet query2;
            query2 = statement.executeQuery("Select * from quote");
            while (query2.next()) {
                usersData.add(new GetUserClassVer(/*Айди*/query2.getString(1),/*Фамилия*/query2.getString(2),
                        /*Имя*/query2.getString(3),/*Отчество*/query2.getString(4),
                        /*Предмет*/query2.getString(5), /*Дата*/query2.getString(6),
                        /*Цитата*/query2.getString(7), query2.getString(8)));
                i++;
                SetQuoteAll();
            }

            System.out.println(i);



            //ResultSet result = statement.executeQuery(query);
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void Count()
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://std-mysql.ist.mospolytech.ru:3306/std_2008_kursovoi",
                    "std_2008_kursovoi", "12345678");

            ResultSet GetInfo;
            Statement statement = connection.createStatement();
            GetInfo = statement.executeQuery("Select Count(*) from quote\nWhere id_people = '" + SaveLogin.ID + "'");
            while (GetInfo.next())
                count_first_metod_ver.setText(GetInfo.getString(1));

            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void CountBtn()
    {
        count_label_button.setVisible(true);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://std-mysql.ist.mospolytech.ru:3306/std_2008_kursovoi",
                    "std_2008_kursovoi", "12345678");

            ResultSet GetInfo;
            Statement statement = connection.createStatement();
            GetInfo = statement.executeQuery("Select Count(*) from quote\nWhere id_people = '" + SaveLogin.ID + "'");
            while (GetInfo.next())
                count_label_button.setText(GetInfo.getString(1));

            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void ChangeInfoGroupAndYou()
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://std-mysql.ist.mospolytech.ru:3306/std_2008_kursovoi",
                    "std_2008_kursovoi", "12345678");

            Statement statement = connection.createStatement();
            ResultSet idshka;
            String newID = "";
            idshka = statement.executeQuery("SELECT id_people from quote\nWhere quote_id = '"+qoute_new_id_ver.getText()+ "'");
            while (idshka.next())
            {
                newID = idshka.getString(1);
            }
            String group = "";
            String groupUser = "";
            String Group = "";
            ResultSet grupka = statement.executeQuery("SELECT group_people from People\nWhere id = '"+SaveLogin.ID+"'");
            while (grupka.next())
                group = grupka.getString(1);
            ResultSet grupkaUnk = statement.executeQuery("SELECT id_people from quote\nwhere quote_id = '" + qoute_new_id_ver.getText() + "'");
            while (grupkaUnk.next())
                groupUser = grupkaUnk.getString(1);
            ResultSet grupkaUnq = statement.executeQuery("SELECT group_people from People\nwhere id = '" + groupUser + "'");
            while (grupkaUnq.next())
                Group = grupkaUnq.getString(1);
            System.out.println(group + "        " + Group);
            if (SaveLogin.ID.equals(newID) || group.equals(Group)) {
                try {
                    int count = statement.executeUpdate("update quote\n" +
                            "set\n" +
                            "quote_surname = '" + qoute_new_surname_ver.getText() + "',\n" +
                            "quote_first_name = '" + qoute_new_first_name_ver.getText() + "',\n" +
                            "quote_second_name = '" + qoute_new_second_name_ver.getText() + "',\n" +
                            "quote_subject = '" + qoute_new_subject_ver.getText() + "',\n" +
                            "quote_text = '" + qoute_new_text_ver.getText() + "'\n" +
                            "where quote_id = '" + qoute_new_id_ver.getText() + "'");
                    System.out.println("Строк изменено " + count);
                    table_ver.getItems().clear();
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

                if (!make_new_surname_ver.getText().equals("") && !make_new_second_name_ver.getText().equals("")&&
                        !make_new_password_ver.getText().equals("") && !make_new_login_ver.getText().equals("") &&
                        !make_first_name_ver.getText().equals("")) {

                    String newPassword = null;
                    try {
                        MessageDigest md5 = MessageDigest.getInstance("MD5");
                        byte[] bytes = md5.digest(make_new_password_ver.getText().getBytes());
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
                            "surname = '" + make_new_surname_ver.getText() + "',\n" +
                            "first_name = '" + make_first_name_ver.getText() + "',\n" +
                            "second_name = '" + make_new_second_name_ver.getText() + "',\n" +
                            "login_name = '" + make_new_login_ver.getText() + "',\n" +
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
                    make_first_name_ver.setText(GetInfo.getString(2));
                    make_new_login_ver.setText(GetInfo.getString(4));
                    make_new_second_name_ver.setText(GetInfo.getString(3));
                    make_new_surname_ver.setText(GetInfo.getString(1));
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
        first_name_ver.setCellValueFactory(new PropertyValueFactory<GetUserClassVer,String>("first_name"));
        data_ver.setCellValueFactory(new PropertyValueFactory<GetUserClassVer,String>("date"));
        id_ver.setCellValueFactory(new PropertyValueFactory<GetUserClassVer,String>("id"));
        qoute_surname_ver.setCellValueFactory(new PropertyValueFactory<GetUserClassVer,String>("surname"));
        quote_ver.setCellValueFactory(new PropertyValueFactory<GetUserClassVer,String>("quote"));
        second_name_ver.setCellValueFactory(new PropertyValueFactory<GetUserClassVer,String>("second_name"));
        subject_ver.setCellValueFactory(new PropertyValueFactory<GetUserClassVer,String>("subject"));
        id_ver_last_colom.setCellValueFactory(new PropertyValueFactory<GetUserClassVer,String>("PeopleID"));
        table_ver.setItems(usersData);
    }

    public void SetQuoteAll(){
        first_name_ver_all.setCellValueFactory(new PropertyValueFactory<GetUserClassVerAll,String>("first_name1"));
        data_ver_all.setCellValueFactory(new PropertyValueFactory<GetUserClassVerAll,String>("date1"));
        id_ver_all.setCellValueFactory(new PropertyValueFactory<GetUserClassVerAll,String>("id1"));
        qoute_surname_ver_all.setCellValueFactory(new PropertyValueFactory<GetUserClassVerAll,String>("surname1"));
        quote_ver_all.setCellValueFactory(new PropertyValueFactory<GetUserClassVerAll,String>("quote1"));
        second_name_ver_all.setCellValueFactory(new PropertyValueFactory<GetUserClassVerAll,String>("second_name1"));
        subject_ver_all.setCellValueFactory(new PropertyValueFactory<GetUserClassVerAll,String>("subject"));
        id_ver_last_colom_all.setCellValueFactory(new PropertyValueFactory<GetUserClassVerAll,String>("PeopleID1"));
        table_people.setItems(usersdata);
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
                    usersData.add(new GetUserClassVer(/*Айди*/GetInfo.getString(1),/*Фамилия*/GetInfo.getString(2),
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
                        BD.QUOTE_PEOPLE + ", quote_group)" + "VALUES(?,?,?,?,?,?,?,?,?)");

                Date d = new Date();

                reg.setInt(1, id_registr_people);
                reg.setString(2, sur_q_ver.getText());
                reg.setString(3, first_name_q_ver.getText());
                reg.setString(4, second_name_q_ver.getText());
                reg.setString(5, subject_q_ver.getText());
                reg.setString(6, d.toString());
                reg.setString(7,quote_q_ver.getText());
                reg.setInt(8,PeopleID);
                reg.setString(9,SaveLogin.Group);
                reg.executeUpdate();

                table_ver.getItems().clear();
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

    public class GetUserClassVerAll  {
        private String first_name;
        private String date;
        private String id;
        private String surname;
        private String quote;
        private String second_name;
        private String subject;
        private String PeopleID;

        public GetUserClassVerAll(String id, String surname, String first_name, String second_name, String subject, String date, String quote, String PeopleID) {
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

        public GetUserClassVerAll() {
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
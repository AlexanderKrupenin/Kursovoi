package com.example.first_krurs;

import java.lang.constant.Constable;
import java.sql.*;
import java.util.List;
import java.util.Arrays;
import java.util.IntSummaryStatistics;

public class VPN {
    public static int last_id;

    public static void registr_bd_people(String surname, String first_name, String second_name, String login_name, String password, String table, int id, String group) {
        try {
            String surname_registr_people = surname;
            String first_name_registr_people = first_name;
            String second_name_registr_people = second_name;
            String login_name_registr_people = login_name;
            String password_registr_people = password;
            String table_registr_people = table;
            String group_registr_people = group;
            int id_registr_people = id;
            String root_registr_people = "student";

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://std-mysql.ist.mospolytech.ru:3306/std_2008_kursovoi",
                    "std_2008_kursovoi", "12345678");

            String query_id = "SELECT id FROM People";
            ResultSet id_save;

            String query_2 = "SELECT MAX(id) FROM People";
            ResultSet count;
            ResultSet checkString;

            Statement statement = connection.createStatement();
            Statement statement_count = connection.createStatement();
            Statement test = connection.createStatement();

            String query = "INSERT INTO " + BD.USER_TABLE + "(" +
                    BD.USER_SURNAME + "," + BD.USER_FIRST_NAME
                    + "," + BD.USER_SECOND_NAME + "," + BD.USER_LOGIN_NAME
                    + "," + BD.USER_PASSWORD + "," + BD.USER_ID + "," + BD.USER_ROOT + "," + BD.USER_GROUP
                    + ")" + " VALUES(?,?,?,?,?,?,?,?)";

            try {
                int check = 0;
                int csharpluchse = 0;
                PreparedStatement reg = null;
                count = statement.executeQuery("SELECT COUNT(*) FROM People");
                while (count.next())
                    check = count.getInt(1);
                if (check != 0) {
                    checkString = statement.executeQuery("SELECT login_name FROM People");
                    while (checkString.next()) {
                        if (login_name.equals(checkString.getString(1))) {
                            csharpluchse = 1;
                            break;
                        } else csharpluchse = 0;
                    }
                }
                if (check != 0) {
                    id_save = statement.executeQuery(query_2);
                    while (id_save.next()) {
                        id_registr_people = id_save.getInt(1) + 1;
                    }
                } else id_registr_people = 1;
                if (csharpluchse == 0) {
                    reg = connection.prepareStatement(query);
                    //reg.setString(1, table_registr_people);
                    reg.setString(1, surname_registr_people);
                    reg.setString(2, first_name_registr_people);
                    reg.setString(3, second_name_registr_people);
                    reg.setString(4, login_name_registr_people);
                    reg.setString(5, password_registr_people);
                    reg.setInt(6, id_registr_people);
                    reg.setString(7, root_registr_people);
                    reg.setString(8, group_registr_people);
                    reg.executeUpdate();
                }
                else if (csharpluchse == 1){}

            } catch (SQLException e) {
                e.printStackTrace();
            }
            //ResultSet result = statement.executeQuery(query);
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
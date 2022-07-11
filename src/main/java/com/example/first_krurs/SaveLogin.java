package com.example.first_krurs;

public class SaveLogin {
    public static String login;
    public static String ID;
    public static String Group;
    public static String SaveID;

    public SaveLogin(String login) {
        this.login = login;
    }

    public String GetLogin() {
        return this.login;
    }
}
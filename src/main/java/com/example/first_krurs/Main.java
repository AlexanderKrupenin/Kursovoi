package com.example.first_krurs;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {
    public static Stage reg_window;
    public static Stage guest_window;
    public static Stage admin_window;
    public static Stage add_window;
    public static Stage ver_window;
    public static Stage table_people;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 850, 600);
        stage.setTitle("Курсовой проект");
        stage.setScene(scene);
        stage.setResizable(false);
        reg_window = stage;
        guest_window = stage;
        admin_window = stage;
        ver_window = stage;
        add_window = stage;
        table_people = stage;
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
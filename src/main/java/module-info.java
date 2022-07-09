module com.example.first_krurs {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.first_krurs to javafx.fxml;
    exports com.example.first_krurs;
}
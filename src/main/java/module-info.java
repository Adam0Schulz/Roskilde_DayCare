module com.example.roskilde_daycare {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.roskilde_daycare to javafx.fxml;
    exports com.example.roskilde_daycare;
}
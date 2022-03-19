module com.example.pojebana_skuska {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.roskilde_daycare to javafx.fxml;
    exports com.example.roskilde_daycare;
}
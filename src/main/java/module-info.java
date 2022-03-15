module com.example.pojebana_skuska {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.pojebana_skuska to javafx.fxml;
    exports com.example.pojebana_skuska;
}
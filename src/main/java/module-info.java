module com.example.pojebana_skuska {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.roskilde_daycare to javafx.fxml;
    exports com.example.roskilde_daycare;
    exports com.example.roskilde_daycare.Controllers;
    opens com.example.roskilde_daycare.Controllers to javafx.fxml;
}
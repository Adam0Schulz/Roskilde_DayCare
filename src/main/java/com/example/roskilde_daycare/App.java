package com.example.roskilde_daycare;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.sql.Date;
import java.util.Objects;



public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Roskilde Daycare");
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
        //DB_Connector.connect();
        //DB_Connector.addChild("Charlie", "Kardel", Date.valueOf("2021-02-10"), "M", "0611942842");
        //DB_Connector.addParent("Charlie", "Kardel", "charlie@gmail.com", "dkdkdk", 2200, "Copenhagen", "8484848");
        //DB_Connector.deleteChildAndParent("0611947373");
        //DB_Connector.addEmployee("Simona", "Kardel", "kardel@gmail.com", "kkk", 2300, "Copenhagen", "84848484", 25000, "0611942842", "1234");

    }

}
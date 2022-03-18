package com.example.roskilde_daycare;

import javafx.application.Application;
import javafx.stage.Stage;



public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {

    }

    public static void main(String[] args) {
        //launch();
        //DB_Connector.connect();
        //DB_Connector.addParent("Charlie", "Kardel", "charlie@gmail.com", "dkdkdk", 2200, "Copenhagen", "8484848");
        DB_Connector.deleteChildAndParent("0611947373");
    }

}
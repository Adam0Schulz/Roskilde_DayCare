package com.example.roskilde_daycare;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
        //DB_Connector.addParent("Charlie", "Kardel", "charlie@gmail.com", "dkdkdk", 2200, "Copenhagen", "8484848");
        //DB_Connector.deleteChildAndParent("0611947373");

    }

}
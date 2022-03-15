package com.example.pojebana_skuska;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB_Connector {
    private static Connection connect = null;
    private static String database;


    private static String url = "jdbc:mysql://localhost:3306/Daycare";
    private static String user;
    private static String password;

    // connection to database
    public static Connection connect(String user, String password) {
        System.out.println("Connecting to JDBC");
        System.out.println("Connecting to DBMS");
        try {
            connect = DriverManager.getConnection(url, user, password);
            System.out.println("Connection successful");
        } catch (SQLException e) {
            System.out.println("Connection failed");
            e.printStackTrace();
        } return connect;
    }

}

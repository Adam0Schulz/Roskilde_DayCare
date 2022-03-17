package com.example.roskilde_daycare;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class DB_Connector {
    private static Connection connect = null;
    private static String database;
    private static String url = "jdbc:mysql://localhost:3306/Daycare";
    private static String user = "root";
    private static String password = "Ebberodes";
    private static PreparedStatement preparedStatement = null;
    private static PreparedStatement psInsert = null;
    private static ResultSet resultSet = null;

    // connection to database
    public static Connection connect() {
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

    public static void changeScene(ActionEvent event, String fxmlFile, String title){
        Parent root = null;
        try {
            root = FXMLLoader.load(DB_Connector.class.getResource(fxmlFile));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root, 600, 400));
        stage.show();

    }

    public static void login(ActionEvent event, String username, String password){
        try {
            connect();
            preparedStatement = connect.prepareStatement("SELECT password from users WHERE username = ?");
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.isBeforeFirst()) {
                //System.out.println("User not found");
                Alert alert = new Alert(Alert.AlertType.ERROR, "User not found");
                alert.show();
            } else {
                while (resultSet.next()) {
                    String retrievedPassword = resultSet.getString("password");
                    if (retrievedPassword.equals(password)) {
                        changeScene(event, "logged-in.fxml", "Welcome!");
                    } else {
                       // System.out.println("Password didnt match");
                        Alert alert = new Alert(Alert.AlertType.ERROR, "Password doesn't match. Try again.");
                        alert.show();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    public static Collection<Employee> employeeList() {
        Collection<Employee> employees = new ArrayList<Employee>();

        try {
            connect();
            preparedStatement = connect.prepareStatement("SELECT first_name, last_name, email_address, address, zip_code, city, phone_number, salary, CPR FROM Daycare.Employees");
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String email = resultSet.getString("email_address");
                String address = resultSet.getString("address");
                int zip = resultSet.getInt("zip_code");
                String city = resultSet.getString("city");
                String phoneNumber = resultSet.getString("phone_number");
                Float salary = resultSet.getFloat("salary");
                String CPR = resultSet.getString("CPR");
                employees.add(new Employee(firstName, lastName, email, address, zip, city, phoneNumber, salary, CPR));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        } return employees;
    }

    public static Collection<Child> waitingList() {
        Collection<Child> children = new ArrayList<Child>();

        try {
            connect();
            preparedStatement = connect.prepareStatement("SELECT first_name, last_name, queue_number, CPR, date_of_birth, gender FROM Daycare.Children");
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String queueNumber = resultSet.getString("queue_number");
                String CPR = resultSet.getString("CPR");
                Date dateOfBirth = resultSet.getDate("date_of_birth");
                String gender = resultSet.getString("gender");

                children.add(new Child(firstName, lastName, queueNumber, CPR, dateOfBirth, gender));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        } return children;
    }

    public static Collection<Parent> parentList() {
        Collection<Parent> parents = new ArrayList<Parent>();

        try {
            connect();
            preparedStatement = connect.prepareStatement("SELECT first_name, last_name, email_address, address, zip_code, city, phone_number FROM Daycare.Parents");
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String email = resultSet.getString("email_address");
                String address = resultSet.getString("address");
                int zip = resultSet.getInt("zip_code");
                String city = resultSet.getString("city");
                String phoneNumber = resultSet.getString("phone_number");

                parents.add(new Parent(firstName, lastName, email, address, zip, city, phoneNumber));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        } return parents;
    }

    public static void addEmployee(String firstName, String lastName, String email, String address, int zip, String city, String phone, float salary){
        try {
            connect();
            preparedStatement = connect.prepareStatement("SELECT  * from Daycare.Employees where first_name  = ? AND last_name = ?");
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.isBeforeFirst()) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Employee " + firstName + " " + lastName + "already exists.");
                alert.show();
            } else {
                psInsert = connect.prepareStatement("INSERT INTO Daycare.Employees(first_name, last_name, email_address, address, zip_code, city, phone_number, salary) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
                psInsert.setString(1, firstName);
                psInsert.setString(2, lastName);
                psInsert.setString(3, email);
                psInsert.setString(4, address);
                psInsert.setInt(5, zip);
                psInsert.setString(6, city);
                psInsert.setString(7, phone);
                psInsert.setFloat(8, salary);
                psInsert.executeUpdate();

                // should we create an object and add it to the array?
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    public static void deleteEmployee(String firstName, String lastname) {
        try {
            // do we first need to check if it exists?
            connect();
            preparedStatement = connect.prepareStatement("DELETE FROM Daycare.Employees WHERE first_name = ? AND last_name = ?");
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastname);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    // delete child
    // delete parent
    // transfer child from waiting list to attendees






    private static void closeConnection() {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (psInsert != null) {
            try {
                psInsert.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connect != null) {
            try {
                connect.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }




}

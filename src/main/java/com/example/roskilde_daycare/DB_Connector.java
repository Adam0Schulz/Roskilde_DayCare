package com.example.roskilde_daycare;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    private static PreparedStatement ps = null;
    private static ResultSet resultSet = null;
    private static ResultSet rs = null;

    // connection to databasedk
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
        stage.setScene(new Scene(root, 1327, 747));
        stage.show();
    }

    // Login method (needs adjustments)
    public static void login(ActionEvent event, String email, String password){
        try {
            connect();
            preparedStatement = connect.prepareStatement("SELECT password from Daycare.Employees WHERE email_address = ?");
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();

            if(!resultSet.isBeforeFirst()) {
                //System.out.println("User not found");
                Alert alert = new Alert(Alert.AlertType.ERROR, "User not found");
                alert.show();
            } else {
                while (resultSet.next()) {
                    String retrievedPassword = resultSet.getString("password");
                    if (retrievedPassword.equals(password)) {
                        changeScene(event, "Pupils.fxml", "Welcome!");
                    } else {
                        // System.out.println("Password didnt match");
                        Alert alert = new Alert(Alert.AlertType.ERROR, "Password doesn't match.");
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

    // Method to create Employee objects from database
    public static Collection<Employee> employeeList() {
        Collection<Employee> employees = new ArrayList<Employee>();

        try {
            connect();
            preparedStatement = connect.prepareStatement("SELECT first_name, last_name, email_address, street, zip_code, city, phone_number, salary, CPR, password FROM Daycare.Employees");
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String email = resultSet.getString("email_address");
                String street = resultSet.getString("street");
                int zip = resultSet.getInt("zip_code");
                String city = resultSet.getString("city");
                String phoneNumber = resultSet.getString("phone_number");
                Float salary = resultSet.getFloat("salary");
                String CPR = resultSet.getString("CPR");
                String password = resultSet.getString("password");
                employees.add(new Employee(firstName, lastName, email, street, zip, city, phoneNumber, salary, CPR, password));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        } return employees;
    }

    // Method to create Queuer objects from database
    public static Collection<Queuer> waitingList() {
        Collection<Queuer> waitingList = new ArrayList<Queuer>();

        try {
            connect();


            preparedStatement = connect.prepareStatement("SELECT c.first_name, c.last_name, c.parent_ID, c.CPR, c.date_of_birth, c.gender, w.Timestamp FROM Daycare.Children c JOIN Daycare.Waiting_list w ON c.ID = w.Child_ID");
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                int parentID = resultSet.getInt("parent_ID");
                String CPR = resultSet.getString("CPR");
                Date dateOfBirth = resultSet.getDate("date_of_birth");
                String gender = resultSet.getString("gender");
                Timestamp time = resultSet.getTimestamp("Timestamp");
                //String time = resultSet.getString("Timestamp");
                //LocalDateTime date = LocalDateTime.parse(time);

                preparedStatement = connect.prepareStatement("SELECT first_name, last_name, email_address, street, zip_code, city, phone_number FROM Daycare.Parents WHERE ID = ?");
                preparedStatement.setInt(1, parentID);
                rs = preparedStatement.executeQuery();

                com.example.roskilde_daycare.Parent parent = null;

                if (rs.next()) {
                    String firstN = rs.getString("first_name");
                    String lastN = rs.getString("last_name");
                    String email = rs.getString("email_address");
                    String street = rs.getString("street");
                    int zip = rs.getInt("zip_code");
                    String city = rs.getString("city");
                    String phone = rs.getString("phone_number");

                    parent = new com.example.roskilde_daycare.Parent(firstN, lastN, email, street, zip, city, phone);
                }

                waitingList.add(new Queuer(firstName, lastName, dateOfBirth, gender, parent, time, CPR ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        } return waitingList;
    }

    // Method to create Attendee objects from database
    public static Collection<Attendee> attendeeList(){
        Collection<Attendee> attendees = new ArrayList<Attendee>();

        try {
            connect();
            preparedStatement = connect.prepareStatement("SELECT c.first_name, c.last_name, c.parent_ID, c.CPR, c.date_of_birth, c.gender, g.name FROM Daycare.Children c JOIN Daycare.Attendees a ON c.ID = a.child_ID JOIN Daycare.Classes g ON a.group_ID = g.ID");
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                int parentID = resultSet.getInt("parent_ID");
                String CPR = resultSet.getString("CPR");
                Date dateOfBirth = resultSet.getDate("date_of_birth");
                String gender = resultSet.getString("gender");
                String groupName = resultSet.getString("name");


                preparedStatement = connect.prepareStatement("SELECT first_name, last_name, email_address, street, zip_code, city, phone_number FROM Daycare.Parents WHERE ID = ?");
                preparedStatement.setInt(1, parentID);
                rs = preparedStatement.executeQuery();

                com.example.roskilde_daycare.Parent parent = null;

                if (rs.next()) {
                    String firstN = rs.getString("first_name");
                    String lastN = rs.getString("last_name");
                    String email = rs.getString("email_address");
                    String street = rs.getString("street");
                    int zip = rs.getInt("zip_code");
                    String city = rs.getString("city");
                    String phone = rs.getString("phone_number");

                    parent = new com.example.roskilde_daycare.Parent(firstN, lastN, email, street, zip, city, phone);
                }

                attendees.add(new Attendee(firstName, lastName, dateOfBirth, gender, parent, groupName, CPR));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        } return attendees;
    }

    // not needed at the moment
//    public static Collection<com.example.roskilde_daycare.Parent> parentList() {
//        Collection<com.example.roskilde_daycare.Parent> parents = new ArrayList<>();
//
//        try {
//            connect();
//            preparedStatement = connect.prepareStatement("SELECT first_name, last_name, email_address, street, zip_code, city, phone_number FROM Daycare.Parents");
//            resultSet = preparedStatement.executeQuery();
//
//            while(resultSet.next()) {
//                String firstName = resultSet.getString("first_name");
//                String lastName = resultSet.getString("last_name");
//                String email = resultSet.getString("email_address");
//                String street = resultSet.getString("street");
//                int zip = resultSet.getInt("zip_code");
//                String city = resultSet.getString("city");
//                String phoneNumber = resultSet.getString("phone_number");
//
//                parents.add(new com.example.roskilde_daycare.Parent(firstName, lastName, email, street, zip, city, phoneNumber));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            closeConnection();
//        } return parents;
//    }

    // Method to add employee to the database (tested, works)
    public static void addEmployee(String firstName, String lastName, String email, String address, int zip, String city, String phone, float salary, String CPR, String password){
        try {
            connect();
            preparedStatement = connect.prepareStatement("SELECT  * from Daycare.Employees where CPR  = ?");
            preparedStatement.setString(1, CPR);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.isBeforeFirst()) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Employee " + firstName + " " + lastName + "already exists.");
                alert.show();
            } else {
                psInsert = connect.prepareStatement("INSERT INTO Daycare.Employees(first_name, last_name, email_address, street, zip_code, city, phone_number, salary, CPR, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                psInsert.setString(1, firstName);
                psInsert.setString(2, lastName);
                psInsert.setString(3, email);
                psInsert.setString(4, address);
                psInsert.setInt(5, zip);
                psInsert.setString(6, city);
                psInsert.setString(7, phone);
                psInsert.setFloat(8, salary);
                psInsert.setString(9, CPR);
                psInsert.setString(10, password);
                psInsert.executeUpdate();
                System.out.println("Employee has been added.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    // Method to add a child to the Children table and Waiting list (tested, works, method addParent returns Parent and has to be used to get parent´s ID)
    public static void addChild(String firstName, String lastName, Date dateOfBirth, String gender, String CPR){
        try {
            connect();
            // checking if child already exists
            preparedStatement = connect.prepareStatement("SELECT  * from Daycare.Children where CPR  = ?");
            preparedStatement.setString(1, CPR);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.isBeforeFirst()) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Child " + firstName + " " + lastName + "already exists.");
                alert.show();
            } else {
                psInsert = connect.prepareStatement("INSERT INTO Daycare.Children(first_name, last_name, date_of_birth, gender, CPR) VALUES (?, ?, ?, ?, ?)");
                psInsert.setString(1, firstName);
                psInsert.setString(2, lastName);
                psInsert.setDate(3, dateOfBirth);
                psInsert.setString(4, gender);
                psInsert.setString(5, CPR);
                psInsert.executeUpdate();

                preparedStatement = connect.prepareStatement("SELECT ID FROM Daycare.Children WHERE CPR = ?");
                preparedStatement.setString(1, CPR);
                resultSet = preparedStatement.executeQuery();

                int childID = 0;
                if(resultSet.next()){
                    childID = resultSet.getInt("ID");
                }

                psInsert = connect.prepareStatement("INSERT INTO Daycare.Waiting_list(Child_ID) VALUES (?)");
                psInsert.setInt(1, childID);
                psInsert.executeUpdate();
                System.out.println("Child has been added.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    // Method to add a parent to Parents table (tested, works)
    public static com.example.roskilde_daycare.Parent addParent(String firstName, String lastName, String email, String street, int zip, String city, String phone){
        com.example.roskilde_daycare.Parent parent = null;
        try {
            connect();
            psInsert = connect.prepareStatement("INSERT INTO Daycare.Parents(first_name, last_name, email_address, street, zip_code, city, phone_number) VALUES (?, ?, ?, ?, ?, ?, ?)");
            psInsert.setString(1, firstName);
            psInsert.setString(2, lastName);
            psInsert.setString(3, email);
            psInsert.setString(4, street );
            psInsert.setInt(5, zip);
            psInsert.setString(6, city);
            psInsert.setString(7,phone);

            parent = new com.example.roskilde_daycare.Parent(firstName, lastName, email, street, zip, city, phone);

            psInsert.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        } return parent;
    }
    // Method to delete employee from the database (tested, works)
    public static void deleteEmployee(String CPR) {
        try {
            // first check if exists - needs to be added
            connect();
            preparedStatement = connect.prepareStatement("DELETE FROM Daycare.Employees WHERE CPR = ?");
            preparedStatement.setString(1, CPR);
            preparedStatement.executeUpdate();
            System.out.println("Employee has been deleted.");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }


    // delete child and parent (tested, works)
    public static void deleteChildAndParent(String CPR) {
        try {
            connect();
            // deletes child with given CPR
            preparedStatement = connect.prepareStatement("DELETE c, p FROM Daycare.Children c JOIN Daycare.Parents p ON c.parent_ID = p.ID WHERE c.CPR = ?");
            preparedStatement.setString(1, CPR);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }


    // update child and parent


    // view schedule
    // update schedule
    // delete schedule


    // transfer child from waiting list to attendees
    // delete child from queuers and add to attendees
    // INSERT INTO table_name1(fields you want)
    //  SELECT fields you want FROM table_name2
    public static void transferChild(String CPR, String group) {
        try {
            connect();
            // deletes child from queuers based on CPR
            preparedStatement = connect.prepareStatement("DELETE w FROM Daycare.Waiting_list w JOIN Daycare.Children c ON c.ID = w.Child_ID WHERE c.CPR = ?");
            preparedStatement.setString(1, CPR);
            preparedStatement.executeUpdate();

            // finds group´s ID
            //preparedStatement = connect.prepareStatement()

            // adds child to attendees
            psInsert = connect.prepareStatement("INSERT INTO Daycare.Attendees(child_ID) SELECT c.ID FROM Daycare.Children c WHERE c.CPR = ?;");


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }



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

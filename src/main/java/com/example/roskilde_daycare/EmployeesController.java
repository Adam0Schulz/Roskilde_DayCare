package com.example.roskilde_daycare;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;

public class EmployeesController extends CustomStage {

    @FXML private Button addButton;
    @FXML private Button createEmployeeBtn;
    private String[] search;
    @FXML private TextField searchField;
    @FXML private TextField firstName;
    @FXML private TextField lastName;
    @FXML private TextField street;
    @FXML private TextField zip;
    @FXML private TextField email;
    @FXML private TextField phone;
    @FXML private TextField city;
    @FXML private TextField salary;
    @FXML private TextField CPR;
    @FXML private PasswordField password;
    @FXML private VBox EmployeeList;

    protected ArrayList<VBox> getEmployeeList() {

        ArrayList<VBox> list = new ArrayList<VBox>();
        if(!(DB_Connector.getCurrentUser().equals("Sandra Madsen"))) {
            VBox box = new VBox();
            box.setPrefWidth(977);
            box.setPrefHeight(59);
            box.setAlignment(Pos.CENTER);
            Label accessDeniedMessage = DynamicElements.createListItemLabel("Unfortunately you don't have access to this information :(");
            accessDeniedMessage.setAlignment(Pos.CENTER);
            accessDeniedMessage.setPrefWidth(977);
            box.getChildren().add(accessDeniedMessage);
            list.add(box);
            return list;
        }

        Collection<Employee> employees = DB_Connector.employeeList();

        if(search != null) {
            Collection<Employee> searchResult = (Collection<Employee>) DB_Connector.search("Employee", search[0], search[1]);
            if(searchResult.size() > 0) {
                employees = searchResult;
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Zero employees found :( Sorry");
                alert.show();
            }
        }

        for(Employee employee : employees) {
            list.add(DynamicElements.createListItem("Employee",employee.getAttributeArray(), employee.getAllAttributeArray(), false));
        }
        return list;
    }

    @FXML
    public void initialize() {

        if(EmployeeList != null) {
        EmployeeList.getChildren().addAll(getEmployeeList());
        }

        searchField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if(keyEvent.getCode().equals(KeyCode.ENTER)) {
                    String searchText = searchField.getText();
                    if(searchText == "") {
                        search = null;
                    } else {
                        if(searchText.contains(": ")) {
                            search = searchText.split(": ", 2);
                        } else {
                            search = new String[2];
                            search[0] = "";
                            search[1] = searchText;
                        }

                        System.out.println(search[0] + " " + search[1]);
                    }
                    EmployeeList.getChildren().setAll(getEmployeeList());

                }
            }
        });
    }

    @FXML
    protected void addEmployee(ActionEvent event){
        DB_Connector.changeScene(event, "Employee-add.fxml","New Employee");
    }

    @FXML
    protected void onNewEmployeeClick(ActionEvent event){
        // gets all the info from the scene
        String fName = firstName.getText();
        String lName = lastName.getText();
        String address = street.getText();
        String mail = email.getText();
        String town = city.getText();
        String number = phone.getText();
        String cpr = CPR.getText();
        String pswrd = password.getText();

        int code = Integer.parseInt(zip.getText());
        float income = Float.parseFloat(salary.getText());

        // adds employee to the database
        DB_Connector.addEmployee(fName, lName, mail, address, code, town, number, income, cpr, pswrd);

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Employee has been added successfully.");
        alert.show();
        DB_Connector.changeScene(event, "Employees.fxml", "Employees");
    }

    @FXML
    protected void cancelButton(ActionEvent event){
        DB_Connector.changeScene(event, "Employees.fxml", "Employees");
    }



}

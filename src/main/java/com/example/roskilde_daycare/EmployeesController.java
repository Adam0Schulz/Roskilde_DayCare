package com.example.roskilde_daycare;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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
        Collection<Employee> employees = DB_Connector.employeeList();

        for(Employee employee : employees) {
            list.add(DynamicElements.createListItem(employee.getAttributeArray(), employee.getAllAttributeArray(), false));
        }
        return list;
    }

    @FXML
    public void initialize() {
        EmployeeList.getChildren().addAll(getEmployeeList());
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

package com.example.roskilde_daycare;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class EmployeesController extends CustomStage implements Initializable {

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
    @FXML private TextField password;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DB_Connector.changeScene(event, "Employee-add.fxml", "New Employee");
            }
        });

        createEmployeeBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
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

                DB_Connector.addEmployee(fName, lName, mail, address, code, town, number, income, cpr, pswrd);
                DB_Connector.changeScene(event, "Employees.fxml", "Employees");
            }
        });
    }

/*    @FXML
    protected void onNewEmployeeClick(Event event){
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

        DB_Connector.addEmployee(fName, lName, mail, address, code, town, number, income, cpr, pswrd);
    }*/
}

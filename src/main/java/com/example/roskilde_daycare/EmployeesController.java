package com.example.roskilde_daycare;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.awt.event.MouseEvent;

public class EmployeesController extends CustomStage {


    @FXML private TextField firstName;
    @FXML private TextField lastName;
    @FXML private TextField street;
    @FXML private TextField zip;
    @FXML private TextField email;
    @FXML private TextField phone;
    @FXML private TextField city;
    @FXML private TextField salary;

    // not working
    @FXML
    protected void onAddButtonClick(Event event) {
        DB_Connector.changeScene(event, "Employee-add.fxml", "New Employee");
    }

    @FXML
    protected void onNewEmployeeClick(Event event){
        String fName = firstName.getText();
        String lName = lastName.getText();
        String address = street.getText();
        String mail = email.getText();
        String town = city.getText();
        String number = phone.getText();
    }
}

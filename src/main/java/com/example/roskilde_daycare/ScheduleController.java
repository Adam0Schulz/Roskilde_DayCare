package com.example.roskilde_daycare;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;

import java.util.ArrayList;
import java.util.Collection;

public class ScheduleController extends CustomStage {

    @FXML private ChoiceBox<String> hmor1 = new ChoiceBox<>();
    @FXML private ChoiceBox<String> hmor2;
    @FXML private ChoiceBox<String> haft1;
    @FXML private ChoiceBox<String> haft2;
    @FXML private ChoiceBox<String> fmor1;
    @FXML private ChoiceBox<String> fmor2;
    @FXML private ChoiceBox<String> faft1;
    @FXML private ChoiceBox<String> faft2;
    @FXML private ChoiceBox<String> bmor1;
    @FXML private ChoiceBox<String> bmor2;
    @FXML private ChoiceBox<String> baft1;
    @FXML private ChoiceBox<String> baft2;

    @FXML
    protected void initialize(){
        if(hmor1 != null){
            hmor1.getItems().addAll(getEmployeeFullNames());
        } else if (hmor2 != null){
            hmor2.getItems().addAll(getEmployeeFullNames());
        }
    }

    @FXML
    protected void onAddButtonClick(ActionEvent event){
        DB_Connector.changeScene(event, "Schedule-add.fxml", "New Schedule");
    }

    @FXML
    protected void onCancelButtonClick(ActionEvent event){
        DB_Connector.changeScene(event, "Schedule.fxml", "Schedule");
    }

    private ArrayList<String> getEmployeeFullNames(){
        Collection<Employee> employees = DB_Connector.employeeList();
        ArrayList<String> employeeNames = new ArrayList<>();
        for(Employee employee : employees) {
            String fName = employee.getFirstName();
            String lName = employee.getLastName();
            String fullName = fName + " " +lName;
            employeeNames.add(fullName);
        } return employeeNames;
    }










}

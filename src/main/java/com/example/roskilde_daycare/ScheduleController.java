package com.example.roskilde_daycare;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;

public class ScheduleController extends CustomStage {

    @FXML private ChoiceBox<String> h_mor_1;
    @FXML private ChoiceBox<String> h_mor_2;
    @FXML private ChoiceBox<String> h_aft_1;
    @FXML private ChoiceBox<String> h_aft_2;
    @FXML private ChoiceBox<String> f_mor_1;
    @FXML private ChoiceBox<String> f_mor_2;
    @FXML private ChoiceBox<String> f_aft_1;
    @FXML private ChoiceBox<String> f_aft_2;
    @FXML private ChoiceBox<String> b_mor_1;
    @FXML private ChoiceBox<String> b_mor_2;
    @FXML private ChoiceBox<String> b_aft_1;
    @FXML private ChoiceBox<String> b_aft_2;
    private ArrayList<ChoiceBox<String>> choiceBoxes;

    @FXML private Label monday_active;
    @FXML private Label tuesday_;
    @FXML private Label wednesday_;
    @FXML private Label thursday_;
    @FXML private Label friday_;
    private ArrayList<Label> days;

    private ArrayList<Schedule> assignments= new ArrayList<Schedule>();

    private Label active_day;

    @FXML
    protected void initialize(){
        if(h_mor_1 != null){

            days = new ArrayList<Label>();
            days.add(monday_active);
            days.add(tuesday_);
            days.add(wednesday_);
            days.add(thursday_);
            days.add(friday_);

            choiceBoxes = new ArrayList<ChoiceBox<String>>();
            choiceBoxes.add(h_mor_1);
            choiceBoxes.add(h_mor_2);
            choiceBoxes.add(h_aft_1);
            choiceBoxes.add(h_aft_2);

            choiceBoxes.add(f_mor_1);
            choiceBoxes.add(f_mor_2);
            choiceBoxes.add(f_aft_1);
            choiceBoxes.add(f_aft_2);

            choiceBoxes.add(b_mor_1);
            choiceBoxes.add(b_mor_2);
            choiceBoxes.add(b_aft_1);
            choiceBoxes.add(b_aft_2);

            for (ChoiceBox<String> box : choiceBoxes) {
                box.getItems().addAll(getEmployeeFullNames());
                box.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {

                        // finds the selected day
                        for (Label day : days) {
                            String[] id = day.getId().split("_", 2);
                            if(id[1].equals("active")) {
                                active_day = day;
                            }
                        }

                        // creates schedule object
                        String[] array = box.getId().split("_", 3);

                        String group = array[0];
                        if(group.equals("h")) {
                            group = "hedgehogs";
                        } else if (group.equals("f")) {
                            group = "foxes";
                        } else if (group.equals("b")) {
                            group = "bees";
                        } else {
                            System.out.println("there's a problem with group ids");
                        }

                        String shift = array[1];
                        if (shift.equals("mor")) {
                            shift = "morning";
                        } else if (shift.equals("aft")) {
                            shift = "afternoon";
                        } else {
                            System.out.println("there's a problem with shift ids");
                        }

                        String day = active_day.getText().toLowerCase(Locale.ROOT);

                        String employeeName = box.getItems().get((Integer) t1);

                        Schedule newSchedule = new Schedule(group, shift, day, employeeName);

                        int assignID = 0;
                        for(Schedule assign : assignments) {
                            if(assign.getDay().equals(newSchedule.getDay()) && assign.getGroup().equals(newSchedule.getGroup()) && assign.getEmployeeName().equals(newSchedule.getEmployeeName()) && assign.getShift().equals(newSchedule.getShift())) {
                                assignments.set(assignID, newSchedule);
                            }
                            assignID++;
                        }
                        if(assignID == assignments.size()) {
                            assignments.add(newSchedule);
                        }


                        // debugging print out
                        for(Schedule schedule : assignments) {
                            System.out.println(schedule.getEmployeeName() + " - " + schedule.getGroup() + ", day: " + schedule.getDay() + ", shift: " + schedule.getShift());
                        }
                        System.out.println("");


                    }
                });
            }
        }
    }

    @FXML
    protected void onDaySwitch(Event event) {
        Label selectedDay = (Label) event.getSource();
        String[] id = selectedDay.getId().split("_", 2);
        Label previousActiveDay = active_day;
        previousActiveDay.setId(previousActiveDay.getId().split("_", 2)[0] + "_");
        if(!(id[1].equals("active"))) {
            selectedDay.setId(id[0] + "_active");
        }
        active_day = selectedDay;
        System.out.println("day has been changed to " + active_day.getText());

        previousActiveDay.setStyle("-fx-background-color: transparent; -fx-text-fill: #515151");
        if(active_day.getText().equals("Friday")) {
            active_day.setStyle("-fx-background-color:  #F84E8C; -fx-background-radius: 50px 50px 0 50px; -fx-text-fill: white");
        } else if (active_day.getText().equals("Monday")) {
            active_day.setStyle("-fx-background-color:  #F84E8C; -fx-background-radius: 50px 50px 50px 0; -fx-text-fill: white");
        } else {
            active_day.setStyle("-fx-background-color:  #F84E8C; -fx-background-radius: 50px; -fx-text-fill: white");
        }

        for (ChoiceBox<String> box : choiceBoxes) {
            box.setValue("");
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

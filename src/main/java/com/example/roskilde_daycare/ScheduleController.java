package com.example.roskilde_daycare;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;

public class ScheduleController extends CustomStage {

    @FXML
    protected void onAddButtonClick(ActionEvent event){
        DB_Connector.changeScene(event, "Schedule-add.fxml", "New Schedule");
    }

    @FXML
    protected void onCancelButtonClick(ActionEvent event){
        DB_Connector.changeScene(event, "Schedule.fxml", "Schedule");
    }





}

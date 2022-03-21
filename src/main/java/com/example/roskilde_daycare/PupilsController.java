package com.example.roskilde_daycare;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class PupilsController extends CustomStage {

    @FXML
    protected void onMinimizeClick(MouseEvent event) {
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    protected void onScheduleClick(ActionEvent event) {
        DB_Connector.changeScene(event, "Schedule.fxml", "Schedule");
    }

    @FXML
    protected void onWaitingListClick(ActionEvent event) {
        DB_Connector.changeScene(event, "WaitingList.fxml", "Waiting List");
    }

    @FXML
    protected void onEmployeesClick(ActionEvent event) {
        DB_Connector.changeScene(event, "Employees.fxml", "Employees");
    }



}

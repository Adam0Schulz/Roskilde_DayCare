package com.example.roskilde_daycare;


import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class CustomStage {
    private double x,y;

    @FXML
    protected void onWindowClick(MouseEvent event) {
        javafx.stage.Stage stage = (javafx.stage.Stage)((Node)event.getSource()).getScene().getWindow();
        x = event.getSceneX();
        y = event.getSceneY();
    }

    @FXML
    protected void onWindowDrag(MouseEvent event) {
        javafx.stage.Stage stage = (javafx.stage.Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setX(event.getScreenX() - x);
        stage.setY(event.getScreenY() - y);
        x = event.getSceneX();
        y = event.getSceneY();
    }

    @FXML
    protected void onCloseClick(MouseEvent event) {
        javafx.stage.Stage stage = (javafx.stage.Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    protected void onMaximizeClick(MouseEvent event) {
        javafx.stage.Stage stage = (javafx.stage.Stage)((Node)event.getSource()).getScene().getWindow();
        if( stage.isMaximized() ) {
            stage.setMaximized(false);
        } else {
            stage.setMaximized(true);
        }
    }

    @FXML
    protected void onMinimizeClick(MouseEvent event) {
        javafx.stage.Stage stage = (javafx.stage.Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    protected void onPupilsClick(Event event){
        DB_Connector.changeScene(event, "Pupils.fxml", "Pupils");
    }

    @FXML
    protected void onScheduleClick(Event event){
        DB_Connector.changeScene(event, "Schedule.fxml", "Schedule");
    }

    @FXML
    protected void onWaitingListClick(Event event) {
        DB_Connector.changeScene(event, "WaitingList.fxml", "Waiting List");
    }

    @FXML
    protected void onEmployeesClick(Event event) {
        DB_Connector.changeScene(event, "Employees.fxml", "Employees");
    }

    @FXML
    protected void onLogoutClick(ActionEvent event){
        DB_Connector.changeScene(event, "Login.fxml", "Login");
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "You have successfully logged out");
        alert.show();
    }

    @FXML
    protected void onHover(MouseEvent event) {
        Node node = (Node) event.getSource();
        node.setScaleX(1.1);
        node.setScaleY(1.1);
    }

    @FXML
    protected void onUnhover(MouseEvent event) {
        Node node = (Node) event.getSource();
        node.setScaleX(1);
        node.setScaleY(1);
    }




}

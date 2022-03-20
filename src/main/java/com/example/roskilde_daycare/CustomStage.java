package com.example.roskilde_daycare;

import javafx.fxml.FXML;
import javafx.scene.Node;
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
}

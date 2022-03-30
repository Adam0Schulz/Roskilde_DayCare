package com.example.roskilde_daycare;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.ResourceBundle;


public class PupilsController extends CustomStage {

    private String[] search;

    @FXML
    private VBox PupilList;

    @FXML
    private TextField searchField;

    protected ArrayList<VBox> getPupilList () {
        ArrayList<VBox> list = new ArrayList<VBox>();
        Collection<Attendee> pupils = DB_Connector.attendeeList();
        if(search != null) {
            Collection<Attendee> searchResult = (Collection<Attendee>) DB_Connector.search("Pupil", search[0], search[1]);
            if(searchResult.size() > 0) {
                pupils = searchResult;
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Zero pupils found :( Sorry");
                alert.show();
            }
        }

        for(Attendee pupil : pupils) {
            list.add(DynamicElements.createListItem("Pupil",pupil.getAttributeArray(), pupil.getParent().getAttributeArray(), true));
        }
        return list;
    }


    @FXML
    public void initialize() {

        PupilList.getChildren().addAll(getPupilList());

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
                    PupilList.getChildren().setAll(getPupilList());

                }
            }
        });
    }

    @FXML
    protected void addPupil(ActionEvent event) throws IOException {
        DB_Connector.changeScene(event, "WaitingList.fxml","Waiting List");
    }



}

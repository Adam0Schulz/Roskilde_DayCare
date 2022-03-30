package com.example.roskilde_daycare;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;


import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;

public class WaitingListController extends CustomStage {

    private String[] search;
    @FXML private TextField searchField;
    @FXML private VBox WaitingList;
    @FXML private TextField pFName;
    @FXML private TextField pLName;
    @FXML private TextField street;
    @FXML private TextField zip;
    @FXML private TextField city;
    @FXML private TextField phone;
    @FXML private TextField email;
    @FXML private TextField chFName;
    @FXML private TextField chLName;
    @FXML private TextField cpr;
    @FXML private TextField gender;
    @FXML private TextField dateOfBirth;

    protected ArrayList<VBox> getWaitingList() {
        ArrayList<VBox> list = new ArrayList<VBox>();
        Collection<Queuer> queuers = DB_Connector.waitingList();

        if(search != null) {
            Collection<Queuer> searchResult = (Collection<Queuer>) DB_Connector.search("Queuer", search[0], search[1]);
            if(searchResult.size() > 0) {
                queuers = searchResult;
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Zero children on waiting list found :( Sorry");
                alert.show();
            }
        }

        for(Queuer Queuer : queuers) {
            list.add(DynamicElements.createListItem("queuer",Queuer.getAttributeArray(), Queuer.getParent().getAttributeArray(), true));
        }
        return list;
    }

    @FXML
    public void initialize() {
        if(WaitingList != null) {
            WaitingList.getChildren().addAll(getWaitingList());
        }

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
                    WaitingList.getChildren().setAll(getWaitingList());

                }
            }
        });
    }

    @FXML
    protected void onAddButtonClick(ActionEvent event){
        DB_Connector.changeScene(event, "WaitingList-add.fxml", "New Child");
    }

    @FXML
    protected void onCancelButtonClick(ActionEvent event) {
        DB_Connector.changeScene(event, "WaitingList.fxml", "Waiting List");
    }

    @FXML
    protected void onCreateChildButtonClick(ActionEvent event){

        String pfn = pFName.getText();
        String pln = pLName.getText();
        String address = street.getText();
        String mail = email.getText();
        String town = city.getText();
        String number = phone.getText();
        String CPR = cpr.getText();
        String ZIP = zip.getText();
        String chfn = chFName.getText();
        String chln = chLName.getText();
        String sex = gender.getText();
        String date = dateOfBirth.getText();

        Date dob = null;
        try {
            dob = Date.valueOf(date);
        } catch (IllegalArgumentException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Wrong date format.");
            alert.show();
        }
        int code = 0;
        try{
            code = Integer.parseInt(ZIP);
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Wrong zip code format.");
            alert.show();
        }

        Parent parent = DB_Connector.addParent(pfn, pln, mail, address, code, town, number);
        DB_Connector.addChild(chfn, chln, dob, sex, CPR, parent);

        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Child has been added successfully.");
        alert.show();
        DB_Connector.changeScene(event, "WaitingList.fxml", "Waiting List");
    }
}

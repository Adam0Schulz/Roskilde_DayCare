package com.example.roskilde_daycare;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;

public class WaitingListController extends CustomStage {


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

        for(Queuer Queuer : queuers) {
            list.add(DynamicElements.createListItem(Queuer.getAttributeArray(), Queuer.getParent().getAttributeArray(), true));
        }
        return list;
    }

    @FXML
    public void initialize() {
        WaitingList.getChildren().addAll(getWaitingList());
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

        Date dob = Date.valueOf(date);
        int code = Integer.parseInt(ZIP);

        Parent parent = DB_Connector.addParent(pfn, pln, mail, address, code, town, number);
        DB_Connector.addChild(chfn, chln, dob, sex, CPR);

        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Child has been added successfully.");
        alert.show();
        DB_Connector.changeScene(event, "WaitingList.fxml", "Waiting List");
    }
}

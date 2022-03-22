package com.example.roskilde_daycare;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.Collection;

public class WaitingListController extends CustomStage {

/*    protected void onAddButtonClick(ActionEvent event){
        DB_Connector.changeScene(event, "Child-add.fxml", "New Child");
    }*/

    @FXML
    private VBox WaitingList;

    protected ArrayList<VBox> getWaitingList() {
        ArrayList<VBox> list = new ArrayList<VBox>();
        Collection<Queuer> queuers = DB_Connector.waitingList();
        for(Queuer Queuer : queuers) {
            ArrayList<String> queuerInfo = new ArrayList<String>();
            queuerInfo.add(Queuer.getFirstName());
            queuerInfo.add(Queuer.getLastName());
            queuerInfo.add(Queuer.getFirstName());
            queuerInfo.add(Queuer.getFirstName());
            queuerInfo.add(Queuer.getFirstName());
            list.add(DynamicElements.createListItem(queuerInfo, queuerInfo));
        }
        return list;
    }

    @FXML
    public void initialize() {
        WaitingList.getChildren().addAll(getWaitingList());
    }
}

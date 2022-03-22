package com.example.roskilde_daycare;

import javafx.animation.ScaleTransition;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.control.Label;
import javafx.scene.paint.Paint;

import javafx.event.EventHandler;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Objects;

public class DynamicElements {

    public static VBox createListItem (ArrayList<String> DisplayChildren, ArrayList<String> toggleChildren, boolean hasParent) {
        VBox item = new VBox();

        // Create rolldownPane
        BorderPane rolldownPane = new BorderPane();
        rolldownPane.setPrefHeight(1);
        rolldownPane.setStyle("-fx-background-color: #D3D3D3");
        //rolldownPane.setStyle("-fx-animated: true");


        // Create itemBox
        HBox itemBox = new HBox(0);
        itemBox.setAlignment(Pos.CENTER_LEFT);
        itemBox.setPrefHeight(59);

        // - Creating the rolldown arrow
        Image arrowImg = new Image(String.valueOf(DynamicElements.class.getResource("img/arrow-down.png")));
        ImageView arrow = new ImageView();
        arrow.setImage(arrowImg);
        arrow.setFitWidth(13);
        HBox arrowBox = new HBox();
        arrowBox.setAlignment(Pos.CENTER);
        arrowBox.setPrefWidth(70);
        arrowBox.getChildren().add(arrow);
        arrowBox.setRotate(-90);


        arrowBox.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(rolldownPane.getPrefHeight() >= 197) {
                    rolldownPane.setPrefHeight(1);
                    rolldownPane.setStyle("-fx-background-color: #D3D3D3");
                    rolldownPane.setCenter(null);

                } else {
                    rolldownPane.setPrefHeight(197);
                    rolldownPane.setStyle("-fx-background-color: #FBD3D6");
                    rolldownPane.setCenter(createToggleContent(toggleChildren, hasParent));
                }

            }
        });

        itemBox.getChildren().add(arrowBox);

        // - adding container for the labels
        HBox labelBox = new HBox(50);
        labelBox.setAlignment(Pos.CENTER_LEFT);

        // -- Adding labels to labelbox
        for(String child : DisplayChildren) {
            if(child == null) {
                child = "Unknown";
            }
            labelBox.getChildren().add(createListItemLabel(child));
        }

        itemBox.getChildren().add(labelBox);
        item.getChildren().add(itemBox);
        item.getChildren().add(rolldownPane);
        return item;
    }

    public static Label createListItemLabel(String labelContent) {
        Label label = new Label(labelContent);
        //label.setStyle("-fx-background-color: blue; -fx-border-color: black; -fx-border-width: 2px; ");
        label.setPrefWidth(150);
        label.setStyle("-fx-font-family: 'Segoe UI'");
        return label;
    }

    public static HBox createToggleContent(ArrayList<String> children, boolean hasParent) {
        HBox item = new HBox();
        VBox labelBox = new VBox();
        VBox controlBox = new VBox();

        HBox headingBox = new HBox();
        FlowPane dataBox = new FlowPane();

        labelBox.setPrefWidth(708);

        Label heading = createListItemLabel("Parent");
        heading.setStyle("-fx-font-weight: bold; -fx-font-size: 18px");
        headingBox.getChildren().add(heading);

        if(hasParent) {
            labelBox.getChildren().add(headingBox);
        }


        for(String child : children) {
            Label data = createListItemLabel(child);
            data.setStyle("-fx-font-size: 14px");
            dataBox.getChildren().add(data);
        }
        dataBox.setOrientation(Orientation.VERTICAL);
        dataBox.setVgap(15);
        dataBox.setStyle("-fx-padding: 25px 0 0 0");

        // adding controls to control box
        controlBox.setAlignment(Pos.BOTTOM_RIGHT);
        controlBox.setSpacing(20);

        Button removeBtn = new Button();
        removeBtn.setText("REMOVE");
        removeBtn.setId("remove");
        removeBtn.setStyle("-fx-background-color: #F84E8C; -fx-background-radius: 50px; -fx-text-fill: white; -fx-font-family: 'Segoe UI Semibold'; -fx-font-size: 14px");
        removeBtn.setPrefWidth(130);
        removeBtn.setPrefHeight(20);

        Button editBtn = new Button();
        editBtn.setText("EDIT");
        editBtn.setId("edit");
        editBtn.setStyle("-fx-background-color: #0075A3; -fx-background-radius: 50px; -fx-text-fill: white; -fx-font-family: 'Segoe UI Semibold'; -fx-font-size: 14px");
        editBtn.setPrefWidth(130);
        editBtn.setPrefHeight(20);

        controlBox.getChildren().add(editBtn);
        controlBox.getChildren().add(removeBtn);


        labelBox.getChildren().add(dataBox);

        item.getChildren().add(labelBox);
        item.getChildren().add(controlBox);

        item.setStyle("-fx-padding: 25px 50px");

        return item;
    }

}



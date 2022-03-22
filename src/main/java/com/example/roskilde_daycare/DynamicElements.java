package com.example.roskilde_daycare;

import javafx.animation.ScaleTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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

    public static VBox createListItem (ArrayList<String> DisplayChildren, ArrayList<String> ToggleChildren) {
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

                } else {
                    rolldownPane.setPrefHeight(197);
                    rolldownPane.setStyle("-fx-background-color: #FBD3D6");
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
        return label;
    }
}



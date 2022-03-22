package com.example.roskilde_daycare;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.control.Label;
import javafx.scene.paint.Paint;

import java.util.ArrayList;
import java.util.Objects;

public class DynamicElements {

    public static HBox createListItem (ArrayList<String> children) {
        HBox item = new HBox(0);
        item.setAlignment(Pos.CENTER_LEFT);
        item.setPrefHeight(59);
        item.setStyle("-fx-border-color: #D3D3D3; -fx-border-width: 1px;");



        // Creating the rolldown arrow
        Image arrowImg = new Image(String.valueOf(DynamicElements.class.getResource("img/arrow-down.png")));
        ImageView arrow = new ImageView();
        arrow.setImage(arrowImg);
        arrow.setFitWidth(13);
        HBox arrowBox = new HBox();
        arrowBox.setAlignment(Pos.CENTER);
        arrowBox.setPrefWidth(70);
        arrowBox.getChildren().add(arrow);

        item.getChildren().add(arrowBox);


        //adding container for the labels
        HBox labelBox = new HBox(50);
        labelBox.setAlignment(Pos.CENTER_LEFT);

        //Adding labels to labelbox
        for(String child : children) {
            if(child == null) {
                child = "Unknown";
            }
            labelBox.getChildren().add(createListItemLabel(child));
            System.out.println(child);
        }

        item.getChildren().add(labelBox);

        return item;
    }

    public static Label createListItemLabel(String labelContent) {
        Label label = new Label(labelContent);
        //label.setStyle("-fx-background-color: blue; -fx-border-color: black; -fx-border-width: 2px; ");
        label.setPrefWidth(150);
        return label;
    }
}



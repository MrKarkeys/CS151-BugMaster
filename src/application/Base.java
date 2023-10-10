package application;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Base {
	public BorderPane createBase() {
	    
    
        HBox topBox = new HBox();

        VBox leftBox = new VBox();


        VBox rightBox = new VBox();


        HBox bottomBox = new HBox();

        BorderPane borderPane = new BorderPane();
        borderPane.setStyle("-fx-background-color: cornsilk;");
        borderPane.setTop(topBox);
        borderPane.setLeft(leftBox);
        borderPane.setRight(rightBox);
        borderPane.setBottom(bottomBox);

        return borderPane;
    }
}

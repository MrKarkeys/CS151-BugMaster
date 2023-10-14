package views;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Base {
	public BorderPane createBase(Button home, Button viewProj, Button projForm) {
	    
		//creating the h/v boxes
        HBox topBox = new HBox();
        
        VBox leftBox = new VBox();
        VBox rightBox = new VBox();
        HBox bottomBox = new HBox();
        
        topBox.setPadding(new Insets(10));
        topBox.setSpacing(20);
        topBox.getChildren().addAll(home,viewProj,projForm);

        BorderPane borderPane = new BorderPane();
        borderPane.setStyle("-fx-background-color: cornsilk;");
        borderPane.setTop(topBox);
        borderPane.setLeft(leftBox);
        borderPane.setRight(rightBox);
        borderPane.setBottom(bottomBox);

        return borderPane;
    }
}

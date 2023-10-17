package views;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Base {
	public BorderPane createBase(Button home, Button viewProj, Button projForm, Button ticForm, Button comForm) {

		//creating the h/v boxes
        HBox topBox = new HBox();
        VBox leftBox = new VBox();
        VBox rightBox = new VBox();
        HBox bottomBox = new HBox();
        
        //styling buttons
        home.setStyle("-fx-background-color: TAN");
        viewProj.setStyle("-fx-background-color: TAN");
        projForm.setStyle("-fx-background-color: TAN");
        ticForm.setStyle("-fx-background-color: TAN");
        comForm.setStyle("-fx-background-color: TAN");
        //set text for buttons
        home.setText("home");
        viewProj.setText("view projects");
        projForm.setText("create a project");
        ticForm.setText("create a ticket");
        comForm.setText("create a comment");
        
        //adding the navbar
        topBox.setPadding(new Insets(10));
        topBox.setSpacing(20);
        topBox.getChildren().addAll(home,viewProj,projForm, ticForm, comForm);

        //creating borderpane
        BorderPane borderPane = new BorderPane();
        borderPane.setStyle("-fx-background-color: cornsilk;");
        borderPane.setTop(topBox);
        borderPane.setLeft(leftBox);
        borderPane.setRight(rightBox);
        borderPane.setBottom(bottomBox);

        return borderPane;
    }
}

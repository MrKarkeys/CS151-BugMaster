package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class projectForm {
	public VBox sceneView(Button button) {
		//create new project page
        VBox newProjectPage= new VBox(20);
        newProjectPage.setPadding(new Insets(10));
        newProjectPage.setAlignment(Pos.CENTER);
        newProjectPage.setStyle("-fx-background-color: cornsilk");
        
        //labels for text fields and date picker
        Label pName= new Label("enter the name");
        Label pDesc= new Label("enter the description");
        Label pDate= new Label("enter the starting date");

        //text fields and date picker for project info
        TextField projectName = new TextField();
        TextArea projectDescription = new TextArea();
        DatePicker projectStartDate= new DatePicker();
        projectStartDate.setValue(java.time.LocalDate.now());
        
        
        //save button
        Button submitProj = new Button("Clear");  
        submitProj.setOnAction(e -> {projectName.clear(); projectDescription.clear(); projectStartDate.setValue(java.time.LocalDate.now());});

        //create scene
        newProjectPage.getChildren().addAll(button, pName, projectName, pDesc, projectDescription, pDate, projectStartDate, submitProj);
        return newProjectPage;
	}
}

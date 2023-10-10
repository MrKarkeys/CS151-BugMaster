package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class ProjectForm extends Base{
	public BorderPane render(Button toHomepage, Button toViewProj)  {
		       
        StackPane centerPane = new StackPane();
        
        //nav bar
        BorderPane mainPane = createBase();
        HBox leftBox = (HBox) mainPane.getTop();
        leftBox.setPadding(new Insets(10));
        leftBox.setSpacing(20);
        leftBox.getChildren().addAll(toHomepage,toViewProj);

        
        //labels for text fields and date picker
        Label pName= new Label("enter the name");
        Label pDesc= new Label("enter the description");
        Label pDate= new Label("enter the starting date");

        //text fields and date picker for project info
        TextField projectName = new TextField();
        TextArea projectDescription = new TextArea();
        DatePicker projectStartDate= new DatePicker();
        projectStartDate.setValue(java.time.LocalDate.now());
        
        //center box styling here
        VBox centerBox = new VBox(20);
        centerBox.setPadding(new Insets(10));
        centerBox.setAlignment(Pos.CENTER);
        
        //submit button
        Button subProj = new Button("Submit");
        
        //clear button
        Button clearProj = new Button("Clear");  
        clearProj.setOnAction(e -> {projectName.clear(); projectDescription.clear(); projectStartDate.setValue(java.time.LocalDate.now());});
       
        //create scene
        HBox buttons = new HBox (20);
        buttons.getChildren().addAll(subProj, clearProj);
        buttons.setAlignment(Pos.CENTER);
        centerBox.getChildren().addAll(pName, projectName, pDesc, projectDescription, pDate, projectStartDate, buttons);
        
        centerPane.getChildren().add(centerBox);
        mainPane.setCenter(centerPane);

        return mainPane;
	}


}

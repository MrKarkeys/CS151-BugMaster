package views;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import controllers.ProjectController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import models.Project;

public class EditProjectFormView extends Base{
	public BorderPane render(Button home, Button viewProj, Button projForm, Button viewTic, Button ticForm, Button comForm, Project projToEdit)   {
		final int MAX_COMPONENTS = 8;
		       
        StackPane centerPane = new StackPane();
        
        // navigation bar
        BorderPane mainPane = createBase(home, viewProj, projForm, viewTic, ticForm, comForm);
        projForm.setStyle("-fx-background-color: WHEAT");


        // labels for text fields and date picker
        Label pName= new Label("update project name");
        Label pDesc= new Label("update project description");
        Label pDate= new Label("update the starting date");

        // text fields and date picker for project info --> fill these with the current information of the project being edited
        TextField projectName = new TextField(projToEdit.getName());
        TextArea projectDescription = new TextArea(projToEdit.getDescription());
       
        // undo formating of date to place into DatePicker
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate unformattedDate = LocalDate.parse(projToEdit.getDate(), format);
        DatePicker projectStartDate = new DatePicker();
        projectStartDate.setValue(unformattedDate);

        
        // center box styling here
        VBox centerBox = new VBox(20);
        centerBox.setPadding(new Insets(10));
        centerBox.setAlignment(Pos.CENTER);
        
        // submit/update button
        Button subProj = new Button("Update");
        subProj.setOnAction(e -> {
        	if (centerBox.getChildren().size() >= MAX_COMPONENTS) {
        		centerBox.getChildren().remove(MAX_COMPONENTS-1); // clear bottom text on each project addition
        	}
        	
        	String name = projectName.getText();
            String description = projectDescription.getText();
            LocalDate localDate = projectStartDate.getValue();

            ProjectController controller = new ProjectController();
            String message = controller.handleEditButtonClick(projToEdit.getName(), name, description, localDate);

            Label resultLabel = new Label(message);
            centerBox.getChildren().add(resultLabel);
        });
       
        //clear button
        Button clearProj = new Button("Clear");  
        clearProj.setOnAction(e -> {clear(projectName, projectDescription, projectStartDate);});
       
        //create scene
        HBox buttons = new HBox (20);
        buttons.getChildren().addAll(subProj, clearProj);
        buttons.setAlignment(Pos.CENTER);
        centerBox.getChildren().addAll(pName, projectName, pDesc, projectDescription, pDate, projectStartDate, buttons);
        
        centerPane.getChildren().add(centerBox);
        mainPane.setCenter(centerPane);

        return mainPane;
	}
	
	// clear fields
	public void clear(TextField projectName, TextArea projectDescription, DatePicker projectStartDate) {
		projectName.clear(); 
    	projectDescription.clear(); 
    	projectStartDate.setValue(java.time.LocalDate.now());
	}
}

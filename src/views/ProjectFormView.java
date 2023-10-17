package views;

import java.time.LocalDate;
import controllers.ProjectFormController;
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

public class ProjectFormView extends Base{
	public BorderPane render(Button home, Button viewProj, Button projForm, Button ticForm, Button comForm)   {
		       
        StackPane centerPane = new StackPane();
        
        //nav bar
        BorderPane mainPane = createBase(home, viewProj, projForm,ticForm, comForm);
        projForm.setStyle("-fx-background-color: WHEAT");


        //labels for text fields and date picker
        Label pName= new Label("enter the name");
        Label pDesc= new Label("enter the description");
        Label pDate= new Label("enter the starting date");
        Label filler= new Label(":)");

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
        subProj.setOnAction(e -> {
        	centerBox.getChildren().remove(centerBox.getChildren().size()-1); // clear bottom text on each project addition
        	String name = projectName.getText();
            String description = projectDescription.getText();
            LocalDate localDate = projectStartDate.getValue();

            ProjectFormController controller = new ProjectFormController();
            String message = controller.handleSubmitButtonClick(name, description, localDate);

            Label resultLabel = new Label(message);
            centerBox.getChildren().add(resultLabel);
        	
        	clear(projectName, projectDescription, projectStartDate);
        });
       
        //clear button
        Button clearProj = new Button("Clear");  
        clearProj.setOnAction(e -> {clear(projectName, projectDescription, projectStartDate);});
       
        //create scene
        HBox buttons = new HBox (20);
        buttons.getChildren().addAll(subProj, clearProj);
        buttons.setAlignment(Pos.CENTER);
        centerBox.getChildren().addAll(pName, projectName, pDesc, projectDescription, pDate, projectStartDate, buttons, filler);
        
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

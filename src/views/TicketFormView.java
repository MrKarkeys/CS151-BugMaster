package views;
	
	import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import controllers.ProjectFormController;
import daos.ProjectDAO;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
	import javafx.geometry.Pos;
	import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
	import javafx.scene.control.Label;
	import javafx.scene.control.TextArea;
	import javafx.scene.control.TextField;
	import javafx.scene.layout.BorderPane;
	import javafx.scene.layout.HBox;
	import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import models.Project;
import models.ProjectModel;

	public class TicketFormView  extends Base{
		public BorderPane render(Button home, Button viewProj, Button projForm, Button ticForm, Button comForm)   {
		       
	        StackPane centerPane = new StackPane();
	        
	        //nav bar
	        BorderPane mainPane = createBase(home, viewProj, projForm,ticForm, comForm);
	        ticForm.setStyle("-fx-background-color: WHEAT");

	        //labels for text fields and date picker
	        Label pName = new Label("choose a project");
	        Label tName= new Label("enter the name");
	        Label tDesc= new Label("enter the description");
	        Label filler= new Label(":)");

	        //text fields and date picker for project info
	        
	        //CHANGE TO LIST OF PROJECTS
	        List<Project> projects = (new ProjectDAO()).getAllProjects();
	        List<String> projectNames = projects.stream().map(Project::getName).collect(Collectors.toList());
			ComboBox<String> combo_box =  new ComboBox<String>(FXCollections.observableList(projectNames));
	        TilePane dropdown = new TilePane(combo_box);
	        TextField ticketName = new TextField();
	        TextArea ticketDescription = new TextArea();
	        DatePicker ticketStartDate= new DatePicker();
	        ticketStartDate.setValue(java.time.LocalDate.now());
	        
	        //box styling here
	        VBox centerBox = new VBox(20);
	        centerBox.setPadding(new Insets(10));
	        centerBox.setAlignment(Pos.CENTER);
	        dropdown.setAlignment(Pos.CENTER);
	        
	        //submit button
	        
	        //CHANGE TO TICKETFORMCONTROLLER
	        Button subTic = new Button("Submit");
	        subTic.setOnAction(e -> {
	        	centerBox.getChildren().remove(centerBox.getChildren().size()-1); // clear bottom text on each project addition
	        	String name = ticketName.getText();
	            String description = ticketDescription.getText();
	            LocalDate localDate = ticketStartDate.getValue();
	            ProjectFormController controller = new ProjectFormController();
	            String message = controller.handleSubmitButtonClick(name, description, localDate);

	            Label resultLabel = new Label(message);
	            centerBox.getChildren().add(resultLabel);
	        	
	        	clear(ticketName, ticketDescription, ticketStartDate);
	        });
	       
	        //clear button
	        Button clearTic = new Button("Clear");  
	        clearTic.setOnAction(e -> {clear(ticketName, ticketDescription, ticketStartDate);});
	       
	        //create scene
	        HBox buttons = new HBox (20);
	        buttons.getChildren().addAll(subTic, clearTic);
	        buttons.setAlignment(Pos.CENTER);
	        centerBox.getChildren().addAll(pName, dropdown, tName, ticketName, tDesc, ticketDescription, buttons, filler);
	        centerPane.getChildren().add(centerBox);
	        mainPane.setCenter(centerPane);

	        return mainPane;
		}
		
		// clear fields
		public void clear(TextField ticketName, TextArea ticketDescription, DatePicker ticketStartDate) {
			ticketName.clear(); 
			ticketDescription.clear(); 
			ticketStartDate.setValue(java.time.LocalDate.now());
		}


	}
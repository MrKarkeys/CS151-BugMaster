package views;
	
	import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import controllers.ProjectFormController;
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

	public class CommentFormView  extends Base{
		public BorderPane render(Button home, Button viewProj, Button projForm, Button ticForm, Button comForm )  {
		       
	        StackPane centerPane = new StackPane();
	        
	        //nav bar
	        BorderPane mainPane = createBase(home, viewProj, projForm,ticForm, comForm);
	        comForm.setStyle("-fx-background-color: WHEAT");
	        //labels for text fields and date picker
	        Label pName = new Label("choose a project");
	        Label tName = new Label("choose a ticket");
	        Label cDesc= new Label("enter the description");
	        Label filler= new Label(":)");

	        //text fields and date picker for project info
	        //CHANGE TO LIST OF PROJECTS
	        List<String> allProj = Arrays.asList("proj1", "proj2");
			ComboBox<String> combo_boxP =  new ComboBox<String>(FXCollections.observableList(allProj));
	        TilePane dropdownP = new TilePane(combo_boxP);
	        //CHANGE TO LIST OF TICKETS
	        List<String> allTic = Arrays.asList("ticket1", "ticket2");
			ComboBox<String> combo_boxC = new ComboBox<String>(FXCollections.observableList(allTic));
			TilePane dropdownC = new TilePane(combo_boxC);
	        
	        TextField ticketName = new TextField();
	        TextArea cDescription = new TextArea();
	        
	       
	        DatePicker commentStartDate= new DatePicker();
	        TextField commentDate = new TextField();
	        commentStartDate.setValue(java.time.LocalDate.now());
	        commentDate.setText(commentStartDate.getValue().toString());
	        commentDate.setEditable(false);

	        //box styling here
	        VBox centerBox = new VBox(20);
	        centerBox.setPadding(new Insets(10));
	        centerBox.setAlignment(Pos.CENTER);
	        dropdownP.setAlignment(Pos.CENTER);
	        dropdownC.setAlignment(Pos.CENTER);
	        
	        //submit button
	        //CHANGE THIS AFTER MAKING THE COMMENT MODEL
	        Button subTic = new Button("Submit");
	        subTic.setOnAction(e -> {
	        	centerBox.getChildren().remove(centerBox.getChildren().size()-1); // clear bottom text on each project addition
	        	String name = ticketName.getText();
	            String description = cDescription.getText();
	            LocalDate localDate = commentStartDate.getValue();
	            ProjectFormController controller = new ProjectFormController();
	            String message = controller.handleSubmitButtonClick(name, description, localDate);

	            Label resultLabel = new Label(message);
	            centerBox.getChildren().add(resultLabel);
	        	
	        	clear(ticketName, cDescription, commentStartDate);
	        });
	       
	        //clear button
	        Button clearTic = new Button("Clear");  
	        clearTic.setOnAction(e -> {clear(ticketName, cDescription, commentStartDate);});
	       
	        //create scene
	        HBox buttons = new HBox (20);
	        buttons.getChildren().addAll(subTic, clearTic);
	        buttons.setAlignment(Pos.CENTER);
	        centerBox.getChildren().addAll(pName, dropdownP, tName, dropdownC, cDescription, commentDate, buttons, filler);
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
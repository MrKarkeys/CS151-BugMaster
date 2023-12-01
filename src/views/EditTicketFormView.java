package views;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import controllers.ProjectController;
import controllers.TicketController;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import models.Ticket;

public class EditTicketFormView extends Base{
	public BorderPane render(Button home, Button viewProj, Button projForm, Button viewTic, Button ticForm, Button comForm, Ticket ticToEdit)   {
		final int MAX_COMPONENTS = 8;
		       
        StackPane centerPane = new StackPane();
        
        // navigation bar
        BorderPane mainPane = createBase(home, viewProj, projForm, viewTic, ticForm, comForm);
        projForm.setStyle("-fx-background-color: WHEAT");


        // labels for text fields and date picker
        Label tProj= new Label("update the associated project");
        Label tName= new Label("update ticket name");
        Label tDesc= new Label("update ticket description");

        // text fields and date picker for project info --> fill these with the current information of the project being edited
        ProjectController projController = new ProjectController();
		List<String> allProj = projController.getProjectName();
		Collections.sort(allProj);
		ComboBox<String> associatedProjects = new ComboBox<String>(FXCollections.observableList(allProj));
		associatedProjects.getSelectionModel().select(ticToEdit.getProjectName());
        TextField ticketName = new TextField(ticToEdit.getName());
        TextArea ticketDescription = new TextArea(ticToEdit.getDescription());
        
        // center box styling here
        VBox centerBox = new VBox(20);
        centerBox.setPadding(new Insets(10));
        centerBox.setAlignment(Pos.CENTER);
        
        // submit/update button
        Button subTic = new Button("Update");
        subTic.setOnAction(e -> {
        	if (centerBox.getChildren().size() >= MAX_COMPONENTS) {
        		centerBox.getChildren().remove(MAX_COMPONENTS-1); // clear bottom text on each project addition
        	}
        	
        	String projectName = associatedProjects.getValue();
			String name = ticketName.getText();
			String description = ticketDescription.getText();
			
			// gets the data
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
			formatter = formatter.withLocale(Locale.US);
			LocalDate localDate = LocalDate.parse(ticToEdit.getDate(), formatter);
			
			TicketController controller = new TicketController();
			String message = controller.handleEditButtonClick(ticToEdit.getId(), projectName, name, description, localDate);

			Label resultLabel = new Label(message);
			centerBox.getChildren().add(resultLabel);
        });
       
        //clear button
        Button clearProj = new Button("Clear");  
        clearProj.setOnAction(e -> {clear(ticketName, ticketDescription, associatedProjects);});
       
        //create scene
        HBox buttons = new HBox (20);
        buttons.getChildren().addAll(subTic, clearProj);
        buttons.setAlignment(Pos.CENTER);
        centerBox.getChildren().addAll(tProj, associatedProjects, tName, ticketName, tDesc, ticketDescription, buttons);
        
        centerPane.getChildren().add(centerBox);
        mainPane.setCenter(centerPane);

        return mainPane;
	}
	
	// clear fields
	public void clear(TextField ticketName, TextArea ticketDescription, ComboBox<String> projects) {
		ticketName.clear(); 
    	ticketDescription.clear();
    	projects.getSelectionModel().clearSelection();    	
	}
}

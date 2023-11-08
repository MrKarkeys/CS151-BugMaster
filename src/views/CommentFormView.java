package views;

import java.time.LocalDate;


import java.util.Collections;
import java.util.List;

import controllers.CommentController;
import controllers.ProjectController;
import controllers.TicketController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

public class CommentFormView extends Base {
	public BorderPane render(Button home, Button viewProj, Button projForm, Button viewTic, Button ticForm, Button comForm) {

		StackPane centerPane = new StackPane();

		// nav bar
		BorderPane mainPane = createBase(home, viewProj, projForm, viewTic, ticForm, comForm);
		comForm.setStyle("-fx-background-color: WHEAT");
		// labels for text fields and date picker
		

		// text fields and date picker for project info
		// CHOOSING A PROJECT
		Label pName = new Label("choose a project");
		ProjectController projController = new ProjectController();
		TicketController tickController = new TicketController();
		
		// dropdown for proj
		List<String> allProj = projController.getProjectName();
		Collections.sort(allProj);
		ComboBox<String> combo_boxP = new ComboBox<String>(FXCollections.observableList(allProj));
		TilePane dropdownP = new TilePane(combo_boxP);
		dropdownP.setAlignment(Pos.CENTER);
		Button subProj = new Button("View Project's Tickets");

		//box styling
		VBox chooseProj = new VBox(20);
		chooseProj.getChildren().addAll(pName, dropdownP,subProj);
		chooseProj.setPadding(new Insets(10));
		chooseProj.setAlignment(Pos.CENTER);
		VBox centerBox = new VBox(20);
		centerBox.setPadding(new Insets(10));
		centerBox.setAlignment(Pos.CENTER);
		centerBox.getChildren().addAll(chooseProj);
		centerPane.getChildren().add(centerBox);
		mainPane.setCenter(centerPane);

		//CHOOSING TICKET
		subProj.setOnAction(a -> {
			centerBox.getChildren().remove(chooseProj);
			Label tName = new Label("choose a ticket");
			
			//dropdown for ticket
			List<String> allTick = FXCollections.observableList(tickController.getTicketName(combo_boxP.getValue()));
			Collections.sort(allTick);
			ComboBox<String> combo_boxT = new ComboBox<String>((ObservableList<String>) allTick);
			TilePane dropdownT = new TilePane(combo_boxT);
			dropdownT.setAlignment(Pos.CENTER);
			Button subTic = new Button("View Ticket Info");
			Label projName = new Label("Project: "+combo_boxP.getValue());
			
			//ADD TO STAGE
			VBox chooseTic = new VBox(20);
			chooseTic.getChildren().addAll(projName,tName, dropdownT, subTic);
			chooseTic.setPadding(new Insets(10));
			chooseTic.setAlignment(Pos.CENTER);
			centerBox.getChildren().addAll(chooseTic);
			
			
			//ADDING THE COMMENT DESCRIPTION
			subTic.setOnAction(o -> {
				centerBox.getChildren().remove(chooseTic);
				
				//content
				TicketController ticCon = new TicketController();
				Label ticDescrip = new Label("Ticket Description: "+ticCon.getTicketDes(combo_boxT.getValue()));
				Label exampleComments = new Label("Previous Comment: this is a previous comment");
				Label cDesc = new Label("Write comments:");
				Label c1 = new Label("Comment 1");
				Label c2 = new Label("Comment 2");
				TextArea c1Description = new TextArea();
				TextArea c2Description = new TextArea();
				DatePicker commentStartDate = new DatePicker();
				TextField commentDate = new TextField();
				commentStartDate.setValue(java.time.LocalDate.now());
				commentDate.setText(commentStartDate.getValue().toString());
				commentDate.setEditable(false);
				Label tickName = new Label("Ticket: " + combo_boxT.getValue());
				
				//SENDING COMMENT TO CONTROLLER
				Button subCom = new Button("Submit");
				subCom.setOnAction(e -> {
					String ticketName = combo_boxT.getValue();
					String description1 = c1Description.getText();
					String description2 = c2Description.getText();
					LocalDate localDate = commentStartDate.getValue();
					CommentController controller = new CommentController();
					String message1 = controller.handleSubmitButtonClick(ticketName, description1, localDate);
					String message2 = controller.handleSubmitButtonClick(ticketName, description2, localDate);
					Label result1 = new Label(message1);
					Label result2 = new Label(message2);
					VBox results = new VBox(20);
					results.getChildren().addAll(result1,result2);
					centerBox.getChildren().add(results);

					clear(c1Description,commentStartDate);
					clear(c2Description,commentStartDate);
				});
				
				Button clearTic = new Button("Clear");
				clearTic.setOnAction(e -> {
					clear(c1Description, commentStartDate);
				});
				//ADD TO STAGE
				VBox commentStuff = new VBox(20);
				commentStuff.getChildren().addAll(projName, tickName, ticDescrip,exampleComments,cDesc, c1, c1Description,c2, c2Description,commentDate, subCom, clearTic);
				commentStuff.setPadding(new Insets(10));
				commentStuff.setAlignment(Pos.CENTER);
				centerBox.getChildren().addAll(commentStuff);
			});

			
		});

		return mainPane;
	}

	// clear fields
	public void clear(TextArea commentDescription, DatePicker commentStartDate) {
		commentDescription.clear();
		commentStartDate.setValue(java.time.LocalDate.now());
	}

}
package views;

import java.time.LocalDate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import controllers.CommentController;
import controllers.ProjectController;
import controllers.TicketController;
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

public class CommentFormView extends Base {
	public BorderPane render(Button home, Button viewProj, Button projForm, Button viewTic, Button ticForm,
			Button comForm) {

		StackPane centerPane = new StackPane();

		// nav bar
		BorderPane mainPane = createBase(home, viewProj, projForm, viewTic, ticForm, comForm);
		comForm.setStyle("-fx-background-color: WHEAT");
		// labels for text fields and date picker
		Label pName = new Label("choose a project");
		Label tName = new Label("choose a ticket");
		Label cDesc = new Label("enter the description");
		Label filler = new Label(":)");

		// text fields and date picker for project info
		// CHANGE TO LIST OF PROJECTS
		ProjectController projController = new ProjectController();
		List<String> allProj = projController.getProjectName();
		Collections.sort(allProj);
		ComboBox<String> combo_boxP = new ComboBox<String>(FXCollections.observableList(allProj));
		TilePane dropdownP = new TilePane(combo_boxP);
		dropdownP.setAlignment(Pos.CENTER);

		// CHANGE TO LIST OF TICKETS
		TicketController tickController = new TicketController();
		List<String> allTick = tickController.getTicketName("Example1");
		Collections.sort(allTick);
		ComboBox<String> combo_boxT = new ComboBox<String>(FXCollections.observableList(allTick));
		TilePane dropdownC = new TilePane(combo_boxT);
		dropdownC.setAlignment(Pos.CENTER);

		VBox chooseProj = new VBox(20);
		chooseProj.getChildren().addAll(pName, dropdownP);
		chooseProj.setPadding(new Insets(10));
		chooseProj.setAlignment(Pos.CENTER);

		VBox chooseTic = new VBox(20);
		chooseTic.getChildren().addAll(tName, dropdownC);
		chooseTic.setPadding(new Insets(10));
		chooseTic.setAlignment(Pos.CENTER);

		HBox choosePT = new HBox(20);
		choosePT.getChildren().addAll(chooseProj, chooseTic);
		choosePT.setPadding(new Insets(10));
		choosePT.setAlignment(Pos.CENTER);

		TextArea cDescription = new TextArea();

		// ticket info and at least 2 comments added to this ticket ex.
		Label ticketInfo = new Label("Ticket Info: this ticket talks about ____ bug that affects ____");
		Label commentex1 = new Label("Comment: made ____ changes to ____");
		Label commentex2 = new Label("Comment: create new ____ to ____ class");

		DatePicker commentStartDate = new DatePicker();
		TextField commentDate = new TextField();
		commentStartDate.setValue(java.time.LocalDate.now());
		commentDate.setText(commentStartDate.getValue().toString());
		commentDate.setEditable(false);

		// box styling here
		VBox centerBox = new VBox(20);
		centerBox.setPadding(new Insets(10));
		centerBox.setAlignment(Pos.CENTER);

		// submit button
		// CHANGE THIS AFTER MAKING THE COMMENT MODEL
		Button subTic = new Button("Submit");
		subTic.setOnAction(e -> {
			centerBox.getChildren().remove(centerBox.getChildren().size() - 1); // clear bottom text on each project
																				// addition
			String ticketName = "Example1";
			String description = cDescription.getText();
			LocalDate localDate = commentStartDate.getValue();
			CommentController controller = new CommentController();
			String message = controller.handleSubmitButtonClick(ticketName, description, localDate);

			Label resultLabel = new Label(message);
			centerBox.getChildren().add(resultLabel);

			clear(cDescription, commentStartDate);
		});

		// clear button
		Button clearTic = new Button("Clear");
		clearTic.setOnAction(e -> {
			clear(cDescription, commentStartDate);
		});

		// create scene
		HBox buttons = new HBox(20);
		buttons.getChildren().addAll(subTic, clearTic);
		buttons.setAlignment(Pos.CENTER);
		centerBox.getChildren().addAll(choosePT, ticketInfo, commentex1, commentex2, cDescription, commentDate, buttons,
				filler);
		centerPane.getChildren().add(centerBox);
		mainPane.setCenter(centerPane);

		return mainPane;
	}

	// clear fields
	public void clear(TextArea commentDescription, DatePicker commentStartDate) {
		commentDescription.clear();
		commentStartDate.setValue(java.time.LocalDate.now());
	}

}
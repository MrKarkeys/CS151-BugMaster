package views;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
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

public class TicketFormView extends Base {
	public BorderPane render(Button home, Button viewProj, Button projForm, Button viewTic, Button ticForm, Button comForm) {
		final int MAX_COMPONENTS = 8;

		StackPane centerPane = new StackPane();

		// nav bar
		BorderPane mainPane = createBase(home, viewProj, projForm, viewTic, ticForm, comForm);
		ticForm.setStyle("-fx-background-color: WHEAT");

		// labels for text fields and date picker
		Label pName = new Label("choose a project");
		Label tName = new Label("enter the name");
		Label tDesc = new Label("enter the description");

		// text fields and date picker for project info

		// CHANGE TO LIST OF PROJECTS
		ProjectController projController = new ProjectController();
		List<String> allProj = projController.getProjectName();
		Collections.sort(allProj);
		ComboBox<String> combo_box = new ComboBox<String>(FXCollections.observableList(allProj));
		TilePane dropdown = new TilePane(combo_box);
		TextField ticketName = new TextField();
		TextArea ticketDescription = new TextArea();

		// box styling here
		VBox centerBox = new VBox(20);
		centerBox.setPadding(new Insets(10));
		centerBox.setAlignment(Pos.CENTER);
		dropdown.setAlignment(Pos.CENTER);

		// submit button

		// CHANGE TO TICKETFORMCONTROLLER
		Button subTic = new Button("Submit");
		subTic.setOnAction(e -> {
			if (centerBox.getChildren().size() >= MAX_COMPONENTS) {
        		centerBox.getChildren().remove(MAX_COMPONENTS-1); // clear bottom text on each ticket addition
        	}
			
			String projectName = combo_box.getValue();
			String name = ticketName.getText();
			String description = ticketDescription.getText();
			
			TicketController controller = new TicketController();
			String message = controller.handleSubmitButtonClick(projectName, name, description);

			Label resultLabel = new Label(message);
			centerBox.getChildren().add(resultLabel);

			clear(ticketName, ticketDescription);
		});

		// clear button
		Button clearTic = new Button("Clear");
		clearTic.setOnAction(e -> {
			clear(ticketName, ticketDescription);
		});

		// create scene
		HBox buttons = new HBox(20);
		buttons.getChildren().addAll(subTic, clearTic);
		buttons.setAlignment(Pos.CENTER);
		centerBox.getChildren().addAll(pName, dropdown, tName, ticketName, tDesc, ticketDescription, buttons);
		centerPane.getChildren().add(centerBox);
		mainPane.setCenter(centerPane);

		return mainPane;
	}

	// clear fields
	public void clear(TextField ticketName, TextArea ticketDescription) {
		ticketName.clear();
		ticketDescription.clear();
	}
}
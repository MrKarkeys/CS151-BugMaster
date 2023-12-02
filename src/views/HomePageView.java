package views;

import javafx.scene.control.Button;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import models.ProjectModel;

public class HomePageView extends Base{
	public BorderPane render(Button home, Button viewProj, Button projForm, Button viewTic, Button ticForm, Button comForm) {
		//content here
		Label intro = new Label("Welcome to BugMaster!");
		Label connectionStatus = new Label(getLabelValue());
        intro.setStyle("-fx-font: normal bold 50px elephant; -fx-text-fill: darkolivegreen;");
        connectionStatus.setStyle("-fx-font: normal bold 40px elephant; -fx-text-fill: darkolivegreen;");
        VBox stackPane = new VBox();
        stackPane.getChildren().addAll(intro, connectionStatus);

        //creating nav bar
        BorderPane mainPane = createBase(home, viewProj, projForm, viewTic, ticForm, comForm);
        home.setStyle("-fx-background-color: WHEAT");
        
        //set scene
        mainPane.setCenter(stackPane);

        return mainPane;
	}
	
	/**
	 * @return connection to the database
	 */
	public String getLabelValue() {
		ProjectModel p = new ProjectModel();
		if (p.isDBConnected()) return "Database is connected";
		else return "Database is not connected";
	}
}

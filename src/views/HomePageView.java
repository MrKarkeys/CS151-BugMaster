package views;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import models.ProjectModel;

public class HomePageView extends Base{
	public BorderPane render(Button toHomepage, Button toViewProj, Button toProjForm) {
		//content here
		Label intro = new Label("welcome to BugMaster!");
		Label connectionStatus = new Label(getLabelValue());
        intro.setStyle("-fx-font: normal bold 50px elephant; -fx-text-fill: darkolivegreen;");
        connectionStatus.setStyle("-fx-font: normal bold 40px elephant; -fx-text-fill: darkolivegreen;");
        VBox stackPane = new VBox();
        stackPane.getChildren().addAll(intro, connectionStatus);

        //creating nav bar
        BorderPane mainPane = createBase(toHomepage, toViewProj, toProjForm);
        HBox leftBox = (HBox) mainPane.getTop();


        //set scene
        mainPane.setCenter(stackPane);

        return mainPane;
	}
	
	public String getLabelValue() {
		ProjectModel p = new ProjectModel();
		if (p.isDBConnected()) return "connected";
		else return "home";
	}
}

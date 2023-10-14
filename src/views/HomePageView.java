package views;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class HomePageView extends Base{
	public BorderPane render(Button toHomepage, Button toViewProj, Button toProjForm) {
		//content here
		Label label = new Label("home");
        label.setStyle("-fx-font: normal bold 80px elephant; -fx-text-fill: darkolivegreen;");
        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(label);

        //creating nav bar
        BorderPane mainPane = createBase(toHomepage, toViewProj, toProjForm);
        HBox leftBox = (HBox) mainPane.getTop();


        //set scene
        mainPane.setCenter(stackPane);

        return mainPane;
	}
}

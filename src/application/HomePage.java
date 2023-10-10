package application;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class HomePage extends Base{
	public BorderPane render(Button addProj, Button viewProj) {
		
		//content here
		Label label = new Label("home");
        label.setStyle("-fx-font: normal bold 80px elephant; -fx-text-fill: darkolivegreen;");
        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(label);

        //creating nav bar
        BorderPane mainPane = createBase();
        HBox leftBox = (HBox) mainPane.getTop();
        leftBox.setPadding(new Insets(10));
        leftBox.setSpacing(20);
        leftBox.getChildren().addAll(addProj, viewProj);

        //set scene
        mainPane.setCenter(stackPane);

        return mainPane;
	}
}

package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class ViewProject extends Base{

    public BorderPane render(Button toHomepage) {
        
    	//content
        Label label = new Label("list of projs");
        
        StackPane centerPane = new StackPane();
        centerPane.getChildren().add(label);
    
        //nav bar
        BorderPane mainPane = createBase();
        VBox leftBox = (VBox) mainPane.getLeft();
        leftBox.setPadding(new Insets(10));
        leftBox.setSpacing(20);
        leftBox.getChildren().add(toHomepage);

        //set scene
        mainPane.setCenter(centerPane);

        return mainPane;
    }
}


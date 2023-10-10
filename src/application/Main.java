package application;
	
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene; 
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.geometry.*;
import javafx.stage.Stage; 

public class Main extends Application {
	Scene scene;
	@Override
	  public void start(Stage s) { 
        // set title for the stage 
        s.setTitle("BugMaster"); 
  
        //buttons and pages
        HomePage home = new HomePage();
        Button toHome = new Button("home");
        
        ViewProject viewproj = new ViewProject();
        Button toViewProj = new Button("view project");
        
        ProjectForm projForm = new ProjectForm();
        Button toProjForm = new Button("create a project");
        
        //styling buttons
        toHome.setStyle("-fx-background-color: TAN");
        toViewProj.setStyle("-fx-background-color: TAN");
        toProjForm.setStyle("-fx-background-color: TAN");
        
        //action buttons
        toHome.setOnAction(e->scene.setRoot(home.render(toViewProj,toProjForm)));
        toViewProj.setOnAction(e -> scene.setRoot(viewproj.render(toHome)));
        toProjForm.setOnAction(e->scene.setRoot(projForm.render(toHome, toViewProj)));
        
        //set scene    
        scene = new Scene(home.render(toProjForm,toViewProj), 600, 600);
        s.setScene(scene);
        s.show(); 
    } 


	
	public static void main(String[] args) {
		launch(args);
	}
}

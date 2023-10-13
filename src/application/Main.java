package application;
	
import Model.HomePage;
import Model.ProjectFormPage;
import Model.ViewProjectPage;
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
        
        ViewProjectPage viewproj = new ViewProjectPage();
        Button toViewProj = new Button("view project");
        
        ProjectFormPage projForm = new ProjectFormPage();
        Button toProjForm = new Button("create a project");
        
        //styling buttons
        toHome.setStyle("-fx-background-color: TAN");
        toViewProj.setStyle("-fx-background-color: TAN");
        toProjForm.setStyle("-fx-background-color: TAN");
        
        //action buttons
        toHome.setOnAction(e->scene.setRoot(home.render(toHome, toProjForm, toViewProj)));
        toViewProj.setOnAction(e -> scene.setRoot(viewproj.render(toHome, toProjForm, toViewProj)));
        toProjForm.setOnAction(e->scene.setRoot(projForm.render(toHome, toProjForm, toViewProj)));
        
        //set scene    
        scene = new Scene(home.render(toHome,toProjForm,toViewProj), 600, 600);
        s.setScene(scene);
        s.show(); 
    } 


	
	public static void main(String[] args) {
		launch(args);
	}
}

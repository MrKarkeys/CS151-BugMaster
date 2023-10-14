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
import views.HomePageView;
import views.ProjectFormView;
import views.DisplayProjectsView; 

public class Main extends Application {
	Scene scene;
	@Override
	  public void start(Stage s) { 
        // set title for the stage 
        s.setTitle("BugMaster"); 
  
        //buttons and pages
        HomePageView home = new HomePageView();
        Button toHome = new Button("home");
        
        DisplayProjectsView viewproj = new DisplayProjectsView();
        Button toViewProj = new Button("view projects");
        
        ProjectFormView projForm = new ProjectFormView();
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

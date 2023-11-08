package application;
	
import javafx.application.Application;
import javafx.scene.Scene; 
import javafx.scene.control.*;
import javafx.stage.Stage;
import views.HomePageView;
import views.ProjectFormView;
import views.TicketFormView;
import views.CommentFormView;
import views.DisplayProjectsView;
import views.DisplayTicketsView; 

public class Main extends Application {
	Scene scene;
	@Override
	  public void start(Stage s) { 
        // set title for the stage 
        s.setTitle("BugMaster"); 
  
        //buttons and pages
        HomePageView home = new HomePageView();
        Button toHome = new Button();
        
        DisplayProjectsView viewProj = new DisplayProjectsView();
        Button toViewProj = new Button();
        
        ProjectFormView projForm = new ProjectFormView();
        Button toProjForm = new Button();

        DisplayTicketsView viewTic = new DisplayTicketsView();
        Button toViewTic = new Button();
        
        TicketFormView ticForm = new TicketFormView();
        Button toTicForm = new Button();
        
        CommentFormView comForm = new CommentFormView();
        Button toComForm = new Button();

        //action buttons
        toHome.setOnAction(e->scene.setRoot(home.render(toHome, toViewProj, toProjForm, toViewTic, toTicForm, toComForm)));
        toViewProj.setOnAction(e -> scene.setRoot(viewProj.render(toHome, toViewProj, toProjForm, toViewTic, toTicForm, toComForm)));
        toProjForm.setOnAction(e -> scene.setRoot(projForm.render(toHome, toViewProj, toProjForm, toViewTic, toTicForm, toComForm)));
        toViewTic.setOnAction(e -> scene.setRoot(viewTic.render(toHome, toViewProj, toProjForm, toViewTic, toTicForm, toComForm)));
        toTicForm.setOnAction(e->scene.setRoot(ticForm.render(toHome, toViewProj, toProjForm, toViewTic, toTicForm, toComForm)));
        toComForm.setOnAction(e->scene.setRoot(comForm.render(toHome, toViewProj, toProjForm, toViewTic, toTicForm, toComForm)));

        //set scene    
        scene = new Scene(home.render(toHome, toViewProj, toProjForm, toViewTic, toTicForm, toComForm), 700, 600);
        s.setScene(scene);
        s.show(); 
    } 


	
	public static void main(String[] args) {
		launch(args);
	}
}

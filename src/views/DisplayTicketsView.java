

package views;

import java.util.*;
import controllers.TicketController;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import models.Ticket;

public class DisplayTicketsView extends Base{

    public BorderPane render(Button home, Button viewProj, Button projForm, Button viewTic, Button ticForm, Button comForm) {
    	
    	//nav bar
        BorderPane mainPane = createBase(home, viewProj, projForm, viewTic, ticForm, comForm);
        viewProj.setStyle("-fx-background-color: WHEAT");

        // table of projects
    	BorderPane centerPane = new BorderPane();
        Label label = new Label("list of tickets");
        label.setStyle("-fx-font: normal bold 50px elephant; -fx-text-fill: darkolivegreen;");
        centerPane.setTop(label);
        BorderPane.setAlignment(label, Pos.CENTER);
        
        TilePane ticketsTable = new TilePane();
        ticketsTable.setHgap(50);
        ticketsTable.setVgap(30);
        
        TicketController controller = new TicketController();
        List<Ticket> Ticket = controller.getTickets();
        for(int i = 0; i < Ticket.size(); i++) {
        	VBox ticketBox = new VBox();
        	Label ProjectName = new Label(Ticket.get(i).getProjectName());
        	ProjectName.setStyle("-fx-font: normal bold 25.0px elephant; -fx-text-fill: darkolivegreen;");
            Label TicketName = new Label(Ticket.get(i).getName());
            TicketName.setStyle("-fx-font: normal bold 22.5px elephant; -fx-text-fill: darkolivegreen;");
            Label ticketDesc = new Label(Ticket.get(i).getDescription());
            ticketDesc.setStyle("-fx-font: normal bold 17.5px elephant; -fx-text-fill: darkolivegreen;");
            ticketDesc.setWrapText(true);
            ticketDesc.setMaxWidth(400);
            Label ticketLabel = new Label(Ticket.get(i).getDate());
            ticketLabel.setStyle("-fx-font: normal bold 15px elephant; -fx-text-fill: darkolivegreen;");
            
            ticketBox.getChildren().addAll(ProjectName, TicketName, ticketDesc, ticketLabel);
            ticketsTable.getChildren().add(ticketBox);
        }
        centerPane.setCenter(ticketsTable);
        mainPane.setCenter(centerPane);

        return mainPane;
    }
}


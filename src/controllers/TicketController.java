package controllers;

import java.time.LocalDate;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import models.Ticket;
import models.TicketModel;

public class TicketController {
	private TicketModel ticketModel;

	public TicketController() {
        ticketModel = new TicketModel();        
    }

	public String handleSubmitButtonClick(String projectName, String name, String description, LocalDate localDate) {
		// format date in mm/dd/yyyy format
		DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		String formattedDate = localDate.format(format);

		boolean success = (new TicketModel()).addTicket(new Ticket(projectName, name, description, formattedDate));

		if (success) {
			return "Ticket \"" + name + "\" was successfully added.";
		} else {
			return "Ticket \"" + name + "\" was not added.";
		}
	}

	public List<Ticket> getTickets() {
		return ticketModel.getAllTickets();
	}
	
	public List<Ticket> getTickets(String substring) {
		return ticketModel.getAllTickets(substring);
	}
	
	public List<String> getTicketName(String projName){
		ArrayList<String> tickNames = new ArrayList<String>();
		List<Ticket> tickets = ticketModel.getAllTickets();
		for(int i = 0; i < tickets.size(); i++) {
			if(tickets.get(i).getProjectName().equals(projName)) {
				tickNames.add(tickets.get(i).getName());
			}
		}
		return tickNames;
	}
	
	public String getTicketDes(String ticketName){
		List<Ticket> tickets = ticketModel.getAllTickets();
		String tickDes = null;
		for(int i = 0; i < tickets.size(); i++) {
			if(tickets.get(i).getName().equals(ticketName)) {
				tickDes = tickets.get(i).getDescription();
				return tickDes;
			}
		}
		return tickDes;
	}

	public String handleEditButtonClick(int id, String projectName, String name, String description, LocalDate localDate) {
		ticketModel = new TicketModel();
		boolean success = ticketModel.editTicket(id, new Ticket(projectName, name, description, localDate.toString()));
		
		if (success) {
            return "Ticket \"" + name + "\" was successfully edited.";
        } else {
            return "Ticket \"" + name + "\" was not edited.";
        }
	}
}
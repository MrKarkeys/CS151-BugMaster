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
	
	public List<Integer> getTicketId(String projName){
		List<Ticket> tickets = ticketModel.getAllTickets();
		List<Integer> tickId = new ArrayList<Integer>();
		for(int i = 0; i < tickets.size(); i++) {
			if(tickets.get(i).getProjectName().equals(projName)) {
				tickId.add(tickets.get(i).getId());
			}
		}
		return tickId;
	}
}
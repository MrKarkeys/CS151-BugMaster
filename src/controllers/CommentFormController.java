package controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import daos.ProjectDAO;
import models.Ticket;

public class CommentFormController {
	public String handleSubmitButtonClick(int projectId, int ticketId, String description, LocalDate localDate) {
		// format date in mm/dd/yyyy format
		DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String formattedDate = localDate.format(format);
        
        boolean success = (new ProjectDAO()).insertTicket(new Comment(projectId, description, formattedDate));

        if (success) {
            return "Comment was successfully added.";
        } else {
            return "Comment was not added.";
        }
	}
	
	/**
	 * used to populate the form on create comment page
	 * @return 'ticketName: ProjectName'
	 */
	public List<String> getTicketNames() {
		List<String> res = new ArrayList<String>();
		
		List<Ticket> tickets = (new ProjectDAO()).getAllTickets();
		for (Ticket t: tickets) {
			String name = t.getName() + ": "
					+ (new ProjectDAO()).getProjectByID(t.getProjectId()).getName();
			res.add(name);
		}
		
		return res;
	}
}

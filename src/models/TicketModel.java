package models;

import java.util.List;
import daos.ProjectDAO;

public class TicketModel {
	private static ProjectDAO projectDAO;

    public TicketModel() {
        projectDAO = new ProjectDAO();
    }

    public boolean isDBConnected() {
        return projectDAO.validDBConnection();
    }
    
    public List<Ticket> getAllTickets() {
        return projectDAO.getAllTickets();
    }

    public boolean addTicket(Ticket ticket) {
        return projectDAO.insertTicket(ticket);
    }
}

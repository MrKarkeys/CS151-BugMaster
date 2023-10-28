package models;

public class Comment {
	int id;
	String ticketName;
	String description;
	String date;
	
	/**
	 * @param TicketId the SQL key (id) for the ticket row
	 * @param description
	 * @param date
	 */
	public Comment(String ticketName, String description, String date) {
		this.ticketName = ticketName;
		this.description = description;
		this.date = date;
	}
	
	public Comment(int id, String ticketName, String description, String date) {
		this(ticketName, description, date);
		this.id = id;
	}

	public String getticketName() {
		return ticketName;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getDate() {
		return date;
	}
}
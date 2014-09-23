package demo.refactoring;

import java.util.Date;

public class TicketService {
	public Ticket orderTicket(String concertCode, User user) throws NoTicketsAvailableException {
		TicketDAO repository = new TicketDAO();
		Date now = new Date();
		
		if (repository.getAvailableTicketsForConcert(concertCode) < 1) {
			throw new NoTicketsAvailableException(concertCode);
		}		
		
		Ticket t = new Ticket(concertCode);
		t.setOwner(user);
		t.setOrderedAt(now);
		
		repository.save(t);
		
		return t;
	}
}

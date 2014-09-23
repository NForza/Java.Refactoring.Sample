package demo.refactoring;

import java.util.Date;

public class TicketService {
	private TicketRepository repository;


	public TicketService() {
		repository = new TicketDAO();
	}
	
	public TicketService(TicketRepository repo) {
		repository = repo;
	}
	
	public Ticket orderTicket(String concertCode, User user) throws NoTicketsAvailableException {
		
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

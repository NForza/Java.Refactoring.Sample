package demo.refactoring;

import java.util.Hashtable;
import java.util.Random;

public class TicketDAO implements TicketRepository {
	private Hashtable<String, Integer> tickets;
	private Random r;
	private int low = 3;
	private int high = 10;
	
	public TicketDAO() {
		tickets = new Hashtable<String, Integer>();
		r = new Random();
		setupTickets();
	}

	private int getRandomNumber() {
		return r.nextInt(high-low) + low;
	}
	private void setupTickets() {
		tickets.put("AAA", getRandomNumber());
		tickets.put("BBB", getRandomNumber());
		tickets.put("CCC", getRandomNumber());
	}
	
	public int getAvailableTicketsForConcert(String concertCode) {
		Integer nrOfRemainingTickets = tickets.get(concertCode);
		if (nrOfRemainingTickets == null)
			return 0;
		else
			return nrOfRemainingTickets;
	}

	public void save(Ticket t) throws NoTicketsAvailableException {
		String concertCode = t.getConcertCode();
		int currentlyAvailable = getAvailableTicketsForConcert(concertCode);
		if (currentlyAvailable < 1)
			throw new NoTicketsAvailableException(concertCode);
		
		tickets.put(concertCode, currentlyAvailable - 1);
		
		//TODO: write to actual DB
	}

	public void addAvailabeTickets(String concertCode, int nrOfTickets)
	{
		int currentlyAvailable = getAvailableTicketsForConcert(concertCode);
		tickets.put(concertCode, currentlyAvailable + nrOfTickets);
	}
}

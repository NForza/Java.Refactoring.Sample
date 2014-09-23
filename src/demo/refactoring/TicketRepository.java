package demo.refactoring;

public interface TicketRepository {

	public abstract int getAvailableTicketsForConcert(String concertCode);

	public abstract void save(Ticket t) throws NoTicketsAvailableException;

}
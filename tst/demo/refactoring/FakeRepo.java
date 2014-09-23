package demo.refactoring;

public class FakeRepo implements TicketRepository {

	private int nrOfCalls;
	
	@Override
	public int getAvailableTicketsForConcert(String concertCode) {
		// TODO Auto-generated method stub
		if (concertCode == "AAA") {
			nrOfCalls++;
			if (nrOfCalls > 1)
				return 0;
			else
				return 1;
		}
		else
			return 0;
	}

	@Override
	public void save(Ticket t) throws NoTicketsAvailableException {
		// TODO Auto-generated method stub

	}

}

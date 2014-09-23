package demo.refactoring;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.easymock.EasyMock.*;

import org.junit.Before;
import org.junit.Test;

public class TicketServiceTest {
	private TicketService service;
	private User currentUser;
	private TicketRepository mock;
	
	@Before
	public void setUp() throws NoTicketsAvailableException {
		mock = createNiceMock(TicketRepository.class);
		
		service = new TicketService(mock);
		currentUser = User.signIn("user", "secret");
	}

	@Test
	public void WhenOrderingAnAvailableTicket_ItShould_ReturnTheTicket() throws NoTicketsAvailableException {
		expect(mock.getAvailableTicketsForConcert("AAA")).andReturn(1);
		replay(mock);
		
		Ticket expected = service.orderTicket("AAA", currentUser);
		assertThat(expected, is(notNullValue()));
	}

	@Test
	public void WhenOrderingAnAvailableTicket_ItShould_ReturnATicketForTheRightConcert() throws NoTicketsAvailableException {
		expect(mock.getAvailableTicketsForConcert("AAA")).andReturn(1);
		replay(mock);
		
		Ticket expected = service.orderTicket("AAA", currentUser);
		assertThat(expected.getConcertCode(), is(equalTo("AAA")));
	}

	@Test(expected=NoTicketsAvailableException.class)
	public void WhenOrderingAnUnAvailableTicket_ItShould_Throw() throws NoTicketsAvailableException {
		expect(mock.getAvailableTicketsForConcert("DDD")).andReturn(0);
		replay(mock);
		
		Ticket expected = service.orderTicket("DDD", currentUser);
		assertThat(expected.getConcertCode(), is(equalTo("DDD")));
	}
	
	@Test(expected=NoTicketsAvailableException.class)
	public void WhenOrderingATicketAfterStockRanOut_ItShould_Throw() throws NoTicketsAvailableException {
		expect(mock.getAvailableTicketsForConcert("AAA")).andReturn(1);
		expect(mock.getAvailableTicketsForConcert("AAA")).andReturn(0);
		replay(mock);
		
		Ticket expected = service.orderTicket("AAA", currentUser);
		expected = service.orderTicket("AAA", currentUser);
		assertThat(expected.getConcertCode(), is(equalTo("AAA")));
	}
}

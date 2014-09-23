package demo.refactoring;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class TicketServiceTest {
	private TicketService service;
	private User currentUser;
	private TicketRepository mock;
	
	@Before
	public void setUp() {
		mock = mock(TicketRepository.class);
		
		service = new TicketService(mock);
		currentUser = User.signIn("user", "secret");
	}

	@Test
	public void WhenOrderingAnAvailableTicket_ItShould_ReturnTheTicket() throws NoTicketsAvailableException {
		when(mock.getAvailableTicketsForConcert("AAA")).thenReturn(1);
		
		Ticket expected = service.orderTicket("AAA", currentUser);
		assertThat(expected, is(notNullValue()));
	}

	@Test
	public void WhenOrderingAnAvailableTicket_ItShould_ReturnATicketForTheRightConcert() throws NoTicketsAvailableException {
		when(mock.getAvailableTicketsForConcert("AAA")).thenReturn(1);
		
		Ticket expected = service.orderTicket("AAA", currentUser);
		assertThat(expected.getConcertCode(), is(equalTo("AAA")));
	}

	@Test(expected=NoTicketsAvailableException.class)
	public void WhenOrderingAnUnAvailableTicket_ItShould_Throw() throws NoTicketsAvailableException {
		when(mock.getAvailableTicketsForConcert("AAA")).thenReturn(1);
		
		Ticket expected = service.orderTicket("DDD", currentUser);
		assertThat(expected.getConcertCode(), is(equalTo("DDD")));
	}
	
	@Test(expected=NoTicketsAvailableException.class)
	public void WhenOrderingATicketAfterStockRanOut_ItShould_Throw() throws NoTicketsAvailableException {
		when(mock.getAvailableTicketsForConcert("AAA")).thenReturn(1);
		when(mock.getAvailableTicketsForConcert("AAA")).thenReturn(0);
		
		Ticket expected = service.orderTicket("AAA", currentUser);
		
		verify(mock).save(Mockito.any(Ticket.class));
		
		expected = service.orderTicket("AAA", currentUser);
		assertThat(expected.getConcertCode(), is(equalTo("AAA")));
	}
}

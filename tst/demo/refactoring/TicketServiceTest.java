package demo.refactoring;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class TicketServiceTest {
	private TicketService service;
	private User currentUser;
	
	@Before
	public void setUp() {
		service = new TicketService();
		currentUser = User.signIn("user", "secret");
	}

	@Test
	public void testOrderTicket() throws NoTicketsAvailableException {
		Ticket expected = service.orderTicket("AAA", currentUser);
		assertThat(expected, is(notNullValue()));
	}

}

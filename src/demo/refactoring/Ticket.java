package demo.refactoring;

import java.util.Date;

public class Ticket {
	private Date orderedAt;
	private User owner;
	private String concertCode;
	
	public Ticket(String concertCode) {
		this.concertCode = concertCode;
	}

	public String getConcertCode() {
		return concertCode;
	}
	
	public Date getOrderedAt() {
		return orderedAt;
	}

	public void setOrderedAt(Date orderedAt) {
		this.orderedAt = orderedAt;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

}

package model;

public class MailProperties {

	private String emailAddress;
	private String auctionName;
	
	public MailProperties(String auctionName) {
		super();
		this.auctionName = auctionName;
	}
	
	public MailProperties(String emailAddress, String auctionName) {
		super();
		this.emailAddress = emailAddress;
		this.auctionName = auctionName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getAuctionName() {
		return auctionName;
	}

	public void setAuctionName(String auctionName) {
		this.auctionName = auctionName;
	}
	
}

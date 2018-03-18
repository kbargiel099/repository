package hello;

public class RequestForm {

    private String auctionId;
    private String userId;
    private String username;
    private String price;

    public RequestForm() {
    }

    public RequestForm(String auctionId, String userId,String username, String price) {
        this.auctionId = auctionId;
        this.userId = userId;
        this.username = username;
        this.price = price;
    }

	public String getAuctionId() {
		return auctionId;
	}

	public void setAuctionId(String auctionId) {
		this.auctionId = auctionId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
   
}
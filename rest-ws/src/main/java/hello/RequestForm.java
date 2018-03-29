package hello;

public class RequestForm {

    private String userId;
    private String username;
    private String price;
    private String endDate;
    private String quantity;
    private int auctionTypeId;
    
    public RequestForm() {
    }

    public RequestForm( String userId,String username, String price,
    		String endDate, String quantity, int auctionTypeId) {
        this.endDate = endDate;
        this.userId = userId;
        this.username = username;
        this.price = price;
        this.quantity = quantity;
        this.auctionTypeId = auctionTypeId;
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

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public int getAuctionTypeId() {
		return auctionTypeId;
	}

	public void setAuctionTypeId(int auctionTypeId) {
		this.auctionTypeId = auctionTypeId;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
   
}
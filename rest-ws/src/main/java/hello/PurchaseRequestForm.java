package hello;

public class PurchaseRequestForm {

    private String userId;
    private String username;
    private String endDate;
    private String quantity;
    
    public PurchaseRequestForm() {
    }

    public PurchaseRequestForm( String userId,String username,
    		String endDate, String quantity) {
        this.endDate = endDate;
        this.userId = userId;
        this.username = username;
        this.quantity = quantity;
    }

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
   
}
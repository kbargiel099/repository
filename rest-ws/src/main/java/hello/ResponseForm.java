package hello;

public class ResponseForm extends Response{

    private boolean success;
    private String username;
    private String price;
    private String quantity;

    public ResponseForm() { }

    public ResponseForm(boolean success) {
        this.success = success;
    }
    
    public ResponseForm(boolean success, String username) {
        this.success = success;
        this.username = username;
    }

    public ResponseForm(boolean success, String username, String price, String quantity) {
        this.success = success;
        this.username = username;
        this.price = price;
        this.quantity = quantity;
    }
    
    public ResponseForm(long incrementAndGet, String format) {
    	username = format;
	}

	public boolean getSuccess() {
        return success;
    }

    public String getUsername() {
        return username;
    }

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
    
}

package hello;

public class MessageResponse extends Response{

    private boolean success;
    private String username;
    private String message;
    private String date;
    	
	public MessageResponse(boolean success, String username, String message, String date) {
		super();
		this.success = success;
		this.username = username;
		this.message = message;
		this.date = date;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
    
}

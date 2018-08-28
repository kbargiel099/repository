package hello;

import java.util.Date;

public class MessageResponse extends Response{

    private boolean success;
    private String senderId;
    private String senderName;
    private String message;
    private String date;
    	
	public MessageResponse(boolean success, String senderId, String senderName, String message, String date) {
		super();
		this.success = success;
		this.senderId = senderId;
		this.senderName = senderName;
		this.message = message;
		this.date = date;
	}
	
	public MessageResponse(MessageRequestForm form) {
		super();
		this.success = true;
		this.senderId = form.getSenderId();
		this.senderName = form.getSenderName();
		this.message = form.getMessage();
		this.date = new Date().toString();
	}


	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getSenderId() {
		return senderId;
	}

	public void setSenderId(String senderId) {
		this.senderId = senderId;
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

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
    
}

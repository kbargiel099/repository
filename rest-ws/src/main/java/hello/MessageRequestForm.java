package hello;

public class MessageRequestForm {

    private String senderId;
    private String senderName;
    private String receiverId;
    private String message;
    
    
    public MessageRequestForm() {
    }

	public MessageRequestForm(String senderId, String receiverId, String senderName, String message) {
		super();
		this.senderId = senderId;
		this.receiverId = receiverId;
		this.senderName = senderName;
		this.message = message;
	}


	public String getSenderId() {
		return senderId;
	}


	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}


	public String getReceiverId() {
		return receiverId;
	}


	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId;
	}


	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
   
}
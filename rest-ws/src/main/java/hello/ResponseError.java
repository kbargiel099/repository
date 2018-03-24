package hello;

public class ResponseError extends Response{
	
	private int errorCode;

	public ResponseError(int errorCode) {
		super();
		this.errorCode = errorCode;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	} 

}

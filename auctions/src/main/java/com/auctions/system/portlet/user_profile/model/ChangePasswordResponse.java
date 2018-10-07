package com.auctions.system.portlet.user_profile.model;

public class ChangePasswordResponse {
	
	private boolean success;
	private String error;
	
	public ChangePasswordResponse() { }
	
	public ChangePasswordResponse(boolean success, String error) {
		super();
		this.success = success;
		this.error = error;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
	
	

}

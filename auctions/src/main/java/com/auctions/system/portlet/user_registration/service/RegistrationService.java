package com.auctions.system.portlet.user_registration.service;

public interface RegistrationService {
	
	public boolean checkIfEmailExist(String email);
	
	public boolean checkIfLoginExist(String login);

}

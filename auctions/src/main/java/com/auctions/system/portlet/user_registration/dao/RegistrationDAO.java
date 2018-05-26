package com.auctions.system.portlet.user_registration.dao;

public interface RegistrationDAO {
	
	public boolean checkIfEmailExist(String email);
	
	public boolean checkIfLoginExist(String login);

}

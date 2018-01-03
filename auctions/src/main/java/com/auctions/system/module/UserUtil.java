package com.auctions.system.module;

import com.auctions.system.portlet.users_management.model.User;

public class UserUtil {

	private static boolean isAdmin;
	private static User user;
	
	public static int getUserId(){
		return user.getId();
	}
	public static String getUserLogin(){
		return user.getLogin();
	}
	public static String getUserPassword(){
		return user.getPassword();
	}
	
	public static User getUser(){
		return user;
	}
	
	public static boolean getIsAdmin(){
		return isAdmin;
	}
	
	public static boolean isGuest(){
		return user == null ? true : false;
	}
	
	public static void setUser(User loggedUser){
		user = loggedUser;
	}
	
	public static void setIsAdmin(boolean loggedUserIsAdmin){
		isAdmin = loggedUserIsAdmin;
	}
}

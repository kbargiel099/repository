package com.auctions.system.portlet.users_management.model;

import java.io.Serializable;

public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long id;
	private String login;
	private String password;
	private String email;
	private String firstname;
	private String lastname;
	private boolean lockout;

	public User(){}
	
	public User(long id, String login, String password) {
		super();
		this.id = id;
		this.login = login;
		this.password = password;
	}
	
	public User(long id, String login,String firstname,
			String lastname, String email,boolean lockout) {
		super();
		this.id = id;
		this.login = login;
		this.password = "";
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.lockout = lockout;
	}
	
	public User(long id, String login, String password,String firstname,
			String lastname, String email,boolean lockout) {
		super();
		this.id = id;
		this.login = login;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.lockout = lockout;
	}
	
	public User(User user) {
		super();
		this.id = user.getId();
		this.login = user.getLogin();
		this.password = user.getPassword();
		this.firstname = user.getFirstname();
		this.lastname = user.getLastname();
		this.email = user.getEmail();
		this.lockout = user.isLockout();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public boolean isLockout() {
		return lockout;
	}

	public void setLockout(boolean lockout) {
		this.lockout = lockout;
	}
	
}

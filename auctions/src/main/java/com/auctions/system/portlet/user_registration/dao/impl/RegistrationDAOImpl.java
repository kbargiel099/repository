package com.auctions.system.portlet.user_registration.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.auctions.system.portlet.user_profile.model.UserProfileAuction;
import com.auctions.system.portlet.user_registration.dao.RegistrationDAO;
import com.auctions.system.portlet.users_management.model.User;
import com.google.gson.JsonObject;

@Repository("registrationDAO")
public class RegistrationDAOImpl implements RegistrationDAO {
		
	private JdbcTemplate dao;
	
	@Autowired
	@Qualifier("dataSource-lportal")
	private DataSource dataSource;
	
	@PostConstruct
	public void init() {
	 dao = new JdbcTemplate(dataSource);
	}
	
	@Override
	public boolean createUser(User user,boolean isAdmin){
		Timestamp stamp = new Timestamp(0);
		
		int numberOfUpdatedRows =  dao.update("INSERT INTO users(login,password,email,firstname,lastname,isActive,isAdmin,createDate,editDate) VALUES(?,?,?,?,?,?,?,?,?)",
				new Object[]{user.getLogin(),user.getPassword(),user.getEmail(),user.getFirstname(),user.getLastname(),true,isAdmin,stamp,stamp});
		
		return numberOfUpdatedRows > 0 ? true : false;
	}
	
	@Override
	public boolean checkIfEmailExist(String email){
		return dao.queryForObject("SELECT EXISTS(SELECT userid FROM user_ WHERE emailaddress=?)", 
				new Object[]{email},Boolean.class);
	}
	
	@Override
	public boolean checkIfLoginExist(String login){
		return dao.queryForObject("SELECT EXISTS(SELECT userid FROM user_ WHERE screenname=?)", 
				new Object[]{login},Boolean.class);
	}
	

}

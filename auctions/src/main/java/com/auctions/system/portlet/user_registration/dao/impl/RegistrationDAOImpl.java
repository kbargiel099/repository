package com.auctions.system.portlet.user_registration.dao.impl;

import java.sql.Timestamp;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.auctions.system.portlet.user_registration.dao.RegistrationDAO;
import com.auctions.system.portlet.users_management.model.User;

@Repository("registrationDAO")
public class RegistrationDAOImpl implements RegistrationDAO {
		
	private JdbcTemplate dao;
	
	@Autowired
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
	

}

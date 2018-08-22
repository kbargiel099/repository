package com.auctions.system.portlet.user_registration.dao.impl;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.auctions.system.portlet.user_registration.dao.RegistrationDAO;

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

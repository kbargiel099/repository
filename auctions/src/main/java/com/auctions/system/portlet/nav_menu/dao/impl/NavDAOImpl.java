package com.auctions.system.portlet.nav_menu.dao.impl;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.auctions.system.portlet.nav_menu.dao.NavDAO;

@Repository("navDAO")
public class NavDAOImpl implements NavDAO{

	private JdbcTemplate dao;
	
	@Autowired
	private DataSource dataSource;
	
	@PostConstruct
	public void init() {
	 dao = new JdbcTemplate(dataSource);
	}
	
	@Override
	public boolean isUserExist(String login, String password){
		return dao.queryForObject("SELECT EXISTS (SELECT id FROM users WHERE login=? and password=? )",
				new Object[]{login,password}, Boolean.class);
	}

}

package com.auctions.system.portlet.nav_menu.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.auctions.system.portlet.nav_menu.dao.NavDAO;
import com.auctions.system.portlet.nav_menu.model.UserViewModel;
import com.auctions.system.portlet.users_management.model.User;

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
	
	/*@Override
	public UserViewModel getUser(String login) {
		return dao.queryForObject("SELECT id,login FROM users WHERE login=?", 
			new Object[]{login},new RowMapper<UserViewModel>(){
				@Override
				public UserViewModel mapRow(ResultSet res, int row) throws SQLException {
					return new UserViewModel(res.getInt("id"),res.getString("login"));
			}
		});
	}*/
	
	@Override
	public User getUser(String login) {
		return dao.queryForObject("SELECT id,login,password,firstname,lastname,email FROM users WHERE login=?", 
			new Object[]{login},new RowMapper<User>(){
				@Override
				public User mapRow(ResultSet res, int row) throws SQLException {
					return new User(res.getInt("id"),res.getString("login"),res.getString("password"),res.getString("firstname"),
							res.getString("lastname"),res.getString("email"));
			}
		});
	}

}

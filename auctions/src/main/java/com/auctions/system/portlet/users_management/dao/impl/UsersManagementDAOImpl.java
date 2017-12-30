package com.auctions.system.portlet.users_management.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.auctions.system.portlet.users_management.dao.UsersManagementDAO;
import com.auctions.system.portlet.users_management.model.User;

@Repository("UsersManagementDAO")
public class UsersManagementDAOImpl implements UsersManagementDAO {

	private JdbcTemplate dao;
	
	@Autowired
	private DataSource dataSource;
	
	@PostConstruct
	public void init() {
	 dao = new JdbcTemplate(dataSource);
	}
	
	@Override
	public List<User> getUser() {
		return dao.query("SELECT id,login,password FROM users", new RowMapper<User>(){
			@Override
			public User mapRow(ResultSet res, int row) throws SQLException {
				return new User(res.getInt("id"),res.getString("login"),res.getString("password"));
			}	
		});
	}

	@Override
	public User getUserById(int userId) {
		return dao.queryForObject("SELECT id,login,password FROM users WHERE id = ?", 
			new Object[]{userId},new RowMapper<User>(){
				@Override
				public User mapRow(ResultSet res, int row) throws SQLException {
					return new User(res.getInt("id"),res.getString("login"),res.getString("password"));
			}
		});
	}
	
	@Override
	public boolean createUser(User user){
		int numberOfUpdatedRows =  dao.update("INSERT INTO users(login,password) VALUES(?,?)",
				new Object[]{user.getLogin(),user.getPassword()});
		
		return numberOfUpdatedRows > 0 ? true : false;
	}
	
	@Override
	public boolean updateUser(User user){
		int numberOfUpdatedRows =  dao.update("UPDATE users SET login = ?, password = ? WHERE id = ?",
				new Object[]{user.getLogin(),user.getPassword(),user.getId()});
		
		return numberOfUpdatedRows > 0 ? true : false;
	}

	@Override
	public boolean deleteUser(int userId) {
		int numberOfDeletedRows =  dao.update("DELETE FROM users WHERE id = ?",
				new Object[]{userId});
		
		return numberOfDeletedRows > 0 ? true : false;
		
	}
	

	
}

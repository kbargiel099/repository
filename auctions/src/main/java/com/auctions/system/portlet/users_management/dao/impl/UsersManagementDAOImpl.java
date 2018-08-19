package com.auctions.system.portlet.users_management.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.auctions.system.portlet.users_management.dao.UsersManagementDAO;
import com.auctions.system.portlet.users_management.model.User;

@Repository("UsersManagementDAO")
public class UsersManagementDAOImpl implements UsersManagementDAO {

	private JdbcTemplate daoPortal;
	
	@Autowired
	@Qualifier("dataSource-lportal")
	private DataSource dataSourcePortal;
	
	@PostConstruct
	public void init() {
		daoPortal = new JdbcTemplate(dataSourcePortal);
	}
	
	@Override
	public List<User> getUsers() {
		return daoPortal.query("SELECT userid,screenname,firstname,lastname,emailaddress,lockout FROM user_ WHERE emailaddress NOT LIKE 'default%'", new RowMapper<User>(){
			@Override
			public User mapRow(ResultSet res, int row) throws SQLException {
				return new User(res.getLong("userid"),res.getString("screenname"),res.getString("firstname"),
						res.getString("lastname"),res.getString("emailaddress"),res.getBoolean("lockout"));
			}	
		});
	}
	
	@Override
	public User getUserById(int userId) {
		return daoPortal.queryForObject("SELECT userid,screenname,password,firstname,lastname,emailaddress,lockout FROM user_ WHERE userid = ?", 
			new Object[]{userId},new RowMapper<User>(){
				@Override
				public User mapRow(ResultSet res, int row) throws SQLException {
					return new User(res.getLong("userid"),res.getString("screenname"),res.getString("password"),res.getString("firstname"),
							res.getString("lastname"),res.getString("emailaddress"),res.getBoolean("lockout"));
			}
		});
	}
		
}

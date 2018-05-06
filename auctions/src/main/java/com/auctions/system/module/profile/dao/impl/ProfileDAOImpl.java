package com.auctions.system.module.profile.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.auctions.system.module.profile.dao.ProfileDAO;
import com.auctions.system.module.profile.model.UserProfile;

@Repository("profileDAO")
public class ProfileDAOImpl implements ProfileDAO{

	private JdbcTemplate daoPortal;
	private JdbcTemplate dao;
	
	@Autowired
	@Qualifier("dataSource-lportal")
	private DataSource dataSourcePortal;
	
	@Autowired
	@Qualifier("dataSource")
	private DataSource dataSource;
	
	@PostConstruct
	public void init() {
		daoPortal = new JdbcTemplate(dataSourcePortal);
		dao = new JdbcTemplate(dataSource);
	}
	
	@Override
	public UserProfile getUserProfile(long userId){
		return daoPortal.queryForObject("SELECT userid,screenname,firstname,lastname,emailaddress,createdate,logindate FROM user_ WHERE userid=?", 
				new Object[]{userId},new RowMapper<UserProfile>(){
					@Override
					public UserProfile mapRow(ResultSet res, int row) throws SQLException {
						return new UserProfile(res.getLong("userid"),res.getString("screenname"),res.getString("firstname"),res.getString("lastname"),
								res.getString("emailaddress"),res.getTimestamp("createdate"),res.getTimestamp("logindate"));
				}
		});
	}

}

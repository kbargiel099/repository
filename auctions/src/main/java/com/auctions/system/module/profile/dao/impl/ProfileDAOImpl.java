package com.auctions.system.module.profile.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.auctions.system.module.profile.dao.ProfileDAO;
import com.auctions.system.module.profile.model.UserProfile;
import com.auctions.system.portlet.category.model.AuctionDetails;
import com.auctions.system.portlet.category.model.UserDetails;

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
	public AuctionDetails getAuctionDetails(long auctionId){
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH.mm.ss");
		
		return dao.queryForObject("SELECT a.id,serial_number,a.name,i.image_name AS image_name,description,create_date,end_date,subject_price,has_video"
				+ " FROM auction a,auction_image i WHERE a.id=i.auction_id AND a.id=?", 
				new Object[]{auctionId},new RowMapper<AuctionDetails>(){
					@Override
					public AuctionDetails mapRow(ResultSet res, int row) throws SQLException {
						return new AuctionDetails(res.getInt("id"),res.getString("serial_number"),
								res.getString("name"),res.getString("description"),
								sdf.format(res.getTimestamp("create_date")),sdf.format(res.getTimestamp("end_date")),res.getString("image_name"),
								res.getBoolean("has_video"),10,res.getLong("subject_price"));
				}
			});
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

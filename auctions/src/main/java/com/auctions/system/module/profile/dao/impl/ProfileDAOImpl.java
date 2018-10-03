package com.auctions.system.module.profile.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.auctions.system.module.DateFormatter;
import com.auctions.system.module.profile.dao.ProfileDAO;
import com.auctions.system.module.profile.model.Grade;
import com.auctions.system.module.profile.model.UserProfile;
import com.auctions.system.portlet.home_page.model.AuctionPresenter;

@Repository("profileDAO")
public class ProfileDAOImpl implements ProfileDAO{

	private JdbcTemplate dao;
	
	@Autowired
	private DataSource dataSource;
	
	@PostConstruct
	public void init() {
		dao = new JdbcTemplate(dataSource);
	}
	
	@Override
	public UserProfile getUserProfile(long userId){
		return dao.queryForObject("SELECT userid,screenname,firstname,lastname,emailaddress,createdate,logindate FROM user_ WHERE userid=?", 
				new Object[]{userId},new RowMapper<UserProfile>(){
					@Override
					public UserProfile mapRow(ResultSet res, int row) throws SQLException {
						return new UserProfile(res.getLong("userid"),res.getString("screenname"),res.getString("firstname"),res.getString("lastname"),
								res.getString("emailaddress"),DateFormatter.format(res.getTimestamp("createdate")),
								DateFormatter.format(res.getTimestamp("logindate")));
				}
		});
	}
	
	@Override
	public List<Grade> getUserGrades(long userId){
		return dao.query("SELECT screenname,grade,comment,g.create_date FROM sys.auction_grade g "
								+ "JOIN user_ u ON u.userid=g.userid "
								+ "JOIN sys.auction a ON a.id=g.auctionid WHERE a.userid=? ORDER BY create_date", 
				new Object[]{userId},new RowMapper<Grade>(){
					@Override
					public Grade mapRow(ResultSet res, int row) throws SQLException {
						return new Grade(res.getString("screenname"),res.getInt("grade"),res.getString("comment"),
								DateFormatter.format(res.getTimestamp("create_date")));
				}
		}); 
	}
	
	@Override
	public List<AuctionPresenter> getUserAuctions(long userId){
		return dao.query("SELECT id,name,image_name,subject_price,create_date,end_date FROM sys.auction_main WHERE userid=?", 
				new Object[]{userId},new RowMapper<AuctionPresenter>(){
					@Override
					public AuctionPresenter mapRow(ResultSet res, int row) throws SQLException {
						return new AuctionPresenter(res.getInt("id"),res.getString("name"),res.getString("image_name"),res.getLong("subject_price"),
								DateFormatter.format(res.getTimestamp("create_date")),DateFormatter.format(res.getTimestamp("end_date")));
				}
			});
	}

}

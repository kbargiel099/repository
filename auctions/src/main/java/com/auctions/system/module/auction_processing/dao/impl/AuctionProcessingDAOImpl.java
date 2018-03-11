package com.auctions.system.module.auction_processing.dao.impl;

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

import com.auctions.system.module.auction_processing.dao.AuctionProcessingDAO;
import com.auctions.system.portlet.category.model.AuctionDetails;
import com.auctions.system.portlet.category.model.UserDetails;

@Repository("auctionProcessingDAO")
public class AuctionProcessingDAOImpl implements AuctionProcessingDAO{

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
		
		return dao.queryForObject("SELECT a.id,serial_number,a.name,i.name AS image_name,i.data AS image_data,description,create_date,end_date,subject_price"
				+ " FROM auction a,image i WHERE a.imageid=i.id AND a.id=?", 
				new Object[]{auctionId},new RowMapper<AuctionDetails>(){
					@Override
					public AuctionDetails mapRow(ResultSet res, int row) throws SQLException {
						String imageData = new String(res.getBytes("image_data"));
						return new AuctionDetails(res.getInt("id"),res.getString("serial_number"),
								res.getString("name"),res.getString("description"),
								sdf.format(res.getTimestamp("create_date")),sdf.format(res.getTimestamp("end_date")),res.getString("image_name"),
								imageData,10,res.getLong("subject_price"));
				}
			});
	}
	
	@Override
	public UserDetails getSellerDetails(long userId){
		return daoPortal.queryForObject("SELECT firstname,lastname,emailaddress FROM user_ WHERE userid=?", 
				new Object[]{userId},new RowMapper<UserDetails>(){
					@Override
					public UserDetails mapRow(ResultSet res, int row) throws SQLException {
						return new UserDetails(res.getString("firstname"),res.getString("lastname"),
								"623189505",res.getString("emailaddress"));
				}
		});
	}

}

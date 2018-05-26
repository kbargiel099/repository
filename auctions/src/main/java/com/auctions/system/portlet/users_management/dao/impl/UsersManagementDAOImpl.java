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

import com.auctions.system.module.auction_processing.DateFormatter;
import com.auctions.system.module.auction_processing.model.AuctionOffer;
import com.auctions.system.portlet.users_management.dao.UsersManagementDAO;
import com.auctions.system.portlet.users_management.model.AuctionDatatable;
import com.auctions.system.portlet.users_management.model.User;

@Repository("UsersManagementDAO")
public class UsersManagementDAOImpl implements UsersManagementDAO {

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
	public List<AuctionDatatable> getAuctions() {
		return dao.query("SELECT id,name,create_date,image_name,status FROM auction_datatable",
				new RowMapper<AuctionDatatable>(){
			@Override
			public AuctionDatatable mapRow(ResultSet res, int row) throws SQLException {
				return new AuctionDatatable(res.getLong("id"),res.getString("name"),DateFormatter.format(res.getTimestamp("create_date")),
						res.getString("image_name"),res.getString("status"));
			}	
		});
	}
	
	@Override
	public boolean deleteAuction(long auctionId) {
		return dao.update("DELETE FROM auction WHERE id=?",
				new Object[]{auctionId}) > 0 ? true : false;
	}
	
	@Override
	public boolean suspendAuction(long auctionId) {
		return dao.update("UPDATE auction a SET statusid=(SELECT s.id FROM auction_status s WHERE s.name='suspended') WHERE a.id=?",
				new Object[]{auctionId}) > 0 ? true : false;
	}
	
	@Override
	public boolean activateAuction(long auctionId) {
		return dao.update("UPDATE auction a SET statusid=(SELECT s.id FROM auction_status s WHERE s.name='active') WHERE a.id=?",
				new Object[]{auctionId}) > 0 ? true : false;
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
	
	@Override
	public List<AuctionOffer> getPurchases(long auctionId){
		return dao.query("SELECT userid,price,quantity,create_date FROM auction_purchase WHERE auctionid=?", 
				new Object[]{auctionId},new RowMapper<AuctionOffer>(){
					@Override
					public AuctionOffer mapRow(ResultSet res, int row) throws SQLException {
						return new AuctionOffer(res.getLong("userid"),res.getLong("price"),
							res.getInt("quantity"),DateFormatter.format(res.getTimestamp("create_date")));
				}
		});
	}
	
	@Override
	public List<AuctionOffer> getWonOffers(long auctionId){
		return dao.query("SELECT userid,price,quantity,current_date AS create_date FROM auction_winner WHERE auctionid=?", 
				new Object[]{auctionId},new RowMapper<AuctionOffer>(){
					@Override
					public AuctionOffer mapRow(ResultSet res, int row) throws SQLException {
						return new AuctionOffer(res.getLong("userid"),res.getLong("price"),
							res.getInt("quantity"),DateFormatter.format(res.getTimestamp("create_date")));
				}
		});
	}
	
	
}

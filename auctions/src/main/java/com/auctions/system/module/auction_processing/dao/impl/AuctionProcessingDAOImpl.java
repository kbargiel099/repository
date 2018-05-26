package com.auctions.system.module.auction_processing.dao.impl;

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
import com.auctions.system.module.auction_processing.dao.AuctionProcessingDAO;
import com.auctions.system.module.auction_processing.model.AuctionOffer;
import com.auctions.system.module.auction_processing.model.PaymentMethod;
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
		return dao.queryForObject("SELECT id,userid,serial_number,name,images,description,create_date,end_date,"
				+ "subject_price,available,videoid,type_name,minimal_price,technical_data FROM auction_details WHERE id=?", 
				new Object[]{auctionId},new RowMapper<AuctionDetails>(){
					@Override
					public AuctionDetails mapRow(ResultSet res, int row) throws SQLException {
						return new AuctionDetails(res.getLong("id"),res.getLong("userid"),res.getString("serial_number"),res.getString("name"),
							res.getString("description"),DateFormatter.format(res.getTimestamp("create_date")),DateFormatter.format(res.getTimestamp("end_date"))
							,res.getArray("images"),res.getString("type_name"),res.getLong("videoid"),res.getInt("available")
							,res.getLong("subject_price"),res.getLong("minimal_price"),res.getString("technical_data"));
				}
			});
	}
	
	@Override
	public UserDetails getSellerDetails(long userId){
		return daoPortal.queryForObject("SELECT userid,screenname,firstname,lastname,emailaddress FROM user_ WHERE userid=?", 
				new Object[]{userId},new RowMapper<UserDetails>(){
					@Override
					public UserDetails mapRow(ResultSet res, int row) throws SQLException {
						return new UserDetails(res.getLong("userid"),res.getString("screenname"),res.getString("firstname"),res.getString("lastname"),
								"623189505",res.getString("emailaddress"));
				}
		});
	}
	
	@Override
	public String getVideoName(long id){
		return dao.queryForObject("SELECT v.name FROM auction_video v JOIN auction a on videoid=v.id WHERE a.id=?", 
				new Object[]{id},new RowMapper<String>(){
					@Override
					public String mapRow(ResultSet res, int row) throws SQLException {
						return res.getString("name");
				}
		});
	}
	
	@Override
	public boolean createObservation(long userId,long auctionId){
		int numberOfInsertedRows = dao.update("INSERT INTO auction_observation(userid,auctionid) VALUES(?,?)", 
				new Object[]{userId,auctionId});
		return numberOfInsertedRows > 0 ? true : false;
	}
	
	@Override
	public boolean removeObservation(long userId,long auctionId){
		int numberOfDeletedRows = dao.update("DELETE FROM auction_observation WHERE userid=? AND auctionid=?", 
				new Object[]{userId,auctionId});
		return numberOfDeletedRows > 0 ? true : false;
	}
	
	@Override
	public boolean isObserved(long userId,long auctionId){
		return dao.queryForObject("SELECT CASE WHEN EXISTS (SELECT 1 FROM auction_observation WHERE userid=? AND auctionid=?) THEN true ELSE false END", 
				new Object[]{userId,auctionId},new RowMapper<Boolean>(){
					@Override
					public Boolean mapRow(ResultSet res, int row) throws SQLException {
						return res.getBoolean(1);
				}
		});
	}
	
	@Override
	public List<AuctionOffer> getAllOffers(long auctionId){
		return dao.query("SELECT userid,price,quantity,create_date FROM auction_process WHERE auctionid=?", 
				new Object[]{auctionId},new RowMapper<AuctionOffer>(){
					@Override
					public AuctionOffer mapRow(ResultSet res, int row) throws SQLException {
						return new AuctionOffer(res.getLong("userid"),res.getLong("price"),
							res.getInt("quantity"),DateFormatter.format(res.getTimestamp("create_date")));
				}
		});
	}
	
	@Override
	public List<PaymentMethod> getPaymentMethods(){
		return dao.query("SELECT id,name,validity_in_days FROM payment_method", 
				new RowMapper<PaymentMethod>(){
					@Override
					public PaymentMethod mapRow(ResultSet res, int row) throws SQLException {
						return new PaymentMethod(res.getInt("id"),res.getString("name"),
							res.getInt("validity_in_days"));
				}
		});
	}

}

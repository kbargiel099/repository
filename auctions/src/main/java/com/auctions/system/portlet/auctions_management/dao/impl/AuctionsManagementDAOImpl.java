package com.auctions.system.portlet.auctions_management.dao.impl;

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
import com.auctions.system.portlet.auctions_management.dao.AuctionsManagementDAO;
import com.auctions.system.portlet.auctions_management.model.AuctionDatatable;
import com.auctions.system.portlet.category.model.Category;
import com.auctions.system.portlet.user_profile.model.Auction;
import com.auctions.system.portlet.user_profile.model.AuctionType;

@Repository("AuctionsManagementDAO")
public class AuctionsManagementDAOImpl implements AuctionsManagementDAO {

	private JdbcTemplate dao;
	
	@Autowired
	private DataSource dataSource;
	
	@PostConstruct
	public void init() {
		dao = new JdbcTemplate(dataSource);
	}
	
	@Override
	public List<AuctionDatatable> getAuctions() {
		return dao.query("SELECT id,name,create_date,image_name,status FROM sys.auction_datatable",
				new RowMapper<AuctionDatatable>(){
			@Override
			public AuctionDatatable mapRow(ResultSet res, int row) throws SQLException {
				return new AuctionDatatable(res.getLong("id"),res.getString("name"),DateFormatter.format(res.getTimestamp("create_date")),
						res.getString("image_name"),res.getString("status"));
			}	
		});
	}
	
	@Override
	public List<Category> getCategories() {
		return dao.query("SELECT id,name FROM sys.category", new RowMapper<Category>() {
			@Override
			public Category mapRow(ResultSet res, int row) throws SQLException {
				return new Category(res.getInt("id"), res.getString("name"));
			}
		});
	}

	@Override
	public List<AuctionType> getAuctionTypes() {
		return dao.query("SELECT id,name FROM sys.auction_type", new RowMapper<AuctionType>() {
			@Override
			public AuctionType mapRow(ResultSet res, int row) throws SQLException {
				return new AuctionType(res.getInt("id"), res.getString("name"));
			}
		});
	}
	
	@Override
	public Auction getAuctionData(final long id) { 
		return dao.queryForObject(
				"SELECT a.name,serial_number,end_date,typeid,s.category_id,subcategory_id,images,description,available,subject_price,technical_data,minimal_price FROM sys.auction a"
						+ " JOIN sys.subcategory s ON a.subcategory_id=s.id WHERE a.id=?",
				new Object[] { id }, new RowMapper<Auction>() {
					@Override
					public Auction mapRow(ResultSet res, int row) throws SQLException {
						return new Auction(id, res.getString("name"), res.getLong("serial_number"),
								DateFormatter.formatForView(res.getTimestamp("end_date")), res.getLong("minimal_price"), res.getInt("typeid"),
								res.getInt("category_id"), res.getInt("subcategory_id"), res.getString("images"),
								res.getString("description"), res.getInt("available"), res.getLong("subject_price"),
								res.getString("technical_data"));
					}
				});
	}
	
	@Override
	public boolean deleteAuction(long auctionId) {
		return dao.update("DELETE FROM sys.auction WHERE id=?",
				new Object[]{auctionId}) > 0 ? true : false;
	}
	
	@Override
	public boolean suspendAuction(long auctionId) {
		return dao.update("UPDATE sys.auction a SET statusid=(SELECT s.id FROM sys.auction_status s WHERE s.name='suspended') WHERE a.id=?",
				new Object[]{auctionId}) > 0 ? true : false;
	}
	
	@Override
	public boolean activateAuction(long auctionId) {
		return dao.update("UPDATE sys.auction a SET statusid=(SELECT s.id FROM sys.auction_status s WHERE s.name='active') WHERE a.id=?",
				new Object[]{auctionId}) > 0 ? true : false;
	}
		
}

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

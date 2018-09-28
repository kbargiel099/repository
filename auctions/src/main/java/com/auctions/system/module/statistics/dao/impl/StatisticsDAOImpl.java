package com.auctions.system.module.statistics.dao.impl;

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
import com.auctions.system.module.auction_process.model.AuctionOffer;
import com.auctions.system.module.statistics.dao.StatisticsDAO;

@Repository("statisticsDAO")
public class StatisticsDAOImpl implements StatisticsDAO{
	
	private JdbcTemplate dao;
	
	@Autowired
	private DataSource dataSource;
	
	@PostConstruct
	public void init() {
		dao = new JdbcTemplate(dataSource);
	}
	
	@Override
	public List<AuctionOffer> getPurchases(long auctionId){
		return dao.query("SELECT userid,price,quantity,create_date FROM sys.transactions WHERE auctionid=? ORDER BY create_date DESC", 
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
		return dao.query("SELECT userid,price,quantity,current_date AS create_date FROM sys.transactions WHERE auctionid=? ORDER BY create_date DESC", 
				new Object[]{auctionId},new RowMapper<AuctionOffer>(){
					@Override
					public AuctionOffer mapRow(ResultSet res, int row) throws SQLException {
						return new AuctionOffer(res.getLong("userid"),res.getLong("price"),
							res.getInt("quantity"),DateFormatter.format(res.getTimestamp("create_date")));
				}
		});
	}

}

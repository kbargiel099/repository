package com.auctions.system.portlet.category.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.auctions.system.portlet.category.dao.CategoryDAO;
import com.auctions.system.portlet.category.model.AuctionDetails;
import com.auctions.system.portlet.category.model.SubCategory;
import com.auctions.system.portlet.category.model.UserDetails;
import com.auctions.system.portlet.home_page.model.AuctionPresenter;

@Repository("categoryDAO")
public class CategoryDAOImpl implements CategoryDAO{

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
	
	@Override//do przerobienia
	public List<AuctionPresenter> getBestAuctionsByCategory(String category){
		return dao.query("SELECT a.id,a.name,subject_name,image_name,subject_price FROM auction a,subject_category s,subject_subcategory sub WHERE subject_subcategory_id=sub.id AND sub.subject_category_id=s.id AND s.name= ? ", 
				new Object[]{category},new RowMapper<AuctionPresenter>(){
					@Override
					public AuctionPresenter mapRow(ResultSet res, int row) throws SQLException {
						return new AuctionPresenter(res.getInt("id"),res.getString("name"),
								res.getString("subject_name"),res.getString("image_name"),
								res.getLong("subject_price"));
				}
			});
	}
	
	@Override
	public AuctionPresenter getBestAuctionsById(int auctionId){
		return dao.queryForObject("SELECT id,name,subject_name,image_name,subject_price FROM auction WHERE id=?", 
				new Object[]{auctionId},new RowMapper<AuctionPresenter>(){
					@Override
					public AuctionPresenter mapRow(ResultSet res, int row) throws SQLException {
						return new AuctionPresenter(res.getInt("id"),res.getString("name"),
								res.getString("subject_name"),res.getString("image_name"),
								res.getLong("subject_price"));
				}
			});
	}
	
	@Override
	public AuctionDetails getAuctionDetails(int auctionId){
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH.mm.ss");
		final Timestamp endDate = new Timestamp(System.currentTimeMillis());
		
		return dao.queryForObject("SELECT id,serial_number,name,subject_name,image_name,description,create_date,subject_price"
				+ " FROM auction WHERE id=?", 
				new Object[]{auctionId},new RowMapper<AuctionDetails>(){
					@Override
					public AuctionDetails mapRow(ResultSet res, int row) throws SQLException {
						return new AuctionDetails(res.getInt("id"),res.getString("serial_number"),
								res.getString("name"),res.getString("subject_name"),res.getString("description"),
								sdf.format(res.getTimestamp("create_date")),sdf.format(endDate),res.getString("image_name"),
								10,res.getLong("subject_price"));
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
	
	@Override
	public List<SubCategory> getSubCategories(String categoryName){
		return dao.query("SELECT sub.id,sub.name FROM subject_subcategory sub,subject_category c "
				+ "WHERE c.id=sub.subject_category_id AND c.name=?", 
				new Object[]{categoryName},new RowMapper<SubCategory>(){
					@Override
					public SubCategory mapRow(ResultSet res, int row) throws SQLException {
						return new SubCategory(res.getInt("id"),res.getString("name"));
				}
		});
	}

}

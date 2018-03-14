package com.auctions.system.portlet.category.dao.impl;

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

import com.auctions.system.portlet.category.dao.CategoryDAO;
import com.auctions.system.portlet.category.model.SearchingForm;
import com.auctions.system.portlet.category.model.SubCategory;
import com.auctions.system.portlet.home_page.model.AuctionPresenter;

@Repository("categoryDAO")
public class CategoryDAOImpl implements CategoryDAO{

	private JdbcTemplate dao;
	
	@Autowired
	@Qualifier("dataSource")
	private DataSource dataSource;
	
	@PostConstruct
	public void init() {
		dao = new JdbcTemplate(dataSource);
	}
	
	@Override//do przerobienia
	public List<AuctionPresenter> getBestAuctionsByCategory(String category){
		return dao.query("SELECT a.id,a.name,i.image_name AS image_name,subject_price FROM auction a,auction_image i,category s,subcategory sub"
				+ " WHERE a.subcategory_id=sub.id AND sub.category_id=s.id AND a.id=i.auction_id AND s.name=? ", 
				new Object[]{category},new RowMapper<AuctionPresenter>(){
					@Override
					public AuctionPresenter mapRow(ResultSet res, int row) throws SQLException {
						return new AuctionPresenter(res.getInt("id"),res.getString("name"),res.getString("image_name"),
								res.getLong("subject_price"));
				}
			});
	}
	
/*	public List<AuctionPresenter> getSearchingAuctions(SearchingForm form){
		String tempSearch = "%"+form.getSearchingText()+"%";
		
		return dao.query("SELECT a.id,a.name,i.name AS image_name,i.data AS image_data,subject_price FROM auction a "
				+ "JOIN image i ON a.imageid=i.id WHERE (lower(a.name) LIKE lower(?) OR lower(a.description) LIKE lower(?)) "
				+ "AND CASE WHEN ?<>'' AND ?<>'' THEN subject_price BETWEEN CAST(? AS BIGINT) AND CAST(? AS BIGINT) "
                + "ELSE subject_price >= 0 END", 
				new Object[]{tempSearch,tempSearch,form.getMinPrice(),form.getMaxPrice(),form.getMinPrice(),form.getMaxPrice()},
				new RowMapper<AuctionPresenter>(){
					@Override
					public AuctionPresenter mapRow(ResultSet res, int row) throws SQLException {
						String imageData = new String(res.getBytes("image_data"));
						return new AuctionPresenter(res.getInt("id"),res.getString("name"),res.getString("image_name"),
								imageData,res.getLong("subject_price"));
				}
			});
	}*/
	public List<AuctionPresenter> getSearchingAuctions(SearchingForm form){
		String tempSearch = "%"+form.getSearchingText()+"%";
		
		return dao.query("SELECT a.id,a.name,i.image_name AS image_name,subject_price FROM auction a "
				+ "JOIN auction_image i ON a.id=i.auction_id WHERE lower(a.name) LIKE lower(?) "
				+ "AND CASE WHEN ?<>'' AND ?<>'' THEN subject_price BETWEEN CAST(? AS BIGINT) AND CAST(? AS BIGINT) "
                + "ELSE subject_price >= 0 END", 
				new Object[]{tempSearch,form.getMinPrice(),form.getMaxPrice(),form.getMinPrice(),form.getMaxPrice()},
				new RowMapper<AuctionPresenter>(){
					@Override
					public AuctionPresenter mapRow(ResultSet res, int row) throws SQLException {
						return new AuctionPresenter(res.getInt("id"),res.getString("name"),res.getString("image_name"),
								res.getLong("subject_price"));
				}
			});
	}
	
	@Override
	public AuctionPresenter getBestAuctionsById(int auctionId){
		return dao.queryForObject("SELECT a.id,a.name,i.name AS image_name,subject_price FROM auction a,auction_image i WHERE a.id=i.auction_id AND a.id=?", 
				new Object[]{auctionId},new RowMapper<AuctionPresenter>(){
					@Override
					public AuctionPresenter mapRow(ResultSet res, int row) throws SQLException {
						return new AuctionPresenter(res.getInt("id"),res.getString("name"),res.getString("image_name"),
								res.getLong("subject_price"));
				}
			});
	}
	
	@Override
	public List<SubCategory> getSubCategories(String categoryName){
		return dao.query("SELECT sub.id AS sub_id,c.id,sub.name FROM subcategory sub,category c "
				+ "WHERE c.id=sub.category_id AND c.name=?", 
				new Object[]{categoryName},new RowMapper<SubCategory>(){
					@Override
					public SubCategory mapRow(ResultSet res, int row) throws SQLException {
						return new SubCategory(res.getInt("sub_id"),res.getInt("id"),res.getString("name"));
				}
		});
	}

}

package com.auctions.system.portlet.messages.dao.impl;

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

import com.auctions.system.portlet.category.model.SearchingForm;
import com.auctions.system.portlet.category.model.SubCategory;
import com.auctions.system.portlet.home_page.model.AuctionPresenter;
import com.auctions.system.portlet.message_category.dao.MessageCategoryDAO;
import com.auctions.system.portlet.messages.dao.MessagesDAO;

@Repository("messagesDAO")
public class MessagesDAOImpl implements MessagesDAO{

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
		return dao.query("SELECT id,name,image_name,subject_price,category_name FROM auction_main WHERE category_name=?", 
				new Object[]{category},new RowMapper<AuctionPresenter>(){
					@Override
					public AuctionPresenter mapRow(ResultSet res, int row) throws SQLException {
						return new AuctionPresenter(res.getInt("id"),res.getString("name"),res.getString("image_name"),
								res.getLong("subject_price"));
				}
			});
	}
	
	public List<AuctionPresenter> getSearchingAuctions(SearchingForm form){
		String tempSearch = "%"+form.getSearchingText()+"%";
		
		return dao.query("SELECT id,name,image_name AS image_name,subject_price FROM auction_search "
				+ "WHERE lower(name) LIKE lower(?) AND category_name=? " + preprareQueryForSearching(form), 
				new Object[]{tempSearch,form.getCurrentCategory()},
				new RowMapper<AuctionPresenter>(){
					@Override
					public AuctionPresenter mapRow(ResultSet res, int row) throws SQLException {
						return new AuctionPresenter(res.getInt("id"),res.getString("name"),res.getString("image_name"),
								res.getLong("subject_price"));
				}
			});
	}
	
	private String preprareQueryForSearching(SearchingForm form){
		String query = "";
		boolean hasMin = form.getMinPrice().isEmpty() ? false : true;
		boolean hasMax = form.getMaxPrice().isEmpty() ? false : true;
		
		if(hasMin && hasMax){
			query += " AND subject_price BETWEEN CAST('"+ form.getMinPrice() +"' AS BIGINT) AND CAST('"+ form.getMaxPrice() +"' AS BIGINT) ";
		}else if(hasMin && !hasMax){
			query += " AND subject_price >= CAST('"+ form.getMinPrice() +"' AS BIGINT) ";
		}else if(!hasMin && hasMax){
			query =  " AND subject_price <= CAST('"+ form.getMaxPrice() +"' AS BIGINT)";
		}else{
			query += " AND subject_price >= 0 ";
		}
		return query;
		
	}
	
	@Override
	public List<AuctionPresenter> getAuctionsBySubcategory(int id){
		return dao.query("SELECT id,name,image_name,subject_price FROM auctions_by_subcategory "
				+ "WHERE subcategory_id=? ORDER BY create_date DESC", 
				new Object[]{id},
				new RowMapper<AuctionPresenter>(){
					@Override
					public AuctionPresenter mapRow(ResultSet res, int row) throws SQLException {
						return new AuctionPresenter(res.getInt("id"),res.getString("name"),res.getString("image_name"),
								res.getLong("subject_price"));
				}
			});
	}
	
	@Override
	public List<SubCategory> getSubCategories(String categoryName){
		return dao.query("SELECT sub.id AS sub_id,c.id,sub.name FROM subcategory sub "
                		+ "JOIN category c ON c.id=sub.category_id WHERE c.name=?", 
				new Object[]{categoryName},new RowMapper<SubCategory>(){
					@Override
					public SubCategory mapRow(ResultSet res, int row) throws SQLException {
						return new SubCategory(res.getInt("sub_id"),res.getInt("id"),res.getString("name"));
				}
		});
	}

}

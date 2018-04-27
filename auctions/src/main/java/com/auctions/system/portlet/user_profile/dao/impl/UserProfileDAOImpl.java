package com.auctions.system.portlet.user_profile.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.auctions.system.portlet.category.model.Category;
import com.auctions.system.portlet.category.model.SubCategory;
import com.auctions.system.portlet.home_page.model.AuctionPresenter;
import com.auctions.system.portlet.user_profile.dao.UserProfileDAO;
import com.auctions.system.portlet.user_profile.model.Auction;
import com.auctions.system.portlet.user_profile.model.AuctionGrade;
import com.auctions.system.portlet.user_profile.model.AuctionType;
import com.auctions.system.portlet.user_profile.model.UserProfileAuction;

@Repository("userProfileDAO")
public class UserProfileDAOImpl implements UserProfileDAO{

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
	public boolean isUserExist(String login, String password){
		return daoPortal.queryForObject("SELECT EXISTS (SELECT userid FROM user_ WHERE emailaddress=?)",
				new Object[]{login}, Boolean.class);
	}
	
	@Override
	public List<AuctionPresenter> getUserBoughtSubjects(long userId){
		return dao.query("SELECT auctionid,name,image_name,p.quantity,p.create_date,p.price FROM auction_main m"
						 + " JOIN auction_purchase p ON id=auctionid WHERE p.userid=?", 
				new Object[]{userId},new RowMapper<AuctionPresenter>(){
					@Override
					public AuctionPresenter mapRow(ResultSet res, int row) throws SQLException {
						return new AuctionPresenter(res.getInt("auctionid"),res.getString("name"),res.getString("image_name"),
								res.getLong("subject_price"));
					}
				
			});
	}
	
	@Override
	public List<AuctionPresenter> getUserAuctions(long userId){
		return dao.query("SELECT id,name,image_name,subject_price FROM auction_profile_view WHERE userid=?", 
				new Object[]{userId},new RowMapper<AuctionPresenter>(){
					@Override
					public AuctionPresenter mapRow(ResultSet res, int row) throws SQLException {
						return new AuctionPresenter(res.getInt("id"),res.getString("name"),res.getString("image_name"),
								res.getLong("subject_price"));
				}
			});
	}
	
	@Override
	public List<AuctionPresenter> getUserObservation(long userId){
		return dao.query("SELECT id,name,image_name,subject_price FROM auction_main"
				+ " JOIN auction_observation ON id=auctionid WHERE userid=?", 
				new Object[]{userId},new RowMapper<AuctionPresenter>(){
					@Override
					public AuctionPresenter mapRow(ResultSet res, int row) throws SQLException {
						return new AuctionPresenter(res.getInt("id"),res.getString("name"),res.getString("image_name"),
								res.getLong("subject_price"));
				}
			});
	}
	
	@Override
	public List<Category> getCategories(){
		return dao.query("SELECT id,name FROM category",
				new RowMapper<Category>(){
					@Override
					public Category mapRow(ResultSet res, int row) throws SQLException {
						return new Category(res.getInt("id"),res.getString("name"));
				}
			});
	}
	
	@Override
	public List<AuctionType> getAuctionTypes(){
		return dao.query("SELECT id,name FROM auction_type",
				new RowMapper<AuctionType>(){
					@Override
					public AuctionType mapRow(ResultSet res, int row) throws SQLException {
						return new AuctionType(res.getInt("id"),res.getString("name"));
				}
			});
	}
	
	@Override
	public List<SubCategory> getSubCategories(){
		return dao.query("SELECT c.id,sub.id AS sub_id,sub.name AS sub_name FROM category c,subcategory sub where c.id=sub.category_id",
				new RowMapper<SubCategory>(){
					@Override
					public SubCategory mapRow(ResultSet res, int row) throws SQLException {
						return new SubCategory(res.getInt("sub_id"),res.getInt("id"),
								res.getString("sub_name"));
				}
			});
	}
	
	@Override
	public boolean addAuctionGrade(long userId, AuctionGrade a){
		//Timestamp stamp = new Timestamp(0);
		
		int numberOfUpdatedRows =  dao.update("INSERT INTO auction_grade_comment(userid,auctionid,grade,comment) VALUES(?,?,?,?)",
				new Object[]{userId,a.getAuctionId(),a.getGrade(),a.getComment()});
		
		return numberOfUpdatedRows > 0 ? true : false;
	}
	
	@Override
	public boolean createAuctionVideo(long auctionId, String videoName){
		createVideoReference(auctionId,videoName);
		return true;
	}
	
	@Override//Przejrzec sql bo brakuje ważnych pól i definicje aukcji ogolnie oraz przerobic na procedure
	public boolean createUserAuction(long userId, Auction a) throws ParseException{
		
		Timestamp createDate = new Timestamp(System.currentTimeMillis());
		Timestamp endDate = new Timestamp(Long.parseLong(a.getEndDate()));
		
		boolean success = true;
	
		try {
			long auctionId = createAuction(userId, a);
			long imageId = createImageReference(auctionId, a.getImageName());
			
		} catch(Exception e) {
			e.printStackTrace();
			success = false;
		}
		
		return success;
	}
	
	private long createAuction(long userId, Auction a){
        Long createdAuctionId = (long) -1;
		Timestamp createDate = new Timestamp(System.currentTimeMillis());
		Timestamp endDate = new Timestamp(Long.parseLong(a.getEndDate()));
        
        try {
            PreparedStatement pst = dataSource.getConnection().prepareStatement("INSERT INTO auction(userid,name,description,create_date,edit_date,end_date,"
					+ "statusid,typeid,serial_number,subject_price,subject_quantity,available,subcategory_id,videoid) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
	        pst.setLong(1, userId);
	        pst.setString(2,a.getName());
	        pst.setString(3, a.getDescription());
	        pst.setTimestamp(4, createDate);
	        pst.setTimestamp(5, createDate);
	        pst.setTimestamp(6, endDate);
	        pst.setInt(7, 1);
	        pst.setInt(8, a.getAuctionTypeId());
	        pst.setLong(9, a.getSerialNumber());
	        pst.setLong(10, a.getSubjectPrice());
	        pst.setInt(11, a.getSubjectQuantity());
	        pst.setInt(12, a.getSubjectQuantity());
	        pst.setInt(13, a.getSubCategoryId());
	        pst.setLong(14, -1);
	        pst.executeUpdate();
	        
	        ResultSet keys = pst.getGeneratedKeys();
            if(keys.next())
            	createdAuctionId = keys.getLong(1);
            
            pst.close();
            
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return createdAuctionId;
	}
	
	
	private long createImageReference(long auctionId, String imageName){
        Long createdImageId = (long) -1;
        
        try {
    		PreparedStatement pst = dataSource.getConnection().prepareStatement("INSERT INTO auction_image(auction_id,image_name) VALUES(?,?)",
    				PreparedStatement.RETURN_GENERATED_KEYS);
	        pst.setLong(1, auctionId);
	        pst.setString(2, imageName);
	        pst.executeUpdate();
	        ResultSet keys = pst.getGeneratedKeys();
            if(keys.next())
            	createdImageId = keys.getLong(1);
            
            pst.close();
            
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return createdImageId;
	}
	
	private long createVideoReference(long auctionId, String videoName){
        Long createdVideoId = (long) -1;
        
        try {
    		PreparedStatement pst = dataSource.getConnection().prepareStatement("INSERT INTO auction_video(auctionid,name) VALUES(?,?)",
    				PreparedStatement.RETURN_GENERATED_KEYS);
	        pst.setLong(1, auctionId);
	        pst.setString(2, videoName);
	        pst.executeUpdate();
	        ResultSet keys = pst.getGeneratedKeys();
            if(keys.next())
            	createdVideoId = keys.getLong(1);
            
            pst.close();
            
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return createdVideoId;
	}

}

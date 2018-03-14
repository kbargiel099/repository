package com.auctions.system.portlet.user_profile.dao.impl;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Base64;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.auctions.system.module.Properties;
import com.auctions.system.portlet.category.model.Category;
import com.auctions.system.portlet.category.model.SubCategory;
import com.auctions.system.portlet.user_profile.dao.UserProfileDAO;
import com.auctions.system.portlet.user_profile.model.Auction;
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
	public List<UserProfileAuction> getUserBoughtSubjects(long userId){
		return dao.query("SELECT a.name,i.image_name AS image_name FROM auction a,auction_image i WHERE a.id=i.auction_id AND userid=?", 
				new Object[]{userId},new RowMapper<UserProfileAuction>(){
					@Override
					public UserProfileAuction mapRow(ResultSet res, int row) throws SQLException {
						return new UserProfileAuction(res.getString("name"),
								res.getString("image_name"));
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
	
	@Override//Przejrzec sql bo brakuje ważnych pól i definicje aukcji ogolnie
	public boolean createUserAuction(long userId, Auction a) throws ParseException{
		
		Timestamp createDate = new Timestamp(System.currentTimeMillis());
		Timestamp endDate = new Timestamp(Long.parseLong(a.getEndDate()));
		
		boolean success = createImage(a.getImageData(),a.getImageName());
		
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
					+ "statusid,typeid,serial_number,subject_price,subject_quantity,subcategory_id) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
	        pst.setLong(1, userId);
	        pst.setString(2,a.getName());
	        pst.setString(3, a.getDescription());
	        pst.setTimestamp(4, createDate);
	        pst.setTimestamp(5, createDate);
	        pst.setTimestamp(6, endDate);
	        pst.setInt(7, 1);
	        pst.setInt(8, 1);
	        pst.setLong(9, a.getSerialNumber());
	        pst.setLong(10, a.getSubjectPrice());
	        pst.setInt(11, a.getSubjectQuantity());
	        pst.setInt(12, a.getSubCategoryId());
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
	
	private boolean createImage(String imageData,String imageName){
		boolean success = false;
		byte[] data = Base64.getDecoder().decode((imageData));
		FileOutputStream stream;
		try {
			stream = new FileOutputStream(Properties.getImagesPath() + imageName);
		    stream.write(data);
		    success = true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return success;
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
	
	
	@Override
	public void getImages(long userId){
		
        PreparedStatement pst = null;
        FileOutputStream fos = null;
		
        String outputPath = "E:\\Szkoła\\Praca inżynierska\\Repozytorium\\repository\\auctions\\src\\main\\webapp\\images\\";
        
        try{
	        String query = "SELECT image_data, LENGTH(image_data),image_name FROM auction WHERE userid=? ";
	        pst = dataSource.getConnection().prepareStatement(query);
	        pst.setLong(1,userId);
	        
	        ResultSet result = pst.executeQuery();
	        while(result.next()){
	        	
		        fos = new FileOutputStream(outputPath + result.getString("image_name"));
		        int length = result.getInt(2);
		        byte[] buffer = result.getBytes("image_data");
		        fos.write(buffer, 0, length);
	        }
	        
            pst.close();
            fos.close();
        
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

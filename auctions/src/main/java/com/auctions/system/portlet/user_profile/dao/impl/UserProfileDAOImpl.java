package com.auctions.system.portlet.user_profile.dao.impl;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
import com.auctions.system.portlet.user_profile.dao.UserProfileDAO;
import com.auctions.system.portlet.user_profile.model.Auction;
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
		return dao.query("SELECT name,subject_name,image_name FROM auction WHERE userid=?", 
				new Object[]{userId},new RowMapper<UserProfileAuction>(){
					@Override
					public UserProfileAuction mapRow(ResultSet res, int row) throws SQLException {
						return new UserProfileAuction(res.getString("name"),res.getString("subject_name"),
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
		//final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH.mm.ss");
		//Date temp = sdf.parse(a.getEndDate());
		
		Timestamp createDate = new Timestamp(System.currentTimeMillis());
		Timestamp endDate = new Timestamp(Long.parseLong(a.getEndDate()));
		long imageId = createImage(a.getImageData(),a.getImageName());
		
		int numberOfUpdatedRows = dao.update("INSERT INTO auction(userid,name,description,imageid,create_date,edit_date,end_date,"
				+ "statusid,typeid,serial_number,subject_price,subject_quantity,subcategory_id) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)", 
				new Object[]{userId,a.getName(),a.getDescription(),imageId,createDate,createDate,endDate,
				1,1,a.getSerialNumber(),a.getSubjectPrice(),a.getSubjectQuantity(),a.getSubCategoryId()});
		
		return numberOfUpdatedRows > 0 ? true : false;
	}
	
	@Override //zrobic obsluge bledu
	public long createImage(String imageData,String imageName){
		   
        PreparedStatement pst = null;
        Long createdImageId = (long) -1;
        
        try {
        	InputStream is = new ByteArrayInputStream(imageData.getBytes());
			
	        pst = dataSource.getConnection().prepareStatement("INSERT INTO image(name,data) VALUES(?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
	        pst.setString(1, imageName);
	        pst.setBinaryStream(2, is, (int) imageData.length());
	        pst.executeUpdate();
	        ResultSet keys = pst.getGeneratedKeys();
            if(keys.next())
            	createdImageId = keys.getLong(1);
            
            pst.close();
            is.close();
            
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
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

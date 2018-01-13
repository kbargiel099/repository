package com.auctions.system.portlet.user_profile.dao.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
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

import com.auctions.system.portlet.user_profile.dao.UserProfileDAO;
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
		return dao.query("SELECT name,subject_name,image_name,image_ext FROM auction WHERE userid=?", 
				new Object[]{userId},new RowMapper<UserProfileAuction>(){
					@Override
					public UserProfileAuction mapRow(ResultSet res, int row) throws SQLException {
						return new UserProfileAuction(res.getString("name"),res.getString("subject_name"),
								res.getString("image_name"),res.getString("image_ext"));
				}
			});
	}
	
	public void createImage(){
		   
        PreparedStatement pst = null;
        FileInputStream fin = null;
        
        try {
        	File img = new File("E:\\Szkoła\\Praca inżynierska\\Repozytorium\\repository\\auctions\\images\\logo.jpg");
			fin = new FileInputStream(img);
			
	        pst = dataSource.getConnection().prepareStatement("INSERT INTO image(name,data) VALUES(?,?)");
	        pst.setString(1, "inna nazwa");
	        pst.setBinaryStream(2, fin, (int) img.length());
	        pst.executeUpdate();
	        
            pst.close();
            fin.close();
            
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        
	}
	
	@Override
	public void getImages(long userId){
		
        PreparedStatement pst = null;
        FileOutputStream fos = null;
		
        String outputPath = "E:\\Szkoła\\Praca inżynierska\\Repozytorium\\repository\\auctions\\src\\main\\webapp\\images\\";
        
        try{
	        String query = "SELECT image_data, LENGTH(image_data),image_name,image_ext FROM auction WHERE userid=? ";
	        pst = dataSource.getConnection().prepareStatement(query);
	        pst.setLong(1,userId);
	        
	        ResultSet result = pst.executeQuery();
	        while(result.next()){
	        	
		        fos = new FileOutputStream(outputPath + result.getString("image_name")+"."+result.getString("image_ext"));
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

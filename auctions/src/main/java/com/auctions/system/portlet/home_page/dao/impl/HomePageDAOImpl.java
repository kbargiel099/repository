package com.auctions.system.portlet.home_page.dao.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
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

import com.auctions.system.portlet.home_page.dao.HomePageDAO;
import com.auctions.system.portlet.home_page.model.AuctionPresenter;
import com.auctions.system.portlet.user_profile.model.UserProfileAuction;

@Repository("homePageDAO")
public class HomePageDAOImpl implements HomePageDAO{

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
		return dao.query("SELECT name,i.name AS image_name,i.data AS image_data FROM auction a,image i WHERE a.imageid=i.id AND userid=?", 
				new Object[]{userId},new RowMapper<UserProfileAuction>(){
					@Override
					public UserProfileAuction mapRow(ResultSet res, int row) throws SQLException {
						return new UserProfileAuction(res.getString("name"),
								res.getString("image_name"));
				}
			});
	}
	
	@Override
	public List<AuctionPresenter> getBestAuctionsByCategory(String category){
		return dao.query("SELECT a.id,a.name,i.image_name AS image_name,subject_price FROM auction a,category s,subcategory sub,auction_image i WHERE i.auction_id=a.id AND subcategory_id=sub.id AND sub.category_id=s.id AND s.name=?", 
				new Object[]{category},new RowMapper<AuctionPresenter>(){
					@Override
					public AuctionPresenter mapRow(ResultSet res, int row) throws SQLException {
						return new AuctionPresenter(res.getInt("id"),res.getString("name"),res.getString("image_name"),
								res.getLong("subject_price"));
				}
			});
	}
	
	@Override
	public List<AuctionPresenter> getNewestAuction(){
		return dao.query("SELECT a.id,a.name,i.image_name AS image_name,subject_price "
				+ "FROM auction a JOIN auction_image i ON i.auction_id=a.id "
				+ "ORDER BY create_date DESC LIMIT 3", 
				new Object[]{},new RowMapper<AuctionPresenter>(){
					@Override
					public AuctionPresenter mapRow(ResultSet res, int row) throws SQLException {
						return new AuctionPresenter(res.getInt("id"),res.getString("name"),res.getString("image_name"),
								res.getLong("subject_price"));
				}
			});
	}
	
	@Override
	public void createImage(){
		   
        PreparedStatement pst = null;
        FileInputStream fin = null;
        
        try {
        	File img = new File("E:\\Szkoła\\Praca inżynierska\\Repozytorium\\repository\\auctions\\images\\pc2.jpg");
			fin = new FileInputStream(img);
			
	        pst = dataSource.getConnection().prepareStatement("INSERT INTO image(name,data) VALUES(?,?)");
	        pst.setString(1, "pc2.jpg");
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

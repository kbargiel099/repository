package com.auctions.system.portlet.user_profile.dao.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
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
import com.auctions.system.portlet.user_profile.model.AuctionGrade;
import com.auctions.system.portlet.user_profile.model.AuctionType;
import com.auctions.system.portlet.user_profile.model.UserProfileAuction;

import it.sauronsoftware.jave.AudioAttributes;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.EncodingAttributes;
import it.sauronsoftware.jave.VideoAttributes;
import it.sauronsoftware.jave.VideoSize;

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
	
	@Override
	public boolean addAuctionGrade(long userId, AuctionGrade a){
		//Timestamp stamp = new Timestamp(0);
		
		int numberOfUpdatedRows =  dao.update("INSERT INTO auction_grade_comment(userid,auctionid,grade,comment) VALUES(?,?,?,?)",
				new Object[]{userId,a.getAuctionId(),a.getGrade(),a.getComment()});
		
		return numberOfUpdatedRows > 0 ? true : false;
	}
	
	@Override//Przejrzec sql bo brakuje ważnych pól i definicje aukcji ogolnie oraz przerobic na procedure
	public boolean createUserAuction(long userId, Auction a, boolean hasVideo) throws ParseException{
		
		Timestamp createDate = new Timestamp(System.currentTimeMillis());
		Timestamp endDate = new Timestamp(Long.parseLong(a.getEndDate()));
		
		boolean success = true;
	
		try {
			if(hasVideo) createVideo(a.getVideoData(),a.getVideoName());
			
			createImage(a.getImageData(),a.getImageName());
			long auctionId = createAuction(userId, a, hasVideo);
			long imageId = createImageReference(auctionId, a.getImageName());
			long videoId = createVideoReference(auctionId, a.getVideoName());
			
		} catch(Exception e) {
			e.printStackTrace();
			success = false;
		}
		
		return success;
	}
	
	private long createAuction(long userId, Auction a, boolean hasVideo){
        Long createdAuctionId = (long) -1;
		Timestamp createDate = new Timestamp(System.currentTimeMillis());
		Timestamp endDate = new Timestamp(Long.parseLong(a.getEndDate()));
        
        try {
            PreparedStatement pst = dataSource.getConnection().prepareStatement("INSERT INTO auction(userid,name,description,create_date,edit_date,end_date,"
					+ "statusid,typeid,serial_number,subject_price,subject_quantity,subcategory_id,has_video) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
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
	        pst.setBoolean(13, hasVideo);
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
		    stream.close();
		    stream = null;
		    
		    success = true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return success;
	}
	
	private boolean createVideo(final String videoData,final String videoName){
		boolean success = false;
		
		new Runnable(){
			@Override
			public void run() {
				byte[] data = Base64.getDecoder().decode((videoData));
				FileOutputStream stream;
				try {
					stream = new FileOutputStream(Properties.getVideosPath() + videoName);
				    stream.write(data);
				    stream.close();
				    stream = null;
				    //success = true;
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				createWebMVideoFile(videoData, videoName);
			}
		}.run(); 

		
		return success;
	}
	 
	private boolean createWebMVideoFile(String videoData, String videoName){
		String targetFileName = videoName.split("\\.")[0] + ".ogv";
		try {
			File source = new File(Properties.getVideosPath() + videoName);
			File target = new File(Properties.getVideosPath() + targetFileName);
			
			AudioAttributes audio = new AudioAttributes();
			audio.setCodec("libvorbis");
			audio.setBitRate(new Integer(96000));
			audio.setChannels(new Integer(2));
			audio.setSamplingRate(new Integer(441000));
			
			VideoAttributes video = new VideoAttributes();
			video.setCodec("libtheora");
/*			video.setTag("OGG");*/
			video.setBitRate(new Integer(819200));
			video.setFrameRate(new Integer(20));
			video.setSize(new VideoSize(1280, 720));
			
			EncodingAttributes attrs = new EncodingAttributes();
			attrs.setFormat("ogg");
			attrs.setAudioAttributes(audio);
			attrs.setVideoAttributes(video);
			
			Encoder encoder = new Encoder();
			System.out.println("ENCODERS");
			encoder.encode(source, target, attrs);
			byte[] bFile = Files.readAllBytes(target.toPath());

			FileOutputStream stream = new FileOutputStream(target);
		    stream.write(bFile);
		    stream.close();
			stream = null;
			
			return true;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}  catch (EncoderException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
/*	private boolean createWebMVideoFile(String videoData, String videoName){
		
		try {
			File source = new File(Properties.getVideosPath() + videoName);
			File target = new File(Properties.getVideosPath() + "target123.mp4");
			
			AudioAttributes audio = new AudioAttributes();
			audio.setCodec("libmp3lame");
			audio.setBitRate(new Integer(64000));
			audio.setChannels(new Integer(1));
			audio.setSamplingRate(new Integer(22050));
			
			VideoAttributes video = new VideoAttributes();
			video.setCodec("h264");
			video.setBitRate(new Integer(160000));
			video.setFrameRate(new Integer(15));
			video.setSize(new VideoSize(400, 300));
			
			EncodingAttributes attrs = new EncodingAttributes();
			
			attrs.setFormat("h264");
			attrs.setAudioAttributes(audio);
			attrs.setVideoAttributes(video);
			
			Encoder encoder = new Encoder();
			encoder.encode(source, target, attrs);

			byte[] bFile = Files.readAllBytes(target.toPath());
			FileOutputStream stream = new FileOutputStream(target);
		    stream.write(bFile);
		    stream.close();
			
			return true;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}  catch (EncoderException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}*/
	
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

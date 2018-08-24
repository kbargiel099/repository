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
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.auctions.system.module.auction_processing.DateFormatter;
import com.auctions.system.portlet.category.model.Category;
import com.auctions.system.portlet.category.model.SubCategory;
import com.auctions.system.portlet.home_page.model.AuctionPresenter;
import com.auctions.system.portlet.user_profile.dao.UserProfileDAO;
import com.auctions.system.portlet.user_profile.model.Auction;
import com.auctions.system.portlet.user_profile.model.AuctionGrade;
import com.auctions.system.portlet.user_profile.model.AuctionImages;
import com.auctions.system.portlet.user_profile.model.AuctionType;
import com.auctions.system.portlet.user_profile.model.BoughtPresenter;
import com.auctions.system.portlet.user_profile.model.TechnicalData;
import com.auctions.system.portlet.user_profile.model.UserMessage;
import com.auctions.system.portlet.user_profile.model.UserProfileData;
import com.auctions.system.portlet.user_profile.model.UsernameAndId;

@Repository("userProfileDAO")
public class UserProfileDAOImpl implements UserProfileDAO{

	private JdbcTemplate dao;
	
	@Autowired
	private DataSource dataSource;
	
	@PostConstruct
	public void init() {
		dao = new JdbcTemplate(dataSource);
	}
	
	@Override
	public UserProfileData getUserSimpleData(final long id){		
		return dao.queryForObject("SELECT createdate,modifieddate,emailaddress,screenname,firstname,lastname,lastlogindate,lockout FROM user_ WHERE userid=?",
			new Object[]{id},new RowMapper<UserProfileData>(){
					@Override
					public UserProfileData mapRow(ResultSet res, int row) throws SQLException {
						return new UserProfileData(id,res.getString("firstname"),res.getString("lastname"),res.getString("screenname"),
								DateFormatter.format(res.getTimestamp("createdate")),DateFormatter.format(res.getTimestamp("modifieddate")),
								res.getString("emailaddress"),DateFormatter.format(res.getTimestamp("lastlogindate")),res.getBoolean("lockout"));
				}
			});
	}
	
	@Override
	public List<AuctionPresenter> getUserBoughtSubjects(long userId){
		return dao.query("SELECT id,auctionid,name,image_name,create_date,price,payment_status_name FROM sys.user_purchase_view WHERE userid=?", 
				new Object[]{userId},new RowMapper<AuctionPresenter>(){
					@Override
					public BoughtPresenter mapRow(ResultSet res, int row) throws SQLException {
						return new BoughtPresenter(res.getLong("id"),res.getLong("auctionid"),res.getString("name"),res.getString("image_name"),
							res.getLong("price"),DateFormatter.format(res.getTimestamp("create_date")),res.getString("payment_status_name"));
					}
				
			});
	}
	
	@Override
	public List<AuctionPresenter> getUserSoldSubjects(long userId){
		return dao.query("SELECT a.id,name,images[1] AS image_name,subject_price FROM sys.auction a "
				+ "JOIN sys.transactions t ON a.id=t.auctionid "
				+ "WHERE a.userid=? "
				+ "GROUP BY a.id "
				+ "ORDER BY a.create_date", 
				new Object[]{userId},new RowMapper<AuctionPresenter>(){
					@Override
					public AuctionPresenter mapRow(ResultSet res, int row) throws SQLException {
						return new AuctionPresenter(res.getLong("id"),res.getString("name"),res.getString("image_name"),
							res.getLong("subject_price"));
					}
				
			});
	}

	@Override
	public List<AuctionPresenter> getUserAuctions(long userId){
		return dao.query("SELECT id,name,image_name,subject_price FROM sys.auction_profile_view WHERE userid=?", 
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
		return dao.query("SELECT id,name,image_name,subject_price FROM sys.auction_main"
				+ " JOIN sys.auction_observation ON id=auctionid WHERE userid=?", 
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
		return dao.query("SELECT id,name FROM sys.category",
				new RowMapper<Category>(){
					@Override
					public Category mapRow(ResultSet res, int row) throws SQLException {
						return new Category(res.getInt("id"),res.getString("name"));
				}
			});
	}
	
	@Override
	public List<AuctionType> getAuctionTypes(){
		return dao.query("SELECT id,name FROM sys.auction_type",
				new RowMapper<AuctionType>(){
					@Override
					public AuctionType mapRow(ResultSet res, int row) throws SQLException {
						return new AuctionType(res.getInt("id"),res.getString("name"));
				}
			});
	}
	
	@Override
	public List<SubCategory> getSubCategories(){
		return dao.query("SELECT c.id,sub.id AS sub_id,sub.name AS sub_name FROM sys.category c,sys.subcategory sub where c.id=sub.category_id",
				new RowMapper<SubCategory>(){
					@Override
					public SubCategory mapRow(ResultSet res, int row) throws SQLException {
						return new SubCategory(res.getInt("sub_id"),res.getInt("id"),
								res.getString("sub_name"));
				}
			});
	}
	
	@Override
	public List<TechnicalData> getTechnicalData(int id){
		return dao.query("SELECT t.id,t.name,t.value,t.type FROM sys.technical_data t JOIN sys.subcategory s ON t.id=ANY(technical_data_array) WHERE s.id=?",
			new Object[]{id},new RowMapper<TechnicalData>(){
				@Override
				public TechnicalData mapRow(ResultSet res, int row) throws SQLException {
					return new TechnicalData(res.getInt("id"),res.getString("name"),
							 res.getString("type"),res.getArray("value"));
			}
		});
	}
	
	@Override
	public boolean addAuctionGrade(long userId, AuctionGrade a){		
		return dao.update("INSERT INTO sys.auction_grade_comment(userid,auctionid,grade,comment) VALUES(?,?,?,?)",
			new Object[]{userId,a.getAuctionId(),a.getGrade(),a.getComment()}) > 0 ? true : false;
	}
	
	@Override
	public Auction getAuctionData(final long id){		
		return dao.queryForObject("SELECT a.name,serial_number,end_date,typeid,s.category_id,subcategory_id,images,description,available,subject_price,technical_data FROM sys.auction a"
				+ " JOIN sys.subcategory s ON a.subcategory_id=s.id WHERE a.id=?",
			new Object[]{id},new RowMapper<Auction>(){
					@Override
					public Auction mapRow(ResultSet res, int row) throws SQLException {
						return new Auction(id,res.getString("name"),res.getLong("serial_number"),DateFormatter.formatForView(res.getTimestamp("end_date")),
							res.getInt("typeid"),res.getInt("category_id"),res.getInt("subcategory_id"),res.getString("images"),res.getString("description"),
							res.getInt("available"),res.getLong("subject_price"),res.getString("technical_data"));
				}
			});
	}
	
	@Override
	public AuctionImages getAuctionImages(final long id){		
		return dao.queryForObject("SELECT images FROM sys.auction WHERE id=?",
			new Object[]{id},new RowMapper<AuctionImages>(){
					@Override
					public AuctionImages mapRow(ResultSet res, int row) throws SQLException {
						return new AuctionImages(id,res.getArray("images"));
				}
			});
	}
	
	@Override
	public boolean updateAuction(Auction a){		
		Timestamp editDate = new Timestamp(System.currentTimeMillis());
		Timestamp endDate = new Timestamp(Long.parseLong(a.getEndDate()));
		return dao.update("UPDATE sys.auction SET name=?,description=?,edit_date=?,end_date=?,typeid=?,subject_price=?,available=?,"
				+ "subcategory_id=?,technical_data=? WHERE id=?",
			new Object[]{a.getName(),a.getDescription(),editDate,endDate,a.getAuctionTypeId(),a.getSubjectPrice(),
					a.getSubjectQuantity(),a.getSubCategoryId(),a.getTechnicalData(),a.getId()}) > 0 ? true : false;
	}
	
	@Override
	public boolean updateAuctionImages(String images, long id){
		String[] array = images.split(",");
        try {
            PreparedStatement pst = dataSource.getConnection().prepareStatement("UPDATE sys.auction SET images=? WHERE id=?");
	        pst.setArray(1, pst.getConnection().createArrayOf("varchar",array));
	        pst.setLong(2, id);
	        pst.executeUpdate();
            
            pst.close();
            return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return false;
	}
	
	@Override
	public boolean createUserAuction(long userId, Auction a) throws ParseException{
		Timestamp createDate = new Timestamp(System.currentTimeMillis());
		Timestamp endDate = new Timestamp(Long.parseLong(a.getEndDate()));
		String[] images = a.getImageName().split(",");
        try {
            PreparedStatement pst = dataSource.getConnection().prepareStatement("INSERT INTO sys.auction(userid,name,description,create_date,edit_date,end_date,minimal_price,"
					+ "statusid,typeid,serial_number,subject_price,subject_quantity,available,subcategory_id,technical_data,video,images) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
	        pst.setLong(1, userId);
	        pst.setString(2,a.getName());
	        pst.setString(3, a.getDescription());
	        pst.setTimestamp(4, createDate);
	        pst.setTimestamp(5, createDate);
	        pst.setTimestamp(6, endDate);
	        pst.setInt(7, 0);
	        pst.setInt(8, 1);
	        pst.setInt(9, a.getAuctionTypeId());
	        pst.setLong(10, a.getSerialNumber());
	        pst.setLong(11, a.getSubjectPrice());
	        pst.setInt(12, a.getSubjectQuantity());
	        pst.setInt(13, a.getSubjectQuantity());
	        pst.setInt(14, a.getSubCategoryId());
	        pst.setString(15, a.getTechnicalData());
	        pst.setString(16, "");
	        pst.setArray(17, pst.getConnection().createArrayOf("varchar",images));
	        pst.executeUpdate();
	   
            pst.close();
            return true;
            
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return false;
	}
	
	@Override
	public boolean deleteVideo(long id){		
		return dao.update("UPDATE sys.auction SET video=? WHERE id=?",
			new Object[]{"",id}) > 0 ? true : false;
	}
	
	@Override
	public boolean makePaid(long id, int paymentMethodId){		
		return dao.update("UPDATE sys.transactions SET payment_method_id=?,payment_status_id=(SELECT id FROM sys.payment_status WHERE name='payed') WHERE id=?",
			new Object[]{paymentMethodId,id}) > 0 ? true : false;
	}

	@Override
	public List<UsernameAndId> getUsersIdsForLastConversations(final long id){		
		return dao.query("SELECT DISTINCT senderid FROM sys.chat_messages WHERE receiverid=?",
			new Object[]{id},new RowMapper<UsernameAndId>(){
					@Override
					public UsernameAndId mapRow(ResultSet res, int row) throws SQLException {
						return new UsernameAndId(res.getLong("senderid"));
				}
			});
	}
	
	@Override
	public List<UserMessage> getAllMessagesFromUser(long userId, long interlocutorId){	
		return dao.query("SELECT senderid,message,create_date FROM sys.chat_messages WHERE (senderid=? AND receiverid=?) OR (receiverid=? AND senderid=?) LIMIT 30", 
			new Object[]{userId,interlocutorId,userId,interlocutorId},new RowMapper<UserMessage>(){
			@Override
			public UserMessage mapRow(ResultSet res, int row) throws SQLException {
				return new UserMessage(res.getLong("senderid"),res.getString("message"),res.getDate("create_date"));
			}
		});
	}
}

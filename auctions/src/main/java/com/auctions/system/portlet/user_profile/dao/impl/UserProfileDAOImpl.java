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

import com.auctions.system.module.auction_processing.DateFormatter;
import com.auctions.system.portlet.category.model.Category;
import com.auctions.system.portlet.category.model.SubCategory;
import com.auctions.system.portlet.home_page.model.AuctionPresenter;
import com.auctions.system.portlet.user_profile.dao.UserProfileDAO;
import com.auctions.system.portlet.user_profile.model.Auction;
import com.auctions.system.portlet.user_profile.model.AuctionGrade;
import com.auctions.system.portlet.user_profile.model.AuctionImages;
import com.auctions.system.portlet.user_profile.model.AuctionType;
import com.auctions.system.portlet.user_profile.model.TechnicalData;
import com.auctions.system.portlet.user_profile.model.UserProfileData;

@Repository("userProfileDAO")
public class UserProfileDAOImpl implements UserProfileDAO{

	private JdbcTemplate dao;
	private JdbcTemplate daoPortal;
	
	@Autowired
	@Qualifier("dataSource")
	private DataSource dataSource;
	
	@Autowired
	@Qualifier("dataSource-lportal")
	private DataSource dataSourcePortal;
	
	@PostConstruct
	public void init() {
		dao = new JdbcTemplate(dataSource);
		daoPortal = new JdbcTemplate(dataSourcePortal);
	}
	
	@Override
	public UserProfileData getUserSimpleData(final long id){		
		return daoPortal.queryForObject("SELECT createdate,modifieddate,emailaddress,screenname,firstname,lastname,lastlogindate,lockout FROM user_ WHERE userid=?",
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
		return dao.query("SELECT auctionid,name,quantity,image_name,create_date,price FROM user_purchase_view WHERE userid=?", 
				new Object[]{userId},new RowMapper<AuctionPresenter>(){
					@Override
					public AuctionPresenter mapRow(ResultSet res, int row) throws SQLException {
						return new AuctionPresenter(res.getInt("auctionid"),res.getString("name"),res.getString("image_name"),
								res.getLong("price"));
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
	public List<TechnicalData> getTechnicalData(int id){
		return dao.query("SELECT t.id,t.name,t.value,t.type FROM technical_data t JOIN subcategory s ON t.id=ANY(technical_data_array) WHERE s.id=?",
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
		return dao.update("INSERT INTO auction_grade_comment(userid,auctionid,grade,comment) VALUES(?,?,?,?)",
			new Object[]{userId,a.getAuctionId(),a.getGrade(),a.getComment()}) > 0 ? true : false;
	}
	
	@Override
	public Auction getAuctionData(final long id){		
		return dao.queryForObject("SELECT a.name,serial_number,end_date,typeid,s.category_id,subcategory_id,images,description,available,subject_price,technical_data FROM auction a"
				+ " JOIN subcategory s ON a.subcategory_id=s.id WHERE a.id=?",
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
		return dao.queryForObject("SELECT images FROM auction WHERE id=?",
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
		return dao.update("UPDATE auction SET name=?,description=?,edit_date=?,end_date=?,typeid=?,subject_price=?,available=?,"
				+ "subcategory_id=?,technical_data=? WHERE id=?",
			new Object[]{a.getName(),a.getDescription(),editDate,endDate,a.getAuctionTypeId(),a.getSubjectPrice(),
					a.getSubjectQuantity(),a.getSubCategoryId(),a.getTechnicalData(),a.getId()}) > 0 ? true : false;
	}
	
	@Override
	public boolean createUserAuction(long userId, Auction a) throws ParseException{
	
		try {
			createAuction(userId, a);
			return true;
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	private long createAuction(long userId, Auction a){
        Long createdAuctionId = (long) -1;
		Timestamp createDate = new Timestamp(System.currentTimeMillis());
		Timestamp endDate = new Timestamp(Long.parseLong(a.getEndDate()));
		String[] images = a.getImageName().split(",");
        try {
            PreparedStatement pst = dataSource.getConnection().prepareStatement("INSERT INTO auction(userid,name,description,create_date,edit_date,end_date,minimal_price,"
					+ "statusid,typeid,serial_number,subject_price,subject_quantity,available,subcategory_id,technical_data,videoid,images) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
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
	        pst.setLong(16, -1);
	        pst.setArray(17, pst.getConnection().createArrayOf("varchar",images));
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

}

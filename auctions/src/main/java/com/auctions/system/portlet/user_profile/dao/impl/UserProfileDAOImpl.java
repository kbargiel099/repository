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

import com.auctions.system.module.DateFormatter;
import com.auctions.system.portlet.category.model.Category;
import com.auctions.system.portlet.category.model.SubCategory;
import com.auctions.system.portlet.home_page.model.AuctionPresenter;
import com.auctions.system.portlet.message_category.model.MessageCategory;
import com.auctions.system.portlet.messages.model.Message;
import com.auctions.system.portlet.user_profile.dao.UserProfileDAO;
import com.auctions.system.portlet.user_profile.model.Auction;
import com.auctions.system.portlet.user_profile.model.AuctionForGrade;
import com.auctions.system.portlet.user_profile.model.AuctionGrade;
import com.auctions.system.portlet.user_profile.model.AuctionImages;
import com.auctions.system.portlet.user_profile.model.AuctionType;
import com.auctions.system.portlet.user_profile.model.BoughtPresenter;
import com.auctions.system.portlet.user_profile.model.TechnicalData;
import com.auctions.system.portlet.user_profile.model.UserMessage;
import com.auctions.system.portlet.user_profile.model.UserProfileAddress;
import com.auctions.system.portlet.user_profile.model.UserProfileData;
import com.auctions.system.portlet.user_profile.model.UserProfileDetails;
import com.auctions.system.portlet.user_profile.model.UsernameAndId;

@Repository("userProfileDAO")
public class UserProfileDAOImpl implements UserProfileDAO {

	private JdbcTemplate dao;

	@Autowired
	private DataSource dataSource;

	@PostConstruct
	public void init() {
		dao = new JdbcTemplate(dataSource);
	}

	@Override
	public UserProfileData getUserSimpleData(final long id) {
			return dao.queryForObject(
					"SELECT createdate,modifieddate,emailaddress,screenname,firstname,lastname,lastlogindate,lockout FROM user_ WHERE userid=?",
					new Object[] { id }, new RowMapper<UserProfileData>() {
						@Override
						public UserProfileData mapRow(ResultSet res, int row) throws SQLException {
							return new UserProfileData(id, res.getString("firstname"), res.getString("lastname"),
									res.getString("screenname"), DateFormatter.format(res.getTimestamp("createdate")),
									DateFormatter.format(res.getTimestamp("modifieddate")), res.getString("emailaddress"),
									DateFormatter.format(res.getTimestamp("lastlogindate")), res.getBoolean("lockout"));
						}
			});
	}
	
	@Override
	public UserProfileDetails getUserDataForSettings(final long id) {
		String query = "SELECT u.firstname,u.lastname,p.number_ FROM user_ u "
					 + "JOIN phone p ON p.userid=u.userid WHERE u.userid=? "
					 + "ORDER BY p.createdate DESC LIMIT 1";
		
			return dao.queryForObject(query, new Object[] { id }, 
					new RowMapper<UserProfileDetails>() {
						@Override
						public UserProfileDetails mapRow(ResultSet res, int row) throws SQLException {
							return new UserProfileDetails(res.getString("firstname"), res.getString("lastname"), res.getString("number_"));
						}
			});
	}
	
	@Override
	public UserProfileAddress getUserAddressForSettings(final long id) {
		String query = "SELECT street1,street2,city,zip FROM address WHERE userid=? ORDER BY createdate DESC LIMIT 1";
		
			return dao.queryForObject(query, new Object[] { id }, 
					new RowMapper<UserProfileAddress>() {
						@Override
						public UserProfileAddress mapRow(ResultSet res, int row) throws SQLException {
							return new UserProfileAddress(res.getString("city"), res.getString("street1"),
									res.getString("street2"), res.getString("zip"));
						}
			});
	}

	@Override
	public List<AuctionPresenter> getUserBoughtSubjects(long userId) {
		String query = "SELECT id,auctionid,name,image_name,create_date,price,payment_status_name FROM sys.user_purchase_view WHERE userid=? "
					 + "UNION ALL "
					 + "SELECT id,auctionid,name,image_name,create_date,price,payment_status_name FROM sys.user_won_auctions_view WHERE userid=? "
					 + "ORDER BY create_date DESC";
		
		return dao.query(query, new Object[] { userId, userId }, 
				new RowMapper<AuctionPresenter>() {
					@Override
					public BoughtPresenter mapRow(ResultSet res, int row) throws SQLException {
						return new BoughtPresenter(res.getLong("id"), res.getLong("auctionid"), res.getString("name"),
								res.getString("image_name"), res.getLong("price"),
								DateFormatter.format(res.getTimestamp("create_date")),
								res.getString("payment_status_name"));
					}

				});
	}

	@Override
	public List<AuctionPresenter> getUserSoldSubjects(long userId) {
		String query = "SELECT a.id,a.name,images[1] AS image_name,subject_price,a.create_date FROM sys.auction a " 
					 + "JOIN sys.user_purchase_view t ON a.id=t.auctionid WHERE a.userid=? GROUP BY a.id "
				     + "UNION ALL "
				     + "SELECT a.id,a.name,images[1] AS image_name,subject_price,a.create_date FROM sys.auction a "
				     + "JOIN sys.user_won_auctions_view w ON a.id=w.auctionid WHERE a.userid=? GROUP BY a.id "
				     + "ORDER BY create_date DESC";
		
		return dao.query(query, new Object[] { userId, userId },
				new RowMapper<AuctionPresenter>() {
					@Override
					public AuctionPresenter mapRow(ResultSet res, int row) throws SQLException {
						return new AuctionPresenter(res.getLong("id"), res.getString("name"),
								res.getString("image_name"), res.getLong("subject_price"));
					}

				});
	}

	@Override
	public List<AuctionPresenter> getUserAuctions(long userId) {
		return dao.query("SELECT id,name,image_name,subject_price FROM sys.auction_profile_view WHERE userid=?",
				new Object[] { userId }, new RowMapper<AuctionPresenter>() {
					@Override
					public AuctionPresenter mapRow(ResultSet res, int row) throws SQLException {
						return new AuctionPresenter(res.getInt("id"), res.getString("name"),
								res.getString("image_name"), res.getLong("subject_price"));
					}
				});
	}

	@Override
	public List<AuctionPresenter> getUserObservation(long userId) {
		return dao.query(
				"SELECT id,name,image_name,subject_price FROM sys.auction_main"
						+ " JOIN sys.auction_observation o ON id=auctionid WHERE o.userid=?",
				new Object[] { userId }, new RowMapper<AuctionPresenter>() {
					@Override
					public AuctionPresenter mapRow(ResultSet res, int row) throws SQLException {
						return new AuctionPresenter(res.getInt("id"), res.getString("name"),
								res.getString("image_name"), res.getLong("subject_price"));
					}
				});
	}

	@Override
	public List<Category> getCategories() {
		return dao.query("SELECT id,name FROM sys.category WHERE parent_category_id IS NULL", new RowMapper<Category>() {
			@Override
			public Category mapRow(ResultSet res, int row) throws SQLException {
				return new Category(res.getInt("id"), res.getString("name"));
			}
		});
	}

	@Override
	public List<AuctionType> getAuctionTypes() {
		return dao.query("SELECT id,name FROM sys.auction_type", new RowMapper<AuctionType>() {
			@Override
			public AuctionType mapRow(ResultSet res, int row) throws SQLException {
				return new AuctionType(res.getInt("id"), res.getString("name"));
			}
		});
	}

	@Override
	public List<SubCategory> getSubCategories() {
		return dao.query("SELECT c.id AS sub_id,c.parent_category_id AS id,c.name AS sub_name FROM sys.category c "
					   + "INNER JOIN sys.category p ON c.parent_category_id=p.id",
				new RowMapper<SubCategory>() {
					@Override
					public SubCategory mapRow(ResultSet res, int row) throws SQLException {
						return new SubCategory(res.getInt("sub_id"), res.getInt("id"), res.getString("sub_name"));
					}
				});
	}

	@Override
	public List<TechnicalData> getTechnicalData(int id) {
		String query = "SELECT t.id,t.name,t.value,t.type "
				     + "FROM sys.technical_data t "
				     + "JOIN sys.technical_data_category tc on tc.technical_data_id=t.id "
				     + "JOIN sys.category s ON tc.category_id=s.id WHERE s.id=?";
				
		return dao.query(query, new Object[] { id }, 
				new RowMapper<TechnicalData>() {
					@Override
					public TechnicalData mapRow(ResultSet res, int row) throws SQLException {
						return new TechnicalData(res.getInt("id"), res.getString("name"), res.getString("type"),
								res.getArray("value"));
					}
				});
	}

	@Override
	public boolean addAuctionGrade(long userId, AuctionGrade a) {
		Timestamp current = new Timestamp(System.currentTimeMillis());
		return dao.update("INSERT INTO sys.auction_grade(userid,auctionid,grade,comment,create_date) VALUES(?,?,?,?,?)",
				new Object[] { userId, a.getAuctionId(), a.getGrade(), a.getComment(), current }) > 0;
	}

	@Override
	public Auction getAuctionData(final long id) { 
		String query = "SELECT a.name,serial_number,end_date,typeid,s.parent_category_id AS category_id,a.category_id AS subcategory_id,images,description,available,"
				     + "subject_price,technical_data,minimal_price "
				     + "FROM sys.auction a "
				     + "JOIN sys.category s ON a.category_id=s.id WHERE a.id=?";
		
		return dao.queryForObject(query, new Object[] { id },
				new RowMapper<Auction>() {
					@Override
					public Auction mapRow(ResultSet res, int row) throws SQLException {
						return new Auction(id, res.getString("name"), res.getLong("serial_number"),
								DateFormatter.formatForView(res.getTimestamp("end_date")), res.getLong("minimal_price"), res.getInt("typeid"),
								res.getInt("category_id"), res.getInt("subcategory_id"), res.getString("images"),
								res.getString("description"), res.getInt("available"), res.getLong("subject_price"),
								res.getString("technical_data"));
					}
				});
	}

	@Override
	public AuctionImages getAuctionImages(final long id) {
		return dao.queryForObject("SELECT images FROM sys.auction WHERE id=?", new Object[] { id },
				new RowMapper<AuctionImages>() {
					@Override
					public AuctionImages mapRow(ResultSet res, int row) throws SQLException {
						return new AuctionImages(id, res.getArray("images"));
					}
				});
	}

	@Override
	public boolean updateAuction(Auction a) {
		Timestamp editDate = new Timestamp(System.currentTimeMillis());
		Timestamp endDate = new Timestamp(Long.parseLong(a.getEndDate()));
		return dao.update(
				"UPDATE sys.auction SET name=?,description=?,edit_date=?,end_date=?,typeid=?,subject_price=?,available=?,"
						+ "category_id=?,technical_data=?,minimal_price=? WHERE id=?",
				new Object[] { a.getName(), a.getDescription(), editDate, endDate, a.getAuctionTypeId(),
						a.getSubjectPrice(), a.getSubjectQuantity(), a.getSubCategoryId(), a.getTechnicalData(),
						a.getMinimalPrice(), a.getId() }) > 0;
	}

	@Override
	public boolean updateAuctionImages(String images, long id) {
		String[] array = images.split(",");
		try {
			PreparedStatement pst = dataSource.getConnection()
					.prepareStatement("UPDATE sys.auction SET images=? WHERE id=?");
			pst.setArray(1, pst.getConnection().createArrayOf("varchar", array));
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
	public boolean createUserAuction(long userId, Auction a) throws ParseException {
		Timestamp createDate = new Timestamp(System.currentTimeMillis());
		Timestamp endDate = new Timestamp(Long.parseLong(a.getEndDate()));
		String[] images = a.getImageName().split(",");
		try {
			PreparedStatement pst = dataSource.getConnection().prepareStatement(
					"INSERT INTO sys.auction(userid,name,description,create_date,edit_date,end_date,minimal_price,"
							+ "statusid,typeid,serial_number,subject_price,subject_quantity,available,category_id,technical_data,video,images) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			pst.setLong(1, userId);
			pst.setString(2, a.getName());
			pst.setString(3, a.getDescription());
			pst.setTimestamp(4, createDate);
			pst.setTimestamp(5, createDate);
			pst.setTimestamp(6, endDate);
			pst.setLong(7, a.getMinimalPrice());
			pst.setInt(8, 1);
			pst.setInt(9, a.getAuctionTypeId());
			pst.setLong(10, a.getSerialNumber());
			pst.setLong(11, a.getSubjectPrice());
			pst.setInt(12, a.getSubjectQuantity());
			pst.setInt(13, a.getSubjectQuantity());
			pst.setInt(14, a.getSubCategoryId());
			pst.setString(15, a.getTechnicalData());
			pst.setString(16, "");
			pst.setArray(17, pst.getConnection().createArrayOf("varchar", images));
			pst.executeUpdate();

			pst.close();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteVideo(long id) {
		return dao.update("UPDATE sys.auction SET video=? WHERE id=?", new Object[] { "", id }) > 0;
	}

	@Override
	public boolean makePaid(long id, int paymentMethodId) {
		return dao.update(
				"UPDATE sys.purchase_transactions SET payment_method_id=?,payment_status_id=(SELECT id FROM sys.payment_status WHERE name='payed') WHERE id=?",
				new Object[] { paymentMethodId, id }) > 0;
	}

	@Override
	public List<UsernameAndId> getUsersIdsForLastConversations(final long id) {
		return dao.query("SELECT DISTINCT senderid FROM sys.chat_messages WHERE receiverid=?", new Object[] { id },
				new RowMapper<UsernameAndId>() {
					@Override
					public UsernameAndId mapRow(ResultSet res, int row) throws SQLException {
						return new UsernameAndId(res.getLong("senderid"));
					}
				});
	}

	@Override
	public List<UserMessage> getAllMessagesFromUser(long userId, long interlocutorId) {
		return dao.query(
				"SELECT senderid,message,create_date FROM sys.chat_messages WHERE (senderid=? AND receiverid=?) OR (receiverid=? AND senderid=?)"
				+ " ORDER BY create_date ASC LIMIT 30",
				new Object[] { userId, interlocutorId, userId, interlocutorId }, new RowMapper<UserMessage>() {
					@Override
					public UserMessage mapRow(ResultSet res, int row) throws SQLException {
						return new UserMessage(res.getLong("senderid"), res.getString("message"),
								DateFormatter.format(res.getTimestamp("create_date")));
					}
				});
	}

	@Override
	public List<MessageCategory> getMessageCategories() {
		return dao.query("SELECT id,user_id,name,create_date,activated FROM sys.message_category",
				new RowMapper<MessageCategory>() {
					@Override
					public MessageCategory mapRow(ResultSet res, int row) throws SQLException {
						return new MessageCategory(res.getInt("id"), res.getString("name"),
								DateFormatter.format(res.getTimestamp("create_date")), res.getLong("user_id"),
								res.getBoolean("activated"));
					}
				});
	}

	@Override
	public List<Message> getMessages() {
		return dao.query(
				"SELECT id,message_category_id,title,text,create_date,edit_date,user_id,is_sent FROM sys.message ORDER BY create_date DESC",
				new RowMapper<Message>() {
					@Override
					public Message mapRow(ResultSet res, int row) throws SQLException {
						return new Message(res.getInt("id"), res.getInt("message_category_id"), res.getString("title"),
								res.getString("text"), DateFormatter.format(res.getTimestamp("create_date")),
								DateFormatter.format(res.getTimestamp("create_date")), res.getLong("user_id"),
								res.getBoolean("is_sent"));
					}
				});
	}

	@Override
	public List<AuctionForGrade> getAuctionsForGrade(long userId) {
		String query = "SELECT DISTINCT auctionid,(SELECT name FROM sys.auction a WHERE a.id=auctionid) AS name "
					 + "FROM sys.user_purchase_view WHERE userid=? "
				     + "UNION ALL "
				     + "SELECT DISTINCT auctionid,(SELECT name FROM sys.auction a WHERE a.id=auctionid) AS name "
				     + "FROM sys.user_won_auctions_view WHERE userid=?";
		
		return dao.query(query, new Object[] { userId, userId }, 
				new RowMapper<AuctionForGrade>() {
					@Override
					public AuctionForGrade mapRow(ResultSet res, int row) throws SQLException {
						return new AuctionForGrade(res.getLong("auctionid"), res.getString("name"));
					}
				});
	}
}

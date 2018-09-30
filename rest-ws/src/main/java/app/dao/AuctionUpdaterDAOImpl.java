package app.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import model.MailProperties;
import model.MessageRequestForm;
import model.RequestForm;

@Repository("auctionUpdaterDAO")
public class AuctionUpdaterDAOImpl implements AuctionUpdaterDAO{
	
	private JdbcTemplate dao;

	@Autowired
	private DataSource lportal;
	
	@PostConstruct
	public void init() {
		dao = new JdbcTemplate(lportal);
	}

	@Override
	public boolean proceedOffer(RequestForm form, String id) {
		return dao.update("INSERT INTO sys.auction_process(userid,auctionid,price,quantity,create_date) VALUES(?,?,?,?,current_timestamp)",
				new Object[]{Long.parseLong(form.getUserId()), Long.parseLong(id), Long.parseLong(form.getPrice()), Integer.parseInt(form.getQuantity())}) > 0;
	}
	
	@Override
	public boolean proceedPurchase(RequestForm form, String id) {
		try{
			return dao.update("INSERT INTO sys.transactions(userid,auctionid,price,quantity,create_date,payment_status_id) VALUES(?,?,?,?,current_timestamp,default)",
					new Object[]{Long.parseLong(form.getUserId()), Long.parseLong(id), Long.parseLong(form.getPrice()), Integer.parseInt(form.getQuantity())}) > 0;
		} catch(UncategorizedSQLException e){
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public List<MailProperties> markAuctionsFinished() throws SQLException {
		return dao.query("UPDATE sys.auction a SET statusid=2 WHERE end_date<current_date AND statusid<>2 RETURNING name,"
				+ "(SELECT u.emailaddress FROM user_ u WHERE u.userid=a.userid)",
				new RowMapper<MailProperties>(){
			@Override
			public MailProperties mapRow(ResultSet res, int row) throws SQLException {
				return new MailProperties(res.getString("emailaddress"),res.getString("name"));
			}
		} );
	}
	
	@Override
	public List<MailProperties> getMailProperties(long auctionId, long userId) throws SQLException {
		return dao.query("SELECT emailaddress,name FROM sys.auction_process p "
			+ "JOIN sys.auction a ON a.id=auctionid "
			+ "JOIN user_ u ON u.userid=p.userid "
			+ "WHERE auctionid=? AND p.userid<>? "
			+ "ORDER BY price DESC LIMIT 1", new Object[]{auctionId, userId},
				new RowMapper<MailProperties>(){
			@Override
			public MailProperties mapRow(ResultSet res, int row) throws SQLException {
				return new MailProperties(res.getString("emailaddress"),res.getString("name"));
			}
		} );
	}
	
	@Override
	public List<MailProperties> getMailPropertiesPurchase(long auctionId) throws SQLException {
		return dao.query("SELECT emailaddress,name FROM sys.auction a "
			+ "JOIN user_ u ON u.userid=a.userid WHERE a.id=?", new Object[]{auctionId},
				new RowMapper<MailProperties>(){
			@Override
			public MailProperties mapRow(ResultSet res, int row) throws SQLException {
				return new MailProperties(res.getString("emailaddress"),res.getString("name"));
			}
		} );
	}

	@Override
	public boolean createChatMessage(MessageRequestForm form){
		Timestamp current = new Timestamp(System.currentTimeMillis());
		return dao.update("INSERT INTO sys.chat_messages(senderid,receiverid,message,create_date,is_read) VALUES(?,?,?,?,?)",
				new Object[]{Long.parseLong(form.getSenderId()), Long.parseLong(form.getReceiverId()),
						form.getMessage(), current, false}) > 0;
	}

}

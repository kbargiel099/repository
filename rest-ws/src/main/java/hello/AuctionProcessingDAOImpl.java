package hello;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import model.MailProperties;
import module.DataSourceProvider;

@Repository("auctionProcessingDAO")
public class AuctionProcessingDAOImpl implements AuctionProcessingDAO{
	
	private JdbcTemplate dao;

	private DataSource lportal =  DataSourceProvider.dataSource("lportal");
	
	@PostConstruct
	public void init() {
		dao = new JdbcTemplate(lportal);
	}

	@Override
	public boolean proceedOffer(long userId, long auctionId, long price, int quantity) {
		return dao.update("INSERT INTO sys.auction_process(userid,auctionid,price,quantity,create_date) VALUES(?,?,?,?,current_timestamp)",
				new Object[]{userId,auctionId,price,quantity}) > 0;
	}
	
	@Override
	public boolean proceedPurchase(long userId, long auctionId, long price, int quantity) {
		try{
			return dao.update("INSERT INTO sys.transactions(userid,auctionid,price,quantity,create_date,payment_status_id) VALUES(?,?,?,?,current_timestamp,default)",
					new Object[]{userId,auctionId,price,quantity}) > 0;
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
	public boolean createChatMessage(long senderId,long receiverId, String message, Date date){
		return dao.update("INSERT INTO sys.chat_messages(senderid,receiverid,message,create_date,is_read) VALUES(?,?,?,?,?)",
				new Object[]{senderId,receiverId,message,date,false}) > 0;
	}

}

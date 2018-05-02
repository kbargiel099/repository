package hello;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("auctionProcessingDAO")
public class AuctionProcessingDAOImpl implements AuctionProcessingDAO{

	private JdbcTemplate dao;

	@Autowired
	@Qualifier("dataSource")
	private DataSource dataSource;
	
	@PostConstruct
	public void init() {
		dao = new JdbcTemplate(dataSource);
	}

	@Override
	public boolean proceedOffer(long userId, long auctionId, long price, int quantity) {
		int numberOfInsertedRows = dao.update("INSERT INTO auction_process(userid,auctionid,price,quantity,create_date) VALUES(?,?,?,?,current_timestamp)",
				new Object[]{userId,auctionId,price,quantity});
		return numberOfInsertedRows > 0 ? true : false;
	}
	
	@Override
	public boolean proceedPurchase(long userId, long auctionId, long price, int quantity) {
		int numberOfInsertedRows;
		try{
			numberOfInsertedRows = dao.update("INSERT INTO auction_purchase(userid,auctionid,price,quantity,create_date) VALUES(?,?,?,?,current_timestamp)",
					new Object[]{userId,auctionId,price,quantity});
		} catch(UncategorizedSQLException e){
			e.printStackTrace();
			return false;
		}
		return numberOfInsertedRows > 0 ? true : false;
	}
	
	@Override
	public boolean markAuctionsEnded() {
		int numberOfInsertedRows = dao.update("UPDATE auction SET statusid=2 WHERE end_date < current_date");
		return numberOfInsertedRows > 0 ? true : false;
	}
	
	@Override
	public boolean createChatMessage(long senderId,long receiverId, String message, Date date){
		int numberOfInsertedRows = dao.update("INSERT INTO chat_messages(senderid,receiverid,message,create_date,is_read) VALUES(?,?,?,?,?)",
				new Object[]{senderId,receiverId,message,date,false});
		return numberOfInsertedRows > 0 ? true : false;
	}

}

package hello;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import model.MailProperties;

public interface AuctionProcessingDAO {
	
	public boolean proceedOffer(long userId, long auctionId, long price, int quantity);
	
	public boolean proceedPurchase(long userId, long auctionId, long price, int quantity);
	
	public List<MailProperties> markAuctionsFinished() throws SQLException;
	
	public List<MailProperties> getMailProperties(long auctionId, long userId) throws SQLException;
	
	public boolean createChatMessage(long senderId,long receiverId, String message, Date date);
}

package hello;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import model.MailProperties;

public interface AuctionProcessingService {

	public boolean proceedOffer(RequestForm form, String id);
	
	public boolean proceedPurchase(RequestForm form, String id);
	
	public List<MailProperties> markAuctionsFinished();
	
	public List<MailProperties> getMailProperties(long auctionId, long userId) throws SQLException;
	
	public List<MailProperties> getMailPropertiesPurchase(long auctionId) throws SQLException;
	
	public boolean createChatMessage(long senderId,long receiverId, String message, Date date);

}

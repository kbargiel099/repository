package app.dao;

import java.sql.SQLException;
import java.util.List;

import model.MailProperties;
import model.MessageRequestForm;
import model.RequestForm;

public interface AuctionUpdaterDAO {
	
	public boolean proceedOffer(RequestForm form, String id);
	
	public boolean proceedPurchase(RequestForm form, String id);
	
	public List<MailProperties> markAuctionsFinished() throws SQLException;
	
	public List<MailProperties> getMailProperties(long auctionId, long userId) throws SQLException;
	
	public List<MailProperties> getMailPropertiesPurchase(long auctionId) throws SQLException;
	
	public boolean createChatMessage(MessageRequestForm form);
}

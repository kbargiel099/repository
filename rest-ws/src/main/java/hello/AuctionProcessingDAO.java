package hello;

import java.util.Date;

public interface AuctionProcessingDAO {
	
	public boolean proceedOffer(long userId, long auctionId, long price, int quantity);
	
	public boolean proceedPurchase(long userId, long auctionId, long price, int quantity);
	
	public boolean markAuctionsEnded();
	
	public boolean createChatMessage(long senderId,long receiverId, String message, Date date);
}

package hello;

public interface AuctionProcessingDAO {
	
	public boolean proceedOffer(long userId, long auctionId, long price, int quantity);
	
	public boolean proceedPurchase(long userId, long auctionId, long price, int quantity);
	
	public boolean markAuctionsEnded();
}

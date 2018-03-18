package hello;

public interface AuctionProcessingDAO {
	
	public boolean insertData(long userId, long auctionId, long price);
}

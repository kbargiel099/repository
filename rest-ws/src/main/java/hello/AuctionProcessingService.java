package hello;

public interface AuctionProcessingService {

	public boolean insertData(long userId, long auctionId, long price, int quantity);

}

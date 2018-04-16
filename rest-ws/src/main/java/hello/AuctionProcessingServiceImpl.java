package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("auctionProcessingService")
public class AuctionProcessingServiceImpl implements AuctionProcessingService{

	@Autowired
	AuctionProcessingDAO dataSource;
	
	@Override
	public boolean proceedOffer(long userId, long auctionId, long price, int quantity){
		return dataSource.proceedOffer(userId, auctionId,price,quantity);
	}
	
	@Override
	public boolean proceedPurchase(long userId, long auctionId, long price, int quantity){
		return dataSource.proceedPurchase(userId, auctionId, price, quantity);
	}
	
	@Override
	public boolean markAuctionsEnded(){
		return dataSource.markAuctionsEnded();
	}
	
}

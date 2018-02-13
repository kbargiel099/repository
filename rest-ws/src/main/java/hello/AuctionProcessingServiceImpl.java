package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("auctionProcessingService")
public class AuctionProcessingServiceImpl implements AuctionProcessingService{

	@Autowired
	AuctionProcessingDAO dataSource;
	
	@Override
	public boolean insertData(long userId, long auctionId) {
		return dataSource.insertData(userId, auctionId);
	}
	
}

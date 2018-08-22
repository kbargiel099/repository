package hello;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.MailProperties;

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
	public List<MailProperties> markAuctionsFinished(){
		try {
			return dataSource.markAuctionsFinished();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new ArrayList<MailProperties>();
	}
	
	@Override
	public List<MailProperties> getMailProperties(long auctionId, long userId){
		try {
			return dataSource.getMailProperties(auctionId, userId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new ArrayList<MailProperties>();
	}
	
	@Override
	public boolean createChatMessage(long senderId,long receiverId, String message, Date date){
		return dataSource.createChatMessage(senderId, receiverId, message, date);
	}
	
}

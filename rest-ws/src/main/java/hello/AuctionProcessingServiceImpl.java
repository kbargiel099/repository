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
	public boolean proceedOffer(RequestForm form, String id){
		return dataSource.proceedOffer(form, id);
	}
	
	@Override
	public boolean proceedPurchase(RequestForm form, String id){
		return dataSource.proceedPurchase(form, id);
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
	
	public List<MailProperties> getMailPropertiesPurchase(long auctionId){
		try {
			return dataSource.getMailPropertiesPurchase(auctionId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new ArrayList<MailProperties>();
	}
	
	@Override
	public boolean createChatMessage(MessageRequestForm form){
		return dataSource.createChatMessage(form);
	}
	
}

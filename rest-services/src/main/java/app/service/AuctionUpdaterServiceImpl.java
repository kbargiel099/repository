package app.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.dao.AuctionUpdaterDAO;
import model.MailProperties;
import model.MessageRequestForm;
import model.RequestForm;

@Service("auctionUpdaterService")
public class AuctionUpdaterServiceImpl implements AuctionUpdaterService{

	@Autowired
	AuctionUpdaterDAO dataSource;
	
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
	public List<MailProperties> getMailProperties(String auctionId, String userId) throws SQLException {
		return getMailProperties(Long.parseLong(auctionId), Long.parseLong(userId));
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
	public List<MailProperties> getMailPropertiesPurchase(String auctionId) throws SQLException {
		return getMailPropertiesPurchase(Long.parseLong(auctionId));
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

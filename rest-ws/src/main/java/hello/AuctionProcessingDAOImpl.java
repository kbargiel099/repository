package hello;

import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import model.MailProperties;
import module.DataSourceProvider;

@Repository("auctionProcessingDAO")
public class AuctionProcessingDAOImpl implements AuctionProcessingDAO{

	private JdbcTemplate dao;

	private DataSource auctions = DataSourceProvider.dataSource("auctions");
	private DataSource lportal =  DataSourceProvider.dataSource("lportal");
	
	@PostConstruct
	public void init() {
		dao = new JdbcTemplate(auctions);
	}

	@Override
	public boolean proceedOffer(long userId, long auctionId, long price, int quantity) {
		int numberOfInsertedRows = dao.update("INSERT INTO auction_process(userid,auctionid,price,quantity,create_date) VALUES(?,?,?,?,current_timestamp)",
				new Object[]{userId,auctionId,price,quantity});
		return numberOfInsertedRows > 0 ? true : false;
	}
	
	@Override
	public boolean proceedPurchase(long userId, long auctionId, long price, int quantity) {
		int numberOfInsertedRows;
		try{
			numberOfInsertedRows = dao.update("INSERT INTO transactions(userid,auctionid,price,quantity,create_date,payment_status_id) VALUES(?,?,?,?,current_timestamp,default)",
					new Object[]{userId,auctionId,price,quantity});
		} catch(UncategorizedSQLException e){
			e.printStackTrace();
			return false;
		}
		return numberOfInsertedRows > 0 ? true : false;
	}
	
	@Override
	public List<MailProperties> markAuctionsFinished() throws SQLException {
		List<Long> usersIds = new ArrayList<Long>();
		List<MailProperties> finishedAuctions =  dao.query("UPDATE auction SET statusid=2 WHERE end_date < current_date RETURNING name,userid",
				new RowMapper<MailProperties>(){
			@Override
			public MailProperties mapRow(ResultSet res, int row) throws SQLException {
				usersIds.add(res.getLong("userid"));
				return new MailProperties(res.getString("name"));
			}
		} );
		return getUsersMail(finishedAuctions, usersIds);
	}
	
	private List<MailProperties> getUsersMail(List<MailProperties> list, List<Long> usersIds) throws SQLException {
		PreparedStatement statement = lportal.getConnection().prepareStatement("SELECT emailaddress FROM user_  WHERE userid IN ("+ prepareIn(usersIds) +")");
		prepareValues(statement, usersIds);
		ResultSet rs = statement.executeQuery();
		
		for(int i=0;rs.next();i++){
			list.get(i).setEmailAddress(rs.getString("emailaddress"));
		}
		return list;
	}

	@Override
	public boolean createChatMessage(long senderId,long receiverId, String message, Date date){
		int numberOfInsertedRows = dao.update("INSERT INTO chat_messages(senderid,receiverid,message,create_date,is_read) VALUES(?,?,?,?,?)",
				new Object[]{senderId,receiverId,message,date,false});
		return numberOfInsertedRows > 0 ? true : false;
	}
	
	private String prepareIn(List<Long> list){
		StringBuilder builder = new StringBuilder();
		for(Long item : list){
		    builder.append("?,");
		}
		return builder.deleteCharAt( builder.length() -1 ).toString();
	}
	
	private void prepareValues(PreparedStatement st, List<Long> list) throws SQLException{
		for(int i=0;i<list.size();i++){
		    st.setLong(i+1, list.get(i));
		}
	}

}

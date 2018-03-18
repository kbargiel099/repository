package hello;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("auctionProcessingDAO")
public class AuctionProcessingDAOImpl implements AuctionProcessingDAO{

	private JdbcTemplate dao;

	@Autowired
	@Qualifier("dataSource")
	private DataSource dataSource;
	
	@PostConstruct
	public void init() {
		dao = new JdbcTemplate(dataSource);
	}

	@Override
	public boolean insertData(long userId, long auctionId, long price) {
		int numberOfInsertedRows = dao.update("INSERT INTO auction_process(userid,auctionid,price,create_date) VALUES(?,?,?,current_timestamp)",
				new Object[]{userId,auctionId,price});
		return numberOfInsertedRows > 0 ? true : false;
	}
	
	/*@Override
	public boolean isUserExist(String login, String password){
		return daoPortal.queryForObject("SELECT EXISTS (SELECT userid FROM user_ WHERE emailaddress=?)",
				new Object[]{login}, Boolean.class);
	}*/
	

}

package com.auctions.system.portlet.nav_menu.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.auctions.system.portlet.nav_menu.dao.NavDAO;
import com.auctions.system.portlet.nav_menu.model.MessageAndDate;
import com.auctions.system.portlet.nav_menu.model.UserData;

@Repository("navDAO")
public class NavDAOImpl implements NavDAO{

	private JdbcTemplate dao;
	
	@Autowired
	@Qualifier("dataSource")
	private DataSource dataSource;
	
	@PostConstruct
	public void init() {
	 dao = new JdbcTemplate(dataSource);
	}
	
	@Override
	public List<UserData> getSenderIdsToNotify(long receiverId){	
		return dao.query("SELECT DISTINCT senderid FROM chat_messages WHERE receiverid=? AND is_read=?", 
				new Object[]{receiverId,false},new RowMapper<UserData>(){
			@Override
			public UserData mapRow(ResultSet res, int row) throws SQLException {
				return new UserData(res.getLong("senderid"));
			}
		});
	}
	
	@Override
	public List<MessageAndDate> getUnreadMessagesFromUser(long senderId, long receiverId){	
		return dao.query("SELECT message,create_date FROM chat_messages WHERE senderid=? AND receiverid=? AND is_read=?", 
				new Object[]{senderId,receiverId,false},new RowMapper<MessageAndDate>(){
			@Override
			public MessageAndDate mapRow(ResultSet res, int row) throws SQLException {
				return new MessageAndDate(res.getString("message"),res.getDate("create_date"));
			}
		});
	}
	
	@Override
	public boolean markMessagesAsRead(long senderId, long receiverId){	
		int numberOfUpdatedRows =  dao.update("UPDATE chat_messages SET is_read=? WHERE senderid=? AND receiverid=? AND is_read=?", 
				new Object[]{true,senderId,receiverId,false});
		return numberOfUpdatedRows > 0 ? true : false;
	}
	

}

package com.auctions.system.portlet.nav_menu.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.auctions.system.module.DateFormatter;
import com.auctions.system.portlet.category.model.Category;
import com.auctions.system.portlet.nav_menu.dao.NavDAO;
import com.auctions.system.portlet.nav_menu.model.MessageAndDate;
import com.auctions.system.portlet.nav_menu.model.UserData;

@Repository("navDAO")
public class NavDAOImpl implements NavDAO{

	private JdbcTemplate dao;
	
	@Autowired
	private DataSource dataSource;
	
	@PostConstruct
	public void init() {
	 dao = new JdbcTemplate(dataSource);
	}
	
	@Override
	public List<UserData> getSenderIdsToNotify(long receiverId){	
		return dao.query("SELECT DISTINCT senderid FROM sys.chat_messages WHERE receiverid=? AND is_read=?", 
				new Object[]{receiverId,false},new RowMapper<UserData>(){
			@Override
			public UserData mapRow(ResultSet res, int row) throws SQLException {
				return new UserData(res.getLong("senderid"));
			}
		});
	}
	
	@Override
	public List<MessageAndDate> getUnreadMessagesFromUser(long senderId, long receiverId){	
		return dao.query("SELECT message,create_date FROM sys.chat_messages WHERE senderid=? AND receiverid=? AND is_read=?", 
			new Object[]{senderId,receiverId,false},new RowMapper<MessageAndDate>(){
			@Override
			public MessageAndDate mapRow(ResultSet res, int row) throws SQLException {
				return new MessageAndDate(res.getString("message"),DateFormatter.format(res.getTimestamp("create_date")));
			}
		});
	}
	
	@Override
	public boolean markMessagesAsRead(long senderId, long receiverId){	
		return dao.update("UPDATE sys.chat_messages SET is_read=? WHERE senderid=? AND receiverid=? AND is_read=?", 
				new Object[]{true,senderId,receiverId,false}) > 0 ? true : false;
	}
	
	@Override
	public List<Category> getCategories() {
		return dao.query("SELECT id,name FROM sys.category WHERE parent_category_id IS NULL", new RowMapper<Category>() {
			@Override
			public Category mapRow(ResultSet res, int row) throws SQLException {
				return new Category(res.getInt("id"), res.getString("name"));
			}
		});
	}
	

}

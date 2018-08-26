package com.auctions.system.portlet.messages.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.auctions.system.module.auction_processing.DateFormatter;
import com.auctions.system.portlet.message_category.model.MessageCategory;
import com.auctions.system.portlet.messages.dao.MessagesDAO;
import com.auctions.system.portlet.messages.model.Message;

@Repository("messagesDAO")
public class MessagesDAOImpl implements MessagesDAO{

	private JdbcTemplate dao;
	
	@Autowired
	@Qualifier("dataSource")
	private DataSource dataSource;
	
	@PostConstruct
	public void init() {
		dao = new JdbcTemplate(dataSource);
	}
	
	@Override
	public List<Message> getMessages(){
		return dao.query("SELECT id,message_category_id,title,text,create_date,edit_date,user_id,is_sent FROM sys.message", 
				new RowMapper<Message>(){
					@Override
					public Message mapRow(ResultSet res, int row) throws SQLException {
						return new Message(res.getInt("id"),res.getInt("message_category_id"),res.getString("title"),res.getString("text"),
								DateFormatter.format(res.getTimestamp("create_date")),DateFormatter.format(res.getTimestamp("create_date")),
								res.getLong("user_id"),res.getBoolean("is_sent"));
				}
			});
	}
	
	@Override
	public Message getMessage(int id){
		return dao.queryForObject("SELECT id,message_category_id,title,text,create_date,edit_date,user_id,is_sent FROM sys.message WHERE id=?", 
				new Object[]{id}, new RowMapper<Message>(){
					@Override
					public Message mapRow(ResultSet res, int row) throws SQLException {
						return new Message(res.getInt("id"),res.getInt("message_category_id"),res.getString("title"),res.getString("text"),
								DateFormatter.format(res.getTimestamp("create_date")),DateFormatter.format(res.getTimestamp("create_date")),
								res.getLong("user_id"),res.getBoolean("is_sent"));
				}
			});
	}
	
	@Override
	public List<MessageCategory> getMessageCategories(){
		return dao.query("SELECT id,user_id,name,create_date,activated FROM sys.message_category", 
				new RowMapper<MessageCategory>(){
					@Override
					public MessageCategory mapRow(ResultSet res, int row) throws SQLException {
						return new MessageCategory(res.getInt("id"),res.getString("name"),DateFormatter.format(res.getTimestamp("create_date")),
								res.getLong("user_id"),res.getBoolean("activated"));
				}
		});
	}
	
	@Override
	public boolean insert(Message message, long userId){
		Timestamp current =  new Timestamp(System.currentTimeMillis());
		return dao.update("INSERT INTO sys.message(message_category_id,title,text,create_date,edit_date,user_id,is_sent) VALUES(?,?,?,?,?,?,?)", 
				new Object[]{message.getMessageCategoryId(), message.getTitle(), message.getText(), current, current, userId, true}) > 0;
	}
	
	@Override
	public boolean edit(Message message, long userId){
		Timestamp current =  new Timestamp(System.currentTimeMillis());
		return dao.update("UPDATE sys.message SET message_category_id=?,title=?,text=?,edit_date=?,user_id=? WHERE id=?", 
				new Object[]{message.getMessageCategoryId(), message.getTitle(), message.getText(), current, userId, message.getId()}) > 0;
	}
}

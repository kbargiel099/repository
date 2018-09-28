package com.auctions.system.portlet.message_category.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.auctions.system.module.DateFormatter;
import com.auctions.system.portlet.message_category.dao.MessageCategoryDAO;
import com.auctions.system.portlet.message_category.model.MessageCategory;

@Repository("messageCategoryDAO")
public class MessageCategoryDAOImpl implements MessageCategoryDAO{

	private JdbcTemplate dao;
	
	@Autowired
	private DataSource dataSource;
	
	@PostConstruct
	public void init() {
		dao = new JdbcTemplate(dataSource);
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
	public MessageCategory getMessageCategory(int id){
		return dao.queryForObject("SELECT id,user_id,name,create_date,activated FROM sys.message_category WHERE id=?", new Object[]{id},
				new RowMapper<MessageCategory>(){
					@Override
					public MessageCategory mapRow(ResultSet res, int row) throws SQLException {
						return new MessageCategory(res.getInt("id"),res.getString("name"),DateFormatter.format(res.getTimestamp("create_date")),
								res.getLong("user_id"),res.getBoolean("activated"));
				}
		});
	}
	
	@Override
	public boolean insert(String name, long userId){
		Timestamp current =  new Timestamp(System.currentTimeMillis());
		return dao.update("INSERT INTO sys.message_category(name,create_date,edit_date,activated,user_id) VALUES(?,?,?,?,?)",
				new Object[]{name, current, current, true, userId}) > 0;
	}
	
	@Override
	public boolean edit(String name, int id){
		return dao.update("UPDATE sys.message_category SET name=?,edit_date=? WHERE id=?",
				new Object[]{name, new Timestamp(System.currentTimeMillis()), id}) > 0;
	}
	
	@Override
	public boolean changeStatus(int id, boolean activated){
		return dao.update("UPDATE sys.message_category SET activated=? WHERE id=?",
				new Object[]{activated, id}) > 0;
	}
	
	@Override
	public boolean delete(int id){
		return dao.update("DELETE FROM sys.message_category WHERE id=?",
				new Object[]{id}) > 0;
	}

}

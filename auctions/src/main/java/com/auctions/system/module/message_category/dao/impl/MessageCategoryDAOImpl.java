package com.auctions.system.module.message_category.dao.impl;

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
import com.auctions.system.module.message_category.dao.MessageCategoryDAO;
import com.auctions.system.module.message_category.model.MessageCategory;

@Repository("messageCategoryDAO")
public class MessageCategoryDAOImpl implements MessageCategoryDAO{

	private JdbcTemplate dao;
	
	@Autowired
	@Qualifier("dataSource")
	private DataSource dataSource;
	
	@PostConstruct
	public void init() {
		dao = new JdbcTemplate(dataSource);
	}
	
	@Override
	public List<MessageCategory> getMessageCategories(){
		return dao.query("SELECT id,user_id,name,create_date,activated FROM message_category", 
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
		return dao.update("INSERT INTO message_category(name,create_date,activated,user_id) VALUES(?,?,?,?)",
				new Object[]{name, new Timestamp(System.currentTimeMillis()), true, userId}) > 0 ? true : false;
	}

}

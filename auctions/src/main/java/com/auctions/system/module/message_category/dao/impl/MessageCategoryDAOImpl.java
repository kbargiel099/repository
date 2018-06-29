package com.auctions.system.module.message_category.dao.impl;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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
	public boolean insert(MessageCategory c){
		return dao.update("INSERT INTO message_category(name,communication_channel) VALUES(?,?)",
				new Object[]{c.getName(),c.getCommunicationChannel()}) > 0 ? true : false;
	}

}
